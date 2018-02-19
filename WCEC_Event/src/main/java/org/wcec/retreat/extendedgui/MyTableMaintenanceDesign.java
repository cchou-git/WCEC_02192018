package org.wcec.retreat.extendedgui;

import java.util.ArrayList;

import com.vaadin.ui.ListSelect;
import java.util.List;

import org.wcec.retreat.domain.generated.WCECDomainTable;
import org.wcec.retreat.gui.TableMaintenanceDesign;

public class MyTableMaintenanceDesign extends TableMaintenanceDesign {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void populateTableName(List<WCECDomainTable> aList) {
		List<String> nameList = new ArrayList<String>();
		for (WCECDomainTable each: aList) {
			nameList.add(each.getTableName());
		}
		listSelectTable.setItems(nameList);	
	}
	
	public ListSelect getListSelect() {
		return listSelectTable;
	}
}
