package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
클라이언트 요청
1. 화면주세요 (글쓰기 , 로그인 화면)           write.do
2. 처리해주세요 (로그인 처리 , 글쓰기 완료 처리)   writeok.do

/article/newArticle.do  요청 주소가 하나 
(GET , POST 방식)

/article/newArticle.do  >> GET  >> 화면주세요
/article/newArticle.do  >> POST >> 처리하세요   

*/

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {

	//NewArticleController 는 ArticleService 에 의존합니다(필요)
	private ArticleService articleservice;
	
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}

	@RequestMapping(method = RequestMethod.GET)  //화면보여주기
	public String form() {
		return "article/newArticleForm";
		
		//viewResolver
		//   /WEB-INF/views/ + article/newArticleForm + .jsp
	}
	/*
	1. 전통적인 방법으로 Client 데이터 받기 (Spring 에서 사용하지 않아요)
	
	@RequestMapping(method = RequestMethod.POST) //처리하기
	public ModelAndView submit(HttpServletRequest request) {
		NewArticleCommand article = new NewArticleCommand();
		article.setParentId(Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		
		articleservice.writeArticle(article);
		//처리완료
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", article);
		mv.setViewName("article/newArticleSubmitted");
		return mv;
	}
	
	
	2. Spring Parameter  DTO 타입으로 받기
		전제조건 : <input name="값이"  >> class 가지는 member field 명 같아야 한다
		
		submit(NewArticleCommand command)
		1. 자동 DTO 생성 : NewArticleCommand newArticleCommand = new NewArticleCommand();
		2. 넘어온 parameter setter 로 자동 주입
		3. NewArticleCommand 객체는 IOC 컨테이너 안에 생성  id="newArticleCommand"
		
		//코드가 없어요 ....
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", article);
		mv.setViewName("article/newArticleSubmitted");
		return mv;
		
		mv.addObject("newArticleCommand", article); 없어요 
		위 자동 코드 자동 생성 실행 ....
		NewArticleCommand >> 앞에 글자 : N -> n  >> newArticleCommand
		
	3. 객체의 이름(Key) 자동 생성되는 것이 싫어요
		@ModelAttribute("Articledata")
		NewArticleCommand newArticleCommand = new NewArticleCommand();
		mv.addObject("Articledata",newArticleCommand)
	*/
	
	@RequestMapping(method = RequestMethod.POST) //처리하기
	public String submit(@ModelAttribute("Articledata") NewArticleCommand command) {
		articleservice.writeArticle(command);
		//처리완료
		return "article/newArticleSubmitted";
	}
}







