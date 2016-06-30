package com.reportingtool.normalizer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class to format date and numbers to same patter
 * 
 * @author alberto.olivan
 *
 */
public class Format {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(Format.class);
	
	/**
	 * Constructor of Format with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Format(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to format loadRawData, can create loadErrors
	 * 
	 * @param loadRawData
	 * @return loadRawData formated
	 */
	public LoadRawData formatRaw(LoadRawData loadRawData) {

		logger.info("Format: "
				+ loadRawData.getLoadRawDataType());

		if (loadRawData.getLoadRawDataType().equals("DATE")) {
			this.dateFormat(loadRawData);
		}

		if (loadRawData.getLoadRawDataType().equals("DATETIME")) {
			this.dateTimeFormat(loadRawData);
		}

		if (loadRawData.getLoadRawDataType().equals("NUMBER")) {
			this.numberFormat(loadRawData);
		}

		return loadRawData;
	}

	/**
	 * Function to format a type Date with datePattern = "yyyy-MM-dd";
	 * 
	 * @param loadRawData
	 * @return loadRawData Date formated
	 */
	public LoadRawData dateFormat(LoadRawData loadRawData) {
		String dateText = loadRawData.getLoadRawDataText();

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		// only accept format yyyy-MM-dd
		String dateFormat = fileColum.getColumFormat();

		logger.info("FormatDate "
				+ loadRawData.getLoadRawDataText() + " format "
				+ fileColum.getColumFormat());

		String newstring = "";
		try {
			// SimpleDateFormat#parse() to parse a String in a certain pattern
			// into a Date
			Date date = new SimpleDateFormat(dateFormat).parse(dateText);
			// SimpleDateFormat#format() to format a Date into a String in a
			// certain pattern
			newstring = new SimpleDateFormat(ReportUtilities.datePattern)
					.format(date);

		} catch (Exception e) {
			logger.error("Error in format date: " +e.getMessage());
			// create loadError
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.FORMAT.getErrorType(), loadRawData
							.getLoadRaw().getLoadFile(), "FORMAT",
					"Invalid date format: " + loadRawData.getLoadRawDataText()
							+ " " + fileColum.getColumFormat());
		}

		logger.info("FormatDate Orig: " + dateText + " New "
				+ newstring);

		// if result is empty, we do not change and show error
		if (newstring.length() == 0) {
			newstring = loadRawData.getLoadRawDataText();
			// create loadError
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.FORMAT.getErrorType(), loadRawData
							.getLoadRaw().getLoadFile(), "FORMAT",
					"Invalid date format: " + loadRawData.getLoadRawDataText()
							+ " " + fileColum.getColumFormat());
		}

		// set final date
		loadRawData.setLoadRawDataText(newstring);

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}

	/**
	 * Function to format a type DateTime with dateTimePattern =
	 * "yyyy-MM-ddTHH:mm:ss";
	 * 
	 * @param loadRawData
	 * @return loadRawData DateTime formated
	 */
	public LoadRawData dateTimeFormat(LoadRawData loadRawData) {
		String dateText = loadRawData.getLoadRawDataText();

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		// only accept format yyyy-MM-ddTHH:mm:ss
		String dateFormat = fileColum.getColumFormat();
		
		logger.info("FormatDateTime "
				+ loadRawData.getLoadRawDataText() + " format "
				+ fileColum.getColumFormat());

		String newstring = "";
		try {
			// SimpleDateFormat#parse() to parse a String in a certain pattern
			// into a Date
			Date date = new SimpleDateFormat(dateFormat).parse(dateText);
			// SimpleDateFormat#format() to format a Date into a String in a
			// certain pattern
			newstring = new SimpleDateFormat(ReportUtilities.dateTimePattern)
					.format(date);
		} catch (Exception e) {
			logger.error("Error in format datetime: " +e.getMessage());
			// create loadError
			ReportingErrorManager.createLoadError(
					applicationContext,
					ErrorTypeEnum.FORMAT.getErrorType(),
					loadRawData.getLoadRaw().getLoadFile(),
					"FORMAT",
					"Invalid datetime format: "
							+ loadRawData.getLoadRawDataText() + " "
							+ fileColum.getColumFormat());
		}

		logger.info("FormatDateTime Orig: " + dateText
				+ " New" + newstring);

		// if result is empty, we do not change and show error
		if (newstring.length() == 0) {
			newstring = loadRawData.getLoadRawDataText();
			// create loadError
			ReportingErrorManager.createLoadError(
					applicationContext,
					ErrorTypeEnum.FORMAT.getErrorType(),
					loadRawData.getLoadRaw().getLoadFile(),
					"FORMAT",
					"Invalid datetime format: "
							+ loadRawData.getLoadRawDataText() + " "
							+ fileColum.getColumFormat());
		}

		// set final date
		loadRawData.setLoadRawDataText(newstring);

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}

	/**
	 * Function to format a number with patter: #.#
	 * 
	 * @param loadRawData
	 * @return loadRawData number formated
	 */
	public LoadRawData numberFormat(LoadRawData loadRawData) {
		String number = loadRawData.getLoadRawDataText();

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		// input format -> example => output result
		// ###,###.### -> 12,100.01 => 12100.01
		// ###.###,### -> 1.000,5 => 1000.5
		// 000000.000 -> 000124.010 => 124.01
		// $###.### -> $500 => 500
		// ##% -> 25% => 25
		// http://docs.oracle.com/javase/tutorial/java/data/numberformat.html

		// only accept format #.# removing spaces, characters and ',' '.' of
		// thousands

		// see example in (37) FXEURRate -> FileColum
		String numberFormat = fileColum.getColumFormat();

		try {
			int dot = numberFormat.indexOf(".");
			int comma = numberFormat.indexOf(",");
			if (dot > comma) {
				// dot before comma -> #.# || ###,###.## => remove ','
				number = number.replace(",", "");
			} else {
				// dot after comma -> #,# || ###.###,## => remove '.' change ','
				// ->
				// '.'
				number = number.replace(".", "");
				number = number.replace(",", ".");
			}

			// clean all not digit characters except '.'
			// http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html

			number = number.replaceAll("[\\D+&&[^\\.]]", "");

			logger.info("FormatNumber Orig: "
					+ loadRawData.getLoadRawDataText() + " New " + number);

		} catch (Exception e) {
			logger.error("Error when formating number: " + e.getMessage());
			// create loadError
			ReportingErrorManager.createLoadError(
					applicationContext,
					ErrorTypeEnum.FORMAT.getErrorType(),
					loadRawData.getLoadRaw().getLoadFile(),
					"FORMAT",
					"Error in number format: "
							+ loadRawData.getLoadRawDataText() + " "
							+ fileColum.getColumFormat());
		}

		// if has only characters after formatting, we do not change
		if (number.length() == 0) {
			number = loadRawData.getLoadRawDataText();
			// create loadError
			ReportingErrorManager.createLoadError(
					applicationContext,
					ErrorTypeEnum.FORMAT.getErrorType(),
					loadRawData.getLoadRaw().getLoadFile(),
					"FORMAT",
					"Error in number format: "
							+ loadRawData.getLoadRawDataText() + " "
							+ fileColum.getColumFormat());
		}

		// set the final number, only if is not empty
		loadRawData.setLoadRawDataText(number);

		LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
				.getBean("loadRawDataDAO");
		loadRawDataDAO.edit(loadRawData);

		return loadRawData;
	}
}
