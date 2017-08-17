package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Users;

@Repository
public class UsersDaoImpl implements UsersDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void registration(Users users) {
		Session session = sessionFactory.openSession();
		session.save(users);
		session.flush();
		session.close();
	}

	public List<Users> getAllUsers() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Users");
		@SuppressWarnings("unchecked")
		List<Users> userList = query.list();
		session.close();
		return userList;
	}

	public Users login(Users users) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Users where username=? and password=? and enabled=?");
		query.setString(0, users.getUsername());
		query.setString(1, users.getPassword());
		query.setBoolean(2, true);
		Users validUser = (Users) query.uniqueResult();
		session.close();
		return validUser;
	}

	public Users updateUser(Users validUser) {
		Session session = sessionFactory.openSession();
		session.update(validUser);
		session.flush();
		session.close();
		return validUser;
	}

	public Users getUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		Users user=(Users) session.get(Users.class, username);
		session.close();
		return user;
	}

}
