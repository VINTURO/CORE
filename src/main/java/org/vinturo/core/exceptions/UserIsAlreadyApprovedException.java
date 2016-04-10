/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.Serializable;

public class UserIsAlreadyApprovedException extends WebApplicationException implements Serializable {

    private static final long serialVersionUID = 1169426381288170661L;

    public UserIsAlreadyApprovedException() {
        super("This user is already approved");
    }

    public UserIsAlreadyApprovedException(String msg) {
        super(msg);
    }

    public UserIsAlreadyApprovedException(String msg, Exception e) {
        super(msg, e);
    }

    @Override
    public Response getResponse() {
        return Response.status(403).build();    // Forbidden
    }
}
