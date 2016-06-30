package com.reportingtool.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import com.reportingtool.controllers.forms.UserDetailForm;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;

@Controller
public class UserDetailController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserDetailController.class);

	@RequestMapping(value = "/userDetail.do", method = RequestMethod.GET)
	public String userDetailController(Locale locale, Model model) {

		logger.info("User Detail Controller");

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			// user to edit is user logged

			// User form
			UserDetailForm userDetailForm = new UserDetailForm();
			userDetailForm.setUser(user);
			userDetailForm.setUserId(user.getUsername());

			model.addAttribute("userdetail", userDetailForm);

			return "userdetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(value = "/userDetail.do", method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("userdetail") UserDetailForm userDetailForm,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		logger.info("User Detail Controller - save "
				+ userDetailForm.getUserId());

		try {

			AlertToView alert = new AlertToView(false, "");

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			User user = userDAO.findById(userDetailForm.getUserId());

			try {

				user.setFirstName(userDetailForm.getUser().getFirstName());
				user.setLastName(userDetailForm.getUser().getLastName());
				user.setUserMail(userDetailForm.getUser().getUserMail());

				logger.info("user changes: " + user.getUsername() + " "
						+ user.getFirstName() + " " + user.getLastName());

				// ONLY EDIT firstName, lastName and email
				userDAO.edit(user);

				alert.setMessage("User changes saved.");

				// AUDIT
				ReportUtilities.createUserControl(
						applicationContext,
						user,
						UserControlTypeEnum.USEREDIT.getDesc()
								+ user.getUsername(), false,
						UserControlTypeEnum.USEREDIT.getType());

			} catch (Exception e) {
				alert.setError(true);
				alert.setMessage("User not saved, see error message: "
						+ e.getMessage());
				logger.error("Error userDetailController: " + e.getMessage());
			}

			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);

			// return values
			// User form
			userDetailForm = new UserDetailForm();
			userDetailForm.setUser(user);
			userDetailForm.setUserId(user.getUsername());
			model.addAttribute("userdetail", userDetailForm);
			model.addAttribute("user", user);

			return "userdetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

}
