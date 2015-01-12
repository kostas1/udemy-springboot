package springboot.data.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class User implements UserDetails {
	
	public static PasswordEncoder encoder = new StandardPasswordEncoder("grain_of_salt");
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected long id;

    @Column(name = "username", length = 50, nullable = false)
    protected String username;

    @Column(name = "password", length = 512, nullable = false)
    protected String password;
    
    @Column
	private boolean accountExpired = false;
    
    @Column
	private boolean accountLocked = false;
    
    @Column
	private boolean credentialsExpired = false;
	
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn
	private Set<Role> roles = new HashSet<Role>();

	@Column
	private String emailConfirmationToken;

	@Column
	@Enumerated(EnumType.STRING)
	private UserState state;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role: roles) {
			authorities.addAll(role.getAuthorities());
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void generatePassword() {
        setPlainPassword(KeyGenerators.string().generateKey());
    }
	
	public void setPlainPassword(String plainPassword) {
        this.password = encoder.encode(plainPassword);
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void generateEmailConfirmationToken() {
		this.emailConfirmationToken = KeyGenerators.string().generateKey();
	}

	public String getEmailConfirmationToken() {
		return emailConfirmationToken;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public UserState getState() {
		return state;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public enum UserState {
		EmailNotConfirmed,
		EmailConfirmed
	}
}
