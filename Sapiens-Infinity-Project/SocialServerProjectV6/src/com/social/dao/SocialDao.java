package com.social.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.social.jpa.entities.Group;
import com.social.jpa.entities.Post;
import com.social.jpa.entities.User;

public class SocialDao {
	
	@PersistenceContext
    private EntityManager em;
                   
	public List<?> getAllUsers() throws IllegalArgumentException{
		
		return (em.createNamedQuery(User.GET_All_USERS)
				  .getResultList());
	}
	
	public List<?> getAllGroups() throws IllegalArgumentException{
		return (em.createNamedQuery(Group.GET_ALL_GROUPS)
				  .getResultList());
	}
	
	public void putUser(User usr){	
		em.persist(usr);
	}
	
	public User getUser(int usrID) throws IllegalArgumentException{	
		User usr = em.find(User.class, usrID);		
		return usr;
	}

	public void putGroup(Group grp){	
		em.persist(grp);
	}
	
	public Group getGroup(int grpID){
		Group grp = em.find(Group.class, grpID);
		return grp;
		
	}
	
	public Post getPost(int pstID){		
		Post pst = em.find(Post.class, pstID);
		return pst;			
	}
	
	public void addPost(Post pst){	
		em.persist(pst);
		User usr = pst.getUser();
		usr.addPost(pst);
		Group grp = pst.getGroup();
		grp.addPost(pst);
		em.persist(usr);
		em.persist(grp);
	}
	
	public void addUserToGroup(User usr, Group grp){
		
		grp.addUser(usr);
		usr.addGroup(grp);		
		em.persist(grp);		
		em.persist(usr);
		
	}	
	
	public void removeUserFromGroup(User usr, Group grp){
		
		grp.removeUser(usr);
		usr.removeGroup(grp);
		em.persist(grp);		
		em.persist(usr);
		
	}	

		
	public List<Post> getUserPosts(int usrID){
		
		User usr = em.find(User.class, usrID);
		
		return new ArrayList<>(usr.getPosts());
	}
	
	public Set<Group> getUserGroups(int usrID){
		
		User usr = em.find(User.class, usrID);
		
		return new HashSet<Group>(usr.getGroups());
	}


		
	public void deletePost(Post pst){
		em.remove(pst);
	}
		
	public void deleteUser(User usr){
		
		usr.setFirstName("Deleted");
		usr.setLastName("User");
		usr.setActive(false);
		
		em.persist(usr);
	}
	
	public void deleteGroup(Group grp){
		em.remove(grp);
	}
			

}