package hao1234.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import hao1234.dao.BookDao;
import hao1234.domain.Book;
import hao1234.domain.QueryResult;
import hao1234.utils.JdbcUtils;

public class BookDaoImpl implements BookDao {
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDaoImpl#add(cn.itcast.domain.Book)
	 */
	public void add(Book b){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into book(id,name,price,author,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object params[] = {b.getId(),b.getName(),b.getPrice(),b.getAuthor(),b.getImage(),b.getDescription(),b.getCategory().getId()};
			runner.update(conn, sql, params);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDaoImpl#find(java.lang.String)
	 */
	public Book find(String id){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from book where id=?";
			return (Book) runner.query(conn,sql, id, new  BeanHandler(Book.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//String where =  "where category_id=?"
	/*
	 * 用户带where条件过来，则该方法返回分类下面的分页数
	 * 如果没带where条件，则返回所有书的分页数据
	 * 
	 * where条件的格式：String where =  "where category_id=?"
	 * 
	 */
	
	private List<Book> getPageDate(int startindex,int pagesize,String where,Object param){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			if(where==null || where.trim().equals("")){
				//则返回所有书的分页数据
				String sql = "select * from book limit ?,?";
				Object params[] = {startindex,pagesize};
				return (List<Book>) runner.query(conn, sql, params, new BeanListHandler(Book.class));
			}else{
				String sql = "select * from book "+ where + " limit ?,?";
				Object params[] = {param,startindex,pagesize};
				return (List<Book>) runner.query(conn, sql, params, new BeanListHandler(Book.class));
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private int getPageTotalRecord(String where,Object param){
		
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			if(where==null || where.trim().equals("")){
				String sql = "select count(*) from book";
				return ((Long)runner.query(conn, sql, new ScalarHandler())).intValue();
			}else{
				String sql = "select count(*) from book " + where;
				return ((Long)runner.query(conn, sql, param,new ScalarHandler())).intValue();
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDaoImpl#pageQuery(int, int, java.lang.String, java.lang.Object)
	 */
	public QueryResult pageQuery(int startindex,int pagesize,String where,Object param){
		List list = getPageDate(startindex, pagesize, where, param);
		int totalrecord = getPageTotalRecord(where, param);
		QueryResult result = new QueryResult();
		result.setList(list);
		result.setTotalrecord(totalrecord);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.BookDaoImpl#getAll()
	 */
	public List getAll(){
		try{
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from book";
			return (List) runner.query(conn,sql,new  BeanListHandler(Book.class));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
