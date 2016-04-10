/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.vinturo.core.io.notifications.NotifierModule;
import org.vinturo.core.io.ws.rest.RestModule;
import org.vinturo.core.storage.DatabaseModule;
import org.vinturo.core.storage.StorageModule;

import javax.servlet.FilterRegistration;

public class VinturoApplication extends Application<VinturoConfiguration> {

    public static void main(String[] args) throws Exception {
        new VinturoApplication().run(args);
    }

    @Override
    public String getName() {
        return "vinturo";
    }

    @Override
    public void initialize(Bootstrap<VinturoConfiguration> bootstrap) {

        bootstrap.addBundle(new AssetsBundle("/swagger", "/swagger", null, "swagger"));
        bootstrap.addBundle(new AssetsBundle("/assets", "/shared", null, "shared"));
        //       NotifierService.getInstance();

    }

    @Override
    public void run(VinturoConfiguration configuration, Environment environment) {

        // Dependency Injection entrypoint
        final Injector injector = Guice.createInjector(
                new AppModule(configuration, environment),
                new NotifierModule(),
                new DatabaseModule().getJpaModule(),
                new StorageModule(),
                new RestModule()
        );
        environment.servlets().addFilter("persistFilter", injector.getInstance(PersistFilter.class));

        // CORS Settings
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        /*
        environment.jersey().register(new WebExceptionMapper());


        environment.jersey().register(new AuthDynamicFeature(
                new OAuthCredentialAuthFilter.Builder<Consumer>()
                        //   .setAuthenticator(new VinturoOAuthAuthenticator())
                        .setAuthenticator(injector.getInstance(VinturoOAuthAuthenticator.class))
                        .setAuthorizer(new VinturoAuthorizer())
                        .setPrefix("Bearer")
                        .buildAuthFilter()));




        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Consumer.class));


        environment.getApplicationContext().addServlet("com.github.valdr.ValidationRulesServlet", "/validationRules");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(injector.getInstance(AuthResource.class));
        environment.jersey().register(injector.getInstance(GroupResource.class));
        environment.jersey().register(injector.getInstance(UserResource.class));
        environment.jersey().register(injector.getInstance(RoleResource.class));
        environment.jersey().register(injector.getInstance(LightResource.class));
        environment.jersey().register(injector.getInstance(BeaconResource.class));
        environment.jersey().register(injector.getInstance(RoomResource.class));
        environment.jersey().register(injector.getInstance(RuleResource.class));
        environment.jersey().register(injector.getInstance(PhilipsHueResource.class));
        environment.jersey().register(injector.getInstance(EstimoteResource.class));


        injector.getInstance(RulesEngine.class).init();

        CheckRulesTask task = new CheckRulesTask(injector.getInstance(RulesEngine.class));
        environment.admin().addTask(task);
        */
    }

}
