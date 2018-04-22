/**
 * 
 */
package com.se.main.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.se.main.pojos.User;

/**
 * @author Glen
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, String>{

	public List<User> findAll();
	
	public User findByUserid(String userid);
}
