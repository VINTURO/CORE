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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.vinturo.core.storage.entity.AbstractEntity;
import org.vinturo.core.storage.entity.Consumer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(name = User.QUERY_FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username")
})
@Table(name = "USERS")
public class User extends AbstractEntity {

    public static final String QUERY_FIND_BY_USERNAME = "User.findByUsername";

    private static final long serialVersionUID = 3276795655534895602L;

    @NotNull
    @Size(min = 5, max = 45)
    @Column(name = "USERNAME", length = 45, unique = true, nullable = false)
    private String username;

    @Size(min = 8, max = 256)
    @Column(name = "PASSWORD", length = 256, nullable = false)
    @JsonIgnore
    private String password;

    @NotNull
    @Column(name = "APPROVED", length = 1, nullable = false)
    private boolean approved;

    @Embedded
    private Person profile;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Consumer> consumers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public User() {
        this(null, null);
    }

    public User(String username, String hashedPassword) {
        this.username = username;
        this.password = hashedPassword;
        this.consumers = new ArrayList<>();
        this.profile = new Person();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isApproved() {
        return this.approved;
    }

    public void setApproved(boolean state) {
        this.approved = state;
    }

    /**
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns an immutable list of consumers. If you want to add a consumer simply use addConsumer() method instead.
     *
     * @return
     */
    public List<Consumer> getConsumers() {
        return Collections.unmodifiableList(this.consumers);
    }

    public void addConsumer(Consumer consumer) {

        consumer.setUser(this);
        this.consumers.add(consumer);

    }

    @JsonProperty
    public Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();

        if (this.group != null) {

            this.group.getRoles().forEach(role -> {
                roles.add(role);
            });

        }

        return roles;

    }

    @JsonBackReference
    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
        if (!group.getUsers().contains(this)) {
            group.getUsers().add(this);
        }
    }

    public Person getProfile() {
        return profile;
    }

}
