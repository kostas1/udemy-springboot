package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springboot.data.entities.Post;
import springboot.data.repositories.PostRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);
		
		PostRepository repository = context.getBean(PostRepository.class);
		repository.save(new Post("First post", "Hello everyeone!"));
		repository.save(new Post("Second post", "Hello again!"));
		
		/*
		 * Print initial list of posts
		 */
		Iterable<Post> posts = repository.findAll();
		System.out.println("Printing posts");
		for (Post post: posts) {
			System.out.println(post);
		}
		
		/*
		 * Change title of first post
		 */
		Post first = repository.findOne(1L);
		System.out.println("First post: " + first);
		first.setTitle("Changing the title");
		repository.save(first);
		System.out.println("Changed first post: " + repository.findOne(1L));
		
		/*
		 * Delete first post
		 */
		System.out.println("Deleting second post");
		repository.delete(2L);
		
		/*
		 * Print all posts again to see that first post is no longer there
		 */
		posts = repository.findAll();
		System.out.println("Printing posts again");
		for (Post post: posts) {
			System.out.println(post);
		}
		
		System.out.println("Printing by title: " + repository.findByTitle("Changing the title"));
	}
}
