package com.reportingtool.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.reportingtool.creation.GeneratorXML;

@Controller
public class DownloadController {

	@Autowired
	ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(DownloadController.class);

	@RequestMapping(value = "/downloadAIFMDHelp.do", method = RequestMethod.GET)
	protected void downloadHelp(@RequestParam("id") String id,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("download resource.xls with help");

		GeneratorXML generatorXML = new GeneratorXML(applicationContext);

		String resource = "";

		if (id.equals("AIF")) {
			resource = generatorXML.aifXLSHelp;
		} else {
			resource = generatorXML.aifmXLSHelp;
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ resource);

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(
					generatorXML.aifmXLSHelp).getFile());

			FileInputStream in = new FileInputStream(file);

			ServletOutputStream out = response.getOutputStream();

			IOUtils.copy(in, out);
			in.close();
			out.close();

			in.close();
			out.flush();
			out.close();

		} catch (Exception e) {
			logger.error("Error in Donwload: " + e.getMessage());
		}
	}

}
