package springboot.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities",
        uniqueConstraints = @UniqueConstraint(columnNames = { "username", "authority" }))
public class Authority implements Serializable, GrantedAuthority {

    @Column(name = "authority", length = 50, nullable = false)
    private String authority;

    @Id
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}