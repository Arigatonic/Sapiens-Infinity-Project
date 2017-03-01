package com.social.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.social.web.utils.EntityFactorySingleton;
import com.social.web.utils.ServiceConrol;


/********************************************
 *get* 
 /Groups -> returns all listed Groups
 /Groups/{id} -> returns Group of id
 /Groups/{id}/Users -> returns Groups of Group of id
 /Groups/{id}/Posts ->  returns Posts of Group of id
 
 *put*
 /Groups/(params: groupName) -> creates a new Group
 
 *post*
 /Groups/{gid}/{uid} -> add User to Group
 
 *Delete*
 /Groups/{id} -> delete Group
 /Groups/{gid}/{uid} -> remove user from group
*******************************************/


@WebServlet({"/Groups", "/Groups/*","/Groups/*/Users", "/Groups/*/Posts"})
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Gson gsn = new Gson();

	private static final EntityManagerFactory emf = 
				EntityFactorySingleton.getInstance();
	
	public GroupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceConrol ctrl = new ServiceConrol(request, response, emf);
		String res = null;

		try {

			switch (ctrl.getDefinedService()) {
			case UNRELATED:
				if (ctrl.getID() != null){
					res = gsn.toJson(ctrl.getSNS().getGroup(ctrl.getID()));
				} else {
					res = gsn.toJson(ctrl.getSNS().getAllGroups());
				}
				break;
			case USERS:              		
				res = gsn.toJson(ctrl.getSNS().getGroupUsers((ctrl.getID()))); break;
			case POSTS:  
				res = gsn.toJson(ctrl.getSNS().getGroupPosts(ctrl.getID())); break;
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
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceConrol ctrl = new ServiceConrol(request, response, emf);
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String data = br.readLine();
		
		try {
			data = gsn.toJson(ctrl.getSNS().createGroup((data)));
		} catch (Exception e) {
			data = ctrl.setError(e, response);
		}
		ctrl.sendResults(ctrl.getOut(),data);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceConrol ctrl = new ServiceConrol(request, response, emf);		
		String res = null;

		try {
			
			int usrID = ctrl.getRelated_id();
			int grpID = ctrl.getID();
			ctrl.getSNS().addUserToGroup(usrID, grpID);
			res = "success";

		} catch (Exception e) {
			res = ctrl.setError(e, response);	
		} finally {
			ctrl.sendResults(ctrl.getOut(),res);		    			
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServiceConrol ctrl = new ServiceConrol(request, response, emf);
		String res = null;

		try {
			switch (ctrl.getDefinedService()) {
			case UNRELATED:
				ctrl.getSNS().deleteGroup((ctrl.getID())); break;
			case RELATED_ID:              		
				int usrID = ctrl.getRelated_id();
				int grpID = ctrl.getID();
				ctrl.getSNS().removeUserFromGroup(usrID, grpID);
				break;
			default: break;                       
			}
			res = "success";
		} catch (Exception e) {
			res = ctrl.setError(e, response);
		} finally {
			ctrl.sendResults(ctrl.getOut(),res);
		}
	
	}

}
