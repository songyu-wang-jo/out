package com.songyuwong.comp.enhancer.aop.proxy;

import com.songyuwong.comp.enhancer.aop.proxy.handler.TestInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/14
 */
public class TestProxy{

    public static Object getProxy(Object t) {
        return Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(),new TestInvocationHandler());
    }

}
