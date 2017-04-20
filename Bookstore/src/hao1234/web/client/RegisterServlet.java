package hao1234.web.client;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hao1234.domain.User;
import hao1234.service.BusinessService;
import hao1234.service.impl.BusinessServiceImpl;
import hao1234.utils.WebUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		try {
			User user = WebUtils.request2Bean(request, User.class);
			user.setId(UUID.randomUUID().toString());
			
			BusinessService service = new BusinessServiceImpl();
			service.addUser(user);

			request.setAttribute("message", "×¢²á³É¹¦");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "×¢²áÊ§°Ü");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
