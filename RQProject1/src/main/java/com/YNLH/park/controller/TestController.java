package com.YNLH.park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/aaa")
@Controller
public class TestController {
	@RequestMapping("/aab")
	public String aab(String par1, String par2) {
		System.out.println("testteststestste!!!!");
		return "success";
	}
}
