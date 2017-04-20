package hao1234.service;

import java.util.List;

import hao1234.domain.Book;
import hao1234.domain.Cart;
import hao1234.domain.Category;
import hao1234.domain.Order;
import hao1234.domain.PageBean;
import hao1234.domain.QueryInfo;
import hao1234.domain.User;

public interface BusinessService {

	/*****************************************
	 * 分类相关的服务
	 ******************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List getAllCategory();

	/*****************************************
	 * 图书相关的服务
	 ******************************************/
	void addBook(Book book);

	Book findBook(String id);

	PageBean bookPageQuery(QueryInfo info);

	List getAllBook();

	/*****************************************
		 * 订单相关的服务
		 ******************************************/
	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List getOrderByState(boolean state);

	void updateOrder(String id, boolean state);

	/*****************************************
	 * 用户相关的服务
	 ******************************************/

	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

}