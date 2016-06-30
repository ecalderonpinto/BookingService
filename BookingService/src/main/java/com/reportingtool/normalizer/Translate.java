package com.reportingtool.normalizer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.LoadRawDataDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileColumList;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Class to translate values of file to internal codes
 * 
 * @author alberto.olivan
 *
 */
public class Translate {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(Translate.class);
	
	/**
	 * Constructor of Translate with an applicationContext
	 * 
	 * @param applicationContext
	 */
	public Translate(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Main function to translate raw values, can create loadErrors
	 * 
	 * @param loadRawData
	 * @return loadRawData translated
	 */
	public LoadRawData translateRaw(LoadRawData loadRawData) {

		// find config of column
		FileColum fileColum = loadRawData.getFileColum();

		List<FileColumList> fileColumLists = new ArrayList<FileColumList>(
				fileColum.getFileColumLists());

		logger.info("Translate: fileColum "
				+ fileColum.getColumName() + " "
				+ loadRawData.getLoadRawDataText());

		// a column may or may not have something to translate

		if (!fileColumLists.isEmpty()) {
			String valueOrig = loadRawData.getLoadRawDataText();
			String valueDest = "";

			boolean hasValue = false;
			// find in list the value to translate
			for (FileColumList fileColumList : fileColumLists) {
				logger.debug("Translate:  Dest "
						+ fileColumList.getFileColumListDest());
				if (fileColumList.getFileColumListOrig().equals(valueOrig)) {
					valueDest = fileColumList.getFileColumListDest();
					hasValue = true;
					break;
				}
			}
			logger.info("Translate: Orig " + valueOrig
					+ " Dest " + valueDest);

			if (!hasValue) {
				// create a message of error
				ReportingErrorManager.createLoadError(
						applicationContext,
						ErrorTypeEnum.TRANSLATE.getErrorType(),
						loadRawData.getLoadRaw().getLoadFile(),
						"VALUE",
						"Value not in translate list: "
								+ loadRawData.getLoadRawDataText());
			} else {
				// save new value
				loadRawData.setLoadRawDataText(valueDest);

				LoadRawDataDAO loadRawDataDAO = (LoadRawDataDAO) applicationContext
						.getBean("loadRawDataDAO");
				loadRawDataDAO.edit(loadRawData);
			}
		}

		return loadRawData;
	}
}
