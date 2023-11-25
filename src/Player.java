package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Creatures.Creature;
import Creatures.Wolf;

/**
 * Creates a player object that hasA battleField and bench that contain all the champions
 *      owned by the player.
 * Also tracks health and gold count.
 */
public class Player {
    private final Card[] battleField;
    private final Card[] bench;
    private int health;
    private int gold;
    private int level;
    private Shop shop;
    private int attacking_card;
    private int roundsSince;

    /**
     * Creates player object with default stats and zero champions.
     * Initializes battleField array to only allow 7 champions, located by index.
     */
    public Player() {
        battleField = new Card[10];
        bench       = new Card[15];
        health      = 30;
        gold        = 1;
        shop 		= new Shop();
        level 		= shop.getLevel();
        attacking_card  	= 0;
        roundsSince = 0;
    }
    
    /*
     * Used to set which card in player deck is attacking first
     */
    public void set_attack_card(int position) {
    	attacking_card = position;
    }
    
    /*
     * Used to get which card in player deck should be attacking first
     */
    public int get_attack_card() {
    	return attacking_card;
    }

    /**
     * Reduces the players health by given decrement.
     * @param decrement integer amount by which health is decremented.
     */
    public void loseHealth(int decrement) {
        health -= decrement;
    }

    /**
     * Increases the player's health by given increment.
     * @param increment integer amount by which health is incremented.
     */
    public void gainHealth(int increment) {
        health += increment;
    }

    /**
     * Decreases the player's gold by the given amount.
     * @param amount integer amount by which gold is decremented.
     */
    public void spendGold(int amount) {
        gold -= amount;
    }

    /**
     * Increases the player's gold by the given amount.
     * @param amount
     */
    public void setGold(int amount) {
        gold = amount;
        if(gold >= 10) {
        	gold = 10;
        }
    }
    
    /**
     * returns how much gold the player has
     * @return
     */
    public int getGold() {
    	return gold;
    }

    /**
     * Allows access to player's champions in the bench.
     * @return Arraylist of champions.
     */
    public Card[] getBench() {
        return bench;
    }
    
  
     
   
    
    /**
     * Allows access to player's champions on the battlefield.
     * @return Array of champions.
     */
    public Card[] getBattleField() {
        return battleField;
    }
    
    /**
     * returns the players current level
     * @return
     */
    public int getLevel() {
    	return level;
    }
    
    /**
     * returns the players current health
     * @return
     */
    public int getHealth() {
    	return health;
    }
    
    /**
     * levels up the player by one level with gold
     * @param goldRequired  the gold required to upgrade that level
     * @return              the level after the levelUp
     */
    public int levelup() {
    	int goldRequired = level + 4 - roundsSince;
    	if (goldRequired <= gold && level <= 5) {
    		spendGold(level + 4 - roundsSince);
        	shop.levelUp();
        	level += 1;
        	roundsSince = 0;
    	}
    	return level;
    }
    
    public void incRoundSince() {
    	//bug check later it doesnt go negative
    	if(level + 4 - roundsSince == 0) {
    		//idk
    	}else {
    		roundsSince += 1;
    	}
    }
    
    public int getRoundsSince() {
    	return roundsSince;
    }
    
    /**
     * Gets the shop that the player is using
     * @return
     */
    public Shop getShop() {
    	return shop;
    }
    
    /**
     * Buys the character denoted by the index in the shop, removes champion from
     * shop if player buys it
     * @param index
     */
    public Card buyCharacter(int index) {
    	try {
    		Card toBuy = shop.getShop()[index];
    		int i = 0;
            while (bench[i] != null)
                i++;
        	if (i <= 7 && toBuy != null) {
        		if (gold >= toBuy.getPrice()) {
        			bench[i] = toBuy;
        			spendGold(toBuy.getPrice());
        			shop.getShop()[index] = null;
        		}
        	}
        	return toBuy;
    	} catch (Exception ArrayIndexOutOfBoundsException){
    		//not sure what this is yet
    		return null;
    	}
    }
    
}