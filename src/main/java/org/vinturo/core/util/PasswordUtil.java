/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String encodePassword(String plainPassword) {

        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(13));
        return hashedPassword;
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {

        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            return true;
        } else {
            return false;
        }

    }

}
