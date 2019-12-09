package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

	/*
		WEB: Client 전송 데이터(로그인정보 , 회원정보) -> Server 데이터 받기 -> 업무(DB 연동)
		
		방법_1 전통(request)
		void searchInternal(HttpServletRequest request){
			String id = request.getParameter("id");
		}
		
		방법_2 : DTO 객체를 통해서 받는 방법(게시판 데이터 , 회원가입 데이터)  
		       : 선행조건 >>  do?id=hong&name=김유신&age=100 >> dto 객체의 memberfield 명과 같다
		       : input 태그 : name="dto 객체 memberfield 명과 일치"
		void searchInternal(MemberdDto member){
		  MemberdDto 가 IOC 컨테이터 빈객체 생성
		  setter 통한 자동 주입
		  view 까지 전달(주의 : 전달되는 객체의 key 약속된 표현 : memberdDto
		}       
		
		방법_3 : 편하게 (객체단위로 처리)
		void searchInternal(int page , int size){
		 ** 넘어오는 parameter 이름이 함수의 parameter 이름과 같으면 END
		 ** internal.do?page=100&size=1
		}  
		
		방법_2 , 방법_3 단점 (유효성 체크 불가능)
		
		방법_4 : @RequestParam Annotation 
		유효성 처리 , 기본값 설정
		  
		REST API (method : GET, POST, PUT, DELETE)
		방법_5 : @PathVariable   /member/{memberid}  >> /member/100 , /member/200
		
		 
		실습 : 방법_4  192.168.6.37
	*/
	
	/*
	기본설정
	public ModelAndView searchInternal(@RequestParam("query") String query,
			                           @RequestParam("p") int p) {
		
		return null;
	}
	*/

	//required = true 의무적으로 받겠다. 
	@RequestMapping("/search/external.do")
	public ModelAndView searchInternal( @RequestParam(value = "query", required = false) String query,
													   @RequestParam(value = "p", defaultValue = "1") int p) {
		System.out.println("param query : " + query);
		System.out.println("param p : " + p);
		return new ModelAndView("search/internal");
	}
}









