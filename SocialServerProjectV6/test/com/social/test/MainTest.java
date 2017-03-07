package com.social.test;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.social.jpa.entities.Group;
import com.social.jpa.entities.Post;
import com.social.jpa.entities.User;

public class MainTest {
	
	public static void main(String[] args) {
		
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialServer");
	    EntityManager em = emf.createEntityManager();
	    	   
	    
	    em.getTransaction().begin();
	    
		User usr = new User("Lior", "Gal");
		
		Group grp = new Group("Sample");
		grp.addUser(usr);
//		usr.addGroup(grp);
	    
	    Post pst = new Post(new Date(),usr, grp, "kblak bla bla");
		
		
		em.persist(grp);
		em.persist(usr);		
		em.persist(pst);
		em.getTransaction().commit();
				
		emf.close();

	}

}
