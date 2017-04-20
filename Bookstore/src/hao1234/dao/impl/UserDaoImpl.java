package hao1234.dao.impl;

import java.sql.Connection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import hao1234.dao.UserDao;
import hao1234.domain.User;
import hao1234.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#addUser(cn.itcast.domain.User)
	 */
	public void addUser(User user){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into user (id,name,username,password,phone,cellphone,email,address) values(?,?,?,?,?,?,?)";
			Object params[] = {user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getCellphone(),user.getEmail(),user.getAddress()};
			runner.update(conn, sql, params);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String)
	 */
	public User find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from User where id=?";
			return (User) runner.query(conn, sql,id,new BeanHandler(User.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#Find(java.lang.String, java.lang.String)
	 */
	public User find(String username,String password){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where username=? and password=?";
			String params[]={username,password};
			return (User)runner.query(conn, sql, params,new BeanHandler(User.class));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
