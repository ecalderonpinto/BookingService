package com.reportingtool.scheduler;

import com.entities.entity.loader.LoadError;
import com.entities.entity.loader.LoadFile;
import com.entities.entity.loader.LoadRaw;

/**
 * 
 * @author Esteban Calderon
 *
 * Procesa una linea del fichero;
 * 
 */
public class RTProcessor {

	public LoadFile loadFile;
	public LoadRaw loadRaw;
	public LoadError loadError;
	
	public RTProcessor(){
	}
	
	public RTProcessor(LoadFile loadFile){
		this.loadFile = loadFile;
	}
	
	public LoadRaw getLoadRaw() {
		return loadRaw;
	}

	public void setLoadRaw(LoadRaw loadRaw) {
		this.loadRaw = loadRaw;
	}

	public LoadFile getLoadFile() {
		return loadFile;
	}

	public void setLoadFile(LoadFile loadFile) {
		this.loadFile = loadFile;
	}

	/**
	 * Se generan los objetos Raw y Raw Data;
	 * 
	 * @param line
	 * @return
	 */
	public boolean process(String line){
		
		boolean result = false;
	
		//Create LoadRaw Object;
		/*
		this.loadRaw = new LoadRaw();
		this.loadRaw.setLoadFile(loadFile);
		this.loadRaw.set
		*/
		//Split line;
		
		//Create aifmdRawDatas Objects;
		
		return result;
	}
}
