package hao1234.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static DataSource ds;
	
	static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		ds = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection(){
		Connection conn = tl.get();
		try {
			if(conn==null){
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			} 
		}catch (SQLException e) {
				throw new RuntimeException(e);
		}
		
		tl.set(conn);
		return conn;
	}
	public static void startTransaction() {
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void commitTransaction(){
		Connection conn = getConnection();
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void close(){
		Connection conn = null;
		try{
			conn = getConnection();
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			tl.remove();
		}
		
	}
}
