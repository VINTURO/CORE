/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vinturo.core.exceptions.AccountNotValidatedException;
import org.vinturo.core.exceptions.ConsumerNotFoundException;
import org.vinturo.core.exceptions.InvalidCreditentialsException;
import org.vinturo.core.storage.entity.Consumer;
import org.vinturo.core.storage.entity.VinturoAuth;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.service.AuthService;
import org.vinturo.core.storage.service.UserService;
import org.vinturo.core.util.APIKeyUtil;
import org.vinturo.core.util.PasswordUtil;

import javax.inject.Inject;

public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserService userService;

    @Inject
    public AuthServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Consumer authenticate(VinturoAuth auth) throws InvalidCreditentialsException, AccountNotValidatedException {
        User found = userService.findByUsername(auth.getUsername());

        if (found == null || !PasswordUtil.checkPassword(auth.getPassword(), found.getPassword())) {
            throw new InvalidCreditentialsException();
        } else if (!found.isApproved()) {
            throw new AccountNotValidatedException();
        }

        // If consumer exists
        for (Consumer consumer : found.getConsumers()) {
            if (consumer.getUID().equals(auth.getDevice().getUID())) {
                return consumer;
            }
        }

        // Else create it
        Consumer newConsumer = new Consumer(auth.getDevice().getName(), auth.getDevice().getType(), auth.getDevice().getOS(), auth.getDevice().getUID());
        newConsumer.setApiKey(APIKeyUtil.generate());
        found.addConsumer(newConsumer);
        userService.update(found);
        return newConsumer;

    }

    @Override
    public Consumer getConsumerWithAPIKey(String apiKey) throws ConsumerNotFoundException {
        return null;
    }
}