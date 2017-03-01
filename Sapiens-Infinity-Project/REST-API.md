#REST-API#

Below is the Data manipulation available by the Project API

##Group Elements##
 *get:* <br />
 /Groups -> returns all listed Groups<br />
 /Groups/{id} -> returns Group of id<br />
 /Groups/{id}/Users -> returns Groups of Group of id<br />
 /Groups/{id}/Posts ->  returns Posts of Group of id<br />
 
 *put:*<br />
 /Groups/(params: groupName) -> creates a new Group<br />
 
 *post:*<br />
 /Groups/{gid}/{uid} -> add User to Group<br />
 
 *Delete:*<br />
 /Groups/{id} -> delete Group<br />
 /Groups/{gid}/{uid} -> remove user from group<br />

##User Elements##

 *get:* <br />
 /Users ->returns all listed Users<br />
 /Users/{id} -> returns user of id<br />
 /Users/{id}/Groups -> returns Groups of user of id<br />
 /Users/{id}/Posts ->  returns Posts of user of id<br />
 
 *Post:*<br />
 /Users/(json params: firstName, lastName) -> creates a new User<br />
 
 *Delete:*<br />
 /Users/{id} -> delete user<br />

##Content Elements##
 *Get:* <br />
 /Posts/{id} -> returns Post of id<br />
 
 *Post:*<br />
 /Posts/(params: grpID, usrID,content) -> add new Post of User to a Group
 
 *Delete:*<br />
 /Posts/{id} -> delete Post

