/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.vinturo.core.storage.dao.GroupDAO;
import org.vinturo.core.storage.entity.user.Group;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class GroupDAOImpl extends AbstractDAO<Group> implements GroupDAO {

    @Inject
    public GroupDAOImpl(Provider<EntityManager> entityManager) {
        super(entityManager);
    }

    @Override
    public Group findByName(String groupName) {
        TypedQuery<Group> query = getEntityManager().get().createNamedQuery(Group.QUERY_FIND_BY_NAME, Group.class);
        query.setParameter("name", groupName);
        return query.getSingleResult();
    }
}
