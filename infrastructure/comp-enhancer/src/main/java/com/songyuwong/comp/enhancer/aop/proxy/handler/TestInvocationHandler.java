package com.songyuwong.comp.enhancer.aop.proxy.handler;

import com.songyuwong.comp.enhancer.annotation.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author SongYu
 * @since 2022/7/14
 */
public class TestInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Objects.nonNull(method.getAnnotation(Test.class))){
            System.out.println(method.getAnnotation(Test.class).what());
            return method.invoke(proxy,args);
        }
        return method.invoke(proxy,args);
    }

}
