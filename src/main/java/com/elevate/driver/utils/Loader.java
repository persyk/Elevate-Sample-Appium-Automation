package com.elevate.driver.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Loader {

	private Properties properties;

	public Loader() {
		setProperties(loadProperties());
		applyProperties(getProperties());
	}

	protected void setProperties(Properties properties) {
		this.properties = properties;
	}

	public Properties getProperties() {
		return this.properties;
	}

	protected abstract void applyProperties(Properties paramProperties);

	protected ArrayList<String> getPropertyFileList() {
		ArrayList<String> propFileList = new ArrayList<String>();
		propFileList.add(new File("src/resources/dataFile.properties").getAbsolutePath());
		return propFileList;
	}

	protected Logger getLogger() {
		Logger logger = Logger.getLogger(getClass().toString());
		logger.setLevel(Level.INFO);
		return logger;
	}

	protected Properties loadProperties() {
		ArrayList<String> propFileList = getPropertyFileList();
		return PropertyLoader.loadDefaultAndCustomProps(propFileList);
	}
}