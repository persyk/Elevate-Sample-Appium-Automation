package com.elevate.driver.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PropertyLoader {
	public PropertyLoader() {
	}

	public static Properties loadDefaultAndCustomProps(List<String> propFilePathList) {
		Properties testProps = new Properties();
		for (String propFilePath : propFilePathList) {
			try {
				testProps.load(new FileInputStream(propFilePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		testProps.putAll(System.getProperties());
		return testProps;
	}
}