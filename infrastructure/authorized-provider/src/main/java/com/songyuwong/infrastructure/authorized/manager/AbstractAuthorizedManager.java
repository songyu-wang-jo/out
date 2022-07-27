package com.songyuwong.infrastructure.authorized.manager;

import com.songyuwong.infrastructure.authorized.AuthorizedManager;
import com.songyuwong.infrastructure.authorized.AuthorizedMessage;
import com.songyuwong.infrastructure.authorized.exception.InitialAuthorizedManagerException;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
public abstract class AbstractAuthorizedManager implements AuthorizedManager {

    private static final ServiceLoader<AuthorizedManager> authorizedManagerServiceLoader = ServiceLoader.load(AuthorizedManager.class);

    public static AuthorizedManager loadAuthorizedManagerService(AuthorizedMessage message) {
        AuthorizedManager currentAuthorizedManager = null;
        for (AuthorizedManager authorizedManager : authorizedManagerServiceLoader) {
            if (message.authorizedManagerType(authorizedManager)) {
                currentAuthorizedManager = authorizedManager;
            }
        }
        if (Objects.isNull(currentAuthorizedManager)) {
            throw new InitialAuthorizedManagerException("未找到对应的授权服务类型 << ".concat(message.toString()));
        }
        return currentAuthorizedManager;
    }
}
