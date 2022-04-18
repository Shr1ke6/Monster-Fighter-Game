package monsterfighter.core;

public class Monster {
	
	public enum Type {
		NORMAL("Normal"),
	    FIRE("Fire"),
	    WATER("Water"),
	    GRASS("Grass"),
	    LIGHT("Light"),
	    DARK("Dark");

	    public final String value;

	    Type(String value) {
	        this.value = value;
	    }
	}
	
	private final String name;
	private final Type type;
	private int maxHealth;
	private int damage;
	private int currentHealth;
	
	public Monster(String name, Type type, int maxHealth, int damage, int currentHealth) {
		this.name = name;
		this.type = type;
		this.maxHealth = maxHealth;
		this.damage = damage;
		this.currentHealth = currentHealth;
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getDamage() {
		return damage;
	}
	
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public void setMaxHealth(int healthBuff) {
		maxHealth += healthBuff;
	}
	
	public void setDamage(int damageBuff) {
		damage += damageBuff;
	}

	public void setCurrentHealth(int healthChange) {
		currentHealth += healthChange;
	}
	

	@Override
	public String toString() {
		return "Monster: " + name + " Type: " + type.value + " Health: " + currentHealth + "/" + maxHealth + " Damage: " + damage 
				;
	}

}
