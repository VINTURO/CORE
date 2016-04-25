/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.vinturo.core.exceptions.GroupAlreadyRegistredException;
import org.vinturo.core.exceptions.GroupNotFoundException;
import org.vinturo.core.storage.dao.GroupDAO;
import org.vinturo.core.storage.entity.user.Group;
import org.vinturo.core.storage.service.GroupService;

public class GroupServiceImpl implements GroupService {

    private final GroupDAO dao;

    @Inject
    public GroupServiceImpl(final GroupDAO dao) {
        this.dao = dao;
    }

    @Override
    public Group findById(final Long id) throws GroupNotFoundException {
        return null;
    }

    @Override
    public Group findByName(final String groupName) throws GroupNotFoundException {
        Group found = dao.findByName(groupName);
        if (found == null) {
            throw new GroupNotFoundException();
        }
        return found;
    }

    @Override
    @Transactional
    public Group create(Group group) throws GroupAlreadyRegistredException {

        // Check username is unique
        if (this.isGroupNameAlreadyRegistred(group.getName())) {
            throw new GroupAlreadyRegistredException();
        }

        //   usr.addRole(Role.ROLE_USER);

        dao.persist(group);

        return group;
    }

    @Override
    public boolean isGroupNameAlreadyRegistred(String name) {

        return true;

    }

}