package com.songyuwong.test.com.songyuwong.test.compEnhancer;

import com.songyuwong.comp.enhancer.annotation.Proxy;
import com.songyuwong.test.com.songyuwong.test.compEnhancer.proxy.ExampleProxy;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/17
 */
@Proxy(invocation = ExampleInvocation.class, proxyInterface = ExampleInterface.class)
public class Example implements ExampleInterface {
    @Override
    public void saySomething() {
        System.out.println("example");
    }

    public static void main(String[] args) {
        ExampleInterface proxy = ExampleProxy.getProxy();
        proxy.saySomething();
    }

    @Override
    public String toString() {
        return "this is comp Example";
    }
}
