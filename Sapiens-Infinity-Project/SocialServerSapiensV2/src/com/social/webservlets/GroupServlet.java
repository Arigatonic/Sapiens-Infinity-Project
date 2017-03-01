package com.social.webservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * 
 * 	//	/Group	type:post
	//Returns the ID of the new group or throws exception
	public int createGroup(String groupName)
	{
		return 0;
	}
 * 
	//	/Group/[GroupID] type:delete
	public void deleteGroup(){}
	
	//	/Group/[group_id/ type:get
	public Group getGroup()
	{
		return null;
	}
	
	//	/Group/[groupID]/[UserID]	type:post
	public void addUserToGroup(){}
	
	// /Group/[groupID]/[UserID]	type:delete
	public void removeUserFromGroup(){}
	
	
	//	/Group/[GroupID]/posts	type:get
	public List<Post> getGroupPosts()
	{
		return null;
	}
	
	// Group/[GroupID]/users	type:get
	public List<User> getGroupUsers()
	{
		return null;
	} 
	

*/


/**
 * Servlet implementation class GroupServ
 */
@WebServlet("/GroupServ")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
