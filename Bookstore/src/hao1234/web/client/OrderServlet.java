package hao1234.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hao1234.domain.Cart;
import hao1234.domain.User;
import hao1234.service.BusinessService;
import hao1234.service.impl.BusinessServiceImpl;

public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("message", "请先登陆！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessService service = new BusinessServiceImpl();
			service.saveOrder(cart, user);
			request.setAttribute("message", "订单生成成功，请等待收货");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "失败！！");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
