package com.reportingtool.scheduler.dmo.beans;

public class PRFTransactionBean {

	private Long id = null;
	private String amount = null;
	private String amountEuros = null;
	private String fkContract = null;
	private String fkClient = null;
	private String trType = null;
	private String originCC = null;
	private String destinationCC = null;
	private String destinationCountry = null;
	private String emissionCountry = null;
	private String valueDate = null;
	private String currency = null;
	private String beneficiary = null;
	private String office = null;
	private String country = null;
	private String county = null;
	private String city = null;
	private String countyEx = null;
	private String cityEx = null;
	private String localy = null;

	public PRFTransactionBean(Long id, String amount, String amountEuros,
			String fkContract, String fkClient, String trType, String originCC,
			String destinationCC, String destinationCountry,
			String emissionCountry, String valueDate, String currency,
			String beneficiary, String office, String country, String county, String city, String countyEx,
			String cityEx, String localy) {
		this.id = id;
		this.amount = amount;
		this.amountEuros = amountEuros;
		this.fkContract = fkContract;
		this.fkClient = fkClient;
		this.trType = trType;
		this.originCC = originCC;
		this.destinationCC = destinationCC;
		this.destinationCountry = destinationCountry;
		this.emissionCountry = emissionCountry;
		this.valueDate = valueDate;
		this.currency = currency;
		this.beneficiary = beneficiary;
		this.office = office;
		this.country = country;
		this.county = county;
		this.city = city;
		this.countyEx = countyEx;
		this.cityEx = cityEx;
		this.localy = localy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountEuros() {
		return amountEuros;
	}

	public void setAmountEuros(String amountEuros) {
		this.amountEuros = amountEuros;
	}

	public String getFkContract() {
		return fkContract;
	}

	public void setFkContract(String fkContract) {
		this.fkContract = fkContract;
	}

	public String getFkClient() {
		return fkClient;
	}

	public void setFkClient(String fkClient) {
		this.fkClient = fkClient;
	}

	public String getTrType() {
		return trType;
	}

	public void setTrType(String trType) {
		this.trType = trType;
	}

	public String getOriginCC() {
		return originCC;
	}

	public void setOriginCC(String originCC) {
		this.originCC = originCC;
	}

	public String getDestinationCC() {
		return destinationCC;
	}

	public void setDestinationCC(String destinationCC) {
		this.destinationCC = destinationCC;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getEmissionCountry() {
		return emissionCountry;
	}

	public void setEmissionCountry(String emissionCountry) {
		this.emissionCountry = emissionCountry;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountyEx() {
		return countyEx;
	}

	public void setCountyEx(String countyEx) {
		this.countyEx = countyEx;
	}

	public String getCityEx() {
		return cityEx;
	}

	public void setCityEx(String cityEx) {
		this.cityEx = cityEx;
	}

	public String getLocaly() {
		return localy;
	}

	public void setLocaly(String localy) {
		this.localy = localy;
	}
	

}
