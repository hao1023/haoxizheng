package hao1234.dao;

import java.util.List;

import hao1234.domain.Category;

public interface CategoryDao {

	/**
	 * ��ӷ���
	 * @param c   ������Ҫ��ӵķ���
	 */
	void add(Category c);

	/**
	 * ��ѯһ������
	 * @param id  �����ѯ�����ID
	 * @return		  ���ز�ѯ��������
	 */
	Category find(String id);

	/**
	 * ��ѯ���еķ���
	 * @return ���ز�ѯ����������ļ���
	 */
	List<Category> getAll();

}