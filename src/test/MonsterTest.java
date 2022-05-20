package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Monster;
import monsterfighter.core.Monster.Status;
import monsterfighter.core.Monster.Type;

class MonsterTest {

	@Test
	public void testMonster() {
		
	}
	
	@Test
	public void testSetNickname() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		String nickname = "FlameMan";
		monster.setNickname(nickname);
		
		Monster monster1 = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		String nickname1 = "";
		monster1.setNickname(nickname1);
		
		Assertions.assertEquals("FlameMan", monster.getNickname());
		Assertions.assertEquals("Fireboy", monster1.getNickname());
	}
	
	@Test
	public void testReceiveHealth() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		int heal = 2;
		int damage = 5;
		monster.receiveDamage(damage);
		monster.receiveHealth(heal);
		
		Monster monster1 = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		int heal1 = 10;
		int damage1 = 2;
		monster.receiveDamage(damage1);
		monster.receiveHealth(heal1);
	
		Assertions.assertEquals(50, monster1.getCurrentHealth());
	}
	
	@Test 
	public void testReceiveDamage() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		int damage = 60;
		monster.receiveDamage(damage);
		Assertions.assertEquals(0, monster.getCurrentHealth());
		Assertions.assertEquals(Status.FAINTED, monster.getStatus());
		Assertions.assertEquals(true, monster.getFaintedToday());
		
	}
	@Test 
	public void testRevive() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		int damage = 60;
		monster.receiveDamage(damage);
		int heal = 2;
		monster.revive(heal);
		Assertions.assertEquals(2, monster.getCurrentHealth());
		Assertions.assertEquals(Status.CONSCIOUS, monster.getStatus());
	}
	
	@Test
	public void testAttack() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster enemyMonster = new Monster(0, "FireChump", Monster.Type.FIRE, 50, 20, 200);
		monster.attack(enemyMonster);
		
		Assertions.assertEquals(30, enemyMonster.getCurrentHealth());
		
	}
	
	//randoness
	public void testScaleMonster() {
		int scalar = 2;
		Monster enemyMonster = new Monster(0, "FireChump", Monster.Type.FIRE, 50, 20, 200);
		enemyMonster.scaleMonster(scalar);
		
		Assertions.assertEquals(60, enemyMonster.getCurrentHealth());
		
	}
	
	//randomness
	public void testlevelUp() {

		Monster enemyMonster = new Monster(0, "FireChump", Monster.Type.FIRE, 50, 20, 200);
		enemyMonster.levelUp();
		
		Assertions.assertEquals(25, enemyMonster.getAttack());
		
	}
	
	@Test
	public void testBattleDescription() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		String description = "Fireboy" + " " + "50" + "/" + "50";
		
		Assertions.assertEquals(description, monster.battleDescription());
	}
	
	@Test
	public void testBasicDescription() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		String description = "Monster: " + "Fireboy" + " Type: " + "FIRE" + " Health: " + "50" + " Attack: " + "20";
		
		Assertions.assertEquals(description, monster.basicDescription());
	}
	
	@Test
	public void testTooltipText() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		int damage = 60;
		
		monster.receiveDamage(damage);
		
		Assertions.assertEquals(monster.tooltipText(), monster.tooltipText());
		
	}
	
	
	
	@Test
	public void TestBuyDescription() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		String description = "[Buy Price: " + "200" + "] " + monster.basicDescription();
		
		Assertions.assertEquals(description, monster.buyDescription());
		
		
		
	}

	@Test
	public void TestSellDescription() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		String description = "[Sell Price: " + "100" + "] " + monster.basicDescription();
		
		Assertions.assertEquals(description, monster.sellDescription());
		
		
	}
	

	@Test
	public void TestToString() {
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
	
		
		Assertions.assertEquals(monster.toString(), monster.toString());
		
	}
	

}
