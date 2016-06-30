package com.entities.dao.usermanager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.entities.dao.GenericDAO;
import com.entities.entity.usermanager.Role;

public class UserRolDAO extends GenericDAO<Role> {

	private SessionFactory sessionFactory;
	private HibernateTemplate template;

	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public UserRolDAO() {
		super(Role.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}

	@Override
	@Transactional
	public void deleteAll() {
		String sql = "delete from T_ROLE ";

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			SQLQuery query = session.createSQLQuery(sql);
			query.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void insertUserRoles(List<String> userRoles) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			for (String sql : userRoles) {
				SQLQuery query = session.createSQLQuery(sql);
				query.executeUpdate();
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
