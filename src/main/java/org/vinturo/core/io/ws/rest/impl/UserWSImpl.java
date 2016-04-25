/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest.impl;

import io.swagger.annotations.Api;
import org.vinturo.core.io.Routes;
import org.vinturo.core.io.ws.rest.UserWS;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api(value = Routes.USERS)
@Path(Routes.USERS)
public class UserWSImpl implements UserWS {

    private final UserService service;

    @Inject
    public UserWSImpl(UserService service) {
        this.service = service;
    }

    @Override
    public Response approveUser(String username) {
        User approvedUser = service.approveUser(username);
        return Response.status(200).entity(approvedUser).build();
    }
}
