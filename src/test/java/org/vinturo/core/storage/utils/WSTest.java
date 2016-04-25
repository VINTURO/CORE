/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.utils;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.vinturo.core.VinturoApplication;
import org.vinturo.core.VinturoConfiguration;
import org.vinturo.core.io.Routes;
import org.vinturo.core.storage.entity.user.UserRegistration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WSTest {

    @ClassRule
    public static final DropwizardAppRule<VinturoConfiguration> RULE =
            new DropwizardAppRule<>(VinturoApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    public void registerUser(String username, String plainPassword) {
        Client client = new JerseyClientBuilder().build();

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.REGISTER))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(new UserRegistration("testuser", "testpassword")));
    }
}
