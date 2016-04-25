/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity.user;

import java.io.Serializable;

public class ApprovedUserRegistration extends UserRegistration implements Serializable {

    private static final long serialVersionUID = 2349808397057163678L;

    private Long groupId;

    public ApprovedUserRegistration() {
        super();
    }

    public ApprovedUserRegistration(String username, String plainPassword,
                                    Long groupId) {
        super(username, plainPassword);
        this.groupId = groupId;

    }

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroup(Long groupId) {
        this.groupId = groupId;
    }

}