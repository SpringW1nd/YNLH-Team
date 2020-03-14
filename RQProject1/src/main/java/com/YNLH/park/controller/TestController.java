package com.YNLH.park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/aaa")
@Controller
public class TestController {
	@RequestMapping("/aab")
	public String aab(@RequestParam("username")String username, @RequestParam("password")String password) {
		System.out.println("username=====>"+username);
		System.out.println("password=====>"+password);
		return "registeUserMainPage";
	}
	@RequestMapping("/reservationList")
	public String reservationList() {
		return "reservationList";
	}
	@RequestMapping("/makeReservation")
	public String makeReservation() {
		return "makeReservation";
	}
	@RequestMapping("/reservation")
	public String reservation(@RequestParam("name")String name, @RequestParam("plateNumber")String plateNumber, @RequestParam("RStartDate")String RStartDate, @RequestParam("REndDate")String REndDate) {
		System.out.println("name=====>"+name);
		System.out.println("plateNumber=====>"+plateNumber);
		System.out.println("RStartDate=====>"+RStartDate);
		System.out.println("REndDate=====>"+REndDate);
		return "reservationSuccess";
	}
	@RequestMapping("/Operation")
	public String Operation() {
		
		return "Operation";
	}
}
