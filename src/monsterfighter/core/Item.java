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
	
	private final int index;
	private final String name;
	private final int amount;
	private final Stat stat;

	public Item(int index, String name, int amount, Stat stat) {
		this.index = index;
		this.name = name;
		this.amount = amount;
		this.stat = stat;
	}
	
	public int getIndex() {
		return index;
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
        	if (monster.getMaxHealth() == monster.getCurrentHealth()) {
        		System.out.println("Cannot use potion, monster health already max");
        	} else {
            	monster.receiveHealth(amount);
        	}
        	break;
        case STATUS:
        	if (monster.getStatus().name == "Conscious") {
        		System.out.println("Cannot use revive, monster health above zero");
        	} else {
        		monster.revive(amount);
        	}
        	break;
		}
	}
	
	@Override
	public String toString() {
		return "Item: " + name + " Effect: increases " + stat.name + " by " + amount;
	}
	
}
