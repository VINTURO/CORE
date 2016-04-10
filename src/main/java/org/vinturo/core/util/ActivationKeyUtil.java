/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.util;

import java.util.UUID;

public class ActivationKeyUtil {

    public static String generateKey(String prefix) {

        String key = prefix + UUID.randomUUID().toString();
        return key;
    }

}
