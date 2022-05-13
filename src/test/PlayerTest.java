package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;

public class PlayerTest {
	String name = "pep";
	Player player = new Player(name, 6);
	private int goldBalance = 1;
	int gold = 200;
	private int totalGold = 0;
	final List<Item> items = new ArrayList<>(1);
	private final List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();
	
	



	public PlayerTest() {
		
	}
	
	//player name and inventory test
	@Test
	public void testPlayer() {
		
		Assertions.assertEquals("pep", player.getName());
		Assertions.assertEquals(6, player.getInventory().size());
	}
	@Test
	public void testAddMonsterToParty() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster3 = new Monster(3, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster4 = new Monster(4, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.addMonsterToParty(monster2);
		player.addMonsterToParty(monster3);
		
		Assertions.assertThrows(IllegalStateException.class, () -> player.addMonsterToParty(monster4));
	}
	@Test
	public void testRemoveMonsterFromParty() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster3 = new Monster(3, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.addMonsterToParty(monster2);
		player.addMonsterToParty(monster3);
		player.removeMonsterFromParty(monster2);
		player.removeMonsterFromParty(monster3);
		
		Assertions.assertEquals(2, player.getParty().size());
	}
	
	@Test
	public void testGetLeadingMonster() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fir", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fire", Monster.Type.FIRE, 50, 20, 200);
		Monster monster3 = new Monster(3, "Firebo", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.addMonsterToParty(monster2);
		player.addMonsterToParty(monster3);
		
		Assertions.assertEquals(monster, player.getParty().get(0));
	}
	
	@Test
	public void testSetGoldBalance() {
		goldBalance += gold;
		if (gold > 0) {
			totalGold += gold;
		}
		
		Assertions.assertEquals(201, goldBalance);
		Assertions.assertEquals(200, totalGold);
		
	}

	@Test
	public void inventoryIsEmpty() {
		items.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3));
		inventory.add((ArrayList<Item>) items);
		boolean isEmpty = true;
		for (ArrayList<Item> item : inventory) {
			if (!item.isEmpty()) {
				isEmpty = false;
			}
		}
		Assertions.assertEquals(false, isEmpty);
	}
	
	@Test
	public void testAddItemToInventory() {
		items.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3));
		inventory.add((ArrayList<Item>) items);
		Assertions.assertEquals(items, inventory.get(0));
		
	}
	@Test
	public void testRemoveItemFromInventory() {
		items.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3));
		inventory.add((ArrayList<Item>) items);
		inventory.remove((ArrayList<Item>) items);
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> inventory.get(0));
	}
	
		
		
	@Test
	public void testSwitchMonsters() {
		
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		Collections.swap(player.getParty(), 0, 1);
		Assertions.assertEquals(monster1, player.getParty().get(0));
		Assertions.assertEquals(monster, player.getParty().get(1));
	}
	
	@Test
	public void testPartyFainted() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		boolean fainted = true;
		for (int i = 0; i < player.getParty().size(); i++) {
			if (monster.getStatus() == Monster.Status.CONSCIOUS) {
				fainted = false;
			}
		}
		Assertions.assertEquals(false, fainted);
		
	}
	
	@Test
	public void testInventoryNumItems() {
		items.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3));
		inventory.add((ArrayList<Item>) items);
		int numItems = 0;
		for (ArrayList<Item> items: inventory) {
			for(Item item: items) {
				numItems += 1;
			}
		}
		Assertions.assertEquals(1, numItems);
	}
		
			
	
	
	
	

}
