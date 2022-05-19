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
	
	private int goldBalance = 1;
	int gold = 200;
	private int totalGold = 0;
	List<Item> items = new ArrayList<>();

	Player player = new Player(name, items);
	private List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();
	
	
	public PlayerTest() {
		
	}
	
	//player name and inventory test
	@Test
	public void testPlayer() {
		
		Assertions.assertEquals("pep", player.getName());
		Assertions.assertEquals(0, player.getInventory().size());
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
		player.setGoldBalance(gold);
		Assertions.assertEquals(1, goldBalance);

		
	}

	@Test
	public void inventoryIsEmpty() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(0, "Small Potion", 40, Item.Stat.CURRENTHEALTH, 25, 3));
		items.add(new Item(1, "Big Potion", 80, Item.Stat.CURRENTHEALTH, 50, 2));
		items.add(new Item(2, "Huge Potion", 120, Item.Stat.CURRENTHEALTH, 80, 1));
		items.add(new Item(3, "Attack Snack", 5, Item.Stat.ATTACK, 50, 1));
		items.add(new Item(4, "Max Health Snack", 10, Item.Stat.MAXHEALTH, 50, 1));
		items.add(new Item(5, "Revive candy", 40, Item.Stat.STATUS, 60, 1));
		Player player = new Player(name, items);
		player.addItemToInventory(items.get(0));
		Assertions.assertEquals(false, player.inventoryIsEmpty());
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
		player.switchMonsters(0, 1);
		Assertions.assertEquals(monster1, player.getParty().get(0));
		Assertions.assertEquals(monster, player.getParty().get(1));
	}
	
	@Test
	public void testPartyFainted() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		player.addMonsterToParty(monster);
		player.addMonsterToParty(monster1);
		player.partyFainted();
		
		Assertions.assertEquals(false, player.partyFainted());
		
	}
	
	@Test
	public void testGetConsciousMonsters() {
		player.getConsciousMonsters();
		Assertions.assertEquals(0, player.getConsciousMonsters());
	}
	
	@Test
	public void testInventoryNumItems() {
		player.inventoryNumItems();
		Assertions.assertEquals(0, player.inventoryNumItems());
	}
		
	
			
	
	
	
	

}
