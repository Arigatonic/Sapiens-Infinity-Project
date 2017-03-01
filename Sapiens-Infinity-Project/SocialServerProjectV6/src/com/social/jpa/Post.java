package com.social.jpa;

import java.util.Date;

import javax.persistence.*;

import com.social.references.DBRef;

@Entity
@Table(name = DBRef.POSTS_TABLE) 
public class Post {

	private int postID;    
	private String content;     			
	private Date date;    
    private Group group;    
    private User user;	
    
    protected Post(){
    }
    
    public Post(Date date, User user, Group group, String content){
    	this.date = date;
    	this.content = content;
    	this.user = user;
    	this.group = group;
    }

	@Override
	public boolean equals(Object obj) {
		
        if (this == obj) return true;
        if ( !(obj instanceof Post) ) return false;

        final Post pst = (Post) obj;
		return this.getPostID() == pst.getPostID();
	}

    @Column(name = DBRef.CONTENT, nullable = false)
	public String getContent() {
		return content;
	}

    @Column(name = DBRef.DATE, nullable = false)
	private Date getDate() {
		return date;
	}

    @ManyToOne(optional=false, targetEntity = Group.class)
    @JoinColumn(name=DBRef.GROUP_ID, referencedColumnName=DBRef.GROUP_ID)
	public Group getGroup() {
		return group;
	}

	@Id 
    @Column(name = DBRef.POST_ID, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) 
	public int getPostID() {
		return postID;
	}
	
    @ManyToOne(optional=false, targetEntity = User.class)
    @JoinColumn(name=DBRef.USER_ID, referencedColumnName=DBRef.USER_ID)
	public User getUser() {
		return user;
	}
	
	@Override
	public int hashCode() {
		return new Integer(this.getPostID()).hashCode();
	}

	private void setContent(String content) {
		this.content = content;
	}

	private void setDate(Date date) {
		this.date = date;
	}

	private void setGroup(Group group) {
		this.group = group;
	}		

	private void setPostID(int pstID) {
		this.postID = pstID;
	}
	
	private void setUser(User user) {
		this.user = user;
	}


	
}
