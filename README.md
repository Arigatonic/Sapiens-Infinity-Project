Author: Lior G  

# Overview
This is a Self-Learning-Purposed server that permits users to create and access social groups and sharing their posts through REST API implemented in Apache Tomcat web-container. It's implementation is using Frameworks like: Hibernate, Spring(IOC/AOP) & JUnit. The Project is also relating fundamental Java principles like various Design Patterns, OOP and more. 

# Project References: 

Initial Class Diagram: https://drive.google.com/file/d/0B2NNxddUDrBnNk03U2kyR2JVZlk/view?usp=sharing
Initial Data Diagram: https://drive.google.com/file/d/0B2NNxddUDrBnOTNsY3YzeFFkcVk/view?usp=sharing  

#Next Objective: 
__1. Adding Mock & BDD features__ </br>
__2. Transform DAO & Service to interfaces-based__

# Latest Updates

##06.03.17
* Spring is now fully supporting JPA 
* Logger Base Aspect is now have been issued. 

##04.03.17:##
* User SOAP Web Service has been issued. Currently provide getUser(int) method. 
* SOAP is using JAXB marshalling to return information. 

##02.03.17:##
* Finished Web-Services Layer
* Adding a com.social.web.utils package to support the web-services. 
  This mainly includes Service Control to manage better the connection with the Service layer with a minimum code redundency.  
* Moving EntityFactory to the Dao Layer

##24.02.17:##
* Finished Service layer tests.
* Added 'isActive' Field to User to mark up user existence (deletion of user will mark isActive as false). 

##22.02.17:##
* changing Service layer to work with types which are not necesserly Strings
* Almost Finishing Service layer Tests 
* Adding Named-Queries 
* Managing bi-directional relationship. 



