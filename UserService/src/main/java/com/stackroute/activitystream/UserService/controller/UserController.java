package com.stackroute.activitystream.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.UserService.dao.UserDAO;
import com.stackroute.activitystream.UserService.model.UserModel;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userdao;
	
	@GetMapping(value = "/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserModel> getUser(@PathVariable("email") String email) {
		System.out.println("Fetching User with name " + email);
		UserModel user = userdao.findByEmail(email);
		if (user == null) {
			System.out.println("User with name " + email + " not found");
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserModel>(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "/user/")
	public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
		System.out.println("Creating User " + userModel.getUserName());
		if (userdao.findByEmail(userModel.getEmail()) == null) {
			userdao.saveUser(userModel);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value = "/user")
	public ResponseEntity<List<UserModel>> listAllUsers() {
		List<UserModel> users = userdao.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserModel>>(HttpStatus.NO_CONTENT);} else {
			return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
		}
	}
	
	
	@PutMapping(value = "/user/{email}")
	public ResponseEntity<UserModel> updateUser(@PathVariable("email") String email, @RequestBody UserModel user) {
		System.out.println("Fetching & updating User with email " + email);
		UserModel currentuser = userdao.findByEmail(email);
		if (currentuser == null) {
			System.out.println("Unable to update. User with email " + email + " not found");
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		} else {
			currentuser.setFullName(user.getFullName());
			currentuser.setUserName(user.getUserName());
			currentuser.setContact(user.getContact());
			currentuser.setAddress(user.getAddress());
			currentuser.setPassword(user.getPassword());
			userdao.updateUser(currentuser);
			return new ResponseEntity<UserModel>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/user/{emailId}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable("emailId") String emailId) {
		System.out.println("Fetching & Deleting User with email " + emailId);
		UserModel user = userdao.findByEmail(emailId);
		System.out.println("user is......"+user);
		if (user == null) {
			System.out.println("Unable to delete. User with name " + emailId + " not found");
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}
System.out.println("going to delete....");
		userdao.deleteUserByEmail(emailId);
		return new ResponseEntity<UserModel>(HttpStatus.OK);
	}

}
