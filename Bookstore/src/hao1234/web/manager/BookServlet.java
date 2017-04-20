package hao1234.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hao1234.domain.Book;
import hao1234.service.BusinessService;
import hao1234.service.impl.BusinessServiceImpl;
import hao1234.utils.WebUtils;

public class BookServlet extends HttpServlet {
	private BusinessService service = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("forAddUI".equals(method)){
			forAddUI(request,response);
		}
		if("add".equals(method)){
			add(request,response);
		}
		if("list".equals(method)){
			list(request,response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = service.getAllBook();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Book  book = WebUtils.request2Bean(request, Book.class);
		try {
			
			Book book = WebUtils.upload(request, this.getServletContext().getRealPath("/images"));
			service.addBook(book);
			request.setAttribute("message", "添加成功！！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败！！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void forAddUI(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		List categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/addbook.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
