package com.tp.training.dao;

import java.sql.SQLException;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;

public class StyleDAO extends TrainingDAO {

	@Override
	public String getMainTableName() {
		return "WT_STYLE";
	}

	// 從 WT_STYLE 中抓取STYLE_NO = ? 的資料
	public MapBean getStyle(String Style) throws SQLException {
		return super.queryFirst(" SELECT  * FROM  " + this.getMainTableName() + " WHERE STYLE_NO =? ", Style);
	}

	// 從 WT_STYLE 中抓取所有的資料
	public MapBeanResultList getStyleAll() throws SQLException {
		return super.queryMapBeanResultList(" SELECT * FROM " + this.getMainTableName() + " ORDER BY 1");

	}


}
