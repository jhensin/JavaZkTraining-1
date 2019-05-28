package com.tp.training.zul;

import com.tp.baselib.model.MapBeanDAO;
import com.tp.webplugins.admin.zul.GeneralElistboxActionHandler;

/**
 *
 * @author weifang  設計原理：在原有的 ActionHandler 的基礎上，再封裝一些重複性的動作，以簡化開發要寫的程式碼
 */

public class TraningGeneralElistboxActionHandler extends GeneralElistboxActionHandler {

	public TraningGeneralElistboxActionHandler(MapBeanDAO dao) {
		super(dao);
	}

	public TraningGeneralElistboxActionHandler(MapBeanDAO dao, boolean needDataLog) {
		super(dao, needDataLog);
	}

}
