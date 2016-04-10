/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.security;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, SimplePrincipal> {
    @Override
    public Optional<SimplePrincipal> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("secret".equals(credentials.getPassword())) {
            return Optional.of(new SimplePrincipal(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
