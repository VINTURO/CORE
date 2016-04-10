/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class SimplePrincipal implements Principal {

    private String username;
    private List<String> roles = new ArrayList<>();

    public SimplePrincipal(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isUserInRole(String roleToCheck) {
        return roles.contains(roleToCheck);
    }

    public String getUsername() {
        return username;
    }

    /* (non-Javadoc)
     * @see java.security.Principal#getName()
     */
    @Override
    public String getName() {

        // TODO Auto-generated method stub
        return null;
    }

}
