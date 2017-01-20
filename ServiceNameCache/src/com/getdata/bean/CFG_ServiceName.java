package com.getdata.bean;

/**
 * 與消息表對應的實體類
 * @author Lin
 */

public class CFG_ServiceName {
	
	private String id;
	
	private String typeid;
	
	private String engname;
	
	private String chiname;
	
	private String mappingkey;
	
	private String sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getChiname() {
		return chiname;
	}

	public void setChiname(String chiname) {
		this.chiname = chiname;
	}

	public String getMappingkey() {
		return mappingkey;
	}

	public void setMappingkey(String mappingkey) {
		this.mappingkey = mappingkey;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
