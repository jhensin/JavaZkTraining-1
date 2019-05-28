package com.tp.training.ctrler;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.zul.Elistbox;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TraningGeneralElistboxActionHandler;

/**
 *
 * @author weifang 2019/5/22 品牌基本資料的Elistbox
 *
 */

public class BrandElistboxController extends TrainingBaseComposer {
	/* 宣告同型態的屬性，屬性名與該元件 ID 相同，並加上 @Wire */
	/* 若屬性名需要與 ID 名不同時，則可用 @Wire("#ID名") 來宣告 */
	/* 多重 ID Space 時，則要再加上 ID Space 元件名 @Wire("#IDSpace元件名 #ID名") */
	@Wire
	private Elistbox masterLbox;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);

		this.masterLbox.setActionHandler(new masterActionHandler());
		// 以下兩行是預設帶出資料， 若不需要則不用寫
//		MapBeanResultList data = TrainingDAOFactory.getWtBrandDao().brandAll();
//		this.masterLbox.setModel(new ListModelList<>(data));

	}

	@Override
	protected boolean onQuery(MapBean bean) throws Exception {
		String brandNo = bean.get("BRAND_NO");
		String brandNm = bean.get("BRAND_NAME");
		// 查詢條件不合法，回傳 false 以避免關閉查詢視窗
//		if (brandNo.equals(null) || brandNm.equals(null)) {
//			return false;
//		}
		MapBeanResultList data = TrainingDAOFactory.getWtBrandDao().queryByBrand(brandNo, brandNm);
		this.masterLbox.setModel(new ListModelList<>(data));
		return true;
	}

	private class masterActionHandler extends TraningGeneralElistboxActionHandler {
		public masterActionHandler() {
			super(TrainingDAOFactory.getWtBrandDao(), false);
		}
	}

}
