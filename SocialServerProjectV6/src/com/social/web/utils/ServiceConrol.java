package com.social.web.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.Gson;
import com.social.jpa.utils.BeansConfigure;
import com.social.services.DefinedServices;
import com.social.services.SocialNetworkService;

public class ServiceConrol {

	private static final ApplicationContext ctx = new AnnotationConfigApplicationContext(BeansConfigure.class);

	private Integer classNum;
	Gson gsn = new Gson();
	private Integer id;
	PrintWriter out;	

	private List<String> pathInfoElements;

	private Integer related_id;

	DefinedServices requestedService;
	private SocialNetworkService sns;

	public ServiceConrol() throws IOException {

		initializeServiceBean();

	}

	public ServiceConrol(HttpServletRequest request, HttpServletResponse response) throws IOException {

		initializeServiceBean();

		this.pathInfoElements = getArgsList(request);
		this.id = setID(pathInfoElements);
		this.requestedService = setDefinedServices(pathInfoElements);
		this.out = response.getWriter();

	}

	public List<String> getArgs() {
		return pathInfoElements;
	}

	private List<String> getArgsList(HttpServletRequest request){

		String info = request.getPathInfo();
		if (info == null){
			return null;
		}

		String[] pathInfo = info.split("/");

		return Arrays.asList(pathInfo);  	    	    	
	}


	public Integer getClassNum() {
		return classNum;
	}

	public DefinedServices getDefinedService() {
		return requestedService;
	}

	public Integer getID() {
		return id;
	}

	public PrintWriter getOut() {
		return out;
	}


	public Integer getRelated_id() {
		return related_id;
	}

	public SocialNetworkService getSNS() {
		return sns;
	}

	private void initializeServiceBean(){

		this.sns = ctx.getBean(SocialNetworkService.class);

	}

	public void sendResults(PrintWriter out, String res){

		out.println(res);
		out.flush();
		out.close();		    			
	}

	private DefinedServices setDefinedServices(List<String> argsList){
		if(argsList == null || argsList.size() < 3){
			return DefinedServices.UNRELATED;
		}

		String reqData = argsList.get(URLPattern.RELATED_CLASS.getLayer());

		if (NumberUtils.isNumber(reqData)){

			this.related_id = Integer.parseInt(reqData);
			return DefinedServices.RELATED_ID;
		}

		return DefinedServices.valueOf(reqData.toUpperCase());
	}

	public String setError(Exception e, HttpServletResponse response){
		response.setStatus(400);
		String res = gsn.toJson(e.getMessage());
		e.printStackTrace();			

		return res;

	}

	private Integer setID(List<String> argsList){
		if(argsList == null || argsList.size() < 2){
			return null;
		}

		return Integer.parseInt(argsList.get(URLPattern.ID.getLayer()));
	}


}
