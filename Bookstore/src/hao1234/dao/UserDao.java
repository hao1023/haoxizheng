package hao1234.dao;

import hao1234.domain.User;

public interface UserDao {

	/**
	 * ���һ���û������ݱ���
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * ͨ��ID��ѯUser
	 * @param id
	 * @return
	 */
	User find(String id);

	/**
	 * ͨ���û����������ѯuser   �û���unique
	 * @param username
	 * @param password
	 * @return
	 */
	User find(String username, String password);

}