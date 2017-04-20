package hao1234.dao;

import java.util.List;

import hao1234.domain.Order;

public interface OrderDao {

	/**
	 * 向数据库中插入一条订单
	 * @param o
	 */
	void add(Order order);

	/**
	 * 查询Order表  同时查询所有的Item及Item的书和对应的User，以提供前台显示
	 * @param id
	 * @return
	 */
	Order find(String id);

	/**
	 * 查询所有某个状态的所有订单1：true 已发货
	 * 													2：false未发货
	 * @param state
	 * @return
	 */
	List<Order> getAll(boolean state);

	/**
	 * 修改订单的状态
	 * @param id
	 * @param state
	 */
	void update(String id, boolean state);

}