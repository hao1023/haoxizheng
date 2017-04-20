package hao1234.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hao1234.dao.OrderDao;
import hao1234.domain.Book;
import hao1234.domain.Order;
import hao1234.domain.OrderItem;
import hao1234.domain.User;
import hao1234.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#add(cn.itcast.domain.Order)
	 */
	public void add(Order order){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			
			String sql = "insert into orders(id,ordertime,state,price,user_id) values(?,?,?,?,?)";
			Object params[] = {order.getId(),order.getOrdertime(),order.isState(),order.getPrice(),order.getUser().getId()};
			runner.update(conn,sql, params);
			//
			Set<OrderItem> set = order.getOrderitems();
			for(OrderItem item : set){
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),order.getId()};
				runner.update(conn,sql, params);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#find(java.lang.String)
	 */
	public Order find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from Order where id=?";
			Order order = (Order) runner.query(conn, sql,id,new  BeanHandler(Order.class));
			sql = "select *  from orderitem where order_id=?";
			List<OrderItem> list = (List<OrderItem>) runner.query(conn, sql,id,new BeanHandler(OrderItem.class));
			//查询每一个订单项代表的书
			for(OrderItem item:list){
				sql = "select book .* from orderitem,book where oderitem.id=? and orderitem.book_id=book.id";
				Book book = (Book) runner.query(conn, sql, item.getId(), new BeanHandler(Book.class));
				item.setBook(book);
			}
			//查询每一个订单对应的客户
			sql = "select user.* from order,book where order.id=? and order.book_id=book.id";
			
			User user = (User) runner.query(conn, sql, id, new BeanHandler(User.class));
			order.getOrderitems().addAll(list);
			order.setUser(user);
			
			return order;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#getAll(boolean)
	 */
	public List<Order> getAll(boolean state){
		
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from orders where state=?";
			List<Order> list = (List<Order>) runner.query(conn,sql, state, new BeanListHandler(Order.class));
			
			for(Order o : list){
				sql= "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
				User user = (User) runner.query(conn,sql, o.getId(), new BeanHandler(User.class));
				o.setUser(user);
			}
			
			return list;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.OrderDao#update(java.lang.String, boolean)
	 */
	public void update(String id,boolean state){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "update orders set state=? where id=?";
			Object params[] = {state,id};
			runner.update(conn,sql,params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
