/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage;

import com.google.inject.persist.jpa.JpaPersistModule;

import java.util.Properties;

/**
 * This class manage database settings. For some reasonsGuice JpaPersistModule is final (it cannot be extended)
 */
public class DatabaseModule {

    private final JpaPersistModule jpaModule;

    public DatabaseModule() {

        final Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:file:vinturo");
        properties.put("javax.persistence.jdbc.user", "SA");
        properties.put("javax.persistence.jdbc.password", "");
        jpaModule = new JpaPersistModule("VinturoUnit");
        jpaModule.properties(properties);

    }

    /**
     * Returns the created JpaPersistModule
     *
     * @return JpaPersistModule
     */
    public JpaPersistModule getJpaModule() {
        return this.jpaModule;
    }

}