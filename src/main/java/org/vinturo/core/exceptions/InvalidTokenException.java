/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.exceptions;

import java.io.Serializable;

public class InvalidTokenException extends Exception implements Serializable {

    private static final long serialVersionUID = 1169426381288170661L;

    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException(String msg, Exception e) {
        super(msg, e);
    }

}
