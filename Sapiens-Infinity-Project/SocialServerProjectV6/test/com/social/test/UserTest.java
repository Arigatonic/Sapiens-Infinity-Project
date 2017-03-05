package com.social.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.dao.JPAConfigure;
import com.social.dao.SocialDao;
import com.social.jpa.Group;
import com.social.jpa.User;
import com.social.services.SocialNetworkService;

public class UserTest {

	ApplicationContext ctx = 
			   new AnnotationConfigApplicationContext(JPAConfigure.class);
	
	static SocialNetworkService sd = new SocialNetworkService(new SocialDao());
	
	
	static User usrSample = new User("Test", "User");
	static Group grpSample = new Group("testGroup");

	
	public static void main(String[] args) {
		
		int usrID = sd.createUser(usrSample.getFirstName(), usrSample.getLastName());		
		System.out.println(sd.getUser(usrID));;

	}
}
