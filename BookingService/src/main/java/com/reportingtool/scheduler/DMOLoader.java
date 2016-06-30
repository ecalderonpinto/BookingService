package com.reportingtool.scheduler;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.entities.dao.loader.FileConfigDAO;
import com.entities.entity.install.InstallDMO_DP;
import com.entities.entity.install.InstallDMO_FR;
import com.entities.entity.loader.FileConfig;
import com.reportingtool.controllers.LoadsController;
import com.reportingtool.controllers.forms.AlertToView;
import com.reportingtool.scheduler.dmo.beans.ClientBean;
import com.reportingtool.scheduler.dmo.beans.DMOOperacion820Bean;
import com.reportingtool.scheduler.dmo.beans.DMOOperacionGeneralBean;
import com.reportingtool.scheduler.dmo.beans.PRFTransactionBean;
import com.reportingtool.utilities.ReportUtilities;
import com.reportingtool.utilities.AMLToDMOCountry;
import com.reportingtool.utilities.AMLToDMOCurrency;
import com.reportingtool.utilities.AMLToDMOTypeDoc;

/**
 * Class generate loadFiles DMO type from AML database
 * 
 * @author Alberto
 * 
 */
public class DMOLoader {

	private static final Logger logger = LoggerFactory
			.getLogger(DMOLoader.class);

	public final static String configName = "config/databaseAML.properties";

	// private final static String outputFile820 = "D:/output/output820.file";
	// private final static String outputFileGeneral =
	// "D:/output/outputGeneral.file";

	// number of months to find transactions fractioned
	private int period = 1;
	// amount to investigate transactions DMO
	private int amount = 30000;
	// months less to search
	private int monthLess = 0;

	// separator used to write output file
	private final static String separator = ";";
	
	private final static String dateFormat = "yyyy/MM/dd";
	private final static String dateFormatFull = "yyyy-MM-dd_HH:mm:ss";
	
	private final static String dateFormatDB = "yyyy-MM-dd";
	private final static String dateFormatDMO = "dd/MM/yyyy";
	
	private final static String dateFormatTime = "HH:mm:ss";
	private final static String dateFormatTimeFull = "HH:mm:ss.SSS";

	private Connection connection = null;
	private Statement stmt = null;

	// AML names of attributes, lists used in SQL
	private String attributeResident = "attribute.mandatory.residentcountry";
	private String listKYCPais = "PAISES";
	private String listKYCParaiso = "PARAISOS";
	private String attributeLegal = "attribute.mandatory.legalstructure";
	private String listKYCLegal = "ESTRUCTURALEGAL";

	// tr_types loaded by properties
	private String tr_type01 = "01";
	private String tr_type02 = "02";
	private String tr_type21 = "21";
	private String tr_type22 = "22";
	
	// typeIdentificator
	private String typeIdentificatorSpain = "";
	private String typeIdentificatorOther = "";

	// List and hashMaps to help in process AML -> DMO
	// list_kyc_values from taxHeaven
	private List<String> taxHeavens = new ArrayList<String>();
	// hashMap to convert country ISO AML (ES) to DMO (724)
	private HashMap<String, String> countryConversor = new HashMap<String, String>();
	// list of NB_PRF_CONTRACT with Client is resident in a taxHeaven country
	private List<String> contracts = new ArrayList<String>();
	// hashMap to convert currency ISO AML (EUR) to DMO (978)
	private HashMap<String, String> currencyConversor = new HashMap<String, String>();
	// hashMap to convert typeDoc [DNI, NIE...] ISO AML (001) to DMO (1)
	private HashMap<String, String> typeDocConversor = new HashMap<String, String>();

	// Constructors
	public DMOLoader() {
	}

	public DMOLoader(int period, int amount, int monthLess) {
		this.period = period;
		this.amount = amount;
		this.monthLess = monthLess;
	}

	/**
	 * Main function make all logic: 1) connect to AML database from properties
	 * 2) Search transactions and analyze them 3) Create LoadFiles directly
	 * 
	 * @param applicationContext
	 * @return AlertToView
	 */
	public AlertToView loadFiles(ApplicationContext applicationContext) {

		// return alertView to reporting controller
		AlertToView alertToView = new AlertToView();
		alertToView.setError(false);
		alertToView.setMessage("DMO process OK ");

		List<PRFTransactionBean> transactionsReported = new ArrayList<PRFTransactionBean>();

		// number of files created
		int numFiles = 0;

		InputStream input = null;

		try {

			// //////////////////////////////////////////////
			// 1) Open database connection

			// Load properties file with database connection
			Properties prop = new Properties();
			String filename = configName;
			input = DMOLoader.class.getClassLoader().getResourceAsStream(
					filename);
			if (input == null) {
				alertToView
						.setMessage("DMO database config file unable to find: "
								+ filename);
				alertToView.setError(true);
				return alertToView;
			}
			// load a properties file from class path, inside static method
			prop.load(input);
			String url = prop.getProperty("db.url");
			String username = prop.getProperty("db.username");
			String passw = prop.getProperty("db.password");
			String className = prop.getProperty("db.driverClassName");
			String fileOut = prop.getProperty("fileOut");

			// properties depending client to build SQL
			attributeResident = prop.getProperty("attributeResident");
			listKYCPais = prop.getProperty("listKYCPais");
			listKYCParaiso = prop.getProperty("listKYCParaiso");
			attributeLegal = prop.getProperty("attributeLegal");
			listKYCLegal = prop.getProperty("listKYCLegal");

			// tr_types used to build SQL
			tr_type01 = prop.getProperty("tr_type01");
			tr_type02 = prop.getProperty("tr_type02");
			tr_type21 = prop.getProperty("tr_type21");
			tr_type22 = prop.getProperty("tr_type22");
			
			// typeIdentificator
			typeIdentificatorSpain = prop.getProperty("typeIdentificator.spain");
			typeIdentificatorOther = prop.getProperty("typeIdentificator.other");

			// Establish database connection
			Class.forName(className);
			connection = DriverManager.getConnection(url, username, passw);
			stmt = connection.createStatement();

			// Load taxHeavens[] and contracts[]
			loadLists();

			// ///////////////////////////////////////////////
			// EXAMPLE
			// String query =
			// "select ID, NAME, SURNAME1 from NB_CLIENT where COUNTRY='GQ'";
			// ResultSet rs = stmt.executeQuery(query);
			// while (rs.next()) {
			// long id = rs.getLong("ID");
			// String name = rs.getString("NAME");
			// String surname = rs.getString("SURNAME1");
			// logger.info(id + "\t" + name + "\t" + surname);
			// }
			// ////////////////////////////////////////////////

			// //////////////////////////////////////////////
			// 2) Make SQL extraction

			// EXPLICACION EXTRACCION DATOS DMO
			// Hay tres formas de encontrar operaciones a reportar
			// Forma 1: cliente residente en paraiso fiscal
			// Forma 2: transaction.tr_type = 01 y pais destino paraiso
			// Forma 3: transaction.tr_type = 02 y pais origen paraiso -> NO HAY

			// Luego en cada forma de buscar, hay dos tipos de transacciones:
			// transaction.amount_euros > amount en un mes -> Declaracion
			// Positiva
			// sum(amount_euros) de transacciones similares en un period y
			// cada una no supere amount -> Fraccionamiento

			// SQLs

			// -- transferencias > 30000
			// select id, amount, amount_euros total, FK_CONTRACT, fk_client,
			// tr_type, BENEFICIARY,
			// DESTINATION_COUNTRY, EMISSION_COUNTRY, VALUE_DATE, CURRENCY
			// from NB_PRF_TRANSACTION
			// where amount_euros > 30000 and
			// VALUE_DATE between TO_DATE('2015/11/01', 'yyyy/mm/dd') and
			// TO_DATE('2015/12/31', 'yyyy/mm/dd')

			// -- fk_contract de transacciones fraccionadas
			// select sum(amount_euros) total, FK_CONTRACT, origin_cc, tr_type
			// from NB_PRF_TRANSACTION
			// where amount_euros < 30000
			// and VALUE_DATE between TO_DATE('2015/11/01', 'yyyy/mm/dd') and
			// TO_DATE('2015/12/31', 'yyyy/mm/dd')
			// group by FK_CONTRACT, origin_cc, tr_type
			// having sum(amount_euros) > 30000

			// -- fk_contract de clientes residentes en paraiso fiscal
			// select NB_PRF_CONTRACT_HOLDER.fk_contract
			// from NB_PRF_CONTRACT_HOLDER,
			// (
			// select NB_CLIENT_ATTRIBUTE.fk_client as fk_client
			// from NB_CLIENT_ATTRIBUTE, NB_LIST_KYC_VALUE, NB_ATTRIBUTE,
			// NB_LIST_KYC
			// where NB_ATTRIBUTE.name = 'attribute.mandatory.residentcountry'
			// and NB_LIST_KYC.name = 'PARAISOS'
			// and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST
			// and NB_CLIENT_ATTRIBUTE.FK_ATTRIBUTE = NB_ATTRIBUTE.id
			// and NB_CLIENT_ATTRIBUTE.FK_LISTVALUE = NB_LIST_KYC_VALUE.id
			// ) paraiso
			// where NB_PRF_CONTRACT_HOLDER.FK_CLIENT = paraiso.fk_client

			// -- lista paraisos
			// select value from NB_LIST_KYC_VALUE, NB_LIST_KYC
			// where NB_LIST_KYC_VALUE.FK_LIST = NB_LIST_KYC.id
			// and NB_LIST_KYC.name = 'PARAISOS';

			// fill initDate and endDate depending number of period
			Date dates[] = ReportUtilities
					.getDatesFromPeriod(period, monthLess);

			DateFormat df = new SimpleDateFormat(dateFormat);
			String dateInit = df.format(dates[0]);
			String dateEnd = df.format(dates[1]);

			System.out.println("Creating sql with period: " + period
					+ " from today: " + new Date() + " -> " + dateInit + " - "
					+ dateEnd);
			logger.info("Creating sql with period: " + period + " from today: "
					+ new Date() + " -> " + dateInit + " - " + dateEnd);

			String betweenDateInit = "TO_DATE('" + dateInit + "', '"
					+ dateFormat + "')";
			String betweenDateEnd = "TO_DATE('" + dateEnd + "', '" + dateFormat
					+ "')";

			// Forma1: amount_euros > :amount
			String forma1 = "select id, amount, amount_euros total, FK_CONTRACT, fk_client, tr_type, origin_cc, DESTINATION_COUNTRY, "
					+ " EMISSION_COUNTRY, VALUE_DATE, CURRENCY, BENEFICIARY, COUNTRY, COUNTY, COUNTYEX, CITY, CITYEX, LOCALITY, OFFICE "
					+ "from NB_PRF_TRANSACTION "
					+ "where  amount_euros > "
					+ amount
					+ " and "
					+ "VALUE_DATE between "
					+ betweenDateInit + " and " + betweenDateEnd;

			// Forma2 sum(amount_euros) > :amount where tr_type = 01
			String forma2 = " select sum(amount_euros) total, FK_CONTRACT, origin_cc, DESTINATION_COUNTRY "
					+ "from  NB_PRF_TRANSACTION "
					+ "where  amount_euros < "
					+ amount
					// + " and tr_type = '01' and origin_cc is not null "
					+ " and tr_type in ("
					+ tr_type01
					+ ") "
					// + "and origin_cc is not null "
					+ " and VALUE_DATE between "
					+ betweenDateInit
					+ " and "
					+ betweenDateEnd
					+ " group by FK_CONTRACT, origin_cc, DESTINATION_COUNTRY "
					+ " having sum(amount_euros) > " + amount + " ";

			// Forma3 sum(amount_euros) > :amount where tr_type = 02
			String forma3 = " select sum(amount_euros) total, FK_CONTRACT, DESTINATION_CC, EMISSION_COUNTRY "
					+ "from  NB_PRF_TRANSACTION "
					+ "where  amount_euros < "
					+ amount
					// + " and tr_type = '02' and origin_cc is not null "
					+ " and tr_type in ("
					+ tr_type02
					+ ") "
					// + " and origin_cc is not null "
					+ " and VALUE_DATE between "
					+ betweenDateInit
					+ " and  "
					+ betweenDateEnd
					+ " group by FK_CONTRACT, DESTINATION_CC, EMISSION_COUNTRY "
					+ "having sum(amount_euros) > " + amount + " ";

			// Forma1
			System.out.println("Query forma1: " + forma1);
			logger.info("Query forma1: " + forma1);
			List<PRFTransactionBean> transactionsForma1List = new ArrayList<PRFTransactionBean>(
					0);

			ResultSet rsForma1 = stmt.executeQuery(forma1);
			while (rsForma1.next()) {
				Long id = rsForma1.getLong("id");
				String amountEuros = rsForma1.getString("total");
				String amount = rsForma1.getString("amount");
				String fkContract = rsForma1.getString("FK_CONTRACT");
				String fkClient = rsForma1.getString("fk_client");
				String trType = rsForma1.getString("tr_type");
				String originCC = rsForma1.getString("origin_cc");
				String destinationCC = null;
				String destinationCountry = rsForma1
						.getString("DESTINATION_COUNTRY");
				String emissionCountry = rsForma1.getString("EMISSION_COUNTRY");
				String valueDate = rsForma1.getString("VALUE_DATE");
				String currency = rsForma1.getString("CURRENCY");
				String beneficiary = rsForma1.getString("BENEFICIARY");
				String office = rsForma1.getString("OFFICE");
				String country = rsForma1.getString("COUNTRY");
				String county = rsForma1.getString("COUNTY");
				String city = rsForma1.getString("CITY");
				String countyEx = rsForma1.getString("COUNTYEX");
				String cityEx = rsForma1.getString("CITYEX");
				String localy = rsForma1.getString("LOCALITY");

				// convert tr_type from SQL to a valid DMO
				if (tr_type01.contains(trType))
					trType = "01";
				if (tr_type02.contains(trType))
					trType = "02";
				if (tr_type21.contains(trType))
					trType = "21";
				if (tr_type22.contains(trType))
					trType = "22";
				// TODO others tr_type can be not DMO

				logger.debug(id + "\t" + amountEuros + "\t" + fkContract + "\t"
						+ trType + "\t" + originCC + "\t" + destinationCountry
						+ "\t" + emissionCountry);

				transactionsForma1List.add(new PRFTransactionBean(id, amount,
						amountEuros, fkContract, fkClient, trType, originCC,
						destinationCC, destinationCountry, emissionCountry,
						valueDate, currency, beneficiary, office, country, county, 
						city, countyEx, cityEx, localy));
			}

			// Forma2
			System.out.println("Query forma2: " + forma2);
			logger.info("Query forma2: " + forma2);
			List<PRFTransactionBean> transactionsForma2List = new ArrayList<PRFTransactionBean>();

			if (tr_type01 != null && !tr_type01.isEmpty()) {
				ResultSet rsForma2 = stmt.executeQuery(forma2);
				while (rsForma2.next()) {
					Long id = null;
					String amount = null;
					String amountEuros = rsForma2.getString("total");
					String fkContract = rsForma2.getString("FK_CONTRACT");
					String fkClient = null;
					// String trType = "01";
					String trType = null;
					String originCC = rsForma2.getString("origin_cc");
					String destinationCC = null;
					String destinationCountry = rsForma2
							.getString("DESTINATION_COUNTRY");
					String emissionCountry = null;
					String valueDate = null;
					String currency = null;
					String beneficiary = null;
					String office = null;
					String country = null;
					String county = null;
					String city = null;
					String countyEx = null;
					String cityEx = null;
					String localy = null;

					logger.debug(amountEuros + "\t" + fkContract + "\t"
							+ trType + "\t" + originCC + "\t"
							+ destinationCountry + "\t" + emissionCountry);

					transactionsForma2List.add(new PRFTransactionBean(id,
							amount, amountEuros, fkContract, fkClient, trType,
							originCC, destinationCC, destinationCountry,
							emissionCountry, valueDate, currency, beneficiary,
							office, country, county, city, countyEx, cityEx, localy));
				}
			}

			// Forma3
			System.out.println("Query forma3: " + forma3);
			logger.info("Query forma3: " + forma3);
			List<PRFTransactionBean> transactionsForma3List = new ArrayList<PRFTransactionBean>();

			if (tr_type02 != null && !tr_type02.isEmpty()) {
				ResultSet rsForma3 = stmt.executeQuery(forma3);
				while (rsForma3.next()) {
					Long id = null;
					String amount = null;
					String amountEuros = rsForma3.getString("total");
					String fkContract = rsForma3.getString("FK_CONTRACT");
					String fkClient = null;
					// String trType = "02";
					String trType = null;
					String originCC = null;
					String destinationCC = rsForma3.getString("DESTINATION_CC");
					String destinationCountry = null;
					String emissionCountry = rsForma3
							.getString("EMISSION_COUNTRY");
					String valueDate = null;
					String currency = null;
					String beneficiary = null;
					String office = null;
					String country = null;
					String county = null;
					String city = null;
					String countyEx = null;
					String cityEx = null;
					String localy = null;

					logger.debug(amountEuros + "\t" + fkContract + "\t"
							+ trType + "\t" + originCC + "\t"
							+ destinationCountry + "\t" + emissionCountry);

					transactionsForma3List.add(new PRFTransactionBean(id,
							amount, amountEuros, fkContract, fkClient, trType,
							originCC, destinationCC, destinationCountry,
							emissionCountry, valueDate, currency, beneficiary,
							office, country, county, city, countyEx, cityEx, localy));
				}
			}

			// With all transactions, find witch has clients or country
			// and fill DMOOperations beans
			List<DMOOperacion820Bean> dmoOperacion820Beans = new ArrayList<DMOOperacion820Bean>();
			List<DMOOperacionGeneralBean> dmoOperacionGeneralBeans = new ArrayList<DMOOperacionGeneralBean>();

			List<DMOOperacion820Bean> dmoOperacion820FraccBeans = new ArrayList<DMOOperacion820Bean>();
			List<DMOOperacionGeneralBean> dmoOperacionGeneralFraccBeans = new ArrayList<DMOOperacionGeneralBean>();

			// TODO DMOOperacion820Bean not used by the moment, only
			// tr_type_01_02

			// transactions > 30000
			for (PRFTransactionBean transaction : transactionsForma1List) {
//				System.out.println("transaction.getTrType() "
//						+ transaction.getTrType());
//				System.out.println("transaction.getDestinationCountry() "
//						+ transaction.getDestinationCountry());
//				System.out.println("transaction.getEmissionCountry() "
//						+ transaction.getEmissionCountry());

				// this transaction is for a resident
				if (contracts.contains(transaction.getFkContract())) {
					dmoOperacionGeneralBeans.add(transactionToOperacionGeneral(
							transaction, "4"));
					transactionsReported.add(transaction);
				}
				// this transaction is to a taxHeaven
				if (taxHeavens.contains(transaction.getDestinationCountry())
						&& (transaction.getTrType().equals("01") || transaction
								.getTrType().equals("22"))) {
					dmoOperacionGeneralBeans.add(transactionToOperacionGeneral(
							transaction, "2"));
					transactionsReported.add(transaction);
				}
				// this transaction is from a taxHeaven
				if (taxHeavens.contains(transaction.getEmissionCountry())
						&& (transaction.getTrType().equals("02") || transaction
								.getTrType().equals("21"))) {
					dmoOperacionGeneralBeans.add(transactionToOperacionGeneral(
							transaction, "3"));
					transactionsReported.add(transaction);
				}
			}

			// fractions send tr_type = 01
			for (PRFTransactionBean transaction : transactionsForma2List) {
				// this transaction is for a resident
				if (contracts.contains(transaction.getFkContract())) {
					// because this transaction has few info, load other
					List<PRFTransactionBean> transactionsComplete = loadTransactionFraction(
							transaction, betweenDateInit, betweenDateEnd);
					for (PRFTransactionBean transactionComplete : transactionsComplete) {
						dmoOperacionGeneralFraccBeans
								.add(transactionToOperacionGeneral(
										transactionComplete, "4"));
						transactionsReported.add(transactionComplete);
					}
				}
				// this transaction is to a taxHeaven
				if (taxHeavens.contains(transaction.getDestinationCountry())) {
					// because this transaction has few info, load other
					List<PRFTransactionBean> transactionsComplete = loadTransactionFraction(
							transaction, betweenDateInit, betweenDateEnd);
					for (PRFTransactionBean transactionComplete : transactionsComplete) {
						dmoOperacionGeneralFraccBeans
								.add(transactionToOperacionGeneral(
										transactionComplete, "2"));
						transactionsReported.add(transactionComplete);
					}
				}
			}

			// fractions received tr_type = 02
			for (PRFTransactionBean transaction : transactionsForma3List) {
				// this transaction is for a resident
				if (contracts.contains(transaction.getFkContract())) {
					// because this transaction has few info, load other
					List<PRFTransactionBean> transactionsComplete = loadTransactionFraction(
							transaction, betweenDateInit, betweenDateEnd);
					for (PRFTransactionBean transactionComplete : transactionsComplete) {
						dmoOperacionGeneralFraccBeans
								.add(transactionToOperacionGeneral(
										transactionComplete, "4"));
						transactionsReported.add(transactionComplete);
					}
				}
				// this transaction is from a taxHeaven
				if (taxHeavens.contains(transaction.getEmissionCountry())) {
					// because this transaction has few info, load other
					List<PRFTransactionBean> transactionsComplete = loadTransactionFraction(
							transaction, betweenDateInit, betweenDateEnd);
					for (PRFTransactionBean transactionComplete : transactionsComplete) {
						dmoOperacionGeneralFraccBeans
								.add(transactionToOperacionGeneral(
										transactionComplete, "3"));
						transactionsReported.add(transactionComplete);
					}
				}
			}

			// //////////////////////////////////////////////
			// 3) Write files
			DateFormat dfF = new SimpleDateFormat(dateFormatFull);
			String date = dfF.format(new Date());
			FileConfigDAO fileConfigDAO = (FileConfigDAO) applicationContext
					.getBean("fileConfigDAO");

			// Operacion8_20
			if (dmoOperacion820Beans.size() > 0) {
				InputStream is = writeDMOOperacion820File(dmoOperacion820Beans);

				FileConfig fileConfig = null;
				List<FileConfig> fileConfigs = fileConfigDAO.findAll();
				for (FileConfig fc : fileConfigs)
					if (fc.getFileConfigName().equals(
							InstallDMO_DP.nameOperacion820))
						fileConfig = fc;
				if (fileConfig != null) {
					LoadsController.getLoadFile(applicationContext,
							alertToView, fileConfig, is, "op820_" + period
									+ "_" + date);
					numFiles++;
				}
			}
			// Operacion8_20 Fracc
			if (dmoOperacion820FraccBeans.size() > 0) {
				InputStream is = writeDMOOperacion820File(dmoOperacion820FraccBeans);

				FileConfig fileConfig = null;
				List<FileConfig> fileConfigs = fileConfigDAO.findAll();
				for (FileConfig fc : fileConfigs)
					if (fc.getFileConfigName().equals(
							InstallDMO_FR.nameOperacion820))
						fileConfig = fc;
				if (fileConfig != null) {
					LoadsController.getLoadFile(applicationContext,
							alertToView, fileConfig, is, "op820_" + period
									+ "_" + date);
					numFiles++;
				}
			}
			// OperacionGeneral
			if (dmoOperacionGeneralBeans.size() > 0) {
				InputStream is = writeDMOOperacionGeneralFile(dmoOperacionGeneralBeans);

				FileConfig fileConfig = null;
				List<FileConfig> fileConfigs = fileConfigDAO.findAll();
				for (FileConfig fc : fileConfigs)
					if (fc.getFileConfigName().equals(
							InstallDMO_DP.nameOperacionGeneral))
						fileConfig = fc;
				if (fileConfig != null) {
					LoadsController.getLoadFile(applicationContext,
							alertToView, fileConfig, is, "opGeneral_p" + period
									+ "m" + monthLess + "_" + date);
					numFiles++;
				}
			}
			// OperacionGeneral Fracc
			if (dmoOperacionGeneralFraccBeans.size() > 0) {
				InputStream is = writeDMOOperacionGeneralFile(dmoOperacionGeneralFraccBeans);

				FileConfig fileConfig = null;
				List<FileConfig> fileConfigs = fileConfigDAO.findAll();
				for (FileConfig fc : fileConfigs)
					if (fc.getFileConfigName().equals(
							InstallDMO_FR.nameOperacionGeneral))
						fileConfig = fc;
				if (fileConfig != null) {
					LoadsController.getLoadFile(applicationContext,
							alertToView, fileConfig, is, "opGeneralFracc_p"
									+ period + "m" + monthLess + "_" + date);
					numFiles++;
				}
			}

			// //////////////////////////////////////////////
			// 4) End processing

			String fileOutCreated = "";
			// write file with transactionsReported
			if (transactionsReported.size() > 0) {
				fileOutCreated = writeTransactionsReported(
						transactionsReported, fileOut, dateInit);
				fileOutCreated = " and output file: " + fileOutCreated;
			}

			System.out.println("End DMOLoader with " + numFiles + " files and "
					+ transactionsReported.size() + " transactiones reported.");
			logger.info("End DMOLoader with " + numFiles + " files and "
					+ transactionsReported.size() + " transactiones reported.");

			// summarize of files created and return
			alertToView.setMessage(alertToView.getMessage() + " with "
					+ numFiles + " files new, " + transactionsReported.size()
					+ " transactiones reported " + fileOutCreated);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			alertToView.setError(true);
			alertToView.setMessage("DMO process failed: " + e.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return alertToView;
	}

	/**
	 * Create inputStream with a List<DMOOperacion820Bean> to make LoadFile
	 * 
	 * @param dmoOperacion820Beans
	 * @return InputStream
	 * @throws Exception
	 */
	private InputStream writeDMOOperacion820File(
			List<DMOOperacion820Bean> dmoOperacion820Beans) throws Exception {

		InputStream is = null;

		if (dmoOperacion820Beans != null && dmoOperacion820Beans.size() > 0) {
			// File file = new File(outputFile820);
			StringBuilder st = new StringBuilder();

			// if file doesnt exists, then create it
			// if (!file.exists()) {
			// file.createNewFile();
			// }

			// FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// BufferedWriter bw = new BufferedWriter(fw);

			// for (DMOOperacion820Bean line : dmoOperacion820Beans)
			// bw.write(line.getString());
			//
			// bw.close();

			for (DMOOperacion820Bean line : dmoOperacion820Beans)
				st.append(line.getString() + "\n");

			is = new ByteArrayInputStream(st.toString().getBytes());
		}

		return is;
	}

	/**
	 * Create inputStream with a List<DMOOperacionGeneralBean> to make LoadFile
	 * 
	 * @param dmoOperacionGeneralBeans
	 * @return InputStream
	 * @throws Exception
	 */
	private InputStream writeDMOOperacionGeneralFile(
			List<DMOOperacionGeneralBean> dmoOperacionGeneralBeans)
			throws Exception {

		InputStream is = null;

		if (dmoOperacionGeneralBeans != null
				&& dmoOperacionGeneralBeans.size() > 0) {
			// File file = new File(outputFileGeneral);
			StringBuilder st = new StringBuilder();

			// if file doesnt exists, then create it
			// if (!file.exists()) {
			// file.createNewFile();
			// }

			// FileWriter fw = new FileWriter(file.getAbsoluteFile());
			// BufferedWriter bw = new BufferedWriter(fw);

			// for (DMOOperacionGeneralBean line : dmoOperacionGeneralBeans)
			// bw.write(line.getString());
			//
			// bw.close();

			for (DMOOperacionGeneralBean line : dmoOperacionGeneralBeans)
				st.append(line.getString() + "\n");

			is = new ByteArrayInputStream(st.toString().getBytes());
		}

		return is;
	}

	/**
	 * Initialize values from contracts[] and taxHeavens[]
	 * 
	 * @throws SQLException
	 */
	private void loadLists() throws SQLException {

		// Lista paraisos
		String listaParaisos = "select value from NB_LIST_KYC_VALUE, NB_LIST_KYC "
				+ "where NB_LIST_KYC_VALUE.FK_LIST = NB_LIST_KYC.id and NB_LIST_KYC.name = '"
				+ listKYCParaiso + "'";
		ResultSet rs = stmt.executeQuery(listaParaisos);
		while (rs.next()) {
			String value = rs.getString("value");
			taxHeavens.add(value);
		}
		System.out.println("Paraisos: " + taxHeavens.size());
		logger.info("Paraisos: " + taxHeavens.size());

		// Lista contratos de clientes residentes en paraisos fiscales

		// String clientList =
		// " select NB_PRF_CONTRACT_HOLDER.fk_contract as fkContract "
		// + "from NB_PRF_CONTRACT_HOLDER,  "
		// + "(select NB_CLIENT_ATTRIBUTE.fk_client as fk_client "
		// +
		// "from NB_CLIENT_ATTRIBUTE, NB_LIST_KYC_VALUE, NB_ATTRIBUTE, NB_LIST_KYC "
		// + " where NB_ATTRIBUTE.name = '"
		// + attributeResident
		// + "' "
		// + " and NB_LIST_KYC.name = '"
		// + listKYCParaiso
		// + "' "
		// + " and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST "
		// + " and NB_CLIENT_ATTRIBUTE.FK_ATTRIBUTE = NB_ATTRIBUTE.id "
		// + " and NB_CLIENT_ATTRIBUTE.FK_LISTVALUE = NB_LIST_KYC_VALUE.id "
		// + ") paraiso "
		// + "where NB_PRF_CONTRACT_HOLDER.FK_CLIENT = paraiso.fk_client ";
		//
		String clientList = "select NB_PRF_CONTRACT_HOLDER.fk_contract as fkContract  "
				+ "from NB_PRF_CONTRACT_HOLDER,   "
				+ "(select NB_CLIENT_ATTRIBUTE.fk_client as fk_client  "
				+ "from NB_CLIENT_ATTRIBUTE, NB_LIST_KYC_VALUE, NB_ATTRIBUTE, NB_LIST_KYC  "
				+ " where NB_ATTRIBUTE.name = '"
				+ attributeResident
				+ "'  "
				+ " and NB_LIST_KYC.name = '"
				+ listKYCPais
				+ "'  "
				+ " and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST  "
				+ " and NB_CLIENT_ATTRIBUTE.FK_ATTRIBUTE = NB_ATTRIBUTE.id  "
				+ " and NB_CLIENT_ATTRIBUTE.FK_LISTVALUE = NB_LIST_KYC_VALUE.id "
				+ " and NB_LIST_KYC_VALUE.value in  "
				+ "  (select value from NB_LIST_KYC, NB_LIST_KYC_VALUE where NB_LIST_KYC.name = '"
				+ listKYCParaiso
				+ "'  and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST)  "
				+ " ) paraiso  "
				+ "	where NB_PRF_CONTRACT_HOLDER.FK_CLIENT = paraiso.fk_client";

		System.out.println("clientList: " + clientList);
		logger.info("clientList: " + clientList);

		rs = stmt.executeQuery(clientList);
		while (rs.next()) {
			String value = rs.getString("fkContract");
			contracts.add(value);
		}
		System.out.println("Contracts: " + contracts.size());
		logger.info("Contracts: " + contracts.size());

		// load hashMap of country
		for (AMLToDMOCountry value : AMLToDMOCountry.values()) {
			countryConversor.put(value.getAMLCountry(), value.getDMOCountry());
		}
		System.out.println("Countries: " + countryConversor.size());
		logger.info("Countries: " + countryConversor.size());

		// load hashMap of currency
		for (AMLToDMOCurrency value : AMLToDMOCurrency.values()) {
			currencyConversor.put(value.getAMLCurrency(),
					value.getDMOCurrency());
		}
		System.out.println("Currencies: " + currencyConversor.size());
		logger.info("Currencies: " + currencyConversor.size());

		// load hashMap of currency
		for (AMLToDMOTypeDoc value : AMLToDMOTypeDoc.values()) {
			typeDocConversor.put(value.getAMLTypeDoc(), value.getDMOTypeDoc());
		}
		System.out.println("TypeDocs: " + typeDocConversor.size());
		logger.info("TypeDocs: " + typeDocConversor.size());
	}

	/**
	 * From fkContract find prf_contract.code
	 * 
	 * @param fkContract
	 * @return prf_contract.code
	 * @throws SQLException
	 */
	private String getContractCode(String fkContract) throws SQLException {
		String code = "";

		String sql = "select code from NB_PRF_CONTRACT where id = "
				+ fkContract;

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			code = rs.getString("code");
		}

		return code;
	}

	/**
	 * From a fkClient create a ClientBean
	 * 
	 * @param fkClient
	 * @return ClientBean
	 * @throws SQLException
	 */
	@Deprecated
	private ClientBean getClientBeanOLD(String fkClient) throws SQLException {
		ClientBean result = new ClientBean();

		String sql = "select nb_client.name, surname1, surname2, country, identificator, TYPE_IDENTIFICATOR, NB_LIST_KYC_VALUE.value tipo_legal "
				+ "from nb_client, nb_client_attribute, nb_attribute, NB_LIST_KYC, NB_LIST_KYC_VALUE "
				+ "where nb_client.id = "
				+ fkClient
				+ " and nb_client_attribute.FK_CLIENT =  nb_client.id "
				+ "and nb_attribute.name = '"
				+ attributeLegal
				+ "' "
				+ "and NB_LIST_KYC.NAME = '"
				+ listKYCLegal
				+ "' "
				+ "and nb_client_attribute.FK_ATTRIBUTE = nb_attribute.id "
				+ "and nb_client_attribute.FK_LISTVALUE = NB_LIST_KYC_VALUE.id "
				+ "and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST ";

		System.out.println("Client sql: " + sql);
		logger.info("Client sql: " + sql);

		// si tipo_legal = {01,02,09} -> persona fisica
		// si no, es persona juridica
		List<String> personaFisica = new ArrayList<String>();
		personaFisica.add("01");
		personaFisica.add("02");
		personaFisica.add("09");

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			result.setName(rs.getString("name"));
			result.setSurname1(rs.getString("surname1"));
			result.setSurname2(rs.getString("surname2"));
			result.setCountry(rs.getString("country"));
			result.setIdentificator(rs.getString("identificator"));
			result.setTypeIdentificator(rs.getString("TYPE_IDENTIFICATOR"));

			// return listKYCvalue.value and need to be evaluated
			if (personaFisica.contains(rs.getString("tipo_legal")))
				result.setTipoLegal(ClientBean.PERSONAFISICA);
			else
				result.setTipoLegal(ClientBean.PERSONAJURIDICA);
		}

		return result;
	}

	/**
	 * From a fkClient create a ClientBean
	 * 
	 * @param fkClient
	 * @return ClientBean
	 * @throws SQLException
	 */
	private ClientBean getClientBeanById(String fkClient) throws SQLException {
		ClientBean result = new ClientBean();

		String sql = "select nb_client.name, surname1, surname2, country, identificator, TYPE_IDENTIFICATOR, legal_type "
				+ "from nb_client where nb_client.id = " + fkClient + " ";

		System.out.println("Client by id sql: " + sql);
		logger.info("Client by id sql: " + sql);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			result.setName(rs.getString("name"));
			result.setSurname1(rs.getString("surname1"));
			result.setSurname2(rs.getString("surname2"));
			result.setCountry(rs.getString("country"));
			result.setIdentificator(rs.getString("identificator"));
			result.setTypeIdentificator(rs.getString("TYPE_IDENTIFICATOR"));
			result.setTipoLegal(rs.getString("legal_type"));
		}

		// to prevent legal_type empty
		if (result.getTipoLegal() == null || result.getTipoLegal().isEmpty())
			result.setTipoLegal(ClientBean.PERSONAFISICA);

		return result;
	}

	private ClientBean getClientBeanByContract(String fkContract)
			throws SQLException {
		ClientBean result = new ClientBean();

		String sql = "select cli.name, surname1, surname2, country, identificator, TYPE_IDENTIFICATOR, legal_type  "
				+ " from nb_client cli, nb_prf_contract_holder con  "
				+ " where cli.id = con.FK_CLIENT "
				+ " and con.FK_CONTRACT = "
				+ fkContract
				+ " and cli.id in  "
				+ "(select NB_CLIENT_ATTRIBUTE.fk_client as fk_client  "
				+ "from NB_CLIENT_ATTRIBUTE, NB_LIST_KYC_VALUE, NB_ATTRIBUTE, NB_LIST_KYC  "
				+ " where NB_ATTRIBUTE.name = '"
				+ attributeResident
				+ "'  "
				+ " and NB_LIST_KYC.name = '"
				+ listKYCPais
				+ "'  "
				+ " and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST  "
				+ " and NB_CLIENT_ATTRIBUTE.FK_ATTRIBUTE = NB_ATTRIBUTE.id  "
				+ " and NB_CLIENT_ATTRIBUTE.FK_LISTVALUE = NB_LIST_KYC_VALUE.id "
				+ " and NB_LIST_KYC_VALUE.value in  "
				+ " (select value from NB_LIST_KYC, NB_LIST_KYC_VALUE where NB_LIST_KYC.name = '"
				+ listKYCParaiso
				+ "'  and NB_LIST_KYC.id = NB_LIST_KYC_VALUE.FK_LIST)  "
				+ " )  ";

		System.out.println("Client by contract sql: " + sql);
		logger.info("Client by contract sql: " + sql);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			result.setName(rs.getString("name"));
			result.setSurname1(rs.getString("surname1"));
			result.setSurname2(rs.getString("surname2"));
			result.setCountry(rs.getString("country"));
			result.setIdentificator(rs.getString("identificator"));
			result.setTypeIdentificator(rs.getString("TYPE_IDENTIFICATOR"));
			result.setTipoLegal(rs.getString("legal_type"));
		}

		// to prevent legal_type empty
		if (result.getTipoLegal() == null || result.getTipoLegal().isEmpty())
			result.setTipoLegal(ClientBean.PERSONAFISICA);

		return result;
	}

	/**
	 * From a PRFTransactionBean fractioned (sum(amount) group by fk_contract,
	 * origin_cc), we load full info from first transaction of this group.
	 * 
	 * @param transaction
	 * @param betweenDateInit
	 * @param betweenDateEnd
	 * @return PRFTransactionBean
	 * @throws SQLException
	 */
	private List<PRFTransactionBean> loadTransactionFraction(
			PRFTransactionBean transaction, String betweenDateInit,
			String betweenDateEnd) throws SQLException {

		List<PRFTransactionBean> result = new ArrayList<PRFTransactionBean>();

		String sql = "select id, amount, amount_euros total, FK_CONTRACT, fk_client, tr_type, origin_cc, destination_cc,  "
				+ " DESTINATION_COUNTRY, EMISSION_COUNTRY, VALUE_DATE, CURRENCY, BENEFICIARY, COUNTRY, COUNTY, COUNTYEX, CITY,  " 
				+ " CITYEX, LOCALITY, OFFICE "
				+ "from NB_PRF_TRANSACTION where "
				+ " fk_contract = "
				+ transaction.getFkContract()
				+ " and amount_euros < "
				+ amount
				+ " and "
				+ "VALUE_DATE between "
				+ betweenDateInit
				+ " and "
				+ betweenDateEnd;

		if (transaction.getTrType() != null
				&& !transaction.getTrType().isEmpty()) {
			sql = sql + " and tr_type = " + transaction.getTrType();
		}
		if (transaction.getOriginCC() != null
				&& !transaction.getOriginCC().isEmpty()) {
			sql = sql + " and origin_cc = '" + transaction.getOriginCC() + "'";
		}
		if (transaction.getDestinationCC() != null
				&& !transaction.getDestinationCC().isEmpty()) {
			sql = sql + " and destination_cc = '"
					+ transaction.getDestinationCC() + "'";
		}

		System.out.println("loadTransactionFraction sql: " + sql);
		logger.info("loadTransactionFraction sql: " + sql);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Long id = rs.getLong("id");
			String amountEuros = rs.getString("total");
			String amount = rs.getString("amount");
			String fkContract = rs.getString("FK_CONTRACT");
			String fkClient = rs.getString("fk_client");
			String trType = rs.getString("tr_type");
			String originCC = rs.getString("origin_cc");
			String destinationCC = rs.getString("destination_cc");
			String destinationCountry = rs.getString("DESTINATION_COUNTRY");
			String emissionCountry = rs.getString("EMISSION_COUNTRY");
			String valueDate = rs.getString("VALUE_DATE");
			String currency = rs.getString("CURRENCY");
			String beneficiary = rs.getString("BENEFICIARY");
			String office = rs.getString("OFFICE");
			String country = rs.getString("COUNTRY");
			String county = rs.getString("COUNTY");
			String city = rs.getString("CITY");
			String countyEx = rs.getString("COUNTYEX");
			String cityEx = rs.getString("CITYEX");
			String localy = rs.getString("LOCALITY");

			// convert tr_type from SQL to a valid DMO
			if (tr_type01.contains(trType))
				trType = "01";
			if (tr_type02.contains(trType))
				trType = "02";
			if (tr_type21.contains(trType))
				trType = "21";
			if (tr_type22.contains(trType))
				trType = "22";

			result.add(new PRFTransactionBean(id, amount, amountEuros,
					fkContract, fkClient, trType, originCC, destinationCC,
					destinationCountry, emissionCountry, valueDate, currency,
					beneficiary, office, country, county, city, countyEx, cityEx, localy));
		}

		// to prevent return null, build transaction with minimum info
		if (result.size() == 0) {
			result.add(new PRFTransactionBean(0l, transaction.getAmount(),
					transaction.getAmountEuros(), transaction.getFkContract(),
					transaction.getFkClient(), transaction.getTrType(),
					transaction.getOriginCC(), transaction.getDestinationCC(),
					transaction.getDestinationCountry(), transaction
							.getEmissionCountry(), transaction.getValueDate(),
					transaction.getCurrency(), transaction.getBeneficiary(),
					transaction.getOffice(), transaction.getCountry(), 
					transaction.getCounty(), transaction.getCity(), 
					transaction.getCountyEx(), transaction.getCityEx(), 
					transaction.getLocaly()));
		}

		return result;
	}

	/**
	 * Convert a PRFTransactionBean full to a DMOOperacionGeneralBean
	 * 
	 * @param transaction
	 * @param caracteristica
	 * @return DMOOperacionGeneralBean
	 * @throws SQLException
	 * @throws ParseException 
	 */
	private DMOOperacionGeneralBean transactionToOperacionGeneral(
			PRFTransactionBean transaction, String caracteristica)
			throws SQLException, ParseException {

		DMOOperacionGeneralBean operacionBean = new DMOOperacionGeneralBean();
		operacionBean.set_0NumOperacion(transaction.getId().toString());
		operacionBean.set_1TipoOperacion(transaction.getTrType());
		operacionBean.set_2Caracteristica(caracteristica);

		// valueDate: "yyyy-MM-dd HH:mm:ss.SSS" 
		String string = transaction.getValueDate();
		String[] parts = string.split(" ");
		String fecha = parts[0];
		String hora = parts[1];
		// transform yyyy-MM-dd to dd/MM/yyyy
		fecha = ReportUtilities.formatDate(fecha, dateFormatDB, dateFormatDMO);
		operacionBean.set_3FechaOperacion(fecha);
		// tranform HH:mm:ss.SSS to HH:mm:ss
		hora = ReportUtilities.formatDate(hora, dateFormatTimeFull, dateFormatTime);
		operacionBean.set_4HoraOperacion(hora);

		operacionBean.set_5Importe(transaction.getAmount());

		if (currencyConversor.containsKey(transaction.getCurrency()))
			operacionBean.set_6Divisa(currencyConversor.get(transaction
					.getCurrency()));

		operacionBean.set_7ImporteEnEuros(transaction.getAmountEuros());
		
		//operacionBean.set_19NumeroCuenta(getContractCode(transaction
		//		.getFkContract()));
		
		// calculate DC form account number
		String numeroCuenta = getContractCode(transaction.getFkContract());
		
		logger.info("numeroCuenta antes:"+numeroCuenta);
		numeroCuenta = ReportUtilities.calculateDCFromFullAccount(numeroCuenta);
		logger.info("numeroCuenta despues:"+numeroCuenta);
		
		operacionBean.set_18Oficina(transaction.getOffice());
		operacionBean.set_19NumeroCuenta(numeroCuenta);
			
		
		// tr_type -> send
		if (transaction.getTrType().equals("01")
				|| transaction.getTrType().equals("22")) {
			
			String countryEmission = null;
			if (transaction.getEmissionCountry() != null)
				countryEmission = transaction.getEmissionCountry();
			else
				countryEmission = transaction.getCountry();
			if (countryConversor.containsKey(countryEmission))
				operacionBean.set_8PaisOrigen(countryConversor.get(countryEmission));

			// in send we report origin of transaction
			operacionBean.set_9ProvinciaOrigen(transaction.getCounty());
			operacionBean.set_10ProvinciaOrigenEx(transaction.getCountyEx());
			operacionBean.set_11MunicipioOrigen(transaction.getCity());
			operacionBean.set_12MunicipioOrigenEx(transaction.getCityEx());
			//operacionBean.set_14ProvinciaDestino(transaction.getCounty());
			//operacionBean.set_15ProvinciaDestinoEx(transaction.getCountyEx());
			//operacionBean.set_16MunicipioDestino(transaction.getCity());
			//operacionBean.set_17MunicipioDestinoEx(transaction.getCityEx());

			String countryDest = null;
			if (transaction.getDestinationCountry() != null)
				countryDest = transaction.getDestinationCountry();
			else
				countryDest = transaction.getCountry();
			if (countryConversor.containsKey(countryDest))
				operacionBean.set_13PaisDestino(countryConversor
						.get(countryDest));
			
			// <Origen>
			ClientBean client = null;
			if (transaction.getFkClient() != null
					&& !transaction.getFkClient().isEmpty())
				client = getClientBeanById(transaction.getFkClient());
			else
				client = getClientBeanByContract(transaction.getFkContract());

			if (client.getTipoLegal().equals(ClientBean.PERSONAFISICA)) {
				// PERSONAFISICA
				operacionBean.set_20CDPERSONA(client.getIdentificator());
				operacionBean.set_21Nombre(client.getName());
				operacionBean.set_22PrimerApellido(client.getSurname1());
				operacionBean.set_23SegundoApellido(client.getSurname2());

				if (countryConversor.containsKey(client.getCountry()))
					operacionBean.set_24Pais(countryConversor.get(client
							.getCountry()));
				
				operacionBean.set_25PaisExpedicion(getPaisExpedicion(client));

				if (typeDocConversor.containsKey(client.getTypeIdentificator()))
					operacionBean.set_26TipoDocIdentif(typeDocConversor
							.get(client.getTypeIdentificator()));

				operacionBean.set_27NumDocIdentif(client.getIdentificator());
				
				operacionBean.set_42Relacion(getClientRelation(transaction.getFkClient(), 
						transaction.getFkContract()));
			} else {
				// PERSONAJURIDICA
				operacionBean.set_43CDPERSONA(client.getIdentificator());
				operacionBean.set_44RazonSocial(client.getName());

				if (countryConversor.containsKey(client.getCountry()))
					operacionBean.set_45PaisNacionalidad(countryConversor
							.get(client.getCountry()));

				if (typeDocConversor.containsKey(client.getTypeIdentificator()))
					operacionBean.set_46TipoDocIdentif(typeDocConversor
							.get(client.getTypeIdentificator()));
				
				operacionBean.set_47PaisExpedicion(getPaisExpedicion(client));

				operacionBean.set_48NumDocIdentif(client.getIdentificator());
				
				operacionBean.set_63Relacion(getClientRelation(transaction.getFkClient(), 
						transaction.getFkContract()));
			}

			// <Destino>
			//operacionBean.set_64CDPERSONA(transaction.getOriginCC());
			// El origenCC incumple formato de campo CDPERSONA
			operacionBean.set_64CDPERSONA("000000000");
			operacionBean.set_65Nombre(transaction.getBeneficiary());
			operacionBean.set_66PrimerApellido(".");
			operacionBean.set_67SegundoApellido(".");

		}

		
		// tr_type -> received
		if (transaction.getTrType().equals("02")
				|| transaction.getTrType().equals("21")) {
			
			String countryDest = null;
			if (transaction.getDestinationCountry() != null)
				countryDest = transaction.getDestinationCountry();
			else
				countryDest = transaction.getCountry();
			if (countryConversor.containsKey(countryDest))
				operacionBean.set_8PaisOrigen(countryConversor
						.get(countryDest));

			// in received we report destination
			operacionBean.set_14ProvinciaDestino(transaction.getCounty());
			operacionBean.set_15ProvinciaDestinoEx(transaction.getCountyEx());
			operacionBean.set_16MunicipioDestino(transaction.getCity());
			operacionBean.set_17MunicipioDestinoEx(transaction.getCityEx());
			//operacionBean.set_9ProvinciaOrigen(transaction.getCounty());
			//operacionBean.set_10ProvinciaOrigenEx(transaction.getCountyEx());
			//operacionBean.set_11MunicipioOrigen(transaction.getCity());
			//operacionBean.set_12MunicipioOrigenEx(transaction.getCityEx());

			String countryEmission = null;
			if (transaction.getDestinationCountry() != null)
				countryEmission = transaction.getEmissionCountry();
			else
				countryEmission = transaction.getCountry();
			if (countryConversor.containsKey(countryEmission))
				operacionBean.set_13PaisDestino(countryConversor
						.get(countryEmission));

			// <Origen>
			//operacionBean.set_20CDPERSONA(transaction.getOriginCC());
			// El origenCC incumple formato de campo CDPERSONA
			operacionBean.set_20CDPERSONA("000000000");
			operacionBean.set_21Nombre(transaction.getBeneficiary());
			operacionBean.set_22PrimerApellido(".");
			operacionBean.set_23SegundoApellido(".");

			// <Destino>
			ClientBean client = null;
			if (transaction.getFkClient() != null
					&& !transaction.getFkClient().isEmpty())
				client = getClientBeanById(transaction.getFkClient());
			else
				client = getClientBeanByContract(transaction.getFkContract());

			if (client.getTipoLegal().equals(ClientBean.PERSONAFISICA)) {
				// PERSONAFISICA
				operacionBean.set_64CDPERSONA(client.getIdentificator());
				operacionBean.set_65Nombre(client.getName());
				operacionBean.set_66PrimerApellido(client.getSurname1());
				operacionBean.set_67SegundoApellido(client.getSurname2());

				if (countryConversor.containsKey(client.getCountry()))
					operacionBean.set_68Pais(countryConversor.get(client
							.getCountry()));

				if (typeDocConversor.containsKey(client.getTypeIdentificator()))
					operacionBean.set_70TipoDocIdentif(typeDocConversor
							.get(client.getTypeIdentificator()));
				
				operacionBean.set_69PaisExpedicion(getPaisExpedicion(client));

				operacionBean.set_71NumDocIdentif(client.getIdentificator());
				
				operacionBean.set_86Relacion(getClientRelation(transaction.getFkClient(), 
						transaction.getFkContract()));
			} else {
				// PERSONAJURIDICA
				operacionBean.set_87CDPERSONA(client.getIdentificator());
				operacionBean.set_88RazonSocial(client.getName());

				if (countryConversor.containsKey(client.getCountry()))
					operacionBean.set_89PaisNacionalidad(countryConversor
							.get(client.getCountry()));

				if (typeDocConversor.containsKey(client.getTypeIdentificator()))
					operacionBean.set_90TipoDocIdentif(typeDocConversor
							.get(client.getTypeIdentificator()));
				
				operacionBean.set_91PaisExpedicion(getPaisExpedicion(client));
				
				operacionBean.set_92NumDocIdentif(client.getIdentificator());
				
				operacionBean.set_107Relacion(getClientRelation(transaction.getFkClient(), 
						transaction.getFkContract()));
			}
		}

		return operacionBean;
	}
	
	private String getPaisExpedicion (ClientBean client) {
		
		String result = null;
		
		logger.info("typeIdentificatorSpain:" + typeIdentificatorSpain);
		logger.info("typeIdentificatorOther:"+typeIdentificatorOther);
		
		if (client.getTypeIdentificator() != null) {
			// if typeIdentificator is typeIdentificatorSpain, SPAIN
			if (typeIdentificatorSpain.contains(client.getTypeIdentificator())) {
				result = countryConversor.get("ES");
			}
			// if typeIdentificator is typeIdentificatorOther, Client.country
			if (typeIdentificatorOther.contains(client.getTypeIdentificator())) {
				if (countryConversor.containsKey(client.getCountry()))
					result = countryConversor.get(client.getCountry());
			}
		}
		
		return result;
	}
	
	private String getClientRelation(String idClient, String idContract) throws SQLException {
		
		String relation = null;
		boolean convert = false;
		
		if (idClient != null && !idClient.isEmpty() && idContract != null && !idContract.isEmpty()) {
		
			String sql = "select relation from NB_PRF_CONTRACT_HOLDER hold "
					+ " where fk_client = " + idClient + " and hold.fk_contract= " + idContract ;
			
			logger.info("Query relation: " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				relation = rs.getString("relation");
			}
			
			// convert relation to DMO
			//<!-- 1 - Titular -->
			//<!-- 2 - Firma autorizada -->
			//<!-- 9 - Desconocido -->
			if (relation != null) {
				// 1 - Titular
				if (!convert && (relation.equals("01") || relation.equals("1"))) {
					relation = "1";
					convert = true;
				}
				// 2 - Firma autorizada
				if (!convert && (relation.equals("02") || relation.equals("2"))) {
					relation = "2";
					convert = true;
				}
				// 9 - Desconocido
				if (!convert) {
					relation = "9";
					convert = true;
				}
			}
			
		} // if params are null
		
		return relation;
	}

	/**
	 * Write output file with all transactions reported in this DMO.
	 * update NB_PRF_TRANSACTION.REPORTED_INFO where id is used.
	 * 
	 * @param transactionsReported
	 * @param fileOut
	 */
	private String writeTransactionsReported(
			List<PRFTransactionBean> transactionsReported, String fileOut,
			String dateInit) {

		BufferedWriter writer = null;
		String fileName = "";

		// reportedInfo -> DMO#2016-01-01;
		DateFormat dateFormat = new SimpleDateFormat(
				ReportUtilities.datePattern);
		Date today = Calendar.getInstance().getTime();
		String creationDate = dateFormat.format(today);
		String reportedInfo = "DMO#"+creationDate+";";
		
		// sql to update every transaction record informing is use to report DMO
		String update = "update NB_PRF_TRANSACTION set REPORTED_INFO='" + reportedInfo + "' where id=";
		
		try {
			File folder = new File(fileOut);
			if (folder != null && folder.exists() && folder.isDirectory()) {
				
				dateInit = dateInit.replace("/", "");
				fileName = fileOut + "DMO_" + period + "_" + dateInit + ".out";

				File file = new File(fileName);
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file), "utf8"));

				// write all id from transactions reported in this DMO process
				for (PRFTransactionBean transaction : transactionsReported) {
					writer.write(transaction.getId().toString());
					writer.newLine();
					System.out.println("Report transaction id: "
							+ transaction.getId());
					logger.info("Report transaction id: " + transaction.getId());
					
					// make update against AMLCheckWeb.NB_PRF_TRANSACTION
					stmt.executeUpdate(update + transaction.getId().toString());
					logger.info(update + transaction.getId().toString());
					System.out.println(update + transaction.getId().toString());
					
				}
				System.out.println("Write output file in: " + fileName);
				logger.info("Write output file in: " + fileName);
			} else {
				logger.error("fileOut: " + fileOut + " is not a valid folder, outputFile not created.");
				System.out.println("fileOut: " + fileOut + " is not a valid folder, outputFile not created.");
				fileName = "warning output folder not valid: " + fileOut + " output file not created.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when writing output file: " + fileName
					+ " " + e.getMessage());
			logger.error("Error when writing output file: " + fileName);
			logger.error(e.getMessage());
			fileName = "Error in outputFile " + e.getMessage();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
				}
		}
		return fileName;

	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
