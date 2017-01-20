package com.getdata.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.getdata.bean.CFG_ServiceName;
import com.getdata.db.DBAccess;
import com.getdata.util.IsError;

/**
 * 和Message表相關的數據庫操作
 * @author Lin
 */
public class CFG_ServiceNameDao {
	
	/**
	 * 查詢個人資訊或所有資訊
	 * Account Query
	 * @param cfg_servicename
	 */
	public List<CFG_ServiceName> query_ServiceName(CFG_ServiceName   cfg_servicename){
		DBAccess dbAccess = new DBAccess();
		List<CFG_ServiceName> cfg_servicenamelist = new ArrayList<CFG_ServiceName>();
		SqlSession sqlSession = null;
		
		
		try {
			sqlSession = dbAccess.getSqlSession();
			//通過sqlSession執行SQL語句
			cfg_servicenamelist = sqlSession.selectList("cfg_servicename.Query_CFG_ServiceNameInfo", cfg_servicename);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			IsError.GET_EXCEPTION = e.getMessage();
		} finally {
			if(sqlSession != null){
			   sqlSession.close();
			}
		}
		return cfg_servicenamelist;
	}
	
}
