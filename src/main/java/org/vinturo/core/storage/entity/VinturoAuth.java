/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class VinturoAuth implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Valid
    @NotNull
    private Device device;

    public VinturoAuth() {
    }

    public VinturoAuth(String username, String password, Device device) {
        this.username = username;
        this.password = password;
        this.device = device;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
