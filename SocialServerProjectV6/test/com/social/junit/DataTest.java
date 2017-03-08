package com.social.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.Gson;
import com.social.jpa.entities.Group;
import com.social.jpa.entities.Post;
import com.social.jpa.entities.User;
import com.social.jpa.utils.BeansConfigure;
import com.social.services.SocialNetworkService;

public class DataTest {
	
	private final static ApplicationContext ctx = new AnnotationConfigApplicationContext(BeansConfigure.class);
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
	
	@Before
	public void initialize(){
		
		this.sd = ctx.getBean(SocialNetworkService.class);		
	}

	
	@Test
	public void registerUser() {
		
		
		int usrID = sd.createUser(usrSample.getFirstName(), usrSample.getLastName());		
		User userRes = sd.getUser(usrID);
				
		assertEquals(userRes.getFirstName(), usrSample.getFirstName());
		assertEquals(userRes.getLastName(), usrSample.getLastName());	
	}
	
	@Test(expected=IllegalArgumentException.class)	
	public void registerUserError() {
		
		
		User usr = new User(usrSample.getFirstName(), usrSample.getLastName());
		int usrID = sd.createUser("Test111", "User");		
		User userRes = sd.getUser(usrID);
				
		assertEquals(userRes.getFirstName(), usr.getFirstName());
		assertEquals(userRes.getLastName(), usr.getLastName());				
		
	}
	
	@Test
	public void createGroup() {
					
		int grpID = sd.createGroup(grpSample.getGroupName());
		Group grpRes = sd.getGroup(grpID);		
				
		assertEquals(grpRes.getGroupName(), grpSample.getGroupName());
		
	}
		
	@Test 
	public void createPost(){
								
		Post pstRes = this.createRelatedPostUserAndGroup();
		
		assertEquals(postSample.getContent(), pstRes.getContent());
		assertEquals(postSample.getUser().getFirstName(), pstRes.getUser().getFirstName());
		assertEquals(postSample.getGroup().getGroupName(), pstRes.getGroup().getGroupName());
		
	}
	
	
	@Test //Testing addUserToGroup & getUserGroups & getGroupUsers
	public void addUserToGroup(){
						
		int usrIDinDB = sd.createUser("Test", "User");
		int grpIDinDB = sd.createGroup(grpSample.getGroupName());
		
		sd.addUserToGroup(usrIDinDB, grpIDinDB);
		
		User usrRes = sd.getUser(usrIDinDB);
		Group grpRes = sd.getGroup(grpIDinDB);		
		
		Set<User> usrsFromGroup =  sd.getGroupUsers(grpIDinDB);
						
		assertEquals(usrRes.getFirstName(), usrSample.getFirstName());
		assertEquals(usrRes.getLastName(), usrSample.getLastName());
		assertEquals(grpRes.getGroupName(), grpSample.getGroupName());
		assertEquals(postSample.getUser().getFirstName(), usrRes.getFirstName());		
		assertEquals(1, grpRes.getUsers().size());	
		Set<Group> grpsFromUser = sd.getUserGroups(usrIDinDB);
		assertEquals(1, grpsFromUser.size());	
		
		//checking usrsFromGroup		
		assertEquals(1, usrsFromGroup.size());
		assertEquals(usrRes.getFirstName(), new ArrayList<User>(usrsFromGroup).get(0).getFirstName());
	}
	

	@Test
	public void getGroupPosts(){
		
		
		Post pstRes = this.createRelatedPostUserAndGroup();
		
		Set<Post> psts = sd.getGroupPosts(grpIDinDB);			
				
		assertEquals(pstRes.getContent(), new ArrayList<Post>(psts).get(0).getContent());
		assertEquals(pstRes.getUser(), new ArrayList<Post>(psts).get(0).getUser());
		assertEquals(pstRes.getGroup(), new ArrayList<Post>(psts).get(0).getGroup());
		assertEquals(pstRes.getGroup(), new ArrayList<Post>(psts).get(0).getGroup());
		
	}


	@Test
	public void removeUserFromGroup(){
		
		this.createRelatedPostUserAndGroup();
		Set<User> usrsFromGroup =  sd.getGroupUsers(grpIDinDB);
		assertEquals(1, usrsFromGroup.size());
		sd.removeUserFromGroup(usrIDinDB,grpIDinDB);
		usrsFromGroup = sd.getGroupUsers(grpIDinDB);
		assertEquals(0, usrsFromGroup.size());
	}
	
	@Test 
	public void deleteUserGroupAndPost(){
		
		
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
	
	@Test
	public void getUserPosts(){
		
		Post pstRes = this.createRelatedPostUserAndGroup();
		
		@SuppressWarnings("unchecked")
		List<Post> psts = sd.getUserPosts(usrIDinDB);			
				
		assertEquals(1, psts.size());
		assertEquals(pstRes.getContent(), psts.get(0).getContent());
		assertEquals(pstRes.getUser(), psts.get(0).getUser());
		assertEquals(pstRes.getGroup(), psts.get(0).getGroup());
		assertEquals(pstRes.getGroup(), psts.get(0).getGroup());
		
	}

	
	
	

}
