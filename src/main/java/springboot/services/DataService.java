package springboot.services;

import springboot.data.entities.User;

public interface DataService {

    void save(User user);

    User findUserByUsername(String username);
}
