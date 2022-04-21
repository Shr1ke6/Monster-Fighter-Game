package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;

public class Shop {
	
	private ArrayList<ArrayList<Purchasable>> shopInventory = new ArrayList<ArrayList<Purchasable>>();
	
	public Shop(List<Monster> allMonsters, List<Item> allItems) {
		for (int i = 0; i <= allItems.size(); i++) {
			shopInventory.add(new ArrayList<Purchasable>());
			if (i > 0 && i <= allItems.size()) {
				
			} else ()
		}
	}
	
	public void refillShop(allMonsters, allItems) {
		ArrayList<monsters>newMonsters
		shopInventory
	}
	
	public ArrayList<ArrayList<Purchasable>> getShopInventory() {
		return shopInventory;
	}
}

	