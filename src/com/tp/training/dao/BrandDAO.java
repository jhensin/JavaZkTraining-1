package com.tp.training.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;

//import com.tp.baselib.model.MapBean;
//import com.tp.baselib.model.MapBeanResultList;

public class BrandDAO extends TrainingDAO {

	@Override
	public String getMainTableName() {
		return "WT_BRAND";
	}

	// WT_BRAND 中檢索出BRAND_CODE =xxx 的資料
	public MapBean getBrandName(String brandCode) throws SQLException {
		return super.queryFirst("SELECT *  FROM " + this.getMainTableName() + "  WHERE BRAND_CODE LIKE ?", brandCode);

	}

	// 檢索出所有的資料
	public MapBeanResultList brandAll() throws SQLException {
		return super.queryMapBeanResultList("SELECT * FROM " + this.getMainTableName() + " ORDER BY 1");
	}

	// 按照彈出窗口的查詢條件，查找資料
	public MapBeanResultList queryByBrand(String brandNo, String brandNm) throws SQLException {
		return super.queryMapBeanResultList(
				" SELECT * FROM  " + this.getMainTableName()
						+ " WHERE BRAND_NO LIKE '%' ||?||'%' AND  BRAND_NAME LIKE '%'||?||'%'  ORDER BY BRAND_NO ",
				brandNo, brandNm);
	}

	// 自定義數據類型
	public MapBeanResultList queryBrandDefine() throws SQLException {
		Map<String, String> map = new HashMap<>();
		map.put("GRAND_NM", this.getMainTableName() + ".BRAND_NAME");
		return super.queryMapBeanResultList(map,
				"SELECT BRAND_NO  || '  (' || BRAND_NAME  || ')' GRAND_NM FROM   " + this.getMainTableName());
	}

}
