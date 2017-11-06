package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import model.Post;

@Stateless
public class PostController {

	@PersistenceContext
    private EntityManager entityManager;

    public void create(Post post)
    {
      entityManager.persist(post);
    }

    public List<Post> all()
    { 
        CriteriaQuery<Post> cq = entityManager.getCriteriaBuilder().createQuery(Post.class);
        cq.select(cq.from(Post.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
    public Post byId(int id){
        return entityManager.find(Post.class, id);
    }
    
    protected List<Post> userPostsList = new ArrayList<>();
    
    public List<Post> getUserPost(int userId){
		List<Post> postList = all();
		
		userPostsList.clear();
		for(Post post : postList){
			
			if(post.getId() == userId){
				this.userPostsList.add(post);
			}
		} 
		return userPostsList;
	}
    

}
