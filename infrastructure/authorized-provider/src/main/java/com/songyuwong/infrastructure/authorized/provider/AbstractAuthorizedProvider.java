package com.songyuwong.infrastructure.authorized.provider;

import com.songyuwong.infrastructure.authorized.AuthorizedManager;
import com.songyuwong.infrastructure.authorized.AuthorizedMessage;
import com.songyuwong.infrastructure.authorized.AuthorizedProvider;
import com.songyuwong.infrastructure.authorized.manager.AbstractAuthorizedManager;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
public abstract class AbstractAuthorizedProvider implements AuthorizedProvider {

    AuthorizedMessage authorizedMessage;

    AuthorizedManager authorizedManager;

    @Override
    public <T> void authorize(T message) {
        authorizedMessage = buildAuthorizedMessage(message);
        authorizedManager = AbstractAuthorizedManager.loadAuthorizedManagerService(authorizedMessage);
        authorizedMessage = authorizedManager.verifyCertificationInfo(authorizedManager);
    }

    abstract protected <T> AuthorizedMessage buildAuthorizedMessage(T message);

}
