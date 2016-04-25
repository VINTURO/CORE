/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.vinturo.core.storage.dao.GroupDAO;
import org.vinturo.core.storage.dao.UserDAO;
import org.vinturo.core.storage.dao.GroupDAOImpl;
import org.vinturo.core.storage.dao.UserDAOImpl;
import org.vinturo.core.storage.service.AuthService;
import org.vinturo.core.storage.service.GroupService;
import org.vinturo.core.storage.service.UserService;
import org.vinturo.core.storage.service.impl.AuthServiceImpl;
import org.vinturo.core.storage.service.impl.GroupServiceImpl;
import org.vinturo.core.storage.service.impl.UserServiceImpl;

public class StorageModule extends AbstractModule {

    public StorageModule() {

    }

    @Override
    protected void configure() {

        bind(UserDAO.class).to(UserDAOImpl.class).in(Singleton.class);
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);

        bind(AuthService.class).to(AuthServiceImpl.class).in(Singleton.class);

        bind(GroupDAO.class).to(GroupDAOImpl.class).in(Singleton.class);
        bind(GroupService.class).to(GroupServiceImpl.class).in(Singleton.class);

        /*
        bind(UserServiceImpl.class).in(Singleton.class);
        bind(GroupServiceImpl.class).in(Singleton.class);

        bind(LightService.class).in(Singleton.class);
        bind(BeaconService.class).in(Singleton.class);

        bind(RoomService.class).in(Singleton.class);

        bind(RuleService.class).in(Singleton.class);

        bind(RulesEngine.class).in(Singleton.class);
        //   bind(RulesEngine.class).asEagerSingleton();
        bind(VinturoOAuthAuthenticator.class).in(Singleton.class);

        if(GlobalSettings.IS_HUE_SUPPORT_ENABLED)
            bind(HueService.class).in(Singleton.class);

        if(GlobalSettings.IS_ESTIMOTE_SUPPORT_ENABLED)
            bind(EstimoteService.class).in(Singleton.class);
            */
    }
}