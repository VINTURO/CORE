/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.vinturo.core.security.Role;
import org.vinturo.core.storage.entity.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Group.findByName", query = Group.QUERY_FIND_BY_NAME)
})
@Table(name = "GROUPS")
public class Group extends AbstractEntity {

    public static final String QUERY_FIND_BY_NAME = "SELECT g FROM Group g WHERE g.name = :name";

    private static final long serialVersionUID = 3276795655534895602L;

    @NotNull
    @Size(min = 3, max = 45)
    @Column(name = "name", length = 45, unique = true)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<User>();

    @Column(name = "ROLES", nullable = true)
    private Set<Role> roles = new HashSet<>();

    public Group() {
        super();
    }

    public Group(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return this.users;
    }
}
