package com.full.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import org.datanucleus.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class MainController {
	@RequestMapping("/")
	public String homepage(){
		return "home";
	}
	
	@RequestMapping("/image")
	public void image(@RequestParam("image") String base){
		byte[] valueDecoded= Base64.decode(base );
	}
}
