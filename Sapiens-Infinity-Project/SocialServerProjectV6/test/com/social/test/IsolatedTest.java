package com.social.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.Gson;
import com.social.dao.JPAConfigure;
import com.social.dao.SocialDao;
import com.social.jpa.Group;
import com.social.jpa.Post;
import com.social.jpa.User;
import com.social.services.SocialNetworkService;

public class IsolatedTest {

	ApplicationContext ctx = 
			new AnnotationConfigApplicationContext(JPAConfigure.class);

	SocialNetworkService sd;

	Gson gsn = new Gson();	

	User usrSample = new User("Test", "User");
	Group grpSample = new Group("testGroup");
	Post postSample = new Post(new Date(), usrSample, grpSample, "Test Post");

	int usrIDinDB;
	int grpIDinDB;

	public Post createRelatedPostUserAndGroup(){

		usrIDinDB = sd.createUser("Test", "User");
		grpIDinDB = sd.createGroup(grpSample.getGroupName());

		sd.addUserToGroup(usrIDinDB, grpIDinDB);
		int pstID = sd.createPost(usrIDinDB, grpIDinDB, postSample.getContent());
		Post pstRes = sd.getPost(pstID);

		return pstRes;
	}



	@Test 
	public void deleteUserGroupAndPost(){

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(JPAConfigure.class);
		ctx.scan("services");
		ctx.refresh();
		sd = ctx.getBean(SocialNetworkService.class);
//		sd.setSocialDao(ctx.getBean(SocialDao.class));

		Post pstRes = this.createRelatedPostUserAndGroup();	


		List<Group> grps = sd.getAllGroups();
		List<User> usrs = sd.getAllUsers();
		//		
		int grpCounter = grps.size();
		int usrCounter = usrs.size();

		assertEquals(grpCounter, grps.size());
		assertEquals(usrCounter, usrs.size());


		pstRes = this.createRelatedPostUserAndGroup();

		grps = sd.getAllGroups();
		usrs = sd.getAllUsers();


		assertEquals(grpCounter + 1, grps.size());
		assertEquals(usrCounter + 1, usrs.size());

		sd.deleteUser(usrIDinDB);
		sd.deleteGroup(grpIDinDB);

		assertEquals(grpCounter, sd.getAllGroups().size());
		assertEquals(usrCounter, sd.getAllUsers().size());

	}

}
