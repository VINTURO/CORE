/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.swagger;

import com.google.inject.AbstractModule;
import org.vinturo.core.io.ws.rest.AuthWS;
import org.vinturo.core.io.ws.rest.UserWS;

import java.util.HashSet;
import java.util.Set;

public class SwaggerModule extends AbstractModule {

    public SwaggerModule() {

    }

    @Override
    protected void configure() {


        Set<Class<?>> resources = new HashSet();

        //resources.add(FirstResource.class);
        //resources.add(SecondResource.class);

        resources.add(AuthWS.class);
        resources.add(UserWS.class);

        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);


    }
}