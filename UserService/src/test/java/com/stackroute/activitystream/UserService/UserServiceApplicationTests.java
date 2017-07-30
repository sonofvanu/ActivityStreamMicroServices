package com.stackroute.activitystream.UserService;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.UserService.dao.UserDAO;
import com.stackroute.activitystream.UserService.model.UserModel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceApplicationTests {
	@Autowired
	public UserDAO userDAO;
	public static UserModel userModel;

	@BeforeClass
	public static void ObjectCreator() {
		userModel = new UserModel();
	}

	@Test
	public void saveUser() {
		userModel.setEmail("milaga@gmail.com");
		userModel.setUserName(" Malu");
		userModel.setAddress("savam");
		userModel.setContact(1234567890);
		userModel.setFullName("im malu");
		userModel.setPassword("malu");
		System.out.println(userModel.getUserName());
		assertTrue(userDAO.saveUser(userModel));
	}
	
	
	@Test
	public void testGetUserByEmail() {
		
		//UserModel userModel = new UserModel();
		UserModel userByName = userDAO.findByEmail("second@gmail.com");
		assertNotNull(userByName);
		System.out.println("User Email ID :" + userByName.getFullName());
	}

	@Test
	public void updateUser() {
		
		//UserModel userModel = new UserModel();
		userModel = userDAO.findByEmail("milaga@gmail.com");
		System.out.println(userModel.getFullName());
		userModel.setAddress("Bangalore city");
		boolean updatingUser = userDAO.updateUser(userModel);
		assertEquals(true, updatingUser);
	}
	
	@Test
	public void testDeleteUser() {
		assertTrue(userDAO.deleteUserByEmail("milaga@gmail.com"));
	}
}
