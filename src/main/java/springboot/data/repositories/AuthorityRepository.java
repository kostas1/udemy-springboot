package springboot.data.repositories;

import org.springframework.data.repository.CrudRepository;
import springboot.data.entities.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
