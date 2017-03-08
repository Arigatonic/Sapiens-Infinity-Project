Feature: Registration Action
 
Scenario: Successful Registration with Valid Credentials
	Given User has been registered
	When Requesting the User
	Then User has been retrieved
 
Scenario: Successful Registration an addition to a Group
	Given Group has been registered
	When Requesting the Group
	Then Group has been retrieved
 
Scenario: Successful Addition of User to a Group
	Given Group has been registered
	And User has been registered