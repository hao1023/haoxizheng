package hao1234.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import hao1234.utils.JdbcUtils;

public class TransactionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//index.jsp   dao   dao    
		//1.jpg
		try{
			//拦截下来后：获取连接、开启事务、并把连接绑定到当前线程
			//JdbcUtils.StartTransaction();   //JdbcUtils.getConnection();
			
			chain.doFilter(request, response);  //目标资源执行 dao1   conn =  JdbcUtils.getConnection();   dao2   JdbcUtils.getConnection()
			
			//获取当前线程上绑定的连接，提交事务，并关闭链接，释放连接与当前线程的绑定
			JdbcUtils.commitTransaction();
		}finally{
			JdbcUtils.close();
		}
	
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
