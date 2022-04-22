package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Shop{
	
	private ArrayList<ArrayList<Purchasable>> shopInventory = new ArrayList<ArrayList<Purchasable>>();
	
	public Shop(List<Monster> allMonsters, List<Item> allItems) {
		ArrayList<Monster> allMonstersCopy = new ArrayList<Monster>(allMonsters);
		for (int i = 0; i <= allItems.size() + 1; i++) {
			shopInventory.add(new ArrayList<Purchasable>());
			if (i > 1) {
				for (int j = 0; j < allItems.get(i-2).getStoreQuantity(); j++) {
					shopInventory.get(i).add(allItems.get(i-2));
				}
			} else {
				int randomNumber = ThreadLocalRandom.current().nextInt(0, allMonstersCopy.size());
				shopInventory.get(i).add(allMonstersCopy.get(randomNumber));
				allMonstersCopy.remove(randomNumber);
			}
		}
	}
	

	//public void refillShop(List<Monster> allMonsters, List<Item> allItems) {
	//	shopInventory.get(i).add(allMonsters.get(randomNumber));
	//}
	
	public void purchase(int shopID, List<Monster> party, List<Item> inventory, int goldBalance) {
		
		for(int i = 0; i < party.size(); i++) {
			if (party.get(i) >= 3) {
				throw new IllegalStateException("party full sell a monster before u buy");	
		} else if (party.get(i) <= 3) {
			goldBalance = goldBalance - Shop.get(shopID)get(buyPrice);
			party.add(shopID);
		} else {
			goldBalance = goldBalance - ItemGold;
			inventory.add(shopID);
		}

		
	}
		
	public ArrayList<ArrayList<Purchasable>> getShopInventory() {
		return shopInventory;
	}
}

	