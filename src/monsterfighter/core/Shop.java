package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {

	private ArrayList<ArrayList<Purchasable>> shopInventory;
	
	// Random number generator
	private Random rng = new Random();
	
	public Shop() {
		this.shopInventory = new ArrayList<ArrayList<Purchasable>>();
	}
	
	public void fillShop(int day, List<Item> items, List<Monster> monsters) {
		ArrayList<Integer> monsterIndices = new ArrayList<Integer>();
		for (int i = 0; i < monsters.size(); i++) {
			monsterIndices.add(i);
		}
		if (shopInventory.size() > 0) {
			shopInventory.clear();
		}
		for (int i = 0; i <= items.size() + 2; i++) {
			shopInventory.add(new ArrayList<Purchasable>());
			if (i > 2) {
				for (int j = 0; j < ((items.get(i-3).getShopQuantity()) * ((int) Math.ceil((double)day / 5))) ; j++) {
					shopInventory.get(i).add(new Item(items.get(i-3)));
				}
			} else {
				int randomNumber = rng.nextInt(monsterIndices.size());
				Monster monster = new Monster(monsters.get(monsterIndices.get(randomNumber)));
				monster.scaleMonster(day - 1);
				shopInventory.get(i).add(monster);
				monsterIndices.remove(randomNumber);
			}
		}
	}
	
	public ArrayList<ArrayList<Purchasable>> getShopInventory() {
		return shopInventory;
	}
	
	/**
	 * Checks to see if the shop is empty
	 * @return isEmpty States whether the shop {@link shop} is empty or not
	 */
	public boolean shopIsEmpty() {
		boolean isEmpty = true;
		for (ArrayList<Purchasable> purchasableObject : shopInventory) {
			if (!purchasableObject.isEmpty()) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	public void removeObject(Purchasable object) {
		for (int i = 0; i < shopInventory.size(); i++) {
			if (object.getClass().equals(shopInventory.get(i).get(0).getClass()) && object.getIndex() == shopInventory.get(i).get(0).getIndex()) {
				shopInventory.get(i).remove(0);
				if (shopInventory.get(i).size()==0) {
					shopInventory.remove(i);
				}
				break;
			}
		}
	}
	
}
