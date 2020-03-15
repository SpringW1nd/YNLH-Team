package com.YNLH.park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping("/registeration")
	public ModelAndView regiseration(@RequestParam("username")String username, @RequestParam("password")String password,
			@RequestParam("name")String name, @RequestParam("phoneNumber")String phoneNumber , @RequestParam("email")String email ){
		System.out.println("username=====>"+username);
		System.out.println("password=====>"+password);
		System.out.println("name=====>"+name);
		System.out.println("phoneNumber=====>"+phoneNumber);
		ModelAndView mav = new ModelAndView("registeUserMainPage");
		mav.addObject("username", username);
		mav.addObject("password", password);
		return mav;
	}
	@RequestMapping("/goTORegisteration")
	public String goTORegisteration(){
		return "registeration";
	}
	@RequestMapping("/Operation")
	public String Operation() {
		
		return "Operation";
	}
	@RequestMapping("/sighout")
	public String sighout() {
		
		return "index";
	}
}
