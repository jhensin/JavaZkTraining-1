package com.tp.training.ctrler;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.util.Multilingual;
import com.tp.baselib.zul.Egrid;
import com.tp.baselib.zul.InputsContainer.EditEvent;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Listbox;
import com.tp.baselib.zul.Listitem;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralListboxAndEgridActionHandler;

/**
 *
 * @author WEIFANG 2019/5/27 1.
 *
 */

public class BrandEgridWithQueryController extends TrainingBaseComposer {
	@Wire
	private Listbox indexLbox; // 左邊的 Listbox， DAO 取得的資料，是放到 indexLbox 索引中

	@Wire
	private Egrid masterGrid; // egrid id

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		this.masterGrid.setActionHandler(new MasterActionHandler());
		super.doQuery();
	}

	@Override
	protected boolean onQuery(MapBean bean) throws Exception {
		String brandNo = bean.get("BRAND_NO");
		String brandNm = bean.get("BRAND_NAME");
		// 查詢條件不合法，回傳 false 以避免關閉查詢視窗
//		if() {
//			return false;
//		}
		MapBeanResultList data = TrainingDAOFactory.getWtBrandDao().queryByBrand(brandNo, brandNm);
		this.indexLbox.setModel(new ListModelList<>(data));
		return true;
	}

	@Listen("onItemFocus=#indexLbox")
	// @Listen("事件名=#元件ID名")
	// 所標示的 method，即為可處理標示的事件
	public void onIndexItemFocus() throws Exception {
		Listitem focusedItem = this.indexLbox.getFocusedItem();
		MapBean bean = focusedItem.getValue();
		this.masterGrid.setBean(bean);
	}

	private class MasterActionHandler extends TrainingGeneralListboxAndEgridActionHandler {
		public MasterActionHandler() {
			super(TrainingDAOFactory.getWtBrandDao(), false);
		}

		@Override
		protected String[][] getUkColNames() {
			return new String[][] { { "BRAND_NO" }, { "BRAND_CODE" } };
		}

		@Override
		public void onBeforeSave(EditEvent event) throws Exception {
			// 保存之前判斷品牌簡碼BRAND_CODE 是不是兩碼英數組合
			super.onBeforeSave(event);
			Egrid grid = this.getEgrid();
			MapBean bean = grid.getBean();
			String brandCode = bean.get("BRAND_CODE");
			/* 品牌簡碼必須是2碼 */
			if (brandCode.length() != 2) {
				Component comp = grid.getChildByFld("BRAND_CODE"); // //取得該欄輸入元件
				throw new WrongValueException(comp,
						Multilingual.getByUserLocale("traning.msg.BrandCodeTwoDigits", true, true));

			}
			/* 品牌簡碼若不是兩碼英數則報錯 */
			if (!(isLetterOrDigit(brandCode))) {
				Component comp = grid.getChildByFld("BRAND_CODE"); // //取得該欄輸入元件
				throw new WrongValueException(comp,
						Multilingual.getByUserLocale("traning.msg.BrandCodeIsLetter", true, true));
			}
		}
	}

	public static boolean isLetterOrDigit(String str) {
		String regex = "([A-Za-z][0-9]|[0-9][A-Za-z])";
		return str.matches(regex);
	}
}
