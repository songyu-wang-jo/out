package com.songyuwong.infrastructure.authorized.message;

import com.songyuwong.infrastructure.authorized.AuthorizedMessage;
import com.songyuwong.infrastructure.authorized.manager.AbstractAuthorizedManager;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
public abstract class AbstractAuthorizedMessage implements AuthorizedMessage {

    private Class<? extends AbstractAuthorizedManager> managerClass;

}
