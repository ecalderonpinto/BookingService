package com.entities.dao.loader;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.entities.dao.AbstractDAO;
import com.entities.entity.loader.FileColumList;

public class FileColumListDAO extends AbstractDAO<FileColumList> {

	private SessionFactory sessionFactory;
	private HibernateTemplate template;

	public FileColumListDAO() {
		super(FileColumList.class);
	}

	@Override
	protected HibernateTemplate getHibernateTemplate() {
		return template;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
		this.template = new HibernateTemplate(this.sessionFactory);
		this.template.setCheckWriteOperations(false);
	}
}
