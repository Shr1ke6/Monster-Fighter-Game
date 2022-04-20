package monsterfighter.core;

public class Monster{
	
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
	private String nickname;
	private final Type type;
	private int maxHealth;
	private int attack;
	private int currentHealth;
	private final int buyPrice;
	private final int sellPrice;
	
	public Monster(String name, Type type, int maxHealth, int attack, int currentHealth, int buyPrice, int sellPrice) {
		this.name = name;
		this.nickname = name;
		this.type = type;
		this.maxHealth = maxHealth;
		this.attack = attack;
		this.currentHealth = currentHealth;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Type getType() {
		return type;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getAttack() {
		return attack;
	}
	
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public int getBuyPrice() {
		return buyPrice;
	}
	
	public int getSellPrice() {
		return sellPrice;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setMaxHealth(int healthBuff) {
		maxHealth += healthBuff;
	}
	
	public void setAttack(int attackBuff) {
		attack += attackBuff;
	}

	public void heal(int heal) {
		currentHealth += heal;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	
	public void receiveDamage(int damage) {
		currentHealth -= damage;
		if currentHealth >
	}
	

	@Override
	public String toString() {
		return "Monster: " + name + " Nickname: " + nickname + " Type: " + type.value + " Health: " + currentHealth + "/" + maxHealth + " Attack: " + attack;
	}


}
