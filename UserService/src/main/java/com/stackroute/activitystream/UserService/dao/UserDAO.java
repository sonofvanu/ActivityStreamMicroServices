package com.stackroute.activitystream.UserService.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.activitystream.UserService.model.UserModel;

@Service
public interface UserDAO {
	
	public UserModel findByEmail(String email);

	public boolean saveUser(UserModel user);

	public boolean updateUser(UserModel user);

	public boolean deleteUserByEmail(String emailId);

	public List<UserModel> findAllUsers();

}
