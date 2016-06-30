package com.reportingtool.controllers;

import java.util.Locale;

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
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.usermanager.UserDAO;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.usermanager.User;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.controllers.forms.UserChangePassForm;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;

@Controller
public class UserChangePassController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserChangePassController.class);

	@RequestMapping(value = "/userChangePass.do", method = RequestMethod.GET)
	public String userChangePassController(Locale locale, Model model) {

		logger.info("User Change Pass Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// user to edit is user logged

			// User form
			UserChangePassForm userChangePassForm = new UserChangePassForm();
			userChangePassForm.setUser(user);
			userChangePassForm.setUserId(user.getUsername());

			model.addAttribute("userchangepass", userChangePassForm);

			return "userchangepass";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/userChangePass.do", method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("userchangepass") UserChangePassForm userChangePassForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		logger.info("User Detail Controller - save "
				+ userChangePassForm.getUserId());

		try {

			AlertToView alert = new AlertToView(false, "");

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			User user = userDAO.findById(userChangePassForm.getUserId());

			try {
				String newPass = userChangePassForm.getNewPass();
				String newPass2 = userChangePassForm.getNewPass2();
				String oldPass = userChangePassForm.getOldPass();

				// check empty fields
				if (newPass != null && !newPass.isEmpty() && newPass2 != null
						&& !newPass2.isEmpty() && oldPass != null
						&& !oldPass.isEmpty()) {

					// check newPass = newPass2
					if (newPass.equals(newPass2)) {

						Md5PasswordEncoder md5 = new Md5PasswordEncoder();
						// encoded version of passwords
						oldPass = md5.encodePassword(oldPass, null);
						newPass = md5.encodePassword(newPass, null);

						// check oldPassword
						if (user.getPassword().equals(oldPass)) {

							user.setPassword(newPass);

							logger.info("user changes: " + user.getUsername()
									+ " " + user.getFirstName() + " "
									+ user.getLastName());

							// ONLY EDIT password
							userDAO.edit(user);

							alert.setMessage("User change password saved.");

							// AUDIT
							ReportUtilities.createUserControl(
									applicationContext, user,
									UserControlTypeEnum.USEREDIT.getDesc()
											+ user.getUsername(), false,
									UserControlTypeEnum.USEREDIT.getType());

						} else {
							alert.setError(true);
							alert.setMessage("Old password incorrect.");
						}
					} else {
						alert.setError(true);
						alert.setMessage("New passwords does not match.");
					}
				} else {
					alert.setError(true);
					alert.setMessage("Passwords empty, please fill all.");
				}
			} catch (Exception e) {
				alert.setError(true);
				alert.setMessage("User not saved, see error message: "
						+ e.getMessage());
				logger.error("Error userChangePassController: "
						+ e.getMessage());
			}

			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			// return values
			// User form
			userChangePassForm = new UserChangePassForm();
			userChangePassForm.setUser(user);
			userChangePassForm.setUserId(user.getUsername());
			model.addAttribute("userchangepass", userChangePassForm);
			model.addAttribute("user", user);

			return "userchangepass";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}
}
