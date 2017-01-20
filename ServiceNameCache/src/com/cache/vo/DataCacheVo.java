package com.cache.vo;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

/**
 * 缓存单元bean
 * 
 * @author Lin.Tsai
 */
public class DataCacheVo {

	/**
	 * 用户唯一标识
	 */
	private String userId ;
	
	/**
	 * excall运行步骤
	 */
	private int step ;
	
	/**
	 * 业务场景内最后一次交互的时间
	 */
	private String lastTime ;
	
	
	private Map<String, JSONObject> Data ;
	
	/**
	 * 菜单
	 */
	private List<String> menu ;
	
	/**
	 * 缓存参数
	 */
	private Map<String, Object> params ;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public List<String> getMenu() {
		return menu;
	}

	public void setMenu(List<String> menu) {
		if (this.menu != null) {
			this.menu.clear();
		}
		this.menu = menu;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Map<String, JSONObject> getData() {
		return Data;
	}

	public void setData(Map<String, JSONObject> data) {
		Data = data;
	}
}
