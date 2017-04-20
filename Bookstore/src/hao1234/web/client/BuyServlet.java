package hao1234.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hao1234.domain.Book;
import hao1234.domain.Cart;
import hao1234.service.BusinessService;
import hao1234.service.impl.BusinessServiceImpl;

public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		BusinessService service = new BusinessServiceImpl();
		Book book = service.findBook(id);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		cart.add(book);
		response.sendRedirect(request.getContextPath() + "/client/listcart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
