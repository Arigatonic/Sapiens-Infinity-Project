package com.social.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.social.dao.SocialDao;
import com.social.jpa.entities.Group;
import com.social.jpa.entities.Post;
import com.social.jpa.entities.User;

// this class will validate data before executing queries through dao
public class SocialNetworkService {

	SocialDao dao;

	public void setSocialDao(SocialDao dao){
		this.dao = dao;
	}

	public SocialDao getSocialDao(SocialDao dao){
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() throws IllegalArgumentException{

		return (List<User>) dao.getAllUsers();
	}

	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups() throws IllegalArgumentException{

		return (List<Group>) dao.getAllGroups();
	}

	//returns UserID	
	public int createUser(String firstName, String lastName){		

		if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
			throw new IllegalArgumentException("cannot obtain numbers in a name");
		}		

		User usr = new User(firstName, lastName);
		dao.putUser(usr);

		return usr.getUserID();
	}

	public User getUser(int usrID) throws IllegalArgumentException{

		User usr = dao.getUser(usrID);		
		this.userCheck(usr);

		return usr;
	}

	public Post getPost(int pstID){

		Post pst = dao.getPost(pstID);
		this.postCheck(pst);						

		return pst;				
	}

	//returns GroupID
	public int createGroup(String groupName){

		Group grp = new Group(groupName);
		dao.putGroup(grp);	
		return grp.getGroupID();
	}

	public Group getGroup(int grpID) throws IllegalArgumentException {

		Group grp = dao.getGroup(grpID);
		this.groupCheck(grp);		

		return grp;
	}

	//return pst id
	public int createPost(int usrID, int grpID, String content){

		User usr = dao.getUser(usrID);
		Group grp = dao.getGroup(grpID);

		this.userCheck(usr);
		this.groupCheck(grp);

		if (!grp.getUsers().contains(usr)){
			throw new IllegalArgumentException("user not in group");
		}

		Post pst = new Post(new Date(),usr, grp,content);
		dao.addPost(pst);

		return pst.getPostID();
	}

	public void addUserToGroup(int usrID, int grpID) throws IllegalArgumentException {

		User usr = dao.getUser(usrID);
		Group grp = dao.getGroup(grpID);

		this.userCheck(usr);
		this.groupCheck(grp);

		if (usr.getGroups().contains(grp)){
			throw new IllegalArgumentException("user is already in group");
		}

		dao.addUserToGroup(usr, grp);

	}


	public Set<Post> getGroupPosts(int grpID){

		Group grp = dao.getGroup(grpID);
		this.groupCheck(grp);	

		Set<Post> psts = grp.getPosts();

		if (psts.size() == 0){
			throw new IllegalArgumentException("Group does not have any Posts");
		}

		return psts;
	}

	public List<Post> getUserPosts(int usrID)throws IllegalArgumentException{

		User usr = dao.getUser(usrID);
		this.userCheck(usr);

		List <Post> psts = dao.getUserPosts(usrID);
		
		if (psts.size() == 0){
			throw new IllegalArgumentException("User does not have any Posts");
		}

		return psts;
	}


	public Set<Group> getUserGroups(int usrID) throws IllegalArgumentException{

		User usr = dao.getUser(usrID);
		this.userCheck(usr);
		return usr.getGroups();	    	

	}

	public Set<User> getGroupUsers(int grpID) throws IllegalArgumentException {

		Group grp = dao.getGroup(grpID);
		this.groupCheck(grp);
		Set<User> usrs = grp.getUsers();		

		return usrs;
	} 

	//delete data	
	public void removeUserFromGroup(int usrID, int grpID) throws IllegalArgumentException{

		User usr = dao.getUser(usrID);
		Group grp = dao.getGroup(grpID);

		this.userCheck(usr);
		this.groupCheck(grp);

		if (!usr.getGroups().contains(grp)){
			throw new IllegalArgumentException("user is not in group");
		}

		dao.removeUserFromGroup(usr, grp);

	}

	public void deletePost(int pstID) throws IllegalArgumentException{

		Post pst = dao.getPost(pstID);	
		this.postCheck(pst);		
		dao.deletePost(pst);

	}

	public void deleteUser(int usrID){

		User usr = dao.getUser(usrID);		

		this.userCheck(usr);
		Set<Group> groupList = usr.getGroups();

		for (Group grp: groupList){
			dao.removeUserFromGroup(usr, grp);
		}

		dao.deleteUser(usr);
	}

	public void deleteGroup(int grpID){


		Group grp = dao.getGroup(grpID);
		this.groupCheck(grp);

		for (User usr : grp.getUsers()){
			dao.removeUserFromGroup(usr, grp);
		}

		for (Post pst : grp.getPosts()){
			dao.deletePost(pst);
		}

		dao.deleteGroup(grp);

	}

	private void userCheck(User usr){
		if (usr == null || usr.isActive() == false){
			throw new IllegalArgumentException("User does not exist");
		}
	}

	private void groupCheck(Group grp){
		if (grp == null){
			throw new IllegalArgumentException("group does not exist");
		}
	}

	private void postCheck(Post pst){
		if (pst == null){
			throw new IllegalArgumentException("Post does not exist");
		}
	}   

}
