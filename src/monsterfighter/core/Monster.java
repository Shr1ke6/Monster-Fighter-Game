package monsterfighter.core;

public class Monster implements Purchasable{
	
	public enum Status {
		CONSCIOUS("Conscious"),
		FAINTED("Fainted");
		
		public final String name;
		
		Status(String name) {
			this.name = name;
		}
		
	}
	
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
	private final int index;
	private final String name;
	private String nickname;
	private final Type type;
	private int maxHealth;
	private int attack;
	private int currentHealth;
	private Status status;
	private final int buyPrice;
	private final int sellPrice;
	private boolean faintedToday = false;
	private int wins = 0;
	
	
	public Monster(int index, String name, Type type, int maxHealth, int attack, int buyPrice) {
		this.index = index;
		this.name = name;
		this.nickname = name;
		this.type = type;
		this.maxHealth = maxHealth;
		this.attack = attack;
		this.currentHealth = maxHealth;
		this.status = Status.CONSCIOUS;
		this.buyPrice = buyPrice;
		sellPrice = buyPrice / 2;
	}
	
	public Monster(Monster c) {
		this.index = c.index;
		this.name = c.name;
		this.nickname = c.name;
		this.type = c.type;
		this.maxHealth = c.maxHealth;
		this.attack = c.attack;
		this.currentHealth = c.maxHealth;
		this.status = c.status;
		this.buyPrice = c.buyPrice;
		sellPrice = buyPrice / 2;
	}
	
	@Override
	public int getIndex() {
		return index;
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
	
	public Status getStatus() {
		return status;
	}
	
	@Override
	public int getBuyPrice() {
		return buyPrice;
	}
	
	@Override
	public int getSellPrice() {
		return sellPrice;
	}
	
	public boolean getFaintedToday() {
		return faintedToday;
	}
	
	public int getWins() {
		return wins;
	}
	
	/**
	 * 
	 * 
	 * @param monster Monster object that is compared against
	 * @return Boolean 
	 */

	public boolean sameMonster(Monster monster) {
		return this.name.equals(monster.name) &&
				this.index == monster.index  &&
				this.name.equals(monster.name) &&
				this.nickname.equals(monster.name) &&
				this.type.equals(monster.type) &&
				this.maxHealth == monster.maxHealth &&
				this.attack == monster.attack &&
				this.currentHealth == monster.maxHealth &&
				this.status == monster.status &&
				this.buyPrice == monster.buyPrice;
	}
	
	public void setFaintedToday(boolean fainted) {
		this.faintedToday = fainted;
	}
	
	
	public void resetWins() {
		wins = 0;
	}
	
	public void addWin(int win) {
		wins += win;
	}

	public void setNickname(String nickname) {
		if (nickname.length() > 0) {
			this.nickname = nickname;
		}
	}
	
	public void setMaxHealth(int healthBuff) {
		maxHealth += healthBuff;
	}
	
	public void setAttack(int attackBuff) {
		attack += attackBuff;
	}

	public void receiveHealth(int heal) {
		currentHealth += heal;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
		}
	}
	
	public void receiveDamage(int damage) {
		currentHealth -= damage;
		if (currentHealth <= 0) {
			currentHealth = 0;
			status = Status.FAINTED;
			faintedToday = true;
		}
	}
	
	public void revive(int heal) {
		status = Status.CONSCIOUS;
		receiveHealth(heal);
	}
	
	public void attack(Monster enemyMonster) {
		enemyMonster.receiveDamage(attack);
	}
	
	public String battleDescription() {
		String description = name + " " + currentHealth + "/" + maxHealth;
		return description;
	}
	
	public String basicDescription() {
		String description = "Monster: " + name + " Type: " + type.value + " Health: " + maxHealth + " Attack: " + attack;
		return description;
	}
	
	public String shopDescription() {
		String description = "[Buy Price: " + buyPrice + " | Sell Price: " + sellPrice + "] " + basicDescription();
		return description;
	}
	
	@Override
	public String buyDescription() {
		String description = "[Buy Price: " + buyPrice + "] " + basicDescription();
		return description;
	}

	@Override
	public String sellDescription() {
		String description = "[Sell Price: " + sellPrice + "] " + basicDescription();
		return description;
	}
	
	@Override
	public String toString() {
		String description = "Nickname: " + nickname + " Monster: " + name + " Type: " + type.value + " Health: " + currentHealth + "/" + maxHealth + " Attack: " + attack;
		if (status.name == "Fainted") {
			description = "[FAINTED] " + description;
		}
		return description;
	}



}
