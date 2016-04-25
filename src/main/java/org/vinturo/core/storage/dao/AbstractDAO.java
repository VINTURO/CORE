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
import com.google.inject.persist.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Transactional
public class AbstractDAO<T> implements DAO<T> {

    private static final String QUERY_SELECT_ALL = "SELECT o FROM %s o ORDER BY o.id";

    private final Provider<EntityManager> entityManager;

    @Inject
    public AbstractDAO(final Provider<EntityManager> entityManager) {
        this.entityManager = entityManager;
    }

    public Provider<EntityManager> getEntityManager() {
        return this.entityManager;
    }

    @Override
    public <T> void persist(final T object) {
        entityManager.get().persist(object);
    }

    @Override
    public <T> void update(final T object) {
        entityManager.get().flush();
    }

    @Override
    public <T> void persistAndFlush(final T object) {
        entityManager.get().persist(object);
        entityManager.get().flush();
    }

    @Override
    public <T, ID> T findById(final Class<T> clazz, final ID id) {
        return entityManager.get().find(clazz, id);
    }

    @Override
    public <T> T merge(final T object) {
        return entityManager.get().merge(object);
    }

    @Override
    public <T> void remove(final T object) {
        entityManager.get().remove(object);
    }

    @Override
    public <T, ID> void removeById(final Class<T> clazz, final ID id) {
        remove(findById(clazz, id));
    }

    @Override
    public <T> List<T> findAll(final Class clazz) {
        final String query = String.format(QUERY_SELECT_ALL, clazz.getSimpleName());
        return entityManager.get().createQuery(query).getResultList();
    }

    @Override
    public <T> List<T> find(final Class<T> clazz, final String namedQuery, final Map<String, Object> paramsMap) {
        final Query query = fillNamedParametersQuery(clazz, namedQuery, paramsMap);
        return query.getResultList();
    }

    private Query fillNamedParametersQuery(final Class clazz, final String namedQuery,
                                           final Map<String, Object> paramsMap) {
        final Query query = entityManager.get().createNamedQuery(namedQuery, clazz);
        paramsMap.entrySet().forEach((param) -> query.setParameter(param.getKey(), param.getValue()));
        return query;
    }
}
