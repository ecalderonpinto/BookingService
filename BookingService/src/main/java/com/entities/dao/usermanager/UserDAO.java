package com.entities.dao.usermanager;

import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.entities.dao.GenericDAO;
import com.entities.entity.usermanager.Role;
import com.entities.entity.usermanager.User;

public class UserDAO extends GenericDAO<User> {

	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);

	private SessionFactory sessionFactory;
	private HibernateTemplate template;

	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public UserDAO() {
		super(User.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}

	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session
				.createQuery("from User user where user.username=:name")
				.setParameter("name", username).uniqueResult();

		if (user == null) {
			logger.info("User not found: " + username);
		} else {
			logger.info("User found: " + username);
			Iterator<Role> roleIterator = user.getRoles().iterator();
			while (roleIterator.hasNext()) {
				Role role = roleIterator.next();
			}
		}

		return user;
	}
	
	@Override
	@Transactional
	public void deleteAll() {
		String sql1 = "delete from T_USER ";
		String sql2 = "delete from T_USER_ROLE ";

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			SQLQuery query2 = session.createSQLQuery(sql2);
			query2.executeUpdate();
			
			SQLQuery query1 = session.createSQLQuery(sql1);
			query1.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
