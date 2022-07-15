package com.songyuwong.comp.enhancer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     预习测试注解
 * </p>
 *
 * @author SongYu
 * @since 2022/7/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    String what() default "GenerateIntro";

}
