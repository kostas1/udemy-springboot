package springboot.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import springboot.data.entities.User;
import springboot.data.repositories.RoleRepository;
import springboot.data.repositories.UserRepository;

@Component("userDetailsService")
public class UserManager implements UserDetailsManager {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null || User.UserState.EmailNotConfirmed == user.getState()) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return user;
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// not used
	}
	
    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();

        user.setUsername(userDetails.getUsername());
        user.setPlainPassword(userDetails.getPassword());
        for (GrantedAuthority a: userDetails.getAuthorities()) {
			if (a.getAuthority().startsWith("ROLE_"))
            user.addRole(roleRepository.findByName(a.getAuthority()));
        }
        user.setEnabled(userDetails.isEnabled());
        user.setAccountExpired(!userDetails.isAccountNonExpired());
        user.setCredentialsExpired(!userDetails.isCredentialsNonExpired());
        user.setAccountLocked(!userDetails.isAccountNonLocked());
		user.addRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }
    
	@Override
	public void deleteUser(String username) {
		// not used
	}

	@Override
	public void updateUser(UserDetails user) {
		// not used
	}

    @Override
    public boolean userExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null && User.UserState.EmailNotConfirmed != user.getState();
    }

}
