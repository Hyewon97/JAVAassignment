package com.mycompany.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

// http://localhost:8080/myapp/home
@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		
		
		return "home";
	}
	
}
