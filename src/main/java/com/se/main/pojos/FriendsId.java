/**
 * 
 */
package com.se.main.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Glen
 *
 */
@Embeddable
public class FriendsId implements Serializable{
	
	private String friendid;
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@Column(name="friendid")	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	
	public FriendsId() {
		
	}

		
	public String getFriendid() {
		return friendid;
	}
	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}
/*		@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendsId)) return false;
        FriendsId that = (FriendsId) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getFriendid(), that.getFriendid());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getUser());
    }*/
	
	

}
