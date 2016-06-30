package com.reportingtool.scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.LoadRawDAO;
import com.entities.dictionary.ErrorTypeEnum;
import com.entities.entity.loader.FileColum;
import com.entities.entity.loader.FileConfig;
import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;
import com.entities.entity.loader.LoadRawData;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.ReportingErrorManager;

/**
 * Generate a LoadFile from FileConfig, InputStream
 * 
 * @author Esteban Calderon
 * 
 * 
 */
public class FileLoader {

	private static final Logger logger = LoggerFactory
			.getLogger(FileLoader.class);

	public FileConfig fileConfig;
	public InputStream file;
	public String fileName;
	public User loadUser;

	public FileLoader() {
	}

	public FileLoader(FileConfig fileConfig, File file)
			throws FileNotFoundException {
		this.file = new FileInputStream(file);
		this.fileConfig = fileConfig;
		this.fileName = file.getName();
	}

	public FileLoader(FileConfig fileConfig, String path)
			throws FileNotFoundException {
		this.setInputFile(path);
		this.fileConfig = fileConfig;
	}

	public FileLoader(FileConfig fileConfig, InputStream file, String fileName)
			throws FileNotFoundException {
		this.fileConfig = fileConfig;
		this.file = file;
		this.fileName = fileName;
	}

	public FileConfig getFileConfig() {
		return this.fileConfig;
	}

	public void setFileConfig(FileConfig fileConfig) {
		this.fileConfig = fileConfig;
	}

	public InputStream getInputFile() {
		return file;
	}

	public void setInputFile(FileInputStream inputFile) {
		this.file = inputFile;
	}

	public void setInputFile(String inputFile) throws FileNotFoundException {
		File file = new File(inputFile);
		this.file = new FileInputStream(file);
		this.fileName = file.getName();
	}

	public User getLoadUser() {
		return this.loadUser;
	}

	public void setLoadUser(User user) {
		this.loadUser = user;
	}

	public LoadFile run(ApplicationContext applicationContext)
			throws FileNotFoundException, IOException, SerialException,
			SQLException {

		logger.info("Runing LoadFile");

		// Create LoadFile Object;
		LoadFile loadFile = new LoadFile(this.fileConfig.getDepartment(),
				this.fileConfig, new Date(), this.fileName,
				new ArrayList<LoadError>(), new ArrayList<LoadRaw>(),
				new VersionAuditor("admin"));

		// Get records of the load file;
		List<String> records = LoaderUtilities.loadFileInList(this.file,
				fileConfig.isHeader());

		// Process records and update LoadFile Object;
		for (int i = 0; i < records.size(); i++) {
			String record = records.get(i);
			// Create LoadRaw Object;
			LoadRaw loadRaw = new LoadRaw(loadFile, new BigDecimal(i), null,
					null, null, new ArrayList<LoadRawData>());

			loadRaw.setLoadRawBlob(record.getBytes());

			if (!existLoadRaw(loadRaw, applicationContext)) {
				logger.debug("no existe loadraw, añade nuevo");
				// Split line;
				String separator = fileConfig.getFileSeparator();
				String[] columns = record.split(separator, -1);

				logger.debug("split columns " + columns.length
						+ " vs  fileColum: "
						+ fileConfig.getFileColums().size());

				if (columns.length == fileConfig.getFileColums().size()) {
					// Create LoadRawDatas Objects;
					for (FileColum fileColum : fileConfig.getFileColums()) {
						String columText = columns[fileColum.getColumNumber()
								.intValue()];
						if (columText != null && !columText.isEmpty()) {
							LoadRawData loadRawData = new LoadRawData(
									fileColum, loadRaw, columText,
									fileColum.getColumType(),
									new VersionAuditor("admin"));

							// columBlock can be a number(1,2,3...), null or
							// 'n', this means it is necessary to process
							if (fileColum.getColumBlock() != null) {
								if (ReportUtilities.isInteger(fileColum
										.getColumBlock())) {
									loadRawData.setLoadRawDataBlock(fileColum
											.getColumBlock());
								} else {
									if (fileColum.getColumBlock().equals(
											ReportUtilities.fileColumBlockRepe)) {
										loadRawData.setLoadRawDataBlock(Integer
												.toString(i));
									}
								}
							} else {
								loadRawData.setLoadRawDataBlock(null);
							}

							loadRaw.getLoadRawDatas().add(loadRawData);
						}
					}

					// Set LoadRaw in LoadFile
					loadRaw.setVersionAuditor(new VersionAuditor("admin"));
					loadFile.getLoadRaws().add(loadRaw);
				} else {
					ReportingErrorManager.createLoadError(applicationContext,
							ErrorTypeEnum.LOADER.getErrorType(), loadFile,
							"INVALID ROW",
							"Row with different columns or separator [row " + i
									+ "] -> " + (columns.length)
									+ " diferent of "
									+ fileConfig.getFileColums().size());
				}
			} else {
				ReportingErrorManager.createLoadError(applicationContext,
						ErrorTypeEnum.LOADER.getErrorType(), loadFile,
						"DUPLICATE ROW", "Duplicate row found [row " + i + "]");
			}
		}

		if (loadFile.getLoadRaws().isEmpty()) {
			ReportingErrorManager.createLoadError(applicationContext,
					ErrorTypeEnum.LOADER.getErrorType(), loadFile,
					"FILE EMPTY",
					"No rows found in " + loadFile.getLoadFileName());
		}

		return loadFile;
	}

	private boolean existLoadRaw(LoadRaw loadRaw,
			ApplicationContext applicationContext) {

		boolean result = false;

		LoadRawDAO loadRawDAO = (LoadRawDAO) applicationContext
				.getBean("loadRawDAO");

		List<LoadRaw> loadRaws = loadRawDAO.findAll();
		for (LoadRaw lr : loadRaws) {
			if (lr.equals(loadRaw)) {
				result = true;
				break;
			}
		}

		return result;
	}
}
