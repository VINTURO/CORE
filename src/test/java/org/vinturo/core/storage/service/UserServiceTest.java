/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vinturo.core.exceptions.UsernameIsAlreadyRegistredException;
import org.vinturo.core.storage.dao.UserDAO;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.entity.user.UserRegistration;
import org.vinturo.core.storage.service.impl.UserServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private GroupService groupService;

    private UserService userService;

    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDAO, groupService);

    }

    @Test
    public void userShouldBeCreated() {

        String username = "test";
        String password = "testpassword";

        when(userDAO.findByUsername(username)).thenReturn(null);

        UserRegistration registration = new UserRegistration();
        registration.setUsername(username);
        registration.setPlainPassword(password);

        User createdUser = userService.createUser(registration);
        assertNotNull(createdUser);

        verify(userDAO, times(1)).persist(any(User.class));

    }

    @Test(expected = UsernameIsAlreadyRegistredException.class)
    public void userShouldNotBeCreatedWhenUsernameIsAlreadyRegistred() {
        String username = "test";
        String password = "testpassword";

        User user = new User();

        when(userDAO.findByUsername(username)).thenReturn(user);

        UserRegistration registration = new UserRegistration();
        registration.setUsername(username);
        registration.setPlainPassword(password);

        User createdUser = userService.createUser(registration);

        // An exception should occur because this username is already registred
    }

}
