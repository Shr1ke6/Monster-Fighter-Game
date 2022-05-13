package monsterfighter.core;

import java.awt.Color;
import java.util.Random;

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
		NORMAL("NORMAL", Color.GRAY),
	    FIRE("FIRE", Color.RED),
	    WATER("WATER", Color.BLUE),
	    GRASS("GRASS", new Color(34,139,34)),
	    LIGHT("LIGHT", new Color(255, 215, 0)),
	    DARK("DARK", Color.BLACK);

	    public final String value;
	    public final Color colour;

	    Type(String value, Color colour) {
	        this.value = value;
	        this.colour = colour;
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
	private Random rng = new Random();
	
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
	 * Compares two different {@link Monster}'s and returns true
	 * if they have the same values
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
	
	public void scaleMonster(int scalar) {
		for (int i = 0; i < scalar; i++) {
			levelUp();
		}
	}
	
	public void levelUp() {
		int randomNumber = rng.nextInt(2);
		if (randomNumber == 0) {
			setMaxHealth(20);
			receiveHealth(20);
		} else {
			setAttack(10);
		}
	}
	
	/**
	 * Text displayed for a {@link Monster} while battling
	 * 
	 * @return String description
	 */
	public String battleDescription() {
		String description = name + " " + currentHealth + "/" + maxHealth;
		return description;
	}
	
	public String basicDescription() {
		String description = "Monster: " + name + " Type: " + type.value + " Health: " + maxHealth + " Attack: " + attack;
		return description;
	}
	
	public String toolTipText() {
		String text = "<html>Nickname: " + nickname + "<br>Monster: " + name + "<br>Type: " + type.value + "<br>Health: " + currentHealth + "/" + maxHealth + "<br>Attack: " + attack + "</html>";
		if (status.name == "Fainted") {
			text = "<html>[FAINTED]<br>" + text.substring(6);
		}
		return text;
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
