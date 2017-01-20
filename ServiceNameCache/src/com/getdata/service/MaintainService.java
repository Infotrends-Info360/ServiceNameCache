package com.getdata.service;

import java.util.ArrayList;
import java.util.List;

import com.getdata.bean.CFG_ServiceName;
import com.getdata.dao.CFG_ServiceNameDao;
import com.getdata.util.IsError;

/**
 * 維護相關業務功能
 * 
 * @author Lin, Tim
 */
public class MaintainService {

	/**
	 * Select個人或全體資料的業務邏輯
	 * Account Query
	 * @param cfg_servicename
	 */

	public List<CFG_ServiceName> query_ServiceName(CFG_ServiceName cfg_servicename) {
		if (cfg_servicename.getTypeid() != null && !"".equals(cfg_servicename.getTypeid().trim())) {
			List<CFG_ServiceName> cfg_servicenamelist = new ArrayList<CFG_ServiceName>();
			try {
				CFG_ServiceNameDao cfg_servicenamedao = new CFG_ServiceNameDao();
				cfg_servicenamelist = cfg_servicenamedao.query_ServiceName(cfg_servicename);
			} catch (Exception e) {
				IsError.GET_EXCEPTION = e.getMessage();
			}
			return cfg_servicenamelist;
		}
		 if (cfg_servicename.getTypeid().trim() == null || "".equals(cfg_servicename.getTypeid().trim())) {
			List<CFG_ServiceName> cfg_servicenamelist = new ArrayList<CFG_ServiceName>();
			try {
				CFG_ServiceNameDao cfg_servicenamedao = new CFG_ServiceNameDao();
				cfg_servicenamelist = cfg_servicenamedao.query_ServiceName(cfg_servicename);
			} catch (Exception e) {
				IsError.GET_EXCEPTION = e.getMessage();
			}
			return cfg_servicenamelist;
		}
		return null;
	}
	
}