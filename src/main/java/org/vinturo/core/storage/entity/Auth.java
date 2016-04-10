/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity;

import java.io.Serializable;

public class Auth implements Serializable {

    private String bridgeIP;

    public Auth() {
    }

    public Auth(String bridgeIP) {
        this.bridgeIP = bridgeIP;
    }

    public String getBridgeIP() {
        return bridgeIP;
    }

}
