package ncontroller;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer")

public class CustomerController {

	private NoticeDao noticeDao;

	@Autowired
	public void setDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@RequestMapping("notice.htm")
	public String form(String pg, String f, String q, Model model) {
		// default
		int page = 1;
		String field = "TITLE";
		String query = "%%";

		// 조건처리
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}
		if (f != null && !f.equals("")) {
			field = f;
		}
		if (q != null && !q.equals("")) {
			query = q;
		}
		List<Notice> notices = null;
		try {
			notices = noticeDao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("list", notices); // view까지 전달

		return "notice.jsp";
	}

	@RequestMapping("noticeDetail.htm")
	public String detail(String seq, Model model) {
		Notice notice = null;
		try {
			notice = noticeDao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("notice", notice);

		return "noticeDetail.jsp";
	}

	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String write() {
		return "noticeReg.jsp";
	}

	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String writeOk(Notice notice, HttpServletRequest request) {
		// 실 파일 업로드 (웹서버의 특정 경로)
		String filename = notice.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("customer/upload");
		String fpath = path + "\\" + filename;

		try (FileOutputStream outputStream = new FileOutputStream(fpath)) {
			outputStream.write(notice.getFile().getBytes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		notice.setFileSrc(filename);
		try {
			noticeDao.insert(notice);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:notice.htm";
	}

	@RequestMapping("noticeDel.htm")
	public String delete(String seq) {
		try {
			noticeDao.delete(seq);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:notice.htm";
	}

	@RequestMapping(value = "noticeEdit.htm",method = RequestMethod.GET)
	public String edit(String seq, Model model) {
		System.out.println("edit in");
		Notice notice = null;
		try {
			notice = noticeDao.getNotice(seq);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		model.addAttribute("notice", notice);
		return "noticeEdit.jsp";
	}

	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST)
	public String editOk(Notice notice, HttpServletRequest request) {
		// 실 파일 업로드 (웹서버의 특정 경로)
		String filename = notice.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("customer/upload");
		String fpath = path + "\\" + filename;

		try (FileOutputStream outputStream = new FileOutputStream(fpath)) {
			outputStream.write(notice.getFile().getBytes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		notice.setFileSrc(filename);
		try {
			noticeDao.update(notice);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return "redirect:noticeDetail.htm?seq=" + notice.getSeq();
	}
}
