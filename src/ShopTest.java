import static org.junit.jupiter.api.Assertions.*;

import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.core.Purchasable;
import monsterfighter.ui.GameEnvironmentUi;

abstract class ShopTest implements GameEnvironmentUi{
    private GameEnvironment gameEnvironment;
	
	
	
	@Test
	public void sellTest() {
	List<Monster> party = gameEnvironment.getParty();
	party.size() == 0;
	assertEquals(.getParty());
	
	}
	
	
	@Test
	public void purchaseTest() {
	Monster testMonster = new Monster(100);
	int testgoldBalance = 100;
	assertEquals(0, testGoldBalance.getgoldBalance());
	
	}

	
	
	@Test
	public void fillShopTest() {
		final ArrayList<Monster> allMonstersCopy = new ArrayList<Monster>(allMonsters);
		shop.size() == 0;
		assertEquals(0, testAllMonstersCopy.getindex());
	}


	@Test
	public void shopIsEmptyTest() {
		Purchasable testItem = new Purchasable;
	    assertEquals(0, testItem.getIndex());
	}

}
