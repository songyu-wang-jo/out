package com.songyuwong.comp.enhancer.annotation.process;

import com.google.auto.service.AutoService;
import com.songyuwong.comp.enhancer.annotation.Test;
import com.squareup.javapoet.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 测试注解编译时处理器
 * </p>
 *
 * @author SongYu
 * @since 2022/7/14
 */
@AutoService(Processor.class)
public class TestAnnoProcess extends AbstractProcessor {

    private Messager messager;

    private Elements elementUtils;

    private Filer envFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        envFiler = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportedAnnotationTypes = new HashSet<>();
        supportedAnnotationTypes.add("com.songyuwong.comp.enhancer.annotation.Test");
        return supportedAnnotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        if (SourceVersion.latest().compareTo(SourceVersion.RELEASE_8) > 0) {
            return SourceVersion.latest();
        }
        return SourceVersion.RELEASE_8;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 输出编译消息
        messager.printMessage(Diagnostic.Kind.NOTE, "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest source version ".concat(getSupportedSourceVersion().toString()));
        // 遍历对应支持的注解类型的所有的注解
        for (TypeElement annotation : annotations) {
            // 获取注解的名称
            String annotationName = annotation.getSimpleName().toString();
            // 通过注解编译的环境获取被对应的注解注解了的 元素
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(annotation);
            // 遍历所有被注解的元素
            for (Element element : elementsAnnotatedWith) {
                // 通过注解信息和元素的信息进行判断是否可以进行后面的操作
                if (element.getKind() != ElementKind.CLASS) {
                    // 测试注解只能用于类上
                    messager.printMessage(Diagnostic.Kind.ERROR, "@".concat(annotationName).concat("must be class"));
                }
                // 上面的约束对应的注解只能用于类上，将对应的元素强转成类元素
                TypeElement classElement = (TypeElement) element;
                // 获取对应类的相关信息
                Name className = classElement.getSimpleName();
                PackageElement packageElement = elementUtils.getPackageOf(classElement);
                Name packageElementQualifiedName = packageElement.getQualifiedName();
                // 获取注解中的数据
                Test elementAnnotation = element.getAnnotation(Test.class);
                // 借助 java poet 模板引擎生成对应的 类文件
                TypeSpec typeSpec = generateClassFile(className, packageElementQualifiedName,elementAnnotation.what());
                JavaFile javaFile = JavaFile.builder(packageElementQualifiedName.toString(), typeSpec).build();
                try {
                    javaFile.writeTo(envFiler);
                } catch (IOException e) {
                    messager.printMessage(Diagnostic.Kind.ERROR, "生成 java 文件失败 给类".concat(e.getMessage()), element);
                }
            }
        }
        return roundEnv.processingOver();
    }

    private TypeSpec generateClassFile(Name className, Name packageElementQualifiedName,String annoWhat) {
        // 定义属性
        FieldSpec fieldSpec = FieldSpec.builder(String.class, "className", Modifier.PRIVATE).build();
        // 定义构造函数
        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("this.$N = $S", fieldSpec, className.toString())
                .build();
        // 定义方法
        MethodSpec intro = MethodSpec.methodBuilder("intro")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("System.out.println($S + className)", "the class name is ")
                .build();
        // 创建类
        ClassName generateIntro = ClassName.get(packageElementQualifiedName.toString(), className.toString().concat(annoWhat));
        return TypeSpec.classBuilder(generateIntro)
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldSpec)
                .addMethod(constructor)
                .addMethod(intro)
                .build();
    }

}
