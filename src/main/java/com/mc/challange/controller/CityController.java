package com.mc.challange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mc.challange.service.CityService;

@RestController
public class CityController {
			
	@Autowired CityService cityService;
	final String NO = "no";
	
	@RequestMapping(method = RequestMethod.GET, value = "/connected")
	@ResponseBody
	public String CityConnect(@RequestParam(name = "origin") String originCity,
			@RequestParam(name = "destination") String destinationCity) {
		 
		// Clean Origin and destination City
		originCity = originCity.replaceAll("\\s", "");
		originCity = originCity.toLowerCase();
		
		destinationCity = destinationCity.replaceAll("\\s", "");
		destinationCity = destinationCity.toLowerCase();
		
		// Call Bakcend Service
		try {
			return cityService.isCityConnected(originCity, destinationCity);
		} catch (Exception e) {
			return NO;
		}

}
}
