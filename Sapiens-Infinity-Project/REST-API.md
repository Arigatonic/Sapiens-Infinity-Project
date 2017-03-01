#REST-API#

Below is the Data manipulation available by the Project API

##Group Elements##
 *get:* 
 /Groups -> returns all listed Groups
 /Groups/{id} -> returns Group of id
 /Groups/{id}/Users -> returns Groups of Group of id
 /Groups/{id}/Posts ->  returns Posts of Group of id
 
 *put:*
 /Groups/(params: groupName) -> creates a new Group
 
 *post:*
 /Groups/{gid}/{uid} -> add User to Group
 
 *Delete:*
 /Groups/{id} -> delete Group
 /Groups/{gid}/{uid} -> remove user from group

##User Elements##

 *get:* 
 /Users ->returns all listed Users
 /Users/{id} -> returns user of id
 /Users/{id}/Groups -> returns Groups of user of id
 /Users/{id}/Posts ->  returns Posts of user of id
 
 *Post:*
 /Users/(json params: firstName, lastName) -> creates a new User
 
 *Delete:*
 /Users/{id} -> delete user

##Content Elements##
 *Get:* 
 /Posts/{id} -> returns Post of id
 
 *Post:*
 /Posts/(params: grpID, usrID,content) -> add new Post of User to a Group
 
 *Delete:*
 /Posts/{id} -> delete Post

