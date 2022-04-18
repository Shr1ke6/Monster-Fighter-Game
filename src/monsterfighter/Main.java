package monsterfighter;

import java.util.ArrayList;
import java.util.List;

public class Main {
	

	public static void main(String[] args) {
		final List<Monster> monsta = new ArrayList<>(6);
		
		monsta.add(new Monster("fireboy", "fire", 20, 50, 50));
		monsta.add(new Monster("watergirl", "water", 15, 60, 60));
		monsta.add(new Monster("BrightStar", "light", 10, 70, 70));
		monsta.add(new Monster("DarkStar", "dark", 20, 50, 50));
		monsta.add(new Monster("dirt", "grass", 10, 70, 70));
		monsta.add(new Monster("normie", "normal", 14, 65, 65));

	}

}
