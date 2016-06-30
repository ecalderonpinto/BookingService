package com.entities.entity.install;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallCompanyUserDMO implements InstallAdapter {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallCompanyUserDMO.class);

	public InstallCompanyUserDMO(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void install() {

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		try {

			// DMO Company

			Company company = new Company("AMLCheck Software", "Spain", "AML",
					"", versionAdmin);

			Department department = new Department(company,
					"Anti Money Laundry ", "AML", "", "Spain",
					new VersionAuditor("admin"));

			// /////////////////////////////////////////////////////////////////
			// DAO

			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			companyDAO.create(company);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department);

			// USERS

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String userPass1 = md5.encodePassword("super", null);

			User user1 = new User("amlcheck.super", "userName", "userLastName",
					"user@company.com", userPass1);
			user1.setCompany(company);

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			userDAO.create(user1);

			// USER_ROLE
			UserRolDAO userRolDAO = (UserRolDAO) applicationContext
					.getBean("userRolDAO");

			List<String> userRoles = new ArrayList<String>();
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','amlcheck.super') ");

			userRolDAO.insertUserRoles(userRoles);

		} catch (Exception e) {
			logger.error("Error in InstallCompanyUserDMO: " + e.getMessage());
		}

	}
}
