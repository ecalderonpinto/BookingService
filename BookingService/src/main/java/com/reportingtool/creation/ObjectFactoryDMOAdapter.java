package com.reportingtool.creation;

import com.reportingtool.xml.dmo.DOMICILIO;
import com.reportingtool.xml.dmo.PERSONAFIS;
import com.reportingtool.xml.dmo.PERSONAJUR;
import com.reportingtool.xml.dmo.CABECERAOP.Caracteristicas;

/**
 * Interface used by GeneratorXMLDMO_Utils
 * The ObjectFactoryDMO has to implements it.
 * 
 * @author Alberto
 *
 */
public interface ObjectFactoryDMOAdapter {

	Caracteristicas createCABECERAOPCaracteristicas();
	
	PERSONAJUR.DocIdentifsPJ createPERSONAJURDocIdentifsPJ();

	PERSONAJUR.DocIdentifsPJ.DocIdentifPJ createPERSONAJURDocIdentifsPJDocIdentifPJ();

	PERSONAJUR.DomiciliosPJ createPERSONAJURDomiciliosPJ();

	DOMICILIO createDOMICILIO();

	PERSONAJUR.Telefonos createPERSONAJURTelefonos();

	PERSONAJUR.Telefonos.Telefono createPERSONAJURTelefonosTelefono();

	PERSONAFIS.DocIdentifsPF createPERSONAFISDocIdentifsPF();

	PERSONAFIS.DocIdentifsPF.DocIdentifPF createPERSONAFISDocIdentifsPFDocIdentifPF();

	PERSONAFIS.DomiciliosPF createPERSONAFISDomiciliosPF();

	PERSONAFIS.Telefonos createPERSONAFISTelefonos();

	PERSONAFIS.Telefonos.Telefono createPERSONAFISTelefonosTelefono();

}
