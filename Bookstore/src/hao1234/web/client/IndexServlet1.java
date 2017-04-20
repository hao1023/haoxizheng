package hao1234.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hao1234.domain.Category;
import hao1234.domain.PageBean;
import hao1234.domain.QueryInfo;
import hao1234.service.BusinessService;
import hao1234.service.impl.BusinessServiceImpl;
import hao1234.utils.WebUtils;

@WebServlet("/IndexServlet")
public class IndexServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BusinessService service = new BusinessServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//����ѯ��Ϣ��װ��bean��
		QueryInfo info = WebUtils.request2Bean(request, QueryInfo.class);
		//��request���л�ȡ���ID
		String category_id = request.getParameter("category_id");
		//�������ID�����ڿ� 
		if(category_id!=null && !category_id.trim().equals("")){
			info.setQueryname("category_id");
			info.setQueryvalue(category_id);
		}
		
		List<Category> categories = service.getAllCategory();
		PageBean pagebean = service.bookPageQuery(info);
		
		request.setAttribute("categories", categories);
		request.setAttribute("pagebean", pagebean);
		
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
