package hao1234.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	private static Properties prop = new Properties();
	private DaoFactory(){
		/*�ڼ���Factory��ʱ��ͬʱ���������ļ�*/
		InputStream in = DaoFactory.class.getClass().getClassLoader().getResourceAsStream("/Bookstore/src/cn/itcast/factory/dao.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final DaoFactory instance = new DaoFactory();
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	public static <T> T createDao(Class<T> interfaceclass){
		String key = interfaceclass.getSimpleName();
		String classname = prop.getProperty(key);
		
		try {
			return (T)Class.forName(classname).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	} 
	
}
