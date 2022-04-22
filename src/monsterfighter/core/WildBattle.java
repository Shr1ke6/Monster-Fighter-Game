package monsterfighter.core;

import java.util.List;

public class WildBattle extends Battle{

	public WildBattle(List<Monster> monsters) {
		for (int i = 0; i < 3; i++) 
        {
            int index = (int)(Math.random() * monsters.size());
     
        }
		
	}
}
