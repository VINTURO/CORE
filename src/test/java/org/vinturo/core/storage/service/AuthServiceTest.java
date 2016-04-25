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
import org.vinturo.core.exceptions.AccountNotValidatedException;
import org.vinturo.core.exceptions.InvalidCreditentialsException;
import org.vinturo.core.storage.entity.*;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.service.impl.AuthServiceImpl;
import org.vinturo.core.util.PasswordUtil;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserService userService;

    private AuthService authService;

    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
        authService = new AuthServiceImpl(userService);
    }

    @Test(expected = InvalidCreditentialsException.class)
    public void shouldFireAnExceptionWhenUsernameIsNotRegistred() {

        String username = "test";
        String password = "testpassword";
        Device device = new Device("Computer of test", ConsumerOS.WINDOWS, ConsumerType.COMPUTER, "SECRET-DEVICE-ID");

        when(userService.findByUsername(username)).thenReturn(null);

        VinturoAuth auth = new VinturoAuth(username, password, device);
        authService.authenticate(auth);

        verify(userService, times(1)).findByUsername(username);
    }

    @Test(expected = InvalidCreditentialsException.class)
    public void shouldFireAnExceptionWhenPasswordIsNotCorrect() {

        String username = "test";
        String password = "wrongPassword";
        Device device = new Device("Computer of test", ConsumerOS.WINDOWS, ConsumerType.COMPUTER, "SECRET-DEVICE-ID");

        User user = new User(username, PasswordUtil.encodePassword("correctPassword"));
        user.setApproved(true);

        when(userService.findByUsername(username)).thenReturn(user);

        VinturoAuth auth = new VinturoAuth(username, password, device);
        authService.authenticate(auth);

        verify(userService, times(1)).findByUsername(username);
    }

    @Test(expected = AccountNotValidatedException.class)
    public void shouldFireAnExceptionWhenAccountWithUsernameIsNotValidated() {
        String username = "test";
        String password = "testpassword";
        Device device = new Device("Computer of test", ConsumerOS.WINDOWS, ConsumerType.COMPUTER, "SECRET-DEVICE-ID");

        User user = new User(username, PasswordUtil.encodePassword(password));
        user.setApproved(false);
        when(userService.findByUsername(username)).thenReturn(user);

        VinturoAuth auth = new VinturoAuth(username, password, device);
        authService.authenticate(auth);

        verify(userService, times(1)).findByUsername(username);
    }

    @Test
    public void shouldReturnExistingUserConsumerWhenDeviceUIDIsKnown() {

        String username = "test";
        String password = "testpassword";
        Device device = new Device("Computer of test", ConsumerOS.WINDOWS, ConsumerType.COMPUTER, "SECRET-DEVICE-ID");

        User user = new User(username, PasswordUtil.encodePassword(password));
        user.setApproved(true);

        Consumer cons2 = new Consumer("iPad of test", ConsumerType.TABLET, ConsumerOS.IOS, "a device uid");

        Consumer cons = new Consumer(device.getName(), device.getType(), device.getOS(), device.getUID());

        user.addConsumer(cons);
        user.addConsumer(cons2);

        when(userService.findByUsername(username)).thenReturn(user);

        VinturoAuth auth = new VinturoAuth(username, password, device);
        Consumer consumer = authService.authenticate(auth);
        assertNotNull(consumer);
        assertEquals(cons, consumer);

        verify(userService, times(1)).findByUsername(username);
        verify(userService, times(0)).save(any(User.class));
    }

    @Test
    public void shouldRegisterANewConsumerWhenDeviceUIDIsUnknown() {

        String username = "test";
        String password = "testpassword";
        Device device = new Device("Computer of test", ConsumerOS.WINDOWS, ConsumerType.COMPUTER, "SECRET-DEVICE-ID");

        User user = new User(username, PasswordUtil.encodePassword(password));
        user.setApproved(true);

        Consumer cons2 = new Consumer("iPad of test", ConsumerType.TABLET, ConsumerOS.IOS, "a device uid");
        Consumer cons3 = new Consumer("iPhone of test", ConsumerType.TABLET, ConsumerOS.IOS, "a device uid");
        user.addConsumer(cons2);
        user.addConsumer(cons3);

        when(userService.findByUsername(username)).thenReturn(user);

        VinturoAuth auth = new VinturoAuth(username, password, device);
        Consumer consumer = authService.authenticate(auth);
        assertNotNull(consumer);
        assertNotNull(consumer.getAPIKey());
        assertEquals(device.getUID(), consumer.getUID());
        assertEquals(device.getOS(), consumer.getOS());
        assertEquals(device.getType(), consumer.getType());
        assertEquals(device.getName(), consumer.getName());

        verify(userService, times(1)).findByUsername(username);
        verify(userService, times(1)).save(any(User.class));
    }

}
