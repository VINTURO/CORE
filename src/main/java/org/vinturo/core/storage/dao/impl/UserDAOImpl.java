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
import org.vinturo.core.storage.dao.UserDAO;
import org.vinturo.core.storage.entity.user.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    @Inject
    public UserDAOImpl(Provider<EntityManager> entityManager) {
        super(entityManager);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = getEntityManager().get().createNamedQuery(User.QUERY_FIND_BY_USERNAME, User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
