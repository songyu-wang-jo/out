package com.songyuwong.test.com.songyuwong.test.compEnhancer;

import com.songyuwong.comp.enhancer.annotation.type.ProxiedInvocationHandler;

import java.lang.reflect.Method;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/17
 */
public class ExampleInvocation extends ProxiedInvocationHandler {
    public ExampleInvocation(Object target) {
        super(target);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().equals("saySomething")) {
            System.out.println(target.toString());
            return method.invoke(target, args);
        }
        return method.invoke(target);
    }
}
