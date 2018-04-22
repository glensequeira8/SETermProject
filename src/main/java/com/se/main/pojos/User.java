/**
 * 
 */
package com.se.main.pojos;

import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Glen
 *
 */

@Entity
@Table(name="user")
@Access(AccessType.FIELD)
public class User {
@Id
@Column(name="userid")
private String userid;
@Column(name="name")
private String name;
@Column(name="email")
private String email;
@Column(name="description")
private String description;
@Column(name="photo")
private String photoLink;


@OneToMany(targetEntity=Friends.class,cascade=CascadeType.ALL,fetch = FetchType.EAGER)
private List<Friends> friends;

/*@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
private Set<Post> posts;
*/
public User() {

}

public User(String userId, String name, String description, String photoLink, List<Friends> friends,String email/*, Set<Post> posts*/) {
	this.userid = userId;
	this.name = name;
	this.description = description;
	this.photoLink = photoLink;
	this.friends = friends;
	this.email=email;
	//this.posts = posts;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userId) {
	this.userid = userId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getPhotoLink() {
	return photoLink;
}

public void setPhotoLink(String photoLink) {
	this.photoLink = photoLink;
}

public List<Friends> getFriends() {
	return friends;
}

public void setFriends(List<Friends> friends) {
	this.friends = friends;
}



public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

/*public Set<Post> getPosts() {
	return posts;
}

public void setPosts(Set<Post> posts) {
	this.posts = posts;
}
*/
@Override
public String toString() {
	return "User [userid=" + userid + ", name=" + name + ", description=" + description + ", photoLink=" + photoLink
			+ ", friends=" + friends +", email=" + email +/* ", posts=" + posts +*/ "]";
}


}
