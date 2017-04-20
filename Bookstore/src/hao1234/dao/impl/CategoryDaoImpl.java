package hao1234.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import hao1234.dao.CategoryDao;
import hao1234.domain.Category;
import hao1234.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.CategoryDao#add(cn.itcast.domain.Category)
	 */
	public void add(Category c){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object params[] = {c.getId(),c.getName(),c.getDescription()};
			runner.update(conn, sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.CategoryDao#find(java.lang.String)
	 */
	public Category find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from category where id=?";
			return (Category) runner.query(conn, sql, id, new BeanHandler(Category.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.CategoryDao#getAll()
	 */
	public List<Category> getAll(){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from category";
			return (List<Category>) runner.query(conn, sql, new BeanListHandler(Category.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
