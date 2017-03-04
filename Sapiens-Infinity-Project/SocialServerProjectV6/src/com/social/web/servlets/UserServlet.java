package com.social.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.social.web.utils.ServiceConrol;


/********************************************
 *get* 
 /Users ->returns all listed Users
 /Users/{id} -> returns user of id
 /Users/{id}/Groups -> returns Groups of user of id
 /Users/{id}/Posts ->  returns Posts of user of id
 
 *Post*
 /Users/(json params: firstName, lastName) -> creates a new User
 
 *Delete*
 /Users/{id} -> delete user
*******************************************/


/**
 * Servlet implementation class UserServ
 */
@WebServlet({"/Users", "/Users/*","/Users/*/Groups", "/Users/*/Posts"})
public class UserServlet extends HttpServlet {	

	private Gson gsn = new Gson();

	private static final long serialVersionUID = 1L;
	
	public UserServlet() {
		super();
	}    

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceConrol ctrl = new ServiceConrol(request, response);
		String res = null;

		try {

			switch (ctrl.getDefinedService()) {
			case UNRELATED:
				if (ctrl.getID() != null){
					res = gsn.toJson(ctrl.getSNS().getUser(ctrl.getID()));
				} else {
					res = gsn.toJson(ctrl.getSNS().getAllUsers());
				}
				break;
			case GROUPS:              		
				res = gsn.toJson(ctrl.getSNS().getUserGroups(ctrl.getID()));
				break;
			case POSTS:  
				res = gsn.toJson(ctrl.getSNS().getUserPosts(ctrl.getID()));
				break;
			default:				
				break;                       
			}
			response.setContentType("application/json");  

		} catch (Exception e) {
			res = ctrl.setError(e, response);	
		}
		finally{			
			ctrl.sendResults(ctrl.getOut(),res);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceConrol ctrl = new ServiceConrol(request, response);

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		String res = null;

		try {
			res = gsn.toJson(ctrl.getSNS().createUser(firstName, lastName));

		} catch (Exception e) {
			res = ctrl.setError(e, response);	
		} finally {
			ctrl.sendResults(ctrl.getOut(),res);		    			
		}
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceConrol ctrl = new ServiceConrol(request, response);
		String res = null;
		
		try {
			ctrl.getSNS().deleteUser(ctrl.getID());
			res = "success";
		} catch (Exception e) {
			res = ctrl.setError(e, response);
		} finally {
			ctrl.sendResults(ctrl.getOut(),res);
		}
		
	}
	


}

