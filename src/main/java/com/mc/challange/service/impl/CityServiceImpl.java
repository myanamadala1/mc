// Author : Mohan Yanamadala

package com.mc.challange.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.mc.challange.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	final String CLASSPATHFILEREF = "classpath:static/city.txt";
	final String YES = "yes";
	final String NO = "no";

	final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class.getName());

	@Override
	public String isCityConnected(String originCity, String destinationCity) throws Exception {

		HashMap<String, String> cityMap = getCityMap();
		
		if(cityMap == null || cityMap.size() == 0)
			return NO;
		
		if (cityMap.containsKey(originCity) && cityMap.get(originCity).equals(destinationCity))
			return YES;
		else
			return NO;
	}

	@SuppressWarnings("resource")
	private HashMap<String, String> getCityMap() {

		HashMap<String, String> cityMap = new HashMap<String, String>();
		try {
			File file = ResourceUtils.getFile(CLASSPATHFILEREF);
			Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine(); // get line
				String[] lineSplit = line.split(","); // split line with "," delimiter
				lineSplit = cleanString(lineSplit); // clean data for case sensitivity and whitespaces
				cityMap.put(lineSplit[0], lineSplit[1]);
				return cityMap;
			}
		} catch (FileNotFoundException exception) {
			logger.error("Error at file reading: {}", exception.getMessage());
		}
		
		return new HashMap<String, String>();
	}
	
	private String[] cleanString(String[] lineSplit) {

		lineSplit[0] =lineSplit[0].replaceAll("\\s", "");
		lineSplit[0] = lineSplit[0].toLowerCase();
		
		lineSplit[1] =lineSplit[1].replaceAll("\\s", "");
		lineSplit[1] = lineSplit[1].toLowerCase();
		
		return lineSplit;
		
	}
}
