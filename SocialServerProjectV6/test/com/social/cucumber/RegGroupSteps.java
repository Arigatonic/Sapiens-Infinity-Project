package com.social.cucumber;

import static org.junit.Assert.assertEquals;

import com.social.jpa.entities.Group;
import com.social.jpa.entities.User;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegGroupSteps {
	
//	When Requesting the Group
//	Then Group has been retrieved
	
	int grpID;
	Group grpRes;
	User usrSample = new User("Cucu", "User");

	
	Group grpSample = new Group("CucuGroup");
	@Given("^Group has been registered$")
	public void createGroup() {
		
		grpID = GlobalGlue.getService().createGroup(grpSample.getGroupName());
	}
	
	@When("^Requesting the Group$")
	public void getGroup() {		
		grpRes = GlobalGlue.getService().getGroup(grpID);	
	}
	@Then ("^Group has been retrieved$")
	public void validateGroup() {
		
		assertEquals(grpRes.getGroupName(), grpSample.getGroupName());
		
	}



}
