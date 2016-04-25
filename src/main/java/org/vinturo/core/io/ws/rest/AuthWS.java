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
import org.vinturo.core.storage.entity.Consumer;
import org.vinturo.core.storage.entity.VinturoAuth;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthWS {

    /**
     * Authenticate a consumer
     */
    @ApiOperation(value = "Authenticate a consumer", notes = "A consumer is a device that will act as the user. (Exemple a smartphone, computer etc.)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, user device is authenticated", response = Consumer.class),
            @ApiResponse(code = 401, message = "Wrong username or password"),
            @ApiResponse(code = 403, message = "User is not approved"),
            @ApiResponse(code = 422, message = "Wrong input parameters"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @POST
    Response authenticate(
            @ApiParam(value = "Authentification", required = true)
            @Valid VinturoAuth auth);

    /**
     * Disconnect a consummer
     */
    @ApiOperation(value = "Disconnect a consumer", notes = "Not implemented yet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, user is disconnected")
    })
    @DELETE
    Response logout();
}
