/**
 * 
 */
package com.cache.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cache.DataCache;
import com.getdata.bean.CFG_ServiceName;
import com.getdata.service.MaintainService;
import com.getdata.util.IsError;
import com.getdata.util.Variable;

/**
 * @author Lin.Tsai
 * 
 */

@Path("/datacache")
public class DataCacheServlet extends HttpServlet {


	private static final JSONObject jsonobject = new JSONObject();
	
	private static Map<String,JSONObject> map = new ConcurrentHashMap<String, JSONObject>(); 

	@GET
	//@Produces("application/json")
	public Response GetFromPath(@QueryParam("typeid") String typeid
				,@QueryParam("method") String method
				,@QueryParam("key") String key) throws IOException {
		
		try {
			
			int step = DataCache.getStep(typeid)+1;
			
			if(method.equals("put")){
				String put = "[";
				JSONObject DataCachejsonobject= Query_DataCacheServlet(typeid);
				for(int i = 0; i < DataCachejsonobject.getJSONArray("data").length();i++){
					//String k = "test"+i;
					//String v = "測試"+i;
					JSONObject mkjsonobject = new JSONObject();
					mkjsonobject.put("chiname", DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("chiname"));
					mkjsonobject.put("engname", DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("engname"));
					mkjsonobject.put("sort", DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("sort"));
					String k = DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("mappingkey");
//					String v = DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("chiname");
					map.put(k, mkjsonobject);
					put+=k+":"+mkjsonobject+";";
				}
				DataCache.putData(typeid, map);
				put+="]";
				String reask = "put: "+put;
				jsonobject.put("step", 0);
				jsonobject.put("method", method);
				jsonobject.put("Message", reask);
			}else
			
			if(method.equals("get")){
				Map<String,JSONObject> gettingmap = DataCache.getData(typeid);
				JSONObject value = new JSONObject();
				String result = "";
				if(gettingmap==null || gettingmap.isEmpty()){
					JSONObject DataCachejsonobject= Query_DataCacheServlet(typeid);
					for(int i = 0; i < DataCachejsonobject.getJSONArray("data").length();i++){
						//String k = "test"+i;
						//String v = "測試"+i;
						JSONObject mkjsonobject = new JSONObject();
						mkjsonobject.put("chiname", DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("chiname"));
						mkjsonobject.put("engname", DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("engname"));
						mkjsonobject.put("sort", DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("sort"));
						String k = DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("mappingkey");
						//String v = DataCachejsonobject.getJSONArray("data").getJSONObject(i).getString("chiname");
						map.put(k, mkjsonobject);
					}
					DataCache.putData(typeid, map);
					gettingmap =  DataCache.getData(typeid);
					JSONObject getkeyjsonobject = new JSONObject();
					value = gettingmap.get(key);
					result = "get: "+value;
					getkeyjsonobject.put(key, value);
					method = "put& get";
					jsonobject.put("Message", getkeyjsonobject);
				}else{
					if(key.equals("all")){
						JSONObject getkeyjsonobject = new JSONObject();
						Set<String> keySet = map.keySet();
						synchronized (keySet) {
							for (String getkey : keySet) {
								JSONObject getvalue = map.get(getkey);
								if (getvalue != null) {
									getkeyjsonobject.put(getkey, getvalue);
								}
							}
						}
						jsonobject.put("Message", getkeyjsonobject);
					}else{
						JSONObject getkeyjsonobject = new JSONObject();
						value = gettingmap.get(key);
						result = "get: "+value;
						getkeyjsonobject.put(key, value);
						jsonobject.put("Message", getkeyjsonobject);
					}
				}
				jsonobject.put("step", step);
				jsonobject.put("method", method);
				//jsonobject.put("Message", result);
				DataCache.stepPlus(typeid);
			}else
			
			if(method.equals("cleanup")){
				map.clear();
				String result = "Cleanup!!";
				jsonobject.put("step", step-1);
				jsonobject.put("method", method);
				jsonobject.put("Message", result);
				DataCache.removeCache(typeid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(200).entity(jsonobject.toString())
				.header("Access-Control-Allow-Origin", "*")
			    .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			    .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	public JSONObject Query_DataCacheServlet(String typeid){
		
		JSONObject mainjsonObject = new JSONObject();
		CFG_ServiceName cfg_servicename = new CFG_ServiceName();
		cfg_servicename.setTypeid(typeid);
		//cfg_servicename.setEngname(engname);
		mainjsonObject.put("status", Variable.POST_STATUS);
		
		try{
			MaintainService maintainService = new MaintainService();
	        List<CFG_ServiceName> cfg_servicenamelist = maintainService.query_ServiceName(cfg_servicename);
			
	        mainjsonObject.put("typeid", cfg_servicenamelist.get(0).getEngname());
	        
	        JSONArray PersonJsonArray = new JSONArray();
	        for (int i = 0; i < cfg_servicenamelist.size(); i++) {
	        	JSONObject PersonJsonObject = new JSONObject();
	        	PersonJsonObject.put("id", cfg_servicenamelist.get(i).getId());
	        	//PersonJsonObject.put("typeid", cfg_servicenamelist.get(i).getTypeid());
	        	PersonJsonObject.put("engname", cfg_servicenamelist.get(i).getEngname());
	        	PersonJsonObject.put("chiname",    cfg_servicenamelist.get(i).getChiname());
	        	PersonJsonObject.put("mappingkey", cfg_servicenamelist.get(i).getMappingkey());
	        	PersonJsonObject.put("sort",    cfg_servicenamelist.get(i).getSort());
	        	
	        	PersonJsonArray.put(PersonJsonObject);
	        }		
	        mainjsonObject.put("data", PersonJsonArray);
	        	
		} catch (Exception e) {
			if(IsError.GET_EXCEPTION != null)
				mainjsonObject.put("error", IsError.GET_EXCEPTION);
			else
				mainjsonObject.put("error", e.getMessage());
		}
		
		System.out.println(mainjsonObject.toString());
		
		return mainjsonObject;
	}

}
