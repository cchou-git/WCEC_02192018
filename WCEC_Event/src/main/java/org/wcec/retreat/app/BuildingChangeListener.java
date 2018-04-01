package org.wcec.retreat.app;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.wcec.retreat.entity.BuildingTbl;
import org.wcec.retreat.entity.RoomTbl;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Grid;

public class BuildingChangeListener implements ValueChangeListener<Set<String>> {

	Grid<RoomTbl> theGrid;
	List<BuildingTbl> buildingList;
	BuildingTbl selectedBuilding;
	
	public BuildingChangeListener(Grid<RoomTbl> aGrid, List<BuildingTbl> bList) {
		theGrid = aGrid;
		buildingList = bList;
	}
	
	private void populateGrid(String aBuildingName) {
		for (BuildingTbl each : buildingList) {
			if (each.getName().equalsIgnoreCase(aBuildingName)) {
				Set<RoomTbl> roomSet = each.getRoomTbls();
				theGrid.setItems(roomSet);
				selectedBuilding = each;
			}
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent<Set<String>> event) {
		Set <String> valueSet = event.getValue();
		if (valueSet.size() > 0 ) {
			Iterator itr = valueSet.iterator();
			while (itr.hasNext()) {
				String buildingName = (String) itr.next();
				this.populateGrid(buildingName);
			}
		}
		int debug = 1;
		debug++;
	}

	public BuildingTbl getSelectedBuilding() {
		return selectedBuilding;
	}
	
	public RoomTbl getSelectedRoom() {
		RoomTbl result = null;
		Set<RoomTbl> aSet = theGrid.getSelectedItems();
		if (aSet != null && aSet.size() > 0) {
			Iterator<RoomTbl> itr = aSet.iterator();
			while (itr.hasNext()) {
				result = itr.next();
				break;
			}
		}
		return result;
	}

}
