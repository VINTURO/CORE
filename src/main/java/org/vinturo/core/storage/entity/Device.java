/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity;

import javax.persistence.Basic;
import javax.validation.constraints.NotNull;

public class Device {

    @Basic(optional = false)
    @NotNull(message = "{device.name.is.required}")
    private String name;

    @Basic(optional = false)
    @NotNull(message = "{device.os.is.required}")
    private ConsumerOS os;

    @Basic(optional = false)
    @NotNull(message = "{device.type.is.required}")
    private ConsumerType type;

    @NotNull(message = "{device.uid.is.required}")
    private String uid;

    public Device() {
    }

    public Device(String name, ConsumerOS os, ConsumerType type, String UID) {
        this.name = name;
        this.os = os;
        this.type = type;
        this.uid = UID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConsumerOS getOS() {
        return this.os;
    }

    public void setOS(ConsumerOS os) {
        this.os = os;
    }

    public ConsumerType getType() {
        return this.type;
    }

    public void setType(ConsumerType type) {
        this.type = type;
    }

    public String getUID() {
        return this.uid;
    }

    public void setUID(String UID) {
        this.uid = UID;
    }
}
