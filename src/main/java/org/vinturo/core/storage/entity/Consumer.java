/**
 * This file is part of the Vinturo package.
 * <p>
 * (c) Sébastien Vermeille <sebastien.vermeille@gmail.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package org.vinturo.core.storage.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.vinturo.core.storage.entity.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Entity
@Table(name = "CONSUMERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"UID"}),
                @UniqueConstraint(columnNames = {"NAME", "USER_ID"})
        }
)

@NamedQueries({
        @NamedQuery(name = "Consumer.findByUID", query = "SELECT c FROM Consumer c WHERE c.uid = :UID"),
        @NamedQuery(name = "Consumer.findByAPIKey", query = "SELECT c FROM Consumer c WHERE c.apiKey = :key"),
})
public class Consumer extends AbstractEntity implements Principal {

    private static final long serialVersionUID = 7100416511460667275L;
    @NotNull(message = "{consumer.user.is.required}")
    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Basic(optional = false)
    @NotNull(message = "{consumer.name.is.required}")
    private String name;

    @Basic(optional = false)
    @NotNull(message = "{consumer.type.is.required}")
    @Enumerated(EnumType.STRING)
    private ConsumerType type;

    @Basic(optional = false)
    @NotNull(message = "{consumer.os.is.required}")
    @Enumerated(EnumType.STRING)
    private ConsumerOS os;

    @Basic(optional = false)
    @NotNull(message = "{consumer.uid.is.required}")
    @Column(name = "UID")
    private String uid;

    @Column(name = "API_KEY", unique = true, nullable = false)
    private String apiKey;

    public Consumer() {
    }

    public Consumer(String name, ConsumerType type, ConsumerOS os, String UID) {

        this.name = name;
        this.type = type;
        this.os = os;
        this.uid = UID;

    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User usr) {
        this.user = usr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConsumerType getType() {
        return this.type;
    }

    public void setType(ConsumerType type) {
        this.type = type;
    }

    public ConsumerOS getOS() {
        return this.os;
    }

    public void setOS(ConsumerOS os) {
        this.os = os;
    }

    public String getUID() {
        return this.uid;
    }

    public void setUID(String UID) {
        this.uid = UID;
    }

    public String getAPIKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
