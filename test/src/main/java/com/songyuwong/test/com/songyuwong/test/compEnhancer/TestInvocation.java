package com.songyuwong.test.com.songyuwong.test.compEnhancer;

import com.songyuwong.comp.enhancer.annotation.type.ProxiedInvocationHandler;

import java.lang.reflect.Method;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/15
 */
public class TestInvocation extends ProxiedInvocationHandler {

    public TestInvocation(Object target) {
        super(target);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("test")) {
            System.out.println("success");
            return method.invoke(super.target, args);
        }
        return method.invoke(proxy, args);
    }


}
