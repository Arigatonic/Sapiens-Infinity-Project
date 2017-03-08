package com.social.cucumber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.social.jpa.utils.BeansConfigure;
import com.social.services.SocialNetworkService;

public class GlobalGlue {

	private final static ApplicationContext ctx = new AnnotationConfigApplicationContext(BeansConfigure.class);
	private final static SocialNetworkService sd = ctx.getBean(SocialNetworkService.class);
	public final static SocialNetworkService getService() {
		return sd;
	}

	
}
