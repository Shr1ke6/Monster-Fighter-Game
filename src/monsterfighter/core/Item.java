package monsterfighter.core;

public class Item{
	
	public enum Stat {
		MAXHEALTH("Max Health"),
		ATTACK("Attack"),
		CURRENTHEALTH("Current Health"),
		STATUS("Consciousness");
		
		public final String name;
		
		Stat(String name) {
			this.name = name;
		} 
	}
	
	private final String name;
	private final int amount;
	private final Stat stat;

	public Item(String name, int amount, Stat stat) {
		this.name = name;
		this.amount = amount;
		this.stat = stat;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Stat getStat() {
		return stat;
	}
	
	public void useItem(Monster monster) {
		switch (stat) {
        case MAXHEALTH:
        	monster.setMaxHealth(amount);
        	break;
        case ATTACK:
        	monster.setAttack(amount);
        	break;
        case CURRENTHEALTH:
        	monster.receiveHealth(amount);
        	break;
        case STATUS:
        	monster.revive(amount);
        	break;



    }
	}
	
	@Override
	public String toString() {
		return "Item: " + name + " Effect: increases " + stat + " by " + amount;
	}
	
}
