package com.entities.entity.install;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.dictionary.RoleEnum;
import com.entities.entity.usermanager.Role;
import com.entities.entity.usermanager.User;

public class InstallUser implements InstallAdapter {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallUser.class);

	public InstallUser(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void install() {

		try {

			// BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			// String userPass = bCrypt.encode("user0");
			// String adminPass = bCrypt.encode("admin0");

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			// String userPass = md5.encodePassword("user0", null);
			String adminPass = md5.encodePassword("admin0", null);

			// Usuario por defecto no habilitado
			// USER
			// User user = new User("user", "userName", "userLastName",
			// "user@company.com", userPass);

			// ADMIN
			User admin = new User("admin", "adminName", "adminLastName",
					"admin-reporting@riskms.com", adminPass);

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			// userDAO.create(user);
			userDAO.create(admin);

			// ROLE
			Role roleUser = new Role(RoleEnum.ROLE_USER.getName());
			Role roleAdmin = new Role(RoleEnum.ROLE_ADMIN.getName());

			UserRolDAO userRolDAO = (UserRolDAO) applicationContext
					.getBean("userRolDAO");
			userRolDAO.create(roleAdmin);
			userRolDAO.create(roleUser);

			// USER_ROLE
			List<String> userRoles = new ArrayList<String>();
			// userRoles
			// .add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','user') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','admin') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_ADMIN','admin') ");

			userRolDAO.insertUserRoles(userRoles);

		} catch (Exception e) {
			logger.error("Error in installUser: " + e.getMessage());
		}
	}

}
