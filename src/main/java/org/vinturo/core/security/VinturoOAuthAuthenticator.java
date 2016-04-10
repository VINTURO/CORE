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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vinturo.core.storage.entity.Consumer;
import org.vinturo.core.storage.service.UserService;

import javax.inject.Inject;

public class VinturoOAuthAuthenticator implements Authenticator<String, Consumer> {

    private static final Logger logger = LoggerFactory.getLogger(VinturoOAuthAuthenticator.class);

    @Inject
    private UserService userService;

    /* (non-Javadoc)
     * @see io.dropwizard.auth.Authenticator#authenticate(java.lang.Object)
     */
    @Override
    public Optional<Consumer> authenticate(String apiKey) throws AuthenticationException {

        logger.debug("VinturoOAuthAuthenticator received api key = " + apiKey);
/*
        try {
            Consumer consumer = userService.getConsumerWithAPIKey(apiKey);

            logger.debug("API KEY WAS CORRECT !");

            return Optional.of(consumer);
        } catch (ConsumerNotFoundException ex) {

            logger.debug("\n\n   OOPS WRONG API KEY    !\n\n");
            logger.debug("\n\n   " + apiKey + "    \n\n");
            return Optional.absent();
        }
*/
        return Optional.absent();
    }

}
