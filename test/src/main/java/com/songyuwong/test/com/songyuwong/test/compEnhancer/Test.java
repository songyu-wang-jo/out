package com.songyuwong.test.com.songyuwong.test.compEnhancer;

import com.songyuwong.comp.enhancer.annotation.Proxy;
import com.songyuwong.test.com.songyuwong.test.compEnhancer.proxy.TestProxy;


/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/15
 */
@Proxy(proxyInterface = TestInterface.class, invocation = TestInvocation.class)
public class Test implements TestInterface {

    @Override
    public void test() {
        System.out.println("self");
    }

    public static void main(String[] args) {
        Test test = new Test();
        TestInterface proxy = TestProxy.getProxy(test);
        proxy.test();
    }

}
