package model;

import java.util.Map;

import entity.AddItem;

public class AddNewExpenceLogic {
	public boolean execute(Map<Integer,AddItem> expenceMap, String newExpence) {

		boolean b = false;

		for(Integer key : expenceMap.keySet()) {
			AddItem addItem = expenceMap.get(key);
			//すでに費目が存在する場合はtrueを返す
			if(newExpence.equals(addItem.getExpenceName())) {
				b = true;
			}
		} return b;
	}
}
