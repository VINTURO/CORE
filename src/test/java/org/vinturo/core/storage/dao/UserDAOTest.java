/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.dao;

import com.google.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.utils.DAOTest;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class UserDAOTest extends DAOTest {

    @Inject
    UserDAOImpl userDAO;

    @Before
    public void setUp() {
        super.setUp();
        userDAO.getEntityManager().get().getTransaction().begin();
    }

    @After
    public void tearDown() throws Exception {
        userDAO.getEntityManager().get().getTransaction().rollback();
    }

    @Test
    public void shouldInsertANewUser() {

        String username = "Chuck";
        String hashedPassword = "ae5deb822e0d71992900471a7199d0d95b8e7c9d05c40a8245a281fd2c1d6684";
        boolean approved = true;

        User user = new User(username, hashedPassword);
        user.setApproved(approved);

        userDAO.persistAndFlush(user);    // Guarante we have a user id @see http://stackoverflow.com/a/9649547

        assertNotNull(user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(hashedPassword, user.getPassword());
        assertEquals(approved, user.isApproved());
    }

}
