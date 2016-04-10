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
import org.vinturo.core.exceptions.UsernameIsAlreadyRegistredException;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.entity.user.UserRegistration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

    /**
     * Find a user by username
     *
     * @param username (case sensitive)
     * @return User found
     */
    User findByUsername(@NotNull String username);

    /**
     * Create a new user
     *
     * @param registration
     * @return Created user
     * @throws UsernameIsAlreadyRegistredException
     */
    @Transactional
    User createUser(@Valid @NotNull UserRegistration registration) throws UsernameIsAlreadyRegistredException;

    /**
     * Returns true when a user with the specified username is already registred
     *
     * @param username to look for
     * @return
     */
    boolean isUsernameAlreadyRegistred(@NotNull String username);

/*
    User createUser(
            ApprovedUserRegistration registration) throws UsernameIsAlreadyRegistredException, GroupNotFoundException;

    @Transactional
    User approveUser(@NotNull String username) throws UserNotFoundException, UserIsAlreadyApprovedException;

    User findUserByUsername(String username) throws UserNotFoundException;



    @Transactional
    Consumer authenticate(
            @Valid VinturoAuth auth) throws InvalidCreditentialsException, AccountNotValidatedException;

    Consumer getConsumerWithAPIKey(String apiKey) throws ConsumerNotFoundException;
*/
}
