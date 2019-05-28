package com.tp.training.dao;

import java.sql.SQLException;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;

public class BrandSeasonDAO extends TrainingDAO {

	@Override
	public String getMainTableName() {
		return "WT_BRAND_SEASON";
	}

	// 從WT_BRAND_SEASON 中抓取出SEASON_NO = ? 的資料
	public MapBean getBrandSeason(String brandSeason) throws SQLException {
		return super.queryFirst(" SELECT * FROM " + this.getMainTableName() + " WHERE SEASON_NO = ?", brandSeason);
	}

	// 從 WT_BRAND_SEASON 抓取所有資料
	public MapBeanResultList brandSeasonAll() throws SQLException {
		return this.queryMapBeanResultList(" SELECT * FROM " + this.getMainTableName() + " ORDER BY 1");

	}

}
