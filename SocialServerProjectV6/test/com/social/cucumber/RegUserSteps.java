package com.social.cucumber;

import static org.junit.Assert.assertEquals;

import com.social.jpa.entities.User;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegUserSteps {
	
		
	int usrID;
	User userRes;
	User usrSample = new User("Cucu", "User");

	@Given("^User has been registered$")
	public void registerUser(){
		
		usrID = GlobalGlue.getService().createUser(usrSample.getFirstName(), usrSample.getLastName());		
	}
	
	@When("^Requesting the User$")
	public void getUser(){
		userRes = GlobalGlue.getService().getUser(usrID);		
	}
	
	@Then("^User has been retrieved$")
	public void userValidation(){
		
		assertEquals(userRes.getFirstName(), usrSample.getFirstName());
		assertEquals(userRes.getLastName(), usrSample.getLastName());	
	
	}


}
