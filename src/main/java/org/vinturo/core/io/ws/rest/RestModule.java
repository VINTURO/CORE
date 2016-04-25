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
import io.dropwizard.setup.Environment;
import org.vinturo.core.io.ws.rest.impl.AuthWSImpl;
import org.vinturo.core.io.ws.rest.impl.RegistrationWSImpl;
import org.vinturo.core.io.ws.rest.impl.UserWSImpl;

public class RestModule extends AbstractModule {

    private Environment environment;

    public RestModule(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure() {

        bind(AuthWS.class).to(AuthWSImpl.class);
        bind(RegistrationWS.class).to(RegistrationWSImpl.class);
        bind(UserWS.class).to(UserWSImpl.class);

/*
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
        // injector.getInstance(AuthWSImpl.class)
        //    this.environment.jersey().register(UserWSImpl.class);
    }
}