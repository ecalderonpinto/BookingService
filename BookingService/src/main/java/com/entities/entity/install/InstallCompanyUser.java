package com.entities.entity.install;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.entities.dao.reportingtool.CompanyDAO;
import com.entities.dao.reportingtool.DepartmentDAO;
import com.entities.dao.reportingtool.FundDAO;
import com.entities.dao.usermanager.UserDAO;
import com.entities.dao.usermanager.UserRolDAO;
import com.entities.entity.reportingtool.Company;
import com.entities.entity.reportingtool.Department;
import com.entities.entity.reportingtool.Fund;
import com.entities.entity.usermanager.User;
import com.entities.utilities.hibernate.VersionAuditor;

public class InstallCompanyUser implements InstallAdapter {

	private ApplicationContext applicationContext;

	private static final Logger logger = LoggerFactory
			.getLogger(InstallCompanyUser.class);

	public InstallCompanyUser(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public void install() {

		VersionAuditor versionAdmin = new VersionAuditor("admin");

		try {

			List<Fund> funds = new ArrayList<Fund>();

			Company company1 = new Company("NMAS1 ASSET MANAGEMENT, SGIIC,SA",
					"Spain", "NMAS1 ASSET", "", versionAdmin);
			Department department1 = new Department(company1,
					"NMAS1 ASSET MANAGEMENT, SGIIC,SA department",
					"NMAS1 ASSET", "", "Spain", new VersionAuditor("admin"));
			funds.add(new Fund(company1, "QMC II IBERIAN CAPITAL FUND, FIL",
					"ES0172224000", "ISL47", "", "", versionAdmin));

			Company company2 = new Company("ASESORES Y GESTORES", "Spain",
					"AYG", "", versionAdmin);
			Department department2 = new Department(company2,
					"ASESORES Y GESTORES department", "AYG", "", "Spain",
					new VersionAuditor("admin"));

			funds.add(new Fund(company2, "ALNAMA STOCK, SICAV, S.A.",
					"ES0163024039", "ISS918", "", "", versionAdmin));
			funds.add(new Fund(company2, "CARFY, SICAV S.A.", "ES0116399033",
					"ISS000769", "", "", versionAdmin));
			funds.add(new Fund(company2,
					"CLINVER GESTION PATRIMONIAL, SICAV, S.A.", "ES0119077032",
					"ISS2011", "", "", versionAdmin));
			funds.add(new Fund(company2, "DIANA BOAT, SICAV, S.A.",
					"ES0126401035", "ISS2181", "", "", versionAdmin));
			funds.add(new Fund(company2,
					"EUROPEA DE GESTION EUROGESA 4, SICAV", "ES0127023036",
					"ISS2802", "", "", versionAdmin));
			funds.add(new Fund(company2, "GAMAZO 20, SICAV, S.A",
					"ES0140718034", "ISS1968", "", "", versionAdmin));
			funds.add(new Fund(company2, "GLOBAL MANAGERS FUND, FI",
					"ES0131304034", "ISF3072", "", "", versionAdmin));
			funds.add(new Fund(company2, "GRAZALEMA INVERSIONES SIMCAV",
					"ES0143282038", "ISS1010", "", "", versionAdmin));
			funds.add(new Fund(company2, "LACERTA INVERSIONES, SICAV S.A.",
					"ES0157591035", "ISS327", "", "", versionAdmin));
			funds.add(new Fund(company2,
					"NIBAFER INVERSIONES 2001, SICAV, S.A.", "ES0166291031",
					"ISS1986", "", "", versionAdmin));
			funds.add(new Fund(company2, "ORMIBASA, SICAV, S.A.",
					"ES0167714031", "ISS767", "", "", versionAdmin));
			funds.add(new Fund(company2, "ORTISA SICAV", "ES0173230006",
					"ISS004277", "", "", versionAdmin));
			funds.add(new Fund(company2, "PERYGON DE INVERSIONES SICAV",
					"ES0169268036", "ISS2531", "", "", versionAdmin));
			funds.add(new Fund(company2, "ROUTING INVERSIONES, SICAV, SA",
					"ES0174209033", "ISS3480", "", "", versionAdmin));
			funds.add(new Fund(company2, "TESCO VALORES, SICAV, S.A.",
					"ES0178577039", "ISS2650", "", "", versionAdmin));
			funds.add(new Fund(company2, "VALMER DE INVERSIONES, SICAV, S.A",
					"ES0182853038", "ISS309", "", "", versionAdmin));
			funds.add(new Fund(company2, "VISCASA SICAV", "ES0184261032",
					"ISS000731", "", "", versionAdmin));
			funds.add(new Fund(company2, "SAVIR", "ES0175026030",
					"ISS002611", "", "", versionAdmin));

			Company company3 = new Company("AURIGA", "Spain", "AURIGA", "",
					versionAdmin);
			Department department3 = new Department(company3,
					"AURIGA department", "AURIGA", "", "Spain",
					new VersionAuditor("admin"));
			funds.add(new Fund(company3, "GESBOLSA INVERSIONES, SICAV S.A.",
					"ES0142162033", "ISS2410", "", "", versionAdmin));
			funds.add(new Fund(company3, "RHO INVESTMENTS, SICAV, S.A.",
					"ES0155144035", "ILS100006", "", "", versionAdmin));
			funds.add(new Fund(company3, "VALENCIANA DE VALORES, SICAV S.A.",
					"ES0182790032", "ISS2759", "", "", versionAdmin));

			Company company4 = new Company("BESTINVER GESTION", "Spain",
					"BESTINVER GESTION", "", versionAdmin);
			Department department4 = new Department(company4,
					"BESTINVER GESTION department", "BESTINVER GESTION", "",
					"Spain", new VersionAuditor("admin"));
			funds.add(new Fund(company4, "BESTVALUE, FI", "ES0114579008",
					"ISF4290", "", "", versionAdmin));

			Company company5 = new Company("BESTINVER GESTION HF", "Spain",
					"BESTINVER GESTION HF", "", versionAdmin);
			Department department5 = new Department(company5,
					"BESTINVER GESTION HF department", "BESTINVER GESTION HF",
					"", "Spain", new VersionAuditor("admin"));
			funds.add(new Fund(company5, "BESTINVER HEDGE VALUE FUND, FIL",
					"ES0114578000", "ISL15", "", "", versionAdmin));

			Company company6 = new Company(
					"BRIGHTGATE CAPITAL, S.G.I.I.C., S.A	", "Spain",
					"BRIGHTGATE", "", versionAdmin);
			Department department6 = new Department(company6,
					"BRIGHTGATE CAPITAL, S.G.I.I.C., S.A department",
					"BRIGHTGATE", "", "Spain", new VersionAuditor("admin"));
			funds.add(new Fund(company6, "BRIGHTGATE ABSOLUTE RETURN, FIL", "",
					"", "", "", versionAdmin));

			Company company7 = new Company("ESFERA", "Spain", "ESFERA", "",
					versionAdmin);
			Department department7 = new Department(company7,
					"ESFERA department", "ESFERA", "", "Spain",
					new VersionAuditor("admin"));
			funds.add(new Fund(company7, "ESFERA ENERGY SICAV, S.A",
					"ES0131474001", "ISS4106", "", "", versionAdmin));

			Company company8 = new Company("JP MORGAN", "Spain", "JP MORGAN",
					"", versionAdmin);
			Department department8 = new Department(company8,
					"JP MORGAN department", "JP MORGAN", "", "Spain",
					new VersionAuditor("admin"));
			funds.add(new Fund(company8, "AMANIEL INVERSIONES SICAV S.A",
					"ES0109138034", "ISS3385", "", "", versionAdmin));
			funds.add(new Fund(company8, "HERPRISA INVERSIONES SICAV,S.A.",
					"ES0144172006", "ISS3746", "", "", versionAdmin));
			funds.add(new Fund(company8, "KAHACHA MONEY INVEST SICAV S.A",
					"ES0109167033", "ISS3491", "", "", versionAdmin));
			funds.add(new Fund(company8, "MARILIN INVERSIONES SICAV S.A",
					"ES0160954030", "ISS3305", "", "", versionAdmin));
			funds.add(new Fund(company8, "MUTARE INVERSIONES SICAV S.A",
					"ES0124141039", "ISS2025", "", "", versionAdmin));
			funds.add(new Fund(company8, "OSMOSIS INVESTMENT SICAV",
					"ES0184985036", "ISS759", "", "", versionAdmin));
			funds.add(new Fund(company8, "RED ROCK INVEST, SICAV, S.A.",
					"ES0173092000", "ISS3760", "", "", versionAdmin));
			funds.add(new Fund(company8, "SOANDRES DE ACTIVOS SICAV S.A",
					"ES0176213033", "ISS1839", "", "", versionAdmin));

			Company company9 = new Company("MAGALLANES", "Spain", "MAGALLANES",
					"", versionAdmin);
			Department department9 = new Department(company9,
					"MAGALLANES department", "MAGALLANES", "", "Spain",
					new VersionAuditor("admin"));
			funds.add(new Fund(company9, "SOIXA SICAV", "ES0176251033",
					"ISS3041", "", "", versionAdmin));

			Company company10 = new Company("NMAS1 SYZ GESTION, SGIIC, SA",
					"Spain", "NMAS1 SYZ", "", versionAdmin);
			Department department10 = new Department(company10,
					"NMAS1 SYZ GESTION, SGIIC, SA department", "NMAS1 SYZ", "",
					"Spain", new VersionAuditor("admin"));
			funds.add(new Fund(company10, "COKEFIN 99, SICAV, S.A.",
					"ES0119306035", "ISS687", "", "", versionAdmin));
			funds.add(new Fund(company10, "DEBARAN DE VALORES MOBILIARIOS",
					"ES0153535036", "ISS799", "", "", versionAdmin));
			funds.add(new Fund(company10, "INVERSIONES DEVA", "ES0155931035",
					"ISS000530", "", "", versionAdmin));
			funds.add(new Fund(company10, "KAIMAR INVERSIONES, SICAV, S.A.",
					"ES0166774036", "ISS3285", "", "", versionAdmin));

			Company company11 = new Company("RENTA 4(159)", "Spain", "RENTA 4",
					"", versionAdmin);
			Department department11 = new Department(company11,
					"RENTA 4(159) department", "RENTA 4", "", "Spain",
					new VersionAuditor("admin"));
			funds.add(new Fund(company11, "POSTERA DE INVERSIONES SICAV",
					"ES0170562039", "ISS2753", "", "", versionAdmin));

			// // Santander Asset Manager
			//
			// Company company1 = new Company("Santander Asset Manager",
			// "Spain",
			// "SAM", "", versionAdmin);
			//
			// Department department1 = new Department(company1,
			// "SAM Operation department", "SAM", "", "Spain",
			// new VersionAuditor("admin"));
			//
			// // Santander Private Banking
			//
			// Company company2 = new Company("Santander Private Banking",
			// "Spain", "SPBG", "", versionAdmin);
			//
			// Department department2 = new Department(company2,
			// "SPBG Operation department", "SPBG", "", "Spain",
			// new VersionAuditor("admin"));
			//
			// Fund fund1 = new Fund(company2, "Fund 1", "ES000001", "FUND1",
			// "",
			// "", versionAdmin);
			//
			// FundGroup fundGroup = new FundGroup(fund1, department2,
			// "VALUE FUNDS", "", versionAdmin);
			//
			// // Santander Funds Group
			//
			// Company company3 = new Company("Santander Funds Group", "Spain",
			// "SFG", "", versionAdmin);
			//
			// Department department3 = new Department(company3,
			// "Values department", "VALUE", "", "Spain",
			// new VersionAuditor("admin"));
			//
			// Fund fund2 = new Fund(company3, "Fund 2", "ES000002", "FUND2",
			// "",
			// null, versionAdmin);
			//
			// FundGroup fundGroup2 = new FundGroup(fund2, department3,
			// "VALUE FUNDS", "", versionAdmin);

			// Save Companies

			CompanyDAO companyDAO = (CompanyDAO) applicationContext
					.getBean("companyDAO");
			companyDAO.create(company1);
			companyDAO.create(company2);
			companyDAO.create(company3);
			companyDAO.create(company4);
			companyDAO.create(company5);
			companyDAO.create(company6);
			companyDAO.create(company7);
			companyDAO.create(company8);
			companyDAO.create(company9);
			companyDAO.create(company10);
			companyDAO.create(company11);

			DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
					.getBean("departmentDAO");
			departmentDAO.create(department1);
			departmentDAO.create(department2);
			departmentDAO.create(department3);
			departmentDAO.create(department4);
			departmentDAO.create(department5);
			departmentDAO.create(department6);
			departmentDAO.create(department7);
			departmentDAO.create(department8);
			departmentDAO.create(department9);
			departmentDAO.create(department10);
			departmentDAO.create(department11);

			FundDAO fundDAO = (FundDAO) applicationContext.getBean("fundDAO");
			for (Fund fundExample : funds) {
				fundDAO.create(fundExample);
			}

			// FundGroupDAO fundGroupDAO = (FundGroupDAO) applicationContext
			// .getBean("fundGroupDAO");
			// fundGroupDAO.create(fundGroup);
			// fundGroupDAO.create(fundGroup2);

			// USERS

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String userPass1 = md5.encodePassword("NMAS1ASSET1_", null);
			String userPass2 = md5.encodePassword("ASESORES2_", null);
			String userPass3 = md5.encodePassword("AURIGA3_", null);
			String userPass4 = md5.encodePassword("BESTINVER4_", null);
			String userPass5 = md5.encodePassword("BESTINVERHF5_", null);
			String userPass6 = md5.encodePassword("BRIGHTGATE6_", null);
			String userPass7 = md5.encodePassword("ESFERA7_", null);
			String userPass8 = md5.encodePassword("JPMORGAN8_", null);
			String userPass9 = md5.encodePassword("MAGALLANES9_", null);
			String userPass10 = md5.encodePassword("NMAS1SYZ10_", null);
			String userPass11 = md5.encodePassword("RENTA11_", null);

			User user1 = new User("NMAS1ASSET", "userName", "userLastName",
					"user@company.com", userPass1);
			user1.setCompany(company1);

			User user2 = new User("ASESORES", "userName", "userLastName",
					"user@company.com", userPass2);
			user2.setCompany(company2);

			User user3 = new User("AURIGA", "userName", "userLastNameSPBG",
					"user@company.com", userPass3);
			user3.setCompany(company3);

			User user4 = new User("BESTINVER", "userName", "userLastName",
					"user@company.com", userPass4);
			user4.setCompany(company4);

			User user5 = new User("BESTINVERHF", "userName",
					"userLastName", "user@company.com", userPass5);
			user5.setCompany(company5);

			User user6 = new User("BRIGHTGATE", "userName", "userLastName",
					"user@company.com", userPass6);
			user6.setCompany(company6);

			User user7 = new User("ESFERA", "userName", "userLastName",
					"user@company.com", userPass7);
			user7.setCompany(company7);

			User user8 = new User("JPMORGAN", "userName", "userLastName",
					"user@company.com", userPass8);
			user8.setCompany(company8);

			User user9 = new User("MAGALLANES", "userName", "userLastName",
					"user@company.com", userPass9);
			user9.setCompany(company9);

			User user10 = new User("NMAS1SYZ", "userName", "userLastName",
					"user@company.com", userPass10);
			user10.setCompany(company10);

			User user11 = new User("RENTA", "userName", "userLastName",
					"user@company.com", userPass11);
			user11.setCompany(company11);

			UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
			userDAO.create(user1);
			userDAO.create(user2);
			userDAO.create(user3);
			userDAO.create(user4);
			userDAO.create(user5);
			userDAO.create(user6);
			userDAO.create(user7);
			userDAO.create(user8);
			userDAO.create(user9);
			userDAO.create(user10);
			userDAO.create(user11);

			// USER_ROLE
			UserRolDAO userRolDAO = (UserRolDAO) applicationContext
					.getBean("userRolDAO");

			List<String> userRoles = new ArrayList<String>();
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','NMAS1ASSET') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','ASESORES') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','AURIGA') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','BESTINVER') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','BESTINVERHF') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','BRIGHTGATE') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','ESFERA') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','JPMORGAN') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','MAGALLANES') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','NMAS1SYZ') ");
			userRoles
					.add("Insert into T_USER_ROLE (ROLENAME,USERNAME) values ('ROLE_USER','RENTA') ");

			userRolDAO.insertUserRoles(userRoles);

		} catch (Exception e) {
			logger.error("Error in installCompanies: " + e.getMessage());
		}

	}
}
