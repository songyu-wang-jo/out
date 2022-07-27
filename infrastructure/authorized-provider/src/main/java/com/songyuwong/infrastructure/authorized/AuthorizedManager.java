package com.songyuwong.infrastructure.authorized;

/**
 * <p>
 * 校验拿到的认证信息
 * <p>
 * 拿到内部提供认证的工具类
 * <p>
 * 根据认证信息匹配服务端保存的认证信息
 *
 * @author SongYu
 * @since 2022/7/19
 */
public interface AuthorizedManager {

    AuthorizedMessage verifyCertificationInfo(AuthorizedManager authorizedManager);

}
