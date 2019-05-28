package com.tp.training.dao;

public class TrainingDAOFactory {
	private static final BrandDAO wtBrandDAO = new BrandDAO();
	private static final BrandSeasonDAO wtBrandSeasonDAO = new BrandSeasonDAO();
	private static final StyleDAO wtStyle = new StyleDAO();

	public static BrandDAO getWtBrandDao() {
		return wtBrandDAO;
	}

	public static BrandSeasonDAO getWtBrandSeasonDao() {
		return wtBrandSeasonDAO;
	}

	public static StyleDAO getWtStyle() {
		return wtStyle;
	}

}
