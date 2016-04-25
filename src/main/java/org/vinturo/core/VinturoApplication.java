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
import io.dropwizard.lifecycle.ServerLifecycleListener;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.vinturo.core.io.notifications.NotifierModule;
import org.vinturo.core.io.ws.rest.RestModule;
import org.vinturo.core.io.ws.rest.impl.AuthWSImpl;
import org.vinturo.core.io.ws.rest.impl.RegistrationWSImpl;
import org.vinturo.core.io.ws.rest.impl.UserWSImpl;
import org.vinturo.core.storage.DatabaseModule;
import org.vinturo.core.storage.PopulateDatabase;
import org.vinturo.core.storage.StorageModule;
import org.vinturo.core.storage.service.impl.UserServiceImpl;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
        //       bootstrap.addBundle(new AssetsBundle("/assets", "/shared", null, "shared"));
        //       NotifierService.getInstance();

        bootstrap.addBundle(new SwaggerBundle<VinturoConfiguration>() {

            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(VinturoConfiguration configuration) {
                // Get version from pom.xml
                configuration.swaggerBundleConfiguration.setLicense("The MIT License (MIT)");
                configuration.swaggerBundleConfiguration.setLicenseUrl("https://github.com/VINTURO/CORE/blob/master/LICENSE");
                configuration.swaggerBundleConfiguration.setContact("Sebastien Vermeille");
                configuration.swaggerBundleConfiguration.setTitle("VINTURO API");
                configuration.swaggerBundleConfiguration.setDescription("Welcome dear developer ! Have fun with this api and please contribute to vinturo's project.");
                configuration.swaggerBundleConfiguration.setValidatorUrl("1.0.0");  // TODO: Read it directly from the pom.xml or find an alternative
                return configuration.swaggerBundleConfiguration;
            }
        });

    }

    @Override
    public void run(VinturoConfiguration configuration, Environment environment) {

        // Dependency Injection entrypoint
        final Injector injector = Guice.createInjector(
                new AppModule(configuration, environment),
                new NotifierModule(),
                new DatabaseModule(),
                new StorageModule(),
                new RestModule(environment)
        );
        environment.servlets().addFilter("persistFilter", injector.getInstance(PersistFilter.class));

        // Init database with base data
        environment.lifecycle().addServerLifecycleListener(new ServerLifecycleListener() {
            @Override
            public void serverStarted(Server server) {

                PopulateDatabase populator = new PopulateDatabase(injector.getInstance(UserServiceImpl.class));
                populator.init();

            }
        });

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
        */

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // TODO: Look if we cannot directly register them from the RestModule itself
        environment.jersey().register(injector.getInstance(AuthWSImpl.class));
        environment.jersey().register(injector.getInstance(RegistrationWSImpl.class));
        environment.jersey().register(injector.getInstance(UserWSImpl.class));
/*
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
