package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Player;
import monsterfighter.core.Item.Stat;

class ItemTest {
	Item item = new Item(0, "meth", 2, null, 10, 2);
	
	
	
	
	@Test
	public void testItem() {
		
		Assertions.assertEquals("meth", item.getName());
		Assertions.assertEquals(2, item.getAmount());
	}
	
	public void testUseItem() {
		
	}
	

	

}
