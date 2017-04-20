package hao1234.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import hao1234.domain.Book;
import hao1234.domain.Category;
import hao1234.service.BusinessService;
import hao1234.service.impl.BusinessServiceImpl;

public class WebUtils {
	public static <T> T request2Bean(HttpServletRequest req,Class<T> beanclass){
		try{
			T bean = beanclass.newInstance();
			
			Map map = req.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/** 
	 *  
	 * @author Administrator 
	 * �ļ��ϴ� 
	 * ���岽�裺 
	 * 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ���� 
	 * 2�� ���� request ��ȡ ��ʵ·�� ������ʱ�ļ��洢���� �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ 
	 * 3���� DiskFileItemFactory ��������һЩ ���� 
	 * 4����ˮƽ��API�ļ��ϴ�����  ServletFileUpload upload = new ServletFileUpload(factory); 
	 * Ŀ���ǵ��� parseRequest��request������  ��� FileItem ����list �� 
	 *      
	 * 5���� FileItem ������ ��ȡ��Ϣ��   ������ �ж� ���ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ  �������� 
	 * 6�� 
	 *    ��һ��. �õ����� �ṩ��  item.write( new File(path,filename) );  ֱ��д�������� 
	 *    �ڶ���. �ֶ�����   
	 * 
	 */
	public static Book upload(HttpServletRequest request,String uploadPath){
		try{
			Book book = new Book();
			//��ô����ļ���Ŀ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding(request.getCharacterEncoding());
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String inputName = item.getFieldName();
					String value = item.getString("UTF-8");
					if("category_id".equals(inputName)){
						BusinessService service = new BusinessServiceImpl();
						Category c = service.findCategory(value);
						book.setCategory(c);
					}else{
						BeanUtils.setProperty(book, inputName, value);
					}
				}else{
					String filename = item.getName();
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					String savepath = uploadPath;
					String saveFilename = UUID.randomUUID().toString() + filename;
					
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + saveFilename);
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len=in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					in.close();
					out.close();
					item.delete();
					book.setImage(saveFilename);
				}
			}
			book.setId(UUID.randomUUID().toString());
			return book;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
