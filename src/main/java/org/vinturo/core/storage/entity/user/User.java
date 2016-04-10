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
import org.vinturo.core.exceptions.ConsumerAlreadyLinkedWithUserException;
import org.vinturo.core.security.Role;
import org.vinturo.core.storage.entity.AbstractEntity;
import org.vinturo.core.storage.entity.Consumer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = User.QUERY_FIND_BY_USERNAME)
})
@Table(name = "USERS")
public class User extends AbstractEntity {

    public static final String QUERY_FIND_BY_USERNAME = "SELECT u FROM User u WHERE u.username = :username";

    private static final long serialVersionUID = 3276795655534895602L;

    @NotNull
    @Size(min = 5, max = 45)
    @Column(name = "USERNAME", length = 45, unique = true)
    private String username;

    @Size(min = 8, max = 60)
    @Column(name = "PASSWORD", length = 60, nullable = false)
    @JsonIgnore
    private String password;

    @NotNull
    @Column(name = "APPROVED", length = 1)
    private boolean approved;

    @Embedded
    private Person profile;

    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Consumer> consumers = new ArrayList<Consumer>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public User() {
        super();
        this.profile = new Person();
    }

    public User(String username, String hashedPassword) {
        super();
        this.username = username;
        this.password = hashedPassword;
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

    public List<Consumer> getConsumers() {
        return this.consumers;
    }

    public void addConsumer(Consumer consumer) throws ConsumerAlreadyLinkedWithUserException {

        for (Consumer cons : this.getConsumers()) {
            if (cons.getUID().equals(consumer.getUID()) && cons.getType().equals(consumer.getType())) {

                throw new ConsumerAlreadyLinkedWithUserException();
            }
        }

        consumer.setUser(this);
        this.getConsumers().add(consumer);

    }

    @JsonProperty
    public List<Role> getRoles() {

        List<Role> roles = new ArrayList<Role>();

        if (this.group != null) {

            roles.addAll(group.getRoles());

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
