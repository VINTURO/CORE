/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest;

import com.codahale.metrics.annotation.Timed;
import org.vinturo.core.storage.entity.user.User;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public interface UserWS {
    @GET
    @Timed
    User findPerson(@PathParam("id") Integer id);
}
