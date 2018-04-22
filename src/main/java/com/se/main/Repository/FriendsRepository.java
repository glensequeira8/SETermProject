package com.se.main.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.se.main.pojos.Friends;
import com.se.main.pojos.User;

public interface FriendsRepository extends CrudRepository<Friends, String> {
	public List<Friends> findAll();
	
	//public List<Friends> findByUser(User user);
	//public List<Friends> findByUser(String userid);
	
	//public List<Friends> findById(String userid);
}
