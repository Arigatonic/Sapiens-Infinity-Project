package com.social.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.social.jpa.entities.*;

public class SocialDao {
	
    private EntityManager em;
               
    @Autowired
    public void setEntityManager(EntityManager em){
    	this.em = em;
    }
    
	public List<?> getAllUsers() throws IllegalArgumentException{
		
		return (em.createNamedQuery(User.GET_All_USERS)
				  .getResultList());

	}
	
	public List<?> getAllGroups() throws IllegalArgumentException{
		return (em.createNamedQuery(Group.GET_ALL_GROUPS)
				  .getResultList());

	}
    
	public void putUser(User usr){	
		this.startTransaction();
		em.persist(usr);
		this.closeTransaction();
	}
	
	public User getUser(int usrID) throws IllegalArgumentException{	
		this.startTransaction();
		User usr = em.find(User.class, usrID);		
		this.closeTransaction();
		return usr;
	}

	public void putGroup(Group grp){	
		this.startTransaction();
		em.persist(grp);
		this.closeTransaction();
	}
	
	public Group getGroup(int grpID){
		this.startTransaction();
		Group grp = em.find(Group.class, grpID);
		this.closeTransaction();
		return grp;
		
	}
	
	public Post getPost(int pstID){		
		this.startTransaction();
		Post pst = em.find(Post.class, pstID);
		this.closeTransaction();
		return pst;			
	}
	
	public void addPost(Post pst){	
		this.startTransaction();		
		em.persist(pst);
		User usr = pst.getUser();
		usr.addPost(pst);
		Group grp = pst.getGroup();
		grp.addPost(pst);
		em.persist(usr);
		em.persist(grp);
		this.closeTransaction();
	}
	
	public void addUserToGroup(User usr, Group grp){
		
		this.startTransaction();			
		grp.addUser(usr);
		usr.addGroup(grp);		
		em.persist(grp);		
		em.persist(usr);
		this.closeTransaction();
		
	}	
	
	public void removeUserFromGroup(User usr, Group grp){
		
		this.startTransaction();			
		grp.removeUser(usr);
		usr.removeGroup(grp);
		em.persist(grp);		
		em.persist(usr);
		this.closeTransaction();
		
	}	

		
	public List<Post> getUserPosts(int usrID){
		
		this.startTransaction();
		User usr = em.find(User.class, usrID);
		this.closeTransaction();
		
		return new ArrayList<>(usr.getPosts());
	}

		
	public void deletePost(Post pst){
		this.startTransaction();
		em.remove(pst);
		this.closeTransaction();
	}
		
	public void deleteUser(User usr){
		
		usr.setFirstName("Deleted");
		usr.setLastName("User");
		usr.setActive(false);
		
		this.startTransaction();
		em.persist(usr);
		this.closeTransaction();
	}
	
	public void deleteGroup(Group grp){
		this.startTransaction();
		em.remove(grp);
		this.closeTransaction();
	}
			
	
    // Create transaction
    private void startTransaction(){
    	em.getTransaction().begin();
    }
    
    private void closeTransaction(){
		em.getTransaction().commit();
    }


	

}