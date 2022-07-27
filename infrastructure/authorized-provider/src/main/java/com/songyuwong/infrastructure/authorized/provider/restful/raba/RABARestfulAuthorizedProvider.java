package com.songyuwong.infrastructure.authorized.provider.restful.raba;

import com.songyuwong.infrastructure.authorized.AuthorizedMessage;
import com.songyuwong.infrastructure.authorized.message.RABAAuthorizedMessage;
import com.songyuwong.infrastructure.authorized.provider.restful.RestfulAuthorizedProvider;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/19
 */
public class RABARestfulAuthorizedProvider extends RestfulAuthorizedProvider {

    @Override
    protected <T> AuthorizedMessage buildAuthorizedMessage(T message) {
        return new RABAAuthorizedMessage();
    }

}
