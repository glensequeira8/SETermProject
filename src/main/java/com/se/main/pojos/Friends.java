/**
 * 
 */
package com.se.main.pojos;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Glen
 *
 */

@Entity
@Table(name="friends")
public class Friends {
 
	@EmbeddedId
	private FriendsId id;

	@Column
	private String friendName;
	
	
	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Friends(FriendsId id,String friendName) {
		super();
		this.id = id;
		this.friendName=friendName;
	}

	@Override
	public String toString() {
		return "Friends [id=" + id + " friendName="+ friendName+"]";
	}

	public FriendsId getId() {
		return id;
	}

	public void setId(FriendsId id) {
		this.id = id;
	}
	
	
	
}

