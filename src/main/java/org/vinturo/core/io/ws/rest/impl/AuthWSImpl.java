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
import org.vinturo.core.io.ws.rest.AuthWS;
import org.vinturo.core.storage.entity.Consumer;
import org.vinturo.core.storage.entity.VinturoAuth;
import org.vinturo.core.storage.service.AuthService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Api(value = Routes.AUTH)
@Path(Routes.AUTH)
public class AuthWSImpl implements AuthWS {

    private final AuthService authService;

    @Inject
    public AuthWSImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Response authenticate(VinturoAuth auth) {

        Consumer consumer = authService.authenticate(auth);
        return Response.status(200).entity(consumer).build();
    }

    @Override
    public Response logout() {
        return null;
    }
}
