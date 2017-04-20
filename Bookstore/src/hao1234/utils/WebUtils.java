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
	 * 文件上传 
	 * 具体步骤： 
	 * 1）获得磁盘文件条目工厂 DiskFileItemFactory 要导包 
	 * 2） 利用 request 获取 真实路径 ，供临时文件存储，和 最终文件存储 ，这两个存储位置可不同，也可相同 
	 * 3）对 DiskFileItemFactory 对象设置一些 属性 
	 * 4）高水平的API文件上传处理  ServletFileUpload upload = new ServletFileUpload(factory); 
	 * 目的是调用 parseRequest（request）方法  获得 FileItem 集合list ， 
	 *      
	 * 5）在 FileItem 对象中 获取信息，   遍历， 判断 表单提交过来的信息 是否是 普通文本信息  另做处理 
	 * 6） 
	 *    第一种. 用第三方 提供的  item.write( new File(path,filename) );  直接写到磁盘上 
	 *    第二种. 手动处理   
	 * 
	 */
	public static Book upload(HttpServletRequest request,String uploadPath){
		try{
			Book book = new Book();
			//获得磁盘文件条目工厂
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
