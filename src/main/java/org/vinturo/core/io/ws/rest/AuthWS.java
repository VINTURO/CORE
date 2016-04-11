/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest;

import org.vinturo.core.storage.entity.user.UserRegistration;

import javax.ws.rs.core.Response;

public interface AuthWS {

    /*
    @ApiOperation(value = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success, user is registred", response = User.class),
            @ApiResponse(code = 403, message = "An account with the same username already exists."),
            @ApiResponse(code = 422, message = "Wrong input parameters"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @POST
    @Path("/register")
    */
    // Response register(@ApiParam(value = "User to create", required = true) @Valid UserRegistration registration);
    Response register(UserRegistration registration);

}
