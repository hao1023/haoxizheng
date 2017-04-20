package hao1234.dao;

import java.util.List;

import hao1234.domain.Category;

public interface CategoryDao {

	/**
	 * 添加分类
	 * @param c   传入需要添加的分类
	 */
	void add(Category c);

	/**
	 * 查询一个分类
	 * @param id  传入查询分类的ID
	 * @return		  返回查询的类别对象
	 */
	Category find(String id);

	/**
	 * 查询所有的分类
	 * @return 返回查询到的类别对象的集合
	 */
	List<Category> getAll();

}