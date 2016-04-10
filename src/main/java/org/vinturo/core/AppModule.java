/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import io.dropwizard.setup.Environment;

public class AppModule extends AbstractModule {

    final VinturoConfiguration configuration;
    final Environment environment;

    public AppModule(final VinturoConfiguration configuration, final Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
    }

    @Override
    protected void configure() {
        bind(VinturoConfiguration.class).toInstance(configuration);
        bind(Environment.class).toInstance(environment);
        bind(EventBus.class).asEagerSingleton();
    }
}