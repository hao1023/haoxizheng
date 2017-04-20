package hao1234.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import hao1234.dao.BookDao;
import hao1234.dao.CategoryDao;
import hao1234.dao.OrderDao;
import hao1234.dao.UserDao;
import hao1234.domain.Book;
import hao1234.domain.Cart;
import hao1234.domain.CartItem;
import hao1234.domain.Category;
import hao1234.domain.Order;
import hao1234.domain.OrderItem;
import hao1234.domain.PageBean;
import hao1234.domain.QueryInfo;
import hao1234.domain.QueryResult;
import hao1234.domain.User;
import hao1234.factory.DaoFactory;
import hao1234.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {
	BookDao bookDao = DaoFactory.createDao(BookDao.class);
	UserDao userDao = DaoFactory.createDao(UserDao.class);
	OrderDao orderDao = DaoFactory.createDao(OrderDao.class);
	CategoryDao categoryDao = DaoFactory.createDao(CategoryDao.class);
	public void addCategory(Category c){
		categoryDao.add(c);
	}
	public Category findCategory(String id){
		return categoryDao.find(id);
	}
	public List<Category> getAllCategory(){
		return categoryDao.getAll();
	}
	public void addBook(Book book){
		bookDao.add(book);
	}
	public Book findBook(String id){
		return bookDao.find(id);
	}
	public PageBean bookPageQuery(QueryInfo info){
		
		QueryResult result = bookDao.pageQuery(info.getStartindex(), info.getPagesize(), info.getWhere(), info.getQueryvalue());
		
		PageBean bean = new PageBean();
		bean.setCurrentpage(info.getCurrentpage());
		bean.setList(result.getList());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(result.getTotalrecord());
		
		return bean;
	}
	public List getAllBook(){
		return bookDao.getAll();
	}
public void saveOrder(Cart cart,User user){
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		//定义一个集合，用于保存所有订单项
		Set oitems = new HashSet();
		//用购物车中的购物项生成订单项
		Set<Map.Entry<String, CartItem>> set = cart.getMap().entrySet();
		for(Map.Entry<String, CartItem> entry : set){
			CartItem citem = entry.getValue();   //得到每一个购物项
			OrderItem oitem = new OrderItem();
			//用购物车中的购物项生成订单项
			oitem.setBook(citem.getBook());
			oitem.setId(UUID.randomUUID().toString());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			oitems.add(oitem);
		}
		order.setOrderitems(oitems);
		orderDao.add(order);
	}
	public Order findOrder(String id){
		return orderDao.find(id);
	}
	public List getOrderByState(boolean state){
		return orderDao.getAll(state);
	}
	public void updateOrder(String id,boolean state){
		orderDao.update(id, state);
	}
	public void addUser(User user){
		userDao.addUser(user);
	}
	public User findUser(String username,String password){
		return userDao.find(username, password);
		
	}
	public User findUser(String id){
		return userDao.find(id);
	}
}
