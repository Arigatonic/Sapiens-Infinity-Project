package com.social.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.social.services.SocialNetworkService;

import org.springframework.context.annotation.Configuration;


@Configuration
public class JPAConfigure {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialServer");
	
	@Bean
	@Scope("prototype")
	public SocialNetworkService SocialNetworkService()
	{
		SocialNetworkService sns = new SocialNetworkService();
		sns.setSocialDao(socialDAO());
		
		return sns;
	}
	
	@Bean//(initMethod = "startTransaction", destroyMethod = "closeTransaction")
	@Scope("prototype")
	public SocialDao socialDAO()
	{
		return new SocialDao();
	}
	
//	@Bean 
//	public EntityManager entityManager(){
//		return emf.createEntityManager();
//		
//	}
	
}
