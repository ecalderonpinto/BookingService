package com.reportingtool.scheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.dao.loader.LoadFileDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.reportingtool.normalizer.Format;
import com.reportingtool.normalizer.Translate;
import com.reportingtool.utilities.ReportingErrorManager;

@Component("loaderJob")
public class FileLoaderJob {

	@Autowired
	private ApplicationContext applicationContext;
	private String inputDirectory = "C:/Temp/input";
	private String outputDirectory = "C:/Temp/done";

	private static final Logger logger = LoggerFactory
			.getLogger(FileLoaderJob.class);

	public void run() {

		LoadFile loadFile = new LoadFile();

		logger.info("Starting FileLoaderJob");

		try {
			File input = new File(inputDirectory);

			// Load FileConfigs;
			FileConfigDAO fileConfigDAO = (FileConfigDAO) this.applicationContext
					.getBean("fileConfigDAO");
			List<FileConfig> fileConfigs = fileConfigDAO.findAll();

			// Search files in inputDirectory;
			File[] files = input.listFiles();
			for (File f : files) {
				// For each file create a LoadFile running a FileLoader;
				FileLoader fl = new FileLoader(fileConfigs.get(0), f);
				loadFile = fl.run(applicationContext);
				if (loadFile != null) {
					logger.info(f.getName() + " is goint to be loader");
				} else {
					ReportingErrorManager
							.createLoadError(applicationContext,
									ErrorTypeEnum.LOADER.getErrorType(),
									loadFile, "PARSING",
									"Error parsing " + f.getName() + " file");
				}

				// Save LoadFile in DataBase;
				LoadFileDAO loadFileDAO = (LoadFileDAO) this.applicationContext
						.getBean("loadFileDAO");
				loadFileDAO.create(loadFile);

				// Rename and delete file;
				if (!f.renameTo(new File(outputDirectory + "/" + f.getName()
						+ ".done")))
					ReportingErrorManager.createLoadError(applicationContext,
							ErrorTypeEnum.LOADER.getErrorType(), loadFile,
							"FILE MANAGE", "Manage error file " + f.getName()
									+ ", it can not be rename to DONE status");
				f.deleteOnExit();

				// Validate Proccess;
				for (LoadRaw loadRaw : loadFile.getLoadRaws()) {
					for (LoadRawData loadRawData : loadRaw.getLoadRawDatas()) {
						Translate translate = new Translate(applicationContext);
						translate.translateRaw(loadRawData);

						Format format = new Format(applicationContext);
						format.formatRaw(loadRawData);
					}
				}
			}

		} catch (SerialException e) {
			logger.error("Error processing BLOB data: " + e.getMessage());
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile,
					"BLOB PROCESS",
					"Error processing BLOB data: " + e.getMessage());
		} catch (SQLException e) {
			logger.error("SQL Exception: " + e.getMessage());
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile, "SQL",
					"SQL Exception: " + e.getMessage());
		} catch (FileNotFoundException e) {
			logger.error("File not found [" + e.getMessage() + "]");
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile, "FILE",
					"File not found [" + e.getMessage() + "]");
		} catch (IOException e) {
			logger.error("Input/Output error: " + e.getMessage());
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile, "IO Error",
					"Input/Output error: " + e.getMessage());
		} catch (Exception e) {
			logger.error("General load error: " + e.getMessage());
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile,
					"LOAD ERROR", "General load error: " + e.getMessage());
		}
	}

}
