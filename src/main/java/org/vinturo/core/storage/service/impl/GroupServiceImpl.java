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
import org.vinturo.core.exceptions.GroupNotFoundException;
import org.vinturo.core.storage.dao.GroupDAO;
import org.vinturo.core.storage.entity.user.Group;
import org.vinturo.core.storage.service.GroupService;

public class GroupServiceImpl implements GroupService {

    private final GroupDAO dao;

    @Inject
    public GroupServiceImpl(GroupDAO dao) {
        this.dao = dao;
    }

    @Override
    public Group findByName(final String groupName) throws GroupNotFoundException {
        Group found = dao.findByName(groupName);
        if (found == null) {
            throw new GroupNotFoundException();
        }
        return found;
    }

//    @Override
//    @Transactional
//    public Group createGroup(Group group) throws GroupAlreadyRegistredException {
//
//        // Check username is unique
//        if (this.isGroupNameAlreadyRegistred(group.getName())) {
//            throw new GroupAlreadyRegistredException();
//        }
//
//        //   usr.addRole(Role.ROLE_USER);
//
//        EntityManager em = dao.getEntityManager().get();
//
//        em.persist(group);
//        em.flush();
//
//        return group;
//    }
//
//    @Override
//    public Group findGroupByName(String name) throws GroupNotFoundException {
//
//        try {
//            EntityManager em = dao.getEntityManager().get();
//            TypedQuery<Group> query = em.createNamedQuery("Group.findByName", Group.class).setParameter("name", name);
//            Group found = query.setMaxResults(1).getSingleResult();
//            return found;
//        } catch (NoResultException ex) {
//            throw new GroupNotFoundException();
//        }
//
//    }
//
//    @Override
//    public boolean isGroupNameAlreadyRegistred(String name) {
//
//        EntityManager em = dao.getEntityManager().get();
//        TypedQuery<Group> query = em.createNamedQuery("Group.findByName", Group.class).setParameter("name", name);
//        List<Group> found = query.setMaxResults(1).getResultList();
//        if (found.size() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }

}