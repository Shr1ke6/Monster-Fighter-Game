package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Battle;
import monsterfighter.core.Battles;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Shop;

class WildBattleTest {
	

	@Test
	public void WildBattle() {
		
		
		List<Item> reward = new ArrayList<>();
		reward.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3));
		
		List<Monster> monsters = new ArrayList<>();
		monsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 40, 20, 200));
	
	
		Battle wildBattle = null;
		Assertions.assertEquals(monsters, wildBattle.getMonsters());
	}
	
	@Test
	public void testToString() {
		
		
	}

}
