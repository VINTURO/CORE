/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.notifications;

import com.google.inject.AbstractModule;

public class NotifierModule extends AbstractModule {

    public NotifierModule() {

    }

    @Override
    protected void configure() {
        bind(NotifierService.class).asEagerSingleton();
    }
}