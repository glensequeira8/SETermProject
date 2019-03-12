/**
 * 
 */
package com.se.main.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Glen
 *
 */
@Entity
@Table(name="countobject")
public class CountObject {
	@Id
	@GeneratedValue
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CountObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountObject(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "CountObject [id=" + id + "]";
	}
	

}
