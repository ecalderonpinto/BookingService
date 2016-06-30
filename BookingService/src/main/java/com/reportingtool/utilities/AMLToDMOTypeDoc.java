package com.reportingtool.utilities;

/**
 * Enum with type identificator  defined in AML - DMO
 * 
 * @author Alberto
 *
 */
public enum AMLToDMOTypeDoc {
	

	// TODO check with InstallReportListDMO.44 in TIPDIPF_ENUM
	
	// DMO
//	DNI_NIF  "1"
//	NIE      "2"
//	PAS      "4"
//	OTH     "89"
//	CIF     "20"
//	OTH_CIF "99"
	
	// AML
//	NIF/NIE	                001
//	CIF	                    002
//	Pasaporte	            003
//	Tarjeta de Residente	005
//	Documento extranjero	006
//	CIF no residente	    007
//	NOR (NIF no residente)	012
//	Otro documento	        OTR
	
	NIF_NIE("001", "1"),
	CIF("002","20"),
	PASS("003", "4"),
	TRES("005", "2"),
	DOC_EXTR("006", "89"),
	CIF_NO_RESIDENTE("007", "99"),
	NOR("012", "99"),
	OTHER("OTR", "89");
	;
		
	private String AMLTypeDoc;
	private String DMOTypeDoc;
	
	private AMLToDMOTypeDoc(String AMLTypeDoc, String DMOTypeDoc) {
		this.setAMLTypeDoc(AMLTypeDoc);
		this.setDMOTypeDoc(DMOTypeDoc);
	}

	public String getDMOTypeDoc() {
		return DMOTypeDoc;
	}

	public void setDMOTypeDoc(String dMOTypeDoc) {
		DMOTypeDoc = dMOTypeDoc;
	}

	public String getAMLTypeDoc() {
		return AMLTypeDoc;
	}

	public void setAMLTypeDoc(String aMLTypeDoc) {
		AMLTypeDoc = aMLTypeDoc;
	}

	

}
