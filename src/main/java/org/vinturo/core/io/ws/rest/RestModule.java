/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest;

import com.google.inject.AbstractModule;

public class RestModule extends AbstractModule {

    public RestModule() {

    }

    @Override
    protected void configure() {

        //    bind(DAO.class).to(AbstractDAO.class).in(Singleton.class);
        //     bind(UserDAO.class).to(UserDAOImpl.class).in(Singleton.class);
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