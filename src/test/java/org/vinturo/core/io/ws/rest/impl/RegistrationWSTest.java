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
import org.vinturo.core.storage.entity.user.UserRegistration;
import org.vinturo.core.storage.utils.WSTest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationWSTest extends WSTest {

    @Test
    public void shouldReturn201WhenInputParametersAreCorrect() {
        Client client = new JerseyClientBuilder().build();

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.REGISTER))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(registerForm("testuser", "testpassword")));

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getEntity() != null);
    }

    @Test
    public void shouldReturn422WhenInputParametersAreWrong() {
        Client client = new JerseyClientBuilder().build();

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.REGISTER))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(registerForm("admin", "1")));

        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getEntity() == null);
    }

    @Test
    public void shouldReturn403WhenUsernameIsAlreadyRegistred() {
        Client client = new JerseyClientBuilder().build();

        String username = "kevinmitnick";

        client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.REGISTER))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(registerForm(username, "miamdeschipsPassword")));

        Response response = client.target(
                String.format("http://localhost:%d/api%s", RULE.getLocalPort(), Routes.REGISTER))
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(registerForm(username, "3213211321321312312321312321312312")));

        assertThat(response.getStatus()).isEqualTo(403);
        assertThat(response.getEntity() == null);
    }

    private UserRegistration registerForm(String username, String password) {
        UserRegistration registration = new UserRegistration();

        registration.setUsername(username);
        registration.setPlainPassword(password);

        return registration;
    }

}
