package com.social.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.social.references.DBRef;

@Entity 
@NamedQueries({
    @NamedQuery(name=Group.GET_ALL_GROUPS,
                query="SELECT g FROM Group g")
}) 
@Table(name = DBRef.GROUP_TABLE) 
public class Group {
	
	public static final String GET_ALL_GROUPS = "Group.getAllGroups";

	
	private int group_id;	
	private String groupName;	
    private Set<Post> posts;	   
	private Set<User> users;
    
	protected Group(){
    }
    
    public Group(String groupName){
    	this.groupName = groupName;
    	users = new HashSet<User>();
    	posts = new HashSet<Post>();
    }
          
    @Override
	public boolean equals(Object obj) {
		
        if (this == obj) return true;
        if ( !(obj instanceof Group) ) return false;

        final Group grp = (Group) obj;
		return this.getGroupID() == grp.getGroupID();
	}
    
	@Id 
    @Column(name = DBRef.GROUP_ID)
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public int getGroupID() {
		return group_id;
	}
	
	@Column(name = DBRef.GROUP_NAME, nullable = false)
	public String getGroupName() {
		return groupName;
	}
	
	@OneToMany(mappedBy="group", fetch=FetchType.LAZY)
	public Set<Post> getPosts() {
		return posts;
	}

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name=DBRef.GROUPS_USERS_TABLE, 
            joinColumns=
            @JoinColumn(name=DBRef.GROUP_ID, referencedColumnName=DBRef.GROUP_ID,  nullable = false, updatable = true),
            inverseJoinColumns=
            @JoinColumn(name=DBRef.USER_ID, referencedColumnName=DBRef.USER_ID, nullable = false, updatable = true))    
	public Set<User> getUsers() {
		return users;
	}
	
    @Override
	public int hashCode() {
		return new Integer(this.getGroupID()).hashCode();
	}
    
    public void addPost(Post pst){
    	posts.add(pst);    	
    }
    
    public void addUser(User usr){
    	users.add(usr);
    }
	
	public void removePost(Post pst){
    	posts.remove(pst);    	
    }

	
	public void removeUser(User usr){
    	users.remove(usr);
    }

	private void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	private void setGroupID(int grpID){
		this.group_id = grpID;
	}

	private void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	private void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public void setUsers(Set<User> usrs) {
		this.users = usrs;
	}
}
