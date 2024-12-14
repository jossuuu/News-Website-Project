package com.example.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Users addUser(Users user) {
		return userRepository.save(user);
	}

	@Override
	public Users getUserById(int userId) {
		return userRepository.findById(userId).get();
	}


	@Override
	public Users updateUser(int userId, Users user) {
		Users user1 = getUserById(userId);
		user1.setUsersName(user.getUsersName());
		user1.setUserInterest(user.getUserInterest());
		return userRepository.save(user1);
	}

	@Override
	public Users loginUser(Users users) {
		return userRepository.findByUsersNameAndUserPassword(users.getUsersName(), users.getUserPassword());
	}

	@Override
	public List<Users> GetAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public String getSubscriptionStatus(int userId) {
		Users user1 = getUserById(userId);
		return user1.getSubscriptionStatus();
	}
	
	@Override
	public Users updateSubscriptionStatus(int userId, String subscriptionStatus) {
	    Users user = getUserById(userId);
	    user.setSubscriptionStatus(subscriptionStatus);
	    return userRepository.save(user);
	}
}
