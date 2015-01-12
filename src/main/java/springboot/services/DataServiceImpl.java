package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.data.entities.User;
import springboot.data.repositories.RoleRepository;
import springboot.data.repositories.UserRepository;

@Component("dataService")
public class DataServiceImpl implements DataService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void save(User user) {
        user.addRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
