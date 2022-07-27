package com.songyuwong.infrastructure.authorized;

/**
 * <p>
 * 提供服务
 * <p>
 * 暴露获取对应的方法或者接口接收一个认证相关信息
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
public interface AuthorizedProvider {

    <T> void authorize(T message);

}
