/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.utils;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import org.junit.Before;
import org.vinturo.core.storage.DatabaseModule;

public class DAOTest {

    @Inject
    PersistService persistService;

//    @Inject
//    PopulateDatabase populator;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new DatabaseModule());
        injector.injectMembers(this);

        persistService.start();
        // populator.init();
    }

}
