package springboot.data.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities",
        uniqueConstraints = @UniqueConstraint(columnNames = "authority"))
public class Authority implements Serializable, GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected long id;

    @Column(name = "authority", length = 50, nullable = false)
    private String authority;

    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}