package com.social.web.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactorySingleton {
	
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("SocialServer");
    
    public static EntityManagerFactory getInstance(){
        return emf;
    }

	private EntityFactorySingleton(){}
		
}
