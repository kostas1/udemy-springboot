package springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springboot.data.entities.Post;
import springboot.data.repositories.PostRepository;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	PostRepository postRepository;
	
	@RequestMapping("view")
	public String view(Model model) {
		model.addAttribute("posts", postRepository.findAll());
		return "posts/view";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create() {
		return "posts/create";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(String title, String content) {
		postRepository.save(new Post(title, content));
		return "redirect:/posts/view";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(long id, Model model) {
		model.addAttribute("post", postRepository.findOne(id));
		return "posts/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(Post post) {
		Post original = postRepository.findOne(post.getId());
		original.setTitle(post.getTitle());
		original.setContent(post.getContent());	
		postRepository.save(original);
		return "redirect:/posts/view";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(long id) {
		postRepository.delete(id);
		return "redirect:/posts/view";
	}
}
