/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.dao;

import com.google.inject.Provider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public interface DAO<T> {

    <T> void persist(T object);

    <T> void update(T object);

    <T> void persistAndFlush(final T object);

    <T, ID> T findById(Class<T> clazz, ID id);

    <T> T merge(T object);

    <T> void remove(T object);

    <T, ID> void removeById(Class<T> clazz, ID id);

    <T> List<T> findAll(Class clazz);

    <T> List<T> find(Class<T> clazz, String namedQuery, Map<String, Object> paramsMap);

    Provider<EntityManager> getEntityManager();
}