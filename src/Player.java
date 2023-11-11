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
     * Returns all traits on the battlefield regardless of if they give the 
     * trait bonus or not
     * @return a hashmap containing all the traits and how many there are
     
    public HashMap<String, Integer> getTraits() {
    	HashMap<String, Integer> map = new HashMap<>();
    	ArrayList<String> namesAlreadyAdded = new ArrayList<String>();
    	for (int i = 0; i < 7; i++) {
    		if (battleField[i] == null) {
    			continue;
    			// checks if the map contains the type already and if a champion
    			// with the same name is already in the map

    		} else if(map.containsKey(battleField[i].getType()) &&
    				namesAlreadyAdded.contains(battleField[i].getName())){

    			int alreadyIn = map.get(battleField[i].getType());
        		map.put(battleField[i].getType(), alreadyIn += 1);
        		namesAlreadyAdded.add(battleField[i].getName());
    		} else {
    			map.put(battleField[i].getType(), 1);
    			namesAlreadyAdded.add(battleField[i].getName());
    		}
    	}
    	return map;
    } 
    
    /**
     * Returns all the traits that can give bonuses on the board
     * @return a hashmap containing all the traits and how many there are
     
    public HashMap<String, Integer> getActiveTraits(){
    	HashMap<String, Integer> map = getTraits();
    	HashMap<String, Integer> newMap = new HashMap<>();
    	// Only gives trait bonus if 3 or more different champions
    	// of the same typeon battlefield
    	for (Map.Entry mapElement: map.entrySet()) {
    		int timesIn = (int) mapElement.getValue();
    		if (timesIn == 3) {
    			newMap.put((String) mapElement.getKey(),(int) mapElement.getValue());
    		}
    	}
		return newMap;
    }
    */
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
    	roundsSince += 1;
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