package springboot.data.repositories;

import org.springframework.data.repository.CrudRepository;

import springboot.data.entities.User;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);

}
