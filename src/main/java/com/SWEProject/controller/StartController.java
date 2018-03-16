package com.SWEProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class StartController {
	@GetMapping("/Welcome")
	public String startGet()
	{
		return "Welcome";
	} 
}
