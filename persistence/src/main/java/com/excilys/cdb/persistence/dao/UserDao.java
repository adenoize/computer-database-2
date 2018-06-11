package com.excilys.cdb.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.core.model.QUser;
import com.excilys.cdb.core.model.User;
import com.querydsl.jpa.hibernate.HibernateQuery;

@Repository
public class UserDao {

	private SessionFactory sessionFactory;

	
	@Autowired
	public UserDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public User findUserInfoByUsername(String username) {
		try (Session session = sessionFactory.openSession()) {
			HibernateQuery<User> query = new HibernateQuery<>(session);
			return query.from(QUser.user).where(QUser.user.username.eq(username)).fetchOne();
		}

	}
}