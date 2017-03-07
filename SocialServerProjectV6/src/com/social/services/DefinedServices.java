package com.social.services;

public enum DefinedServices {

	UNRELATED(0),USERS(1), GROUPS(2), POSTS(3), RELATED_ID(4);
	
	int requestedService;
	
	DefinedServices(int requestedService) {

		this.requestedService = requestedService;
	}
	
}
