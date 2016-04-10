package org.vinturo.core.storage.dao;

import org.vinturo.core.storage.entity.user.User;

/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
public interface UserDAO extends DAO<User> {

    /**
     * Find a user by username
     *
     * @param username (case sensitive)
     * @return User found
     */
    User findByUsername(String username);
}
