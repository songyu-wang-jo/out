package com.songyuwong.comp.enhancer.annotation.process;

import com.google.auto.service.AutoService;
import com.songyuwong.comp.enhancer.annotation.Proxy;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 自动生成代理类工厂的处理器
 * </p>
 *
 * @author SongYu
 * @since 2022/7/15
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.songyuwong.comp.enhancer.annotation.Proxy")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ProxyAnnotationProcessor extends AbstractProcessor {

    private Filer filer;
    private Elements elementUtils;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "编译生成代理类对象工厂");
        for (TypeElement annotation : annotations) {
            String annotationName = annotation.getSimpleName().toString();
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                // 通过注解信息和元素的信息进行判断是否可以进行后面的操作
                if (element.getKind() != ElementKind.CLASS) {
                    // 测试注解只能用于类上
                    messager.printMessage(Diagnostic.Kind.ERROR, "@".concat(annotationName).concat("must be class"));
                }
                // 上面的约束对应的注解只能用于类上，将对应的元素强转成类元素
                TypeElement proxyElement = (TypeElement) element;
                String packageName = elementUtils.getPackageOf(proxyElement).getQualifiedName().toString().concat(".proxy");

                Proxy proxyAnnotation = proxyElement.getAnnotation(Proxy.class);

                TypeSpec proxyFactory = buildProxyFactory(proxyElement, proxyAnnotation);
                JavaFile proxyFactoryJavaFile = JavaFile.builder(packageName, proxyFactory).build();
                try {
                    proxyFactoryJavaFile.writeTo(filer);
                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
            }
        }
        return false;
    }

    private TypeSpec buildProxyFactory(TypeElement proxyElement, Proxy proxyAnno) {

        String className = proxyElement.getQualifiedName().toString();

        String simpleClassName = proxyElement.getSimpleName().toString();

        TypeMirror invocationType = null;
        TypeMirror proxyInterfaceType = null;
        Class<?> invocation = null;
        Class<?> proxyInterface = null;
        try {
            invocation = proxyAnno.invocation();
        } catch (MirroredTypeException mirroredTypeException) {
            invocationType = mirroredTypeException.getTypeMirror();
        }
        try {
            proxyInterface = proxyAnno.proxyInterface();
        } catch (MirroredTypeException mirroredTypeException) {
            proxyInterfaceType = mirroredTypeException.getTypeMirror();
        }
        Object interfaceType = Objects.isNull(proxyInterfaceType) ? proxyInterface : proxyInterfaceType;
        Object proxyInvocation = Objects.isNull(invocation) ? invocationType : invocation;
        MethodSpec getProxy = MethodSpec.methodBuilder("getProxy")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Objects.isNull(proxyInterfaceType) ? ClassName.get(proxyInterface) : ClassName.get(proxyInterfaceType))
                .addStatement("Class<?> proxyClass = null")
                .addStatement("ClassLoader classLoader = null")
                .addStatement("$T invocationHandler = null", proxyInvocation)
                .addCode("try {")
                .addStatement("proxyClass = Class.forName($S)", className)
                .addStatement("classLoader = proxyClass.getClassLoader()")
                .addStatement("invocationHandler = new $T(proxyClass.newInstance())", proxyInvocation)
                .addCode("} catch (InstantiationException e) {")
                .addStatement("throw new RuntimeException(e)")
                .addCode("} catch (IllegalAccessException e) {")
                .addStatement("throw new RuntimeException(e)")
                .addCode("} catch (ClassNotFoundException e) {")
                .addStatement("throw new RuntimeException(e)")
                .addCode("}")
                .addStatement("$T proxy = ($T) $T.newProxyInstance(classLoader,  new Class[]{$T.class}, invocationHandler)", interfaceType, interfaceType, java.lang.reflect.Proxy.class, interfaceType)
                .addStatement("return proxy")
                .build();

        MethodSpec getProxyWithTarget = MethodSpec.methodBuilder("getProxy")
                .addParameter(Object.class, "target")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(Objects.isNull(proxyInterfaceType) ? ClassName.get(proxyInterface) : ClassName.get(proxyInterfaceType))
                .addStatement("Class<?> proxyClass = null")
                .addStatement("ClassLoader classLoader = null")
                .addStatement("$T invocationHandler = null", proxyInvocation)
                .addCode("try {")
                .addStatement("proxyClass = Class.forName($S)", className)
                .addStatement("classLoader = proxyClass.getClassLoader()")
                .addStatement("invocationHandler = new $T(target)", proxyInvocation)
                .addCode("} catch (ClassNotFoundException e) {")
                .addStatement("throw new RuntimeException(e)")
                .addCode("}")
                .addStatement("$T proxy = ($T) $T.newProxyInstance(classLoader,  new Class[]{$T.class}, invocationHandler)", interfaceType, interfaceType, java.lang.reflect.Proxy.class, interfaceType)
                .addStatement("return proxy")
                .build();

        return TypeSpec.classBuilder(simpleClassName.concat("Proxy"))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(getProxy)
                .addMethod(getProxyWithTarget)
                .build();
    }

}
