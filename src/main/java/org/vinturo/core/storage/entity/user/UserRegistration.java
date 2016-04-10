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

    @NotNull
    @Size(min = 8, max = 60)
    private String verificationPlainPassword;

    public UserRegistration() {
    }

    public UserRegistration(String username, String plainPassword, String vertificationPlainPassword) {
        this.username = username;
        this.plainPassword = plainPassword;
        this.verificationPlainPassword = vertificationPlainPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPlainPassword() {
        return this.plainPassword;
    }

    public String getVerificationPlainPassword() {
        return this.verificationPlainPassword;
    }

}