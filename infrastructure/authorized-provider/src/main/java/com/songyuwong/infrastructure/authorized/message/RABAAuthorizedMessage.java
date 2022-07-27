package com.songyuwong.infrastructure.authorized.message;

import com.songyuwong.infrastructure.authorized.AuthorizedManager;
import com.songyuwong.infrastructure.authorized.AuthorizedMessage;
import com.songyuwong.infrastructure.authorized.manager.rbab.RBABAuthorizedManager;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
public class RABAAuthorizedMessage implements AuthorizedMessage {

    @Override
    public boolean authorizedManagerType(AuthorizedManager authorizedManager) {
        return authorizedManager instanceof RBABAuthorizedManager;
    }

}
