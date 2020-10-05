package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	private static int usersCounter = 0;
	
	static {
		users.add(new User(1, "Nestor", new Date()));
		users.add(new User(2, "Juan", new Date()));
		users.add(new User(3, "Julio", new Date()));
		
		usersCounter = users.size();
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(Integer id) {
		for(User user:users) {
			if( user.getId() == id ) 
				return user;
		}
		
		return null;
	}
	
	public User save(User user) {
		if( user.getId() == null ) {
			user.setId(++usersCounter);
		}
		
		users.add(user);
		
		return user;
	}
	
	public User deleteUser(Integer id) {
		for( User user:users ) {
			if( user.getId() == id ) {
				users.remove(user);
				
				return user;
			}
		}
		
		return null;
	}
}
