/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.vinturo.core.storage.entity.user.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    @Inject
    public UserDAOImpl(Provider<EntityManager> entityManager) {
        super(entityManager);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = getEntityManager().get().createNamedQuery(User.QUERY_FIND_BY_USERNAME, User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}
