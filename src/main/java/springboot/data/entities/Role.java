package springboot.data.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = "role"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected long id;

    @Column(name = "role", length = 50, nullable = false)
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn
    private Set<Authority> authorities = new HashSet<Authority>();

    public Role(String role) {
        this.role = role;
    }

    public Role() {}

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }
}
