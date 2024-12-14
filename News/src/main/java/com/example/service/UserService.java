package com.example.service;

import java.util.List;

import com.example.model.Users;

public interface UserService {
	
	public Users addUser(Users user);
	
	public Users updateUser(int userId, Users user);
	
	public Users getUserById(int userId);
	
	public Users loginUser(Users users);
	
	public List<Users> GetAllUsers();
	
	public String getSubscriptionStatus(int userId);
	
	public Users updateSubscriptionStatus(int userId, String subscriptionStatus);

}
