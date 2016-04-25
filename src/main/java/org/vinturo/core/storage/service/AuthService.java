/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.service;

import com.google.inject.persist.Transactional;
import org.vinturo.core.exceptions.AccountNotValidatedException;
import org.vinturo.core.exceptions.ConsumerNotFoundException;
import org.vinturo.core.exceptions.InvalidCreditentialsException;
import org.vinturo.core.storage.entity.Consumer;
import org.vinturo.core.storage.entity.VinturoAuth;

import javax.validation.Valid;

public interface AuthService {

    /**
     * Authenticate a user
     * @param auth
     * @return
     * @throws InvalidCreditentialsException
     * @throws AccountNotValidatedException
     */
    @Transactional
    Consumer authenticate(
            @Valid VinturoAuth auth) throws InvalidCreditentialsException, AccountNotValidatedException;

    Consumer getConsumerWithAPIKey(String apiKey) throws ConsumerNotFoundException;
}
