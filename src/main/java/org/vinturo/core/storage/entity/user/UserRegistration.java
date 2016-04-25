/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 2349808397057163678L;

    @NotNull
    @Size(min = 5, max = 45)
    private String username;

    @NotNull
    @Size(min = 8, max = 60)
    private String plainPassword;

    public UserRegistration() {
    }

    public UserRegistration(String username, String plainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlainPassword() {
        return this.plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
}