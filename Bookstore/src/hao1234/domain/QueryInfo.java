package hao1234.domain;

public class QueryInfo {
	//当前页
	private int currentpage = 1;
	//页面大小
	private int pagesize = 4;
	//起始位置
	private int startindex;
	
	private String queryname;   //category_id
	private String queryvalue;   //3
	
	private String where;  //where = "where category_id=?"
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getStartindex() {
		this.startindex = (this.currentpage-1)*this.pagesize;
		return startindex;
	}
	
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public String getQueryvalue() {
		return queryvalue;
	}
	public void setQueryvalue(String queryvalue) {
		this.queryvalue = queryvalue;
	}
	public String getWhere() {
		if(this.queryname==null || this.queryname.trim().equals("")){
			return "";
		}else{
			// this.where = "where category_id=?"
			this.where = "where " + queryname + "=?";
		}
		return where;
	}
}
