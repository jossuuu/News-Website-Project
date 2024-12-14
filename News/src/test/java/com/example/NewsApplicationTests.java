package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.model.Users;
import com.example.repository.UserRepository;

@SpringBootTest     //annotaion for test class
class NewsApplicationTests {
	
	@Autowired
	public UserRepository userRepository;
	
	@Disabled      //to avoid running the same test cases again and again we use this annotation 
	@Test
	void testUserRegister() {
		Users users = new Users();
		users.setUsersName("devaiah");
		users.setUserPassword("Passw0rd");
		users.setUserEmail("devaiah@gmail.com");
		users.setUserInterest("technology");
		users.setUserRole("user");
		users.setSubscriptionStatus("false");
		assertNotNull(userRepository.save(users));
	}
	
	@Disabled
	@Test
	void testUserGetById() {
		assertNotNull(userRepository.findById(1));
	}
	
	@Disabled
	@Test
	void testGetAllUsers() {
		List<Users> usersList = userRepository.findAll();
		assertEquals(5, usersList.size());
	}
	
	@Test
	void testUserPassword() {
		Users users = userRepository.findById(1000).get();
		assertTrue(users.getUserPassword().length() >= 8);
	}

}
