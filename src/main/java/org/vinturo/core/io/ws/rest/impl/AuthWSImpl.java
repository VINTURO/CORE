/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest.impl;

import org.vinturo.core.io.Routes;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.entity.user.UserRegistration;
import org.vinturo.core.storage.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Routes.AUTH)
@Produces(MediaType.APPLICATION_JSON)
public class AuthWSImpl implements org.vinturo.core.io.ws.rest.AuthWS {

    private final UserService userService;

    @Inject
    public AuthWSImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response register(UserRegistration registration) {
        User createdUser = userService.createUser(registration);
        return Response.status(201).entity(createdUser).build();
    }
}
