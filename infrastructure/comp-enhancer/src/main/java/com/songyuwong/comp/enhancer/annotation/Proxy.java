package com.songyuwong.comp.enhancer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 自动生成代理类工厂类注解
 * </p>
 *
 * @author SongYu
 * @since 2022/7/15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Proxy {

    Class<?> invocation();

    Class<?> proxyInterface();

}
