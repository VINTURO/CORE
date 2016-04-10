/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.security;

import io.dropwizard.auth.Authorizer;
import org.vinturo.core.storage.entity.Consumer;

public class VinturoAuthorizer implements Authorizer<Consumer> {

    @Override
    public boolean authorize(Consumer consumer, String role) {

        return true;
   /*   
      if (role.equals("ADMIN")) {
           return true;
       }
       return false;
       */
    }
}