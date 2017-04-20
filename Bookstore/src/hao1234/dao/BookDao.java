package hao1234.dao;

import java.util.List;

import hao1234.domain.Book;
import hao1234.domain.QueryResult;

public interface BookDao {

	void add(Book b);

	Book find(String id);

	QueryResult pageQuery(int startindex, int pagesize, String where, Object param);

	List getAll();

}