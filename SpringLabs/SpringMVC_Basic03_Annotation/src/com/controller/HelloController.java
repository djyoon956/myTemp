package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
	public class HelloController implements Controller
	
		public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception 
		
		단점 : controller 가 하나의 요청만 처리
				10개의 요청 > Controller 10개
		
	Annotation 사용하면 method 단위로 mapping 할 수 있다.
	결과 : 하나의 Contoller가 다수의 요청을 처리한다. >> 대신 method 10개 생성
	
	
	1. implements Controller : class mapping
	2. @Controller >> method mapping 
		@RequestMapping 사용해서 주소 mapping
		 >> HelloController >> bean 생성
*/

@Controller
public class HelloController {

	@RequestMapping("/hello.do") // <a href="hello.do">hello.do</a>
	public ModelAndView hello() {
		System.out.println("[hello.do start]");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("greeting", getGreeting());
		modelAndView.setViewName("Hello");
		
		return modelAndView;
	}

	// 필요하다면 일반 함수 만들어서 사용 가능
	private String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String result = "";
		if (hour >= 6 && hour <= 10) {
			result = "학습 시간";
		} else if (hour >= 11 && hour <= 15) {
			result = "배고픈 시간";
		} else if (hour >= 16 && hour <= 18) {
			result = "졸린 시간";
		} else {
			result = "집에 가는 시간";
		}

		return result;
	}
}
