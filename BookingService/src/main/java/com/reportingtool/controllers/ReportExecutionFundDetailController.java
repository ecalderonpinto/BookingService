package com.reportingtool.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.reportingtool.ReportCatalogDAO;
import com.entities.dao.reportingtool.ReportExecutionDAO;
import com.entities.dictionary.ReportCatalogLevelEnum;
import com.entities.dictionary.ReportExecutionStatusEnum;
import com.entities.dictionary.UserControlTypeEnum;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.Fund;
import com.entities.entity.reportingtool.ReportCatalog;
import com.entities.entity.reportingtool.ReportExecution;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.service.UserService;
import com.reportingtool.utilities.ReportUtilities;

@Controller
@RequestMapping(value = "/reportExecutionFundDetail.do")
@SessionAttributes("reportExecution")
public class ReportExecutionFundDetailController {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	UserService userDetailsService;

	private static final Logger logger = LoggerFactory
			.getLogger(ReportExecutionFundDetailController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String ReportExecutionFundDetailControllerPre(
			@RequestParam("id") String id, Model model) {

		logger.info("ReportExecutionFundDetailControllerPre id=" + id);

		try {

			User user = userDetailsService.getCurrentUser();
			model.addAttribute("user", user);

			FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
			Fund fund = fundDAO.findById(Long.parseLong(id));

			Company company = fund.getCompany();

			ReportCatalog reportCatalog = new ReportCatalog();
			reportCatalog.setReportLevel(ReportCatalogLevelEnum.FUND.getReportLevel());
			ReportCatalogDAO reportCatalogDAO = (ReportCatalogDAO) applicationContext
					.getBean("reportCatalogDAO");
			reportCatalog = reportCatalogDAO.findByExample(reportCatalog)
					.get(0);

			ReportExecution reportExecution = new ReportExecution();
			reportExecution.setReportCatalog(reportCatalog);
			reportExecution.setCompany(company);
			reportExecution.setFund(fund);
			reportExecution.setReportStatus(ReportExecutionStatusEnum.EMPTY
					.getReportExecutionStatus());
			reportExecution.setVersionAuditor(new VersionAuditor("report"));

			logger.info("new report: " + company.getCompanyName() + " "
					+ reportCatalog.getReportCatalogName() + " - "
					+ fund.getFundName());

			model.addAttribute("company", company);
			model.addAttribute("fund", fund);
			model.addAttribute("catalog", reportCatalog);
			model.addAttribute("reportExecution", reportExecution);

			// AUDIT
			ReportUtilities.createUserControl(
					applicationContext,
					user,
					UserControlTypeEnum.NEWREPORT.getDesc()
							+ reportExecution.getId(), false,
					UserControlTypeEnum.NEWREPORT.getType());

			// model.addAttribute("result", "");

			return "reportexecutiondetail";

		} catch (Exception e) {
			AlertToView alert = new AlertToView(true, e.getMessage());
			model.addAttribute("alerts", true);
			model.addAttribute("alert", alert);
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(
			@ModelAttribute("reportExecution") ReportExecution reportExecution,
			BindingResult result, Model model, SessionStatus status,
			HttpSession session) {

		logger.info("SUBMIT reportExecution: "
				+ reportExecution.getReportExecutionName());

		User user = userDetailsService.getCurrentUser();
		model.addAttribute("user", user);

		String resultMessage;

		if (reportExecution.getReportExecutionName() != null
				&& !reportExecution.getReportExecutionName().isEmpty()
				&& reportExecution.getReportPeriodType() != null
				&& !reportExecution.getReportPeriodType().isEmpty()
				&& reportExecution.getReportPeriodYear() != null
				&& !reportExecution.getReportPeriodYear().isEmpty()) {

			ReportExecutionDAO reportExecutionDAO = (ReportExecutionDAO) this.applicationContext
					.getBean("reportExecutionDAO");
			reportExecutionDAO.create(reportExecution);

			ReportUtilities.generateDefaultReportDatas(applicationContext,
					reportExecution, ReportUtilities.reportVersion,
					reportExecution.getReportPeriodYear());

			resultMessage = "Report Execution Fund created";

		} else {
			resultMessage = "Report Execution field is missing";
		}

		logger.info("result: " + resultMessage);

		// refresh Company to display after new reportExecution
		FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
		Fund fund = fundDAO.findById(reportExecution.getFund().getId());
		model.addAttribute("fund", fund);

		return "fundreports";
	}
}
