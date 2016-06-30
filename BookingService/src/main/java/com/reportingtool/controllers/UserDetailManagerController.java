package com.reportingtool.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.usermanager.UserControlDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.dictionary.RoleEnum;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.usermanager.Role;
import com.entities.entity.usermanager.User;
import com.entities.entity.usermanager.UserControl;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.UserDetailForm;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;

@Controller
@SessionAttributes("userdetailmanager")
public class UserDetailManagerController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailManagerController.class);

	@RequestMapping(value = "/admin/userDetailManager.do", method = RequestMethod.GET)
	public String userDetailManagerControllerinit(
			@RequestParam("id") String id, Model model) {

		logger.info("User Detail Controller - preForm id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// user to edit is different to user logged
			User userDetail = null;
			boolean isNew = false;

			// if id=0 new User, else we edit User
			if (id.equals("0")) {
				isNew = true;

				userDetail = new User();
				userDetail.setEnabled(false);

				UserRolDAO userRolDAO = (UserRolDAO) applicationContext
						.getBean("userRolDAO");
				Role userRol = userRolDAO
						.findById(RoleEnum.ROLE_USER.getName());
				List<Role> userRoles = new ArrayList<Role>();
				userRoles.add(userRol);
				userDetail.setRoles(userRoles);
			} else {
				UserDAO userDAO = (UserDAO) applicationContext
						.getBean("userDAO");
				userDetail = userDAO.findById(id);
			}

			// list of Company by populateCompaniesSelect()

			// list of UserRol - NO ROLE AVAILABLE
			// UserRolDAO userRolDAO = (UserRolDAO) applicationContext
			// .getBean("userRolDAO");
			// List<Role> userRolList = userRolDAO.findAll();
			// List<String> userRolDrop = new ArrayList<String>();
			// for (Role userRol : userRolList) {
			// userRolDrop.add(userRol.getRolName());
			// }

			// enabled: true/false
			List<String> userEnabledDrop = new ArrayList<String>();
			userEnabledDrop.add("true");
			userEnabledDrop.add("false");

			// User form
			UserDetailForm userDetailForm = new UserDetailForm();
			userDetailForm.setUser(userDetail);
			// userDetailForm.setSelectUserRol(user.getUserRol().getRolName());
			userDetailForm.setSelectEnabled(Boolean.toString(userDetail
					.isEnabled()));
			userDetailForm.setUserId(userDetail.getUsername());
			userDetailForm.setIsNew(isNew);

			// model.addAttribute("userRolDrop", userRolDrop);
			model.addAttribute("userEnabledDrop", userEnabledDrop);
			model.addAttribute("userdetailmanager", userDetailForm);

			return "userdetailmanager";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/admin/userDetailManager.do", method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("userdetailmanager") UserDetailForm userDetailForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		logger.info("User Detail Controller - save "
				+ userDetailForm.getUserId());

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			AlertToView alert = new AlertToView(false, "");

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			User userDetail = null;

			// CompanyDAO
			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");

			try {

				if (userDetailForm.getIsNew()) {
					// NEW
					userDetail = new User();
					userDetail.setUsername(userDetailForm.getUser()
							.getUsername());
					userDetail.setFirstName(userDetailForm.getUser()
							.getFirstName());
					userDetail.setLastName(userDetailForm.getUser()
							.getLastName());
					userDetail.setUserMail(userDetailForm.getUser()
							.getUserMail());

					// Company
					Company company = null;
					if (!userDetailForm.getSelectCompany().equals(
							ReportUtilities.emptySelect)) {
						company = companyDAO.findById(Long
								.parseLong(userDetailForm.getSelectCompany()));
					}
					userDetail.setCompany(company);

					// Role
					UserRolDAO userRolDAO = (UserRolDAO) applicationContext
							.getBean("userRolDAO");
					Role userRol = userRolDAO.findById(RoleEnum.ROLE_USER
							.getName());
					List<Role> userRoles = new ArrayList<Role>();
					userRoles.add(userRol);
					userDetail.setRoles(userRoles);

					// Enabled
					userDetail.setEnabled(userDetailForm.getUser().isEnabled());

					// password
					String password = userDetailForm.getUser().getPassword();
					// BCryptPasswordEncoder bCrypt = new
					// BCryptPasswordEncoder();
					Md5PasswordEncoder md5 = new Md5PasswordEncoder();
					if (password != null && !password.isEmpty()) {
						// password = bCrypt.encode(password);
						password = md5.encodePassword(password, null);
						userDetail.setPassword(password);

						userDAO.create(userDetail);
						alert.setMessage("User created.");

						// AUDIT
						ReportUtilities.createUserControl(applicationContext,
								user, UserControlTypeEnum.USERNEW.getDesc()
										+ userDetail.getUsername(), false,
								UserControlTypeEnum.USERNEW.getType());
					} else {
						logger.error("An empty password entered, user not created.");
					}
				} else {
					// EDIT

					logger.info("EDIT: " + userDetailForm.getUserId()
							+ " user "
							+ userDetailForm.getUser().getFirstName() + " "
							+ userDetailForm.getUser().getLastName());

					userDetail = userDAO.findById(userDetailForm.getUserId());
					userDetail.setFirstName(userDetailForm.getUser()
							.getFirstName());
					userDetail.setLastName(userDetailForm.getUser()
							.getLastName());
					userDetail.setUserMail(userDetailForm.getUser()
							.getUserMail());

					// Company
					Company company = null;
					if (!userDetailForm.getSelectCompany().equals(
							ReportUtilities.emptySelect)) {
						company = companyDAO.findById(Long
								.parseLong(userDetailForm.getSelectCompany()));
					}
					userDetail.setCompany(company);

					// UserRol is already in userDetail, always ROLE_USER

					// Enabled
					userDetail.setEnabled(userDetailForm.getUser().isEnabled());

					// password is not available

					userDAO.edit(userDetail);
					alert.setMessage("User changes saved.");

					// AUDIT
					ReportUtilities.createUserControl(
							applicationContext,
							user,
							UserControlTypeEnum.USEREDIT.getDesc()
									+ userDetail.getUsername(), false,
							UserControlTypeEnum.USEREDIT.getType());
				}

			} catch (Exception e) {
				alert.setError(true);
				alert.setMessage("User not saved, see error message: "
						+ e.getMessage());
				logger.error("Error userDetailController: " + e.getMessage());
				e.printStackTrace();
			}

			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			// return userManager
			userDAO = (UserDAO) applicationContext.getBean("userDAO");
			List<User> users = userDAO.findAll();

			UserControlDAO userControlDAO = (UserControlDAO) applicationContext
					.getBean("userControlDAO");
			List<UserControl> userControls = userControlDAO.findAll();

			model.addAttribute("users", users);
			model.addAttribute("usercontrols", userControls);

			return "usermanager";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@ModelAttribute("userCompany")
	public Map<String, String> populateCompaniesSelect() {

		CompanyDAO companyDAO = (CompanyDAO) applicationContext
				.getBean("companyDAO");
		List<Company> companyList = companyDAO.findAll();

		Map<String, String> selectLoads = new HashMap<String, String>();
		for (Company company : companyList) {
			selectLoads.put(Long.toString(company.getId()),
					company.getCompanyName());
		}

		return selectLoads;
	}

}
