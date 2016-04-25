/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.io.ws.rest.impl;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import org.vinturo.core.io.Routes;
import org.vinturo.core.storage.entity.ConsumerOS;
import org.vinturo.core.storage.entity.ConsumerType;
import org.vinturo.core.storage.entity.Device;
import org.vinturo.core.storage.entity.VinturoAuth;
import org.vinturo.core.storage.utils.WSTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthWSTest extends WSTest {

    @Test
    public void shouldReturn200WhenCreditentialsAreCorrect() {

        this.registerUser("admin", "admin1234");

        Client client = new JerseyClientBuilder().build();

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.AUTH))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(loginForm("admin", "admin1234")));

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getEntity() != null);
    }

    @Test
    public void shouldReturn401WhenCreditentialsAreNotCorrect() {
        Client client = new JerseyClientBuilder().build();

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.AUTH))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(loginForm("admin", "admin1234")));

        assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    public void shouldReturn403WhenUserIsNotApproved() {
        Client client = new JerseyClientBuilder().build();

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.AUTH))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(loginForm("admin", "admin1234")));

        assertThat(response.getStatus()).isEqualTo(403);
    }

    private VinturoAuth loginForm(String username, String password) {
        Device device = new Device("Computer of test", ConsumerOS.WINDOWS, ConsumerType.COMPUTER, "SECRET-DEVICE-ID");
        VinturoAuth auth = new VinturoAuth(username, password, device);
        return auth;
    }

}
