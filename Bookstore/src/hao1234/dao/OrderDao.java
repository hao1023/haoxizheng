package hao1234.dao;

import java.util.List;

import hao1234.domain.Order;

public interface OrderDao {

	/**
	 * �����ݿ��в���һ������
	 * @param o
	 */
	void add(Order order);

	/**
	 * ��ѯOrder��  ͬʱ��ѯ���е�Item��Item����Ͷ�Ӧ��User�����ṩǰ̨��ʾ
	 * @param id
	 * @return
	 */
	Order find(String id);

	/**
	 * ��ѯ����ĳ��״̬�����ж���1��true �ѷ���
	 * 													2��falseδ����
	 * @param state
	 * @return
	 */
	List<Order> getAll(boolean state);

	/**
	 * �޸Ķ�����״̬
	 * @param id
	 * @param state
	 */
	void update(String id, boolean state);

}