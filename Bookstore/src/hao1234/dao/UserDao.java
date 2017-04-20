package hao1234.dao;

import hao1234.domain.User;

public interface UserDao {

	/**
	 * 添加一个用户到数据表中
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 通过ID查询User
	 * @param id
	 * @return
	 */
	User find(String id);

	/**
	 * 通过用户名和密码查询user   用户名unique
	 * @param username
	 * @param password
	 * @return
	 */
	User find(String username, String password);

}