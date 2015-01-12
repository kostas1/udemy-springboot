package springboot.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import springboot.data.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select r from Role r where r.role = :name")
    Role findByName(@Param("name")String name);
}
