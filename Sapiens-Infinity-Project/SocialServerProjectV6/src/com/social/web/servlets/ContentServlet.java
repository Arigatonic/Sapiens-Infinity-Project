package com.social.web.servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.social.web.utils.EntityFactorySingleton;
import com.social.web.utils.ServiceConrol;

/********************************************
 *Get* 
 /Posts/{id} -> returns Post of id
 
 *Post*
 /Posts/(params: grpID, usrID,content) -> add new Post of User to a Group
 
 *Delete*
 /Posts/{id} -> delete Post
*******************************************/

@WebServlet({"/Posts", "/Posts/*"})
public class ContentServlet extends HttpServlet {
	private Gson gsn = new Gson();
	
	private static final EntityManagerFactory emf = 
			EntityFactorySingleton.getInstance();

	private static final long serialVersionUID = 1L;
       
    public ContentServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceConrol ctrl = new ServiceConrol(request, response, emf);
		String res = null;
		
		try {
			res = gsn.toJson(ctrl.getSNS().getPost(ctrl.getID()));

		} catch (Exception e) {
			res = ctrl.setError(e, response);	
		} finally {
			ctrl.sendResults(ctrl.getOut(),res);		    			
		}
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		ServiceConrol ctrl = new ServiceConrol(request, response, emf);
		int usrID = Integer.parseInt(request.getParameter("usrID"));
		int grpID = Integer.parseInt(request.getParameter("grpID"));
		String content = request.getParameter("content");
		
		String res = null;

		try {
			res = gsn.toJson(ctrl.getSNS().createPost(usrID, grpID, content));

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
			ctrl.getSNS().deletePost(ctrl.getID());
			res = "success";
		} catch (Exception e) {
			res = ctrl.setError(e, response);
		} finally {
			ctrl.sendResults(ctrl.getOut(),res);
		}

	}

}
