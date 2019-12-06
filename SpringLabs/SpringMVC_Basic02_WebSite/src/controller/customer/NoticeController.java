package controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dao.NoticeDao;
import vo.Notice;

public class NoticeController implements Controller {

	public NoticeController() {
		System.out.println("[NoticeController]");
	}

	// spring 코드 상에서 new 작업 (x)
	// injection : xml, annotation
	private NoticeDao dao;

	public void setDao(NoticeDao dao) {
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 검색 처리
		String _page = request.getParameter("pg");
		String _field = request.getParameter("f");
		String _query = request.getParameter("p");

		// default
		int page = 1;
		String field = "TITLE";
		String query = "%%";

		// 조건처리
		if (_page != null && !_page.equals("")) {
			page = Integer.parseInt(_page);
		}
		if (_field != null && !_field.equals("")) {
			field = _field;
		}
		if (_query != null && !_query.equals("")) {
			query = _query;
		}

		ModelAndView modelAndView = new ModelAndView();
		// DAO 데이터 받아오기
		List<Notice> notices = dao.getNotices(page, field, query);
		modelAndView.addObject("list", notices);
		modelAndView.setViewName("notice.jsp");

		return modelAndView;
	}
}
