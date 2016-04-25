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
import org.vinturo.core.exceptions.GroupAlreadyRegistredException;
import org.vinturo.core.exceptions.GroupNotFoundException;
import org.vinturo.core.storage.entity.user.Group;

import javax.validation.constraints.NotNull;

public interface GroupService {

    /**
     * Search a group with this specified id
     *
     * @param id
     * @return
     * @throws GroupNotFoundException
     */
    Group findById(@NotNull Long id) throws GroupNotFoundException;

    /**
     * Search a group with this specified name
     *
     * @param groupName
     * @return
     */
    Group findByName(@NotNull String groupName) throws GroupNotFoundException;

    /**
     * Create a new group
     *
     * @param group
     * @return
     * @throws GroupAlreadyRegistredException
     */
    @Transactional
    Group create(Group group) throws GroupAlreadyRegistredException;

    /**
     * Return true if a group with the specified name exists
     *
     * @param name
     * @return
     */
    boolean isGroupNameAlreadyRegistred(String name);

}
