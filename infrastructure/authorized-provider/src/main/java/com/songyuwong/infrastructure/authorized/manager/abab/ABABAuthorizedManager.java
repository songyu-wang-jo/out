package com.songyuwong.infrastructure.authorized.manager.abab;

import com.google.auto.service.AutoService;
import com.songyuwong.infrastructure.authorized.AuthorizedManager;
import com.songyuwong.infrastructure.authorized.AuthorizedMessage;
import com.songyuwong.infrastructure.authorized.manager.AbstractAuthorizedManager;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
@AutoService(AuthorizedManager.class)
public class ABABAuthorizedManager extends AbstractAuthorizedManager {

    @Override
    public AuthorizedMessage verifyCertificationInfo(AuthorizedManager authorizedManager) {
        return null;
    }

}
