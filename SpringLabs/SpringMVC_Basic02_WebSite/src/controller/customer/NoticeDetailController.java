package controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dao.NoticeDao;
import vo.Notice;

public class NoticeDetailController implements Controller {
	public NoticeDetailController() {
		System.out.println("[NoticeDetailController]");
	}

	// spring 코드 상에서 new 작업 (x)
	// injection : xml, annotation
	private NoticeDao dao;

	public void setDao(NoticeDao dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String seq = request.getParameter("seq");

		ModelAndView modelAndView = new ModelAndView();
		Notice notice = dao.getNotice(seq);
		modelAndView.addObject("notice", notice);
		modelAndView.setViewName("noticeDetail.jsp");
		
		return modelAndView;
	}
}
