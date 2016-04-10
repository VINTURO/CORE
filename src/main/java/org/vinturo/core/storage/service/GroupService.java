/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.service;

import org.vinturo.core.exceptions.GroupNotFoundException;
import org.vinturo.core.storage.entity.user.Group;

import javax.validation.constraints.NotNull;

public interface GroupService {

    /**
     * Search a group with this specified name
     *
     * @param groupName
     * @return
     */
    Group findByName(@NotNull String groupName) throws GroupNotFoundException;

    /*
    @Transactional
    Group createGroup(Group group) throws GroupAlreadyRegistredException;

    Group findGroupByName(String name) throws GroupNotFoundException;

    boolean isGroupNameAlreadyRegistred(String name);
    */
}
