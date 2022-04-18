package monsterfighter.core;

public class Item{
	private final String name;
	private final int amount;
	private final String stat;

	public Item(String name, int amount, String stat) {
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
	
	public String getStat() {
		return stat;
	}
	
	@Override
	public String toString() {
		return "Item: " + name + " Effect: increases " + stat + " by " + amount;
	}
	
}
