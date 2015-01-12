package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springboot.data.entities.Authority;
import springboot.data.entities.Post;
import springboot.data.entities.Role;
import springboot.data.entities.User;
import springboot.data.repositories.AuthorityRepository;
import springboot.data.repositories.PostRepository;
import springboot.data.repositories.RoleRepository;
import springboot.data.repositories.UserRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ImportResource("mailconfig.xml")
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);

		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		AuthorityRepository authorityRepository = context.getBean(AuthorityRepository.class);

		Role userRole = new Role("ROLE_USER");
		Authority viewPosts = new Authority("VIEW_POSTS");
		authorityRepository.save(viewPosts);
		userRole.addAuthority(viewPosts);
		roleRepository.save(userRole);
		Role adminRole = new Role("ROLE_ADMIN");
		Authority editPosts = new Authority("EDIT_POSTS");
		authorityRepository.save(editPosts);
		adminRole.addAuthority(editPosts);
		roleRepository.save(adminRole);
        User user = new User();
        user.setUsername("kostas");
        user.setPlainPassword("1234");
		user.addRole(adminRole);
		user.addRole(userRole);
		UserRepository userRepository = context.getBean(UserRepository.class);
		userRepository.save(user);
        
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
		for (String name: context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}
}
