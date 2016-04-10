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
import org.vinturo.core.storage.dao.impl.UserDAOImpl;
import org.vinturo.core.storage.entity.user.User;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(Routes.USERS)
@Produces(MediaType.APPLICATION_JSON)
public class UserWSImpl implements org.vinturo.core.io.ws.rest.UserWS {

    private UserDAOImpl dao;

    public UserWSImpl(UserDAOImpl dao) {
        this.dao = dao;
    }

    @Override
    public User findPerson(Integer id) {
        return null;
    }

    /*
    @Override
    @GET
    @Timed
    public User findPerson(@PathParam("id") Integer id) {
        return dao.findById(id);
    }
    */
}
