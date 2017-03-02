package com.social.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.social.web.utils.ServiceConrol;

public class UserSoap {

	private Gson gsn = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceConrol ctrl = new ServiceConrol(request, response);
		String res = null;

		try {

			res = gsn.toJson(ctrl.getSNS().getUser(ctrl.getID()));
			
			response.setContentType("application/json");  

		} catch (Exception e) {
			res = ctrl.setError(e, response);	
		}
		finally{			
			ctrl.sendResults(ctrl.getOut(),res);
		}
	}
		
}
