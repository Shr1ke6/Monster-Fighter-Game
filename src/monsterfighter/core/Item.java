package monsterfighter.core;

public class Item implements Purchasable{
	
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
	private final int buyPrice;
	private final int sellPrice;
	private final int storeQuantity;
	
	public Item(int index, String name, int amount, Stat stat, int buyPrice, int storeQuantity) {
		this.index = index;
		this.name = name;
		this.amount = amount;
		this.stat = stat;
		this.buyPrice = buyPrice;
		this.storeQuantity = storeQuantity;
		sellPrice = buyPrice / 2;
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
	
	public int getStoreQuantity() {
		return storeQuantity;
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
        		throw new IllegalStateException("Cannot use " + name + ", " + monster.getNickname() + "'s health is already at max\n");
        	}
            monster.receiveHealth(amount);
        	break;
        case STATUS:
        	if (monster.getStatus().name == "Conscious") {
        		throw new IllegalStateException("Cannot use " + name + ", " + monster.getNickname() + "'s health is not unconscious\n");
        	}
        	monster.revive(amount);
        	break;
		}
	}
	
	@Override
	public String toString() {
		return "Item: " + name + " Effect: increases " + stat.name + " by " + amount;
	}

	@Override
	public String shopDescription() {
		return "[Buy Price: " + buyPrice + "| Sell Price: " + sellPrice + "] " + toString();
	}

	@Override
	public int getBuyPrice() {
		return buyPrice;
	}

	@Override
	public int getSellPrice() {
		return sellPrice;
	}
	
}
