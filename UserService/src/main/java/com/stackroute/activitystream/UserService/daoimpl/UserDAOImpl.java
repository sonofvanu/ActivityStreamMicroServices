package com.stackroute.activitystream.UserService.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.UserService.dao.UserDAO;
import com.stackroute.activitystream.UserService.model.UserCredentials;
import com.stackroute.activitystream.UserService.model.UserModel;

@Repository(value = "userDAO")
@Transactional
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public UserModel findByEmail(String email) {
		// TODO Auto-generated method stub
		UserModel userModel = new UserModel();
		userModel = (UserModel) sessionFactory.getCurrentSession().get(UserModel.class, email);
		return userModel;
	}

	@Override
	public boolean saveUser(UserModel userModel) {
		// TODO Auto-generated method stub
		try {
			UserCredentials userCredentials = new UserCredentials();
			userCredentials.setEmail(userModel.getEmail());
			userCredentials.setPassword(userModel.getPassword());
			sessionFactory.getCurrentSession().save(userModel);
			sessionFactory.getCurrentSession().save(userCredentials);
			System.out.println(userCredentials.getEmail());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUser(UserModel userModel) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(userModel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		try {
			
			sessionFactory.getCurrentSession().delete(findByEmail(emailId));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserModel> findAllUsers() {
		// TODO Auto-generated method stub
		List<UserModel> showuser = sessionFactory.getCurrentSession().createQuery("FROM UserModel").list();
		return showuser;
	}

}
