package springboot.data.repositories;

import org.springframework.data.repository.CrudRepository;

import springboot.data.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
