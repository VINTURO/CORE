/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.dao;

import org.vinturo.core.storage.entity.user.Group;

public interface GroupDAO extends DAO<Group> {

    /**
     * Find a user by username
     *
     * @param groupName (case sensitive)
     * @return Group found
     */
    Group findByName(String groupName);
}
