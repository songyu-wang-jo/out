package com.songyuwong.comp.enhancer.annotation.type;

import java.lang.reflect.InvocationHandler;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/15
 */
public abstract class ProxiedInvocationHandler implements InvocationHandler {

    protected Object target;

    public ProxiedInvocationHandler(Object target) {
        this.target = target;
    }

}
