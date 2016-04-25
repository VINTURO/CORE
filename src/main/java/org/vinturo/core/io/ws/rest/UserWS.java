/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.vinturo.core.io.Routes;
import org.vinturo.core.storage.entity.user.User;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserWS {

    @ApiOperation(value = "Approve a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, user is approved", response = User.class),
            @ApiResponse(code = 403, message = "Account was already approved"),
            @ApiResponse(code = 404, message = "User to approve not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @POST
    @Path(Routes.APPROVE + "/{username}")
    Response approveUser(
            @ApiParam(value = "username of the user to approve", required = true)
            @PathParam(value = "username") @NotNull String username
    );
}
