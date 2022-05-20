package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import monsterfighter.core.Monster;
import monsterfighter.core.RandomEvent;

class RandomEventTest {
	

	

	
	void testRandomEvent() {

	
	}
	@Test
	public void testMonsterLevelUp() {
		

		
		ArrayList<Monster> party = new ArrayList<>();
		
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		party.add(monster);
		party.add(monster1);
		party.add(monster2);
		
		RandomEvent randomEvent = new RandomEvent(party);
		randomEvent.monsterLevelUp(party);
		

	}
	
	@Test
	public void testMonsterLeaves() {

		ArrayList<Monster> party = new ArrayList<>();
		
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		party.add(monster);
		party.add(monster1);
		party.add(monster2);
		
		RandomEvent randomEvent = new RandomEvent(party);
		randomEvent.monsterLeaves(party);
	
		
	}
	
	@Test
	public void testMonsterJoins() {
		
		ArrayList<Monster> party = new ArrayList<>();
		
		Monster monster = new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster1 = new Monster(1, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		Monster monster2 = new Monster(2, "Fireboy", Monster.Type.FIRE, 50, 20, 200);
		party.add(monster);
		party.add(monster1);
		party.add(monster2);
		
		RandomEvent randomEvent = new RandomEvent(party);
		randomEvent.monsterJoins(party);
		
		Assertions.assertEquals(false, randomEvent.getMonsterJoins());
		
	}
}
