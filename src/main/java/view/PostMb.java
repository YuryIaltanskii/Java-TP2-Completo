package view;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import controller.PostController;
import model.Post;

@Named
public class PostMb {

	@Inject
	private PostController postCntr;

	@Inject
	private AuthUserMb authMb;

	private Post post = new Post();

	public String create() {
		post.setUser(authMb.getCurrentUser());
		postCntr.create(post);
		post = new Post();
		return "home";
	}
	
	public List<Post> all(){
		return postCntr.all();
    }

	public String newpost() {
		return "post";
	}

	public List<Post> listPostUser() {
		return postCntr.getUserPost(authMb.getCurrentUserId());
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
