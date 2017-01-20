package com.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import com.cache.vo.DateUtil;
import com.cache.vo.DataCacheVo;


/**
 * Excall外部引用缓存
 * 
 * @author Lin.Tsai
 */
public class DataCache {

	private static Map<String , DataCacheVo> excallCache = new ConcurrentHashMap<String, DataCacheVo>();
	
	/**
	 * 获取缓存bean
	 * 
	 * @param userId
	 * @return
	 */
	public static DataCacheVo get(String userId){
		System.out.println(excallCache);
		System.out.println(excallCache==null);
		DataCacheVo cacheBean = excallCache.get(userId);
		if (cacheBean == null) {
			cacheBean = new DataCacheVo();
			cacheBean.setUserId(userId);
			cacheBean.setStep(0);
			String lastTime = DateUtil.getCurrentTime();
			cacheBean.setLastTime(lastTime);
		}
		
		return cacheBean ;
	}
	
	/**
	 * 获取全部缓存 
	 * 
	 * @return
	 */
	public static Map<String , DataCacheVo> getCache(){
		return excallCache;
	}
	
	/**
	 * 完成一个业务场景之后移除该用户的缓存信息
	 * 
	 * @param userId
	 */
	public static void removeCache(String userId){
		excallCache.remove(userId);
	}
	
	/**
	 * 存缓存
	 * 
	 * @param userId
	 * @param cacheBean
	 */
	public static void put(String userId ,DataCacheVo cacheBean){
		String lastTime = DateUtil.getCurrentTime();
		cacheBean.setLastTime(lastTime);
		excallCache.put(userId, cacheBean);
	}
	
	public static void putData(String userId ,Map<String,JSONObject> map){
		DataCacheVo cacheBean = get(userId);
		cacheBean.setData(map);
		put(userId, cacheBean);
	}
	
	public static Map<String, JSONObject> getData(String userId) {
		DataCacheVo cacheBean = get(userId);
		Map<String, JSONObject> Data = cacheBean.getData();
		return Data;
	}
	
	/**
	 * 获取步数
	 * 
	 * @param userId
	 * @return
	 */
	public static int getStep(String userId) {
		DataCacheVo cacheBean = get(userId);
		int step = cacheBean.getStep();
		return step;
	}

	/**
	 * 步数累加 ，更新session最后访问时间
	 * 
	 * @param userId
	 */
	public static void stepPlus(String userId) {
		DataCacheVo cacheBean = get(userId);
		int step = cacheBean.getStep() + 1;
		cacheBean.setStep(step);
		put(userId, cacheBean);
	}

	/**
	 * 获取最后访问时间
	 * 
	 * @param userId
	 * @return
	 */
	public static String getLastTime(String userId) {
		DataCacheVo cacheBean = get(userId);
		String lastTime = cacheBean.getLastTime();
		return lastTime;
	}

	/**
	 * 获取菜单
	 * 
	 * @param userId
	 * @return
	 */
	public static List<String> getMenu(String userId) {
		DataCacheVo cacheBean = get(userId);
		List<String> menu = cacheBean.getMenu();
		return menu;
	}

	/**
	 * 设置菜单
	 * 
	 * @param userId
	 * @param menu
	 */
	public static void setMenu(String userId ,List<String> menu) {
		DataCacheVo cacheBean = get(userId);
		cacheBean.setMenu(menu);
		put(userId, cacheBean);
	}

	/**
	 * 获取参数列表
	 * 
	 * @param userId
	 * @return
	 */
	public static Map<String, Object> getParams(String userId) {
		DataCacheVo cacheBean = get(userId);
		Map<String, Object> params = cacheBean.getParams();
		return params;
	}

	/**
	 * 设置参数列表
	 * 
	 * @param userId
	 * @param params
	 */
	public static void setParams(String userId ,Map<String, Object> params) {
		DataCacheVo cacheBean = get(userId);
		cacheBean.setParams(params);
		put(userId, cacheBean);
	}
	
	public static void main(String[] args) {
		DataCache excallCache = new DataCache();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(11 + "", 111);
		map.put(22 + "", 222);
		map.put(33 + "", 333);
		excallCache.setParams("asdf", map);
		
		Map<String, Object> params = excallCache.getParams("asdf");
		params.remove("11");
		System.out.println(params);
		params.remove("66");
		System.out.println(params);
		
//		Object defaultNum = excallCache.getParams("asdf").get("11");
//		if (defaultNum != null) {
//			int num = Integer.parseInt(defaultNum.toString());
//			System.out.println(num);
//			excallCache.getParams("asdf").put("11", num + 1);
//			System.out.println(excallCache.getParams("asdf"));
//		} else {
//			System.out.println("null de");
//		}
//		
//		System.out.println(excallCache.getStep("5555"));
	}
}
