/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class APIKeyUtil {

    public static String generate() {

        String apiKey = "";

        try {
            KeyGenerator keyGen;
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            byte[] encoded = secretKey.getEncoded();
            apiKey = DatatypeConverter.printHexBinary(encoded).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getTimeMilisecond() + apiKey;
    }

    public static String getTimeMilisecond() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.getTimeInMillis());
    }

}
