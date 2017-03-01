package com.social.webservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.social.dao.*;
import com.social.services.SocialNetworkService;


/*
	
	//	/User type:Get
	public String getUser(int userID)
	{
		return null;
	}
	
	//	/User	type:post
	//Returns the ID of the new user
	public int createUser(String firstName,
			String LastName)
	{
		return 0;
	}
	
	//	/User/[UserID] type:delete
	public void deleteUser(){}

	//	/User/[userID]/groups type:get
	public List<Group> getUserGroups()
	{
		return null;
	}
	
		//	/Post/[userID]/posts	type:get
	public List<Post> getUserPosts()
	{
		return null;
	}

*/




/**
 * Servlet implementation class UserServ
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	
	Gson gsn = new Gson();
	
	static final EntityManagerFactory emf = 
				 Persistence.createEntityManagerFactory("SocialServer");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String usrID = request.getParameter("id");
		PrintWriter out = response.getWriter();
		
		try {
			SocialNetworkService sns = new SocialNetworkService(emf);
			String user = gsn.toJson(sns.getUser(Integer.parseInt(request.getParameter("id"))));
							
			response.setContentType("application/json");  
			out.print(user);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			response.setStatus(400);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		SocialNetworkService sd = new SocialNetworkService(emf);	
		
		String usrID;
		response.setContentType("application/json"); 
		
		try {
			
			usrID = gsn.toJson(sd.createUser(firstName, lastName));
			PrintWriter out  = response.getWriter();
		    out.println(usrID);
		    out.flush();
		    out.close();		    
			
		} catch (Exception e) {
			response.setStatus(400);
			e.printStackTrace();			
		}
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

