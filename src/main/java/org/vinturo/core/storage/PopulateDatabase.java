/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage;

import com.google.inject.Inject;
import org.vinturo.core.storage.entity.user.User;
import org.vinturo.core.storage.service.UserService;
import org.vinturo.core.storage.service.impl.UserServiceImpl;
import org.vinturo.core.util.PasswordUtil;

public class PopulateDatabase {

    private UserService userService;

    @Inject
    public PopulateDatabase(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void init() {

        if (!isAlreadyPopulated()) {
            createUsers();
        }
    }

    private boolean isAlreadyPopulated() {

        if (userService.findByUsername("admin") == null) {
            return false;
        } else {
            return true;
        }

    }

    private void createUsers() {

        // Create admin user
        User admin = new User("admin", PasswordUtil.encodePassword("admin"));
        admin.setApproved(true);
        userService.save(admin);
    }

}
