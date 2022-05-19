package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.core.Item.Stat;

class ItemTest {
	
	
	
	
	
	@Test
	public void testItem() {
		Item item = new Item(0, "meth", 2, null, 10, 2);
		
		Assertions.assertEquals("meth", item.getName());
		Assertions.assertEquals(2, item.getAmount());
	}
	
	public void testUseItem() {
		int amount = 2;
		Item item = new Item(0, "meth", 2, null, 10, 2);
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200);
		item.useItem(monster);
		
		Assertions.assertEquals(0, monster.setMaxHealth(amount));
		
	}
	


}
