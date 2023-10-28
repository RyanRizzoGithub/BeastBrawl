package src;

/**
 * Abstract class that all out other "Champion" will be based on. Features hp, atk,
 * name, and type.
 * 
 * @author abisg
 *
 */
public class Card {
	private int initialHp;
	private int initialAtk;
    private int hp;
    private int atk;
    private String name;
    private String type;
    private int price;
    private boolean initializedHp;
    private boolean initializedAtk;
    
    public Card() {
    	initializedHp = false;
    	initializedAtk = false;
    }
    
    /**
     * gets the current hp of the champion
     * @return the hp of the champion
     */
    public int getHp(){ return hp; }

    /**
     * gets the initial hp of the champion without buffs, never changes
     * @return
     */
    public int getInitialHp() { return initialHp; }
    
    /**
     * sets the hp of the champion
     * @param hp
     */
    public void setHp(int hp){ 
    	this.hp = hp;
    	if (initializedHp == false) {
    		initialHp = hp;
    		initializedHp = true;
    	}
    }
    
    /**
     * gets the current atk of the champion
     * @return the atk of the champion
     */
    public int getAtk(){ return atk; }
    
    /**
     * gets the initial atk of the champion without buffs, never changes
     * @return
     */
    public int getInitialAtk() {return initialAtk; }

    /**
     * sets the initial atk of the champion
     * @param atk 
     */
    public void setAtk(int atk){
    	this.atk = atk;
    	if (initializedAtk == false) {
    		initialAtk = atk;
    		initializedAtk = true;
    	}
    }

    /**
     * gets the name of the champion
     * @return the name in String form
     */
    public String getName(){ return name; }

    /**
     * sets the name of the champion
     * @param name
     */
    public void setName(String name){ this.name = name; }

    /**
     * gets the elemental/class type of the champion
     * @return
     */
    public String getType(){ return type; }

    /**
     * sets the elemental/class type of the champion
     * @param type
     */
    public void setType(String type){ this.type = type; }

    /**
     * Returns the starCount/rarity of the champion, 1-4
     * @return stars
     */
    public int getPrice() { return this.price; }
    
    /**
     * Sets the star count of the current champion
     * @param stars
     */
    public void setPrice(int coins) { this.price = coins; }
    
    /**
     * adds hp to the current hp of the champion
     * @param toAdd
     * @return The hp after the addition 
     */
    public int gainHp(int toAdd){ 
        hp += toAdd; 
        return hp;
    }

    /**
     * subtracts hp from the current hp of the champion
     * @param damage
     * @return The hp after the subtraction 
     */
    public int loseHp(int damage){ 
        hp -= damage; 
        if (hp < 0) {
        	hp = 0;
        } 
        return hp;
    }

    /**
     * adds atk to the current atk of the champion
     * @param toAdd
     * @return the atk of the champion after the addition
     */
    public int increaseAtk(int toAdd){ 
        atk += toAdd; 
        return atk;
    }

    /**
     * subtracts from the current atk of the champion
     * @param toSub
     * @return the atk of the champion after the subtraction
     */
    public int decreaseAtk(int toSub){ 
        atk -= toSub; 
        return atk;
    }
    
    /**
     * adds class bonus through champion
     * 
     * @param type is the string type of the current Champion
     */
    public void addBonus(int hp, int atk) {
        gainHp(hp);
    	increaseAtk(atk);
    }
    
}





