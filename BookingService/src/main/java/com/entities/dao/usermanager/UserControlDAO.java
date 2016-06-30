package com.entities.dao.usermanager;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.entities.dao.AbstractDAO;
import com.entities.entity.usermanager.UserControl;

public class UserControlDAO extends AbstractDAO<UserControl> {

	private SessionFactory sessionFactory;
	private HibernateTemplate template;

	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public UserControlDAO() {
		super(UserControl.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}

}
