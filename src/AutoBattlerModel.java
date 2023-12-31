package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Random;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
//import Creatures.Creature;


/**
 * Model represents the board on which the game state is changed and updated.
 * Contains two player objects, and allows interaction between them. Thus simulating
 * the state and progression of the game.
 */
public class AutoBattlerModel  {
    private Player p1;
    private Player p2;
    private int round;
    private int attackRound;
    private int [] attacking_arr;
    private int [] defending_arr;
    private PropertyChangeSupport propertyChangeSupport;
    private int AIroundsSince;
    private int difficulty;
    boolean debug = true;
    


    /**
     * constructor for board object.
     * Instantiates player objects.
     */
    public AutoBattlerModel() {
        p1 = new Player();
        p2 = new Player();
        //index [0] will be player, index [1] will be card that is attacking
        attacking_arr = new int[2];
        defending_arr = new int[2];
        round = 1;
        Random rng = new Random();
        attackRound = rng.nextInt(2);
        propertyChangeSupport = new PropertyChangeSupport(this);
        AIroundsSince = 0;
        difficulty = 0;
    }
    public void startNewGame() {
    	p1 = new Player();
        p2 = new Player();
        //index [0] will be player, index [1] will be card that is attacking
        attacking_arr = new int[2];
        defending_arr = new int[2];
       round = 1;
       Random rng = new Random();
      attackRound = rng.nextInt(2);
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    public int [][] getCardInteractions(){
    	int arr [][] = new int [2][1];
    	arr [0] = attacking_arr;
    	arr [1] = defending_arr;
    	return arr;
    }
    
    /*
     * How to add observer to the model
     */
    public void addObserver(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /*
     * how to notify observer something is changing. should this have methods?
     */
    private void notifyObservers(String propertyName, Object oldValue, Object newValue) {
    	
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
        
        
    }
    
    /* 
     * not sure how this is suppose to work
     */
    private void setChanged() {
        // Notify observers with the appropriate property name
    	notifyObservers("modelStateChanged", null, this);
    }

    /**
     * Executes the attackPhase. Each player takes turns attack with their champions
     * from right to left. Defending champions are chosen at random. Starting player
     * is chosen at random.
     * @throws InterruptedException 
     * 
     */
    public void attackPhase()  {
    	
        if (isRoundOver() == 0) {
        	//if attackround is 0 p1 attacks first
            if (attackRound % 2 == 0) { 
                // p1 attacks
                findChamps(p1, p2);
            }
            else {
                // p2 attacks first if attackround is 1
                findChamps(p2, p1);
            }
            //allows attack turn to be switched
            attackRound++;
            setChanged(); 
            

        } 
        
    	//notifyObservers(null);
       
    }
    
    /*
     * Called when the round is over and distributes coins and
     * damage to player that lost
     */
    public void endAttackPhase() {
    	p1.incRoundSince();
        p2.incRoundSince();
        round += 1;
        //p1 won the round
        if (isRoundOver() == 1) {
        	p1.setGold(round);
        	p2.setGold(round);
        	//how they lose health now based on what round it is
        	p2.loseHealth(calculate_damage(p1));
        //p2 won the round
        } else if (isRoundOver() == 2) {
        	p1.setGold(round);
        	p2.setGold(round);
        	//how they lose health now
        	p1.loseHealth(calculate_damage(p2));
        }
        Random rng = new Random();//wildly inefficent but fix later
        attackRound = rng.nextInt(2);
        setChanged();
    }
    
    /*
     * This method currently calculates the damage the losing player
     * takes at the end of the round.
     */
    private int calculate_damage(Player winner) {
    	// the i might be wrong here?
    	int sum = 0;
    	for(int i = 0; i < 6; i++) {
    		if(winner.getBattleField()[i] == null || winner.getBattleField()[i].getHp() == 0) {
    			continue;
    		}else {
    			sum += winner.getBattleField()[i].getPrice();
    		}
    	}
    	return sum;
    }
    
    
    /**
     * Begins the shop phase by setting a new shop for both players
     */
    public void shopPhase() {
		// at the beginning of the shop phase this gives the players 
		// all new shops based on their level
		p1.getShop().rollShop();
		p2.getShop().rollShop();
    }
    
    /**
     * Returns the current shop of the player
     * @param player the player whos shop is chosen
     * @return
     */
    public Card[] getShop(Player player){
    	return player.getShop().getShop();
    }
   
    /**
     * Re-rolls the current shop into a new shop, costs the player 1 gold, if the 
     * player doesnt have 1 gold, returns the old shop they have, no change.
     * @param player the player who wants to re-roll
     * @return
     */
    public Card[] rerollShop(Player player){
    	if (player.getGold() > 0) {
    		player.spendGold(1);
    		setChanged();
        	//notifyObservers(player);
        	player.getShop().rollShop();
    		return player.getShop().getShop();
    	}
    	//if the player doesn't have 1 gold, return the current shop
    	setChanged();
    	//notifyObservers(player);
		return player.getShop().getShop();
    }
    
    /**
     * Levels up the player using gold. The amount of gold required is 5 times their
     * current level
     * @param player player the player who wants to re-roll
     * @return
     */
    public int playerLevelUp(Player player) {
    	int level = player.getLevel();
    	return player.levelup();
    }
    /**
     * Finds champions to attack with, and executes one single attack and respective defender's
     * singular defense.
     * Assumes that both player's battlefields are NOT EMPTY.
     * @param rng       A Random object for choosing a defending champion to attack.
     * @param attacking Player that is attacking.
     * @param defending Player that is defending.
     */
    private void findChamps(Player attacking, Player defending) { 
        int i = attacking.get_attack_card();
        int j = 0; 
        Random rng = new Random();
        if(attacking.equals(p1)) {
        	System.out.println("Player 1 is attacking Player 2");
        	//player 1 is attacking player 2
        	attacking_arr [0] = 1;
        	defending_arr [0] = 2;
        }else {
        	System.out.println("Player 2 is attacking Player 1");
        	//player 2 is attacking player 1
        	attacking_arr [0] = 2;
        	defending_arr [0] = 1;
        }
        
        Card attacker = null;
        Card defender = null;
        //while loop finds current attacker or next available attacker.
        while (attacker == null || attacker.getHp() <= 0) {
            attacker = attacking.getBattleField()[i];
            attacking_arr [1] = i;
            if (i >= 9)
                i = 0;
            else
                i++;
        }
        //sets new attack card position
        attacking.set_attack_card(i);
       
        System.out.println("This is the attacker: " + attacker.getName());
        while (defender == null || defender.getHp() <= 0) {
            j = rng.nextInt(9);
            //rng keeps choosing a random number until it can find a not empty slot
            while(defending.getBattleField()[j] == null) {
            	j = rng.nextInt(9);
            }
            defender = defending.getBattleField()[j];
        }
        defending_arr[1] = j;
        System.out.println("This is the defender: " + defender.getName());
        int result = executeAttack(attacker, defender); //fix later
        
    }
    
    /**
     * resets traits of champions on battlefield
     */
    public void resetChampStats() {
    	//something aint right here
    		Card[] p1BattleField = p1.getBattleField();
    		Card[] p2BattleField = p2.getBattleField();
		for (int i = 0; i < 7; i++) {
			if (p1BattleField[i] != null) {
				p1BattleField[i].setHp(p1BattleField[i].getInitialHp());
				//p1BattleField[i].getCard().setAtk(p1BattleField[i].getCard().getInitialAtk());
			}
			if (p2BattleField[i] != null) {
				p2BattleField[i].setHp(p2BattleField[i].getInitialHp());
				//p2BattleField[i].getCard().setAtk(p2BattleField[i].getCard().getInitialAtk());
			}
		}
		p1.set_attack_card(0);
		p2.set_attack_card(0);
		setChanged();
    	//notifyObservers(null);
    }
    
    
    /**
     * Executes one attack. Subtracts each champions HP by the attack of the other Champion.
     * @param attacker Champion attacking.
     * @param defender Champion defending.
     * @return returns 0 if the defending champ killed the attacker, 1 if other way around, 
     * and 3 if they both died
     */
    private int executeAttack(Card attacker, Card defender) {
    	//TODO set buffs or debuffs?
    	//Something feels wrong here
    	System.out.println("This is defender hp before attack " + defender.getHp());
    	
        defender.loseHp(attacker.getAtk());
        
        System.out.println("This is defender hp after attack " + defender.getHp());
      
       // attacker.loseHp(defender.getAtk());
        // both defending and attacking champ die
        if (defender.getHp() <= 0 && attacker.getHp() <= 0) {
        	return 3;
        } else if (defender.getHp() <= 0) {
        	return 1;
        // if defender killed attacker, i dont think this can happen now?
        } else {
        	return 0;
        }
        
    }

    /**
     * Checks both player's battlefields. If either only contains champions with hp <= 0, the
     * round is over.
     * @return if neither won return 0, if p1 wins, return 1, if p2 wins return 2,
     * if everythings dead, return 3
     */
    public int isRoundOver() {
        int p1Alive = 0;
        int p2Alive = 0;
        //if p1 has alive champs, stillAlive == 1
        for (int i = 0; i < 7; i++) {
        	if(p1.getBattleField()[i] == null) {
        		continue;
        	}
            if (p1.getBattleField()[i].getHp() > 0)
                p1Alive = 1;
        }
        for (int i = 0; i < 7; i++) {
        	if(p2.getBattleField()[i] == null) {
        		continue;
        	}
            if (p2.getBattleField()[i].getHp() > 0)
                p2Alive = 1;
        } 
        //both alive
        if (p1Alive == 1 && p2Alive == 1) {
        	return 0;
        //p1 alive, p2 dead
        } else if (p1Alive == 1 && p2Alive == 0) {
        	return 1;
        //p2 alive, p1 dead
        } else if (p1Alive == 0 && p2Alive == 1) {
        	return 2;
        //both dead
        } else {
        	return 3;
        }
    }

    /**
     * Moves given champion to given destination on battlefield to the bench.
     * A value of -1 for destination indicates that a champion should be moved to the bench.
     * If a swap is desired, simply pass in one champion object to the champion argument
     * and the location of the other one to the destination argument.
     * @param origin        The original location of the champion to be moved. First element indicates
     *                          whether it is in the bench or battlefield.
     * @param owner         The player whose champion is being moved.
     * @param destination   The desired final location of the champion. First element indicates
     *                          whether it is in the bench or battlefield.
     * @return              true if the move was successful.
     */
    public boolean moveChampion(int[] origin, int owner, int[] destination) {
        Player player;
        if (owner == 1)
            player = p1;
        else
            player = p2;
        if (destination[0] == 0 && origin[0] == 1) 
            return battleToBench(origin[1], player, destination[1]);
        else if (destination[0] == 1 && origin[0] == 0) 
            return benchToBattle(origin[1], player, destination[1]);
         else 
            return champSwap(origin, player, destination[1]);
        
        // TODO send update to observer with foundAt and location indices and player object.
    }

    /**
     * Moves champion from battlefield to bench.
     * @param destination destination index of champion on the battlefield.
     * @param player   Integer representing the player whose champion is being moved.
     * @return         true if a champion exists at the selected origin on the battlefield.
     */
    private boolean battleToBench(int origin, Player player, int destination) {
    	if (origin < 0 || destination >= 15 || destination < 0) {
    		return false;
    	}
        if (player.getBattleField()[origin] == null) {
        	return false;
        }
        Card temp = player.getBench()[destination];
        player.getBench()[destination] = player.getBattleField()[origin];
        player.getBattleField()[origin] = temp;
        setChanged();
    	//notifyObservers(player);
        return true;
    }

    /**
     * Moves champion from bench to the battlefield.
     * If desired location already contains a champion, moves this champion to the bench before
     * moving the next one to the battlefield.
     * @param origin        The original location on the bench of the champion to be moved.
     * @param player        The player wishing to move their champion.
     * @param destination   The location on the battlefield the player wishes to move the champion to.
     * @return              true if the champion exists at the selected origin on the bench.
     */
    private boolean benchToBattle(int origin, Player player, int destination) {
    	if (origin < 0 || destination == -1) {
    		return false;
    	}
        if (player.getBench()[origin] == null)
            return false;
        Card temp = player.getBattleField()[destination];
        player.getBattleField()[destination] = player.getBench()[origin];
        player.getBench()[origin] = temp;
        setChanged();
    	//notifyObservers(player);
        return true;
    }

    /**
     * Swaps champions between locations on the same space (bench or battlefield).
     * @param origin        The location of the champion to be moved.
     * @param player        The player whose champion will be moved.
     * @param destination   The desired destination of the champion to be moved.
     * @return              true if a champion exists in at least one of the two slots.
     */
    private boolean champSwap(int[] origin, Player player, int destination) {
    	if (origin[0] < 0|| origin[1] < 0 || destination < 0 || destination > 7) {
    		return false;
    	}
        if (origin[0] == 0) {
            if (player.getBench()[origin[1]] == null && player.getBench()[destination] == null)
                return false;
            Card temp = player.getBench()[origin[1]];
            player.getBench()[origin[1]] = player.getBench()[destination];
            player.getBench()[destination] = temp;
        }
        else {
            if (player.getBattleField()[origin[1]] == null && player.getBattleField()[destination] == null)
                return false;
            Card temp = player.getBattleField()[origin[1]];
            player.getBattleField()[origin[1]] = player.getBattleField()[destination];
            player.getBattleField()[destination] = temp;
        }
        setChanged();
    	//notifyObservers(player);
        return true;
    }

    /**
     * Looks for a champion on the battlefield at a certain index.
     * @param location  The index for the champion to look for.
     * @param player    The player whose battlefield we're searching.
     * @return          true if found, false otherwise.
     */
    private boolean isOnBattleField(int location, Player player) {
        return player.getBattleField()[location] != null;
    }
    
	/**
	 * sells the champion at the index, adds the gold back to your bank
	 * @param player the current player who wants to sell
	 * @param benchOrBattleField 0 means that the champion sold comes from the bench,
	 *  	  1 means champion comes from battlefield
	 * @param index the current index of the champion that we want to sell
	 */
    public void sellChampion(Player player, int benchOrBattleField, int index) {
    	if (benchOrBattleField == 0) {
    		Card toRemove = player.getBench()[index];
    		if (toRemove == null) {
    			return;
    		}
    		player.getBench()[index] = null;
    		player.addGold(toRemove.getPrice());
    	} else if (benchOrBattleField == 1) {
    		Card toRemove = player.getBattleField()[index];
    		if (toRemove == null) {
    			return;
    		}
    		player.getBattleField()[index] = null;
    		player.addGold(toRemove.getPrice());
    	}
    	setChanged();
    	//notifyObservers(player);
    }
    
    public void buyCharacter(Player player,int location) {
    	player.buyCharacter(location);
    	setChanged();
    	//notifyObservers(player);
    }
    
    /**
     * makes an AI turn
     */
    public void AIturn() {
if (debug) System.out.println("\nAIturn()");
    	
    	if (difficulty == 0) {
    		// Attempt to level up
    		if (AIroundsSince >= 5) {
    			aiLevelUp(round);
    		}
    		// Buy a random card
    		else {
    			Card[] selection = p2.getShop().getShop();
    			aiBuyCard(selection, 0);
    		}
    		// Sell a random card
    		int battleCount = 0;
    		for (int i=0; i<p2.getBattleField().length; i++) {
    			if (p2.getBattleField()[i] != null) {
    				battleCount++;
    			}
    		}
    		int handCount = 0;
			for (int i=0; i<p2.getBench().length; i++) {
				if (p2.getBench()[i] != null) {
					handCount++;
				}
			}
    		if (battleCount == 10 && handCount > 0) {
    			aiSellCard(0);
    		}
    		
    		// Play a random card
    		if (handCount > 0) {
	    		for (int j=0; j<p2.getBattleField().length; j++) {
					if (p2.getBattleField()[j] == null) {
						for (int i=0; i<p2.getBench().length; i++) {
							if (p2.getBench()[i] != null) {
								p2.getBattleField()[j] = p2.getBench()[i];
								p2.getBench()[i] = null;
								break;
							}
						}
						break;
					}
				}
    		}
    		AIroundsSince++;
			setChanged();
    		
    	}
    	
    	if (difficulty == 1) {
    		// Buy a random card
    		if (!aiLevelUp(round)) {
    			Card[] selection = p2.getShop().getShop();
    			aiBuyCard(selection, 0);
    		}
    		// Sell a random card
    		int battleCount = 0;
    		for (int i=0; i<p2.getBattleField().length; i++) {
    			if (p2.getBattleField()[i] != null) {
    				battleCount++;
    			}
    		}
    		int handCount = 0;
			for (int i=0; i<p2.getBench().length; i++) {
				if (p2.getBench()[i] != null) {
					handCount++;
				}
			}
    		if (battleCount == 10 && handCount > 0) {
    			aiSellCard(0);
    		}
    		
    		// Play a random card
    		if (handCount > 0) {
	    		for (int j=0; j<p2.getBattleField().length; j++) {
					if (p2.getBattleField()[j] == null) {
						for (int i=0; i<p2.getBench().length; i++) {
							if (p2.getBench()[i] != null) {
								p2.getBattleField()[j] = p2.getBench()[i];
								p2.getBench()[i] = null;
								break;
							}
						}
						break;
					}
				}
    		}
    		AIroundsSince++;
			setChanged();
    	}
    	
    	if (difficulty == 2) {
    		System.out.println("oops");
	    	// Check if we can level up
			if (!aiLevelUp(round)) {
				// Create a shop selection
				Card[] selection = p2.getShop().getShop();
				
				// Buy the best card in the shop (based on hp + atk)
				int bestBuy = 0;
				for (int i=0; i<selection.length; i++) {
					if (selection[i].getHp() + selection[i].getAtk() > 
					selection[bestBuy].getHp() + selection[bestBuy].getAtk()) {
						bestBuy = i;
					}
				}
				aiBuyCard(selection, bestBuy);
			}
			
			// Determine if we should sell any cards
			int battleCount = 0;
			for (int i=0; i<p2.getBattleField().length; i++) {
				if (p2.getBattleField()[i] != null) {
					battleCount++;
				}
			}
			int handCount = 0;
			for (int i=0; i<p2.getBench().length; i++) {
				if (p2.getBench()[i] != null) {
					handCount++;
				}
			}
			if (battleCount == 10 && handCount > 0) {
				// Sell our worst card (based on hp + atk)
				int bestSell = 0;
				for (int i=0; i<p2.getBattleField().length; i++) {
					if (p2.getBattleField()[i] != null) {
						if (p2.getBattleField()[i].getHp() + p2.getBattleField()[i].getAtk() < 
								p2.getBattleField()[bestSell].getHp() + p2.getBattleField()[bestSell].getAtk()) {
							bestSell = i;
						}
					}
				}
			aiSellCard(bestSell);
			}
			
			// Play the best card
			if (handCount > 0) {
				int bestPlay = 0;
				for (int i=0; i<p2.getBench().length; i++) {
					if (p2.getBench()[i] != null) {
						bestPlay = i;
						break;
					}
				}
				for (int i=0; i<p2.getBench().length; i++) {
					if (p2.getBench()[i] != null) {
						if (p2.getBench()[i].getHp() + p2.getBench()[i].getAtk() > 
						p2.getBench()[bestPlay].getHp() + p2.getBench()[bestPlay].getAtk()) {
							bestPlay = i;
						}
					}
				}
				System.out.println("AI Playing [" + p2.getBench()[bestPlay].getName() + "]");
				for (int j=0; j<p2.getBattleField().length; j++) {
					if (p2.getBattleField()[j] == null) {
						p2.getBattleField()[j] = p2.getBench()[bestPlay];
						p2.getBench()[bestPlay] = null;
						break;
					}
				}
				setChanged();
			}
			AIroundsSince++;
			System.out.println("AI balance: " + p2.getGold());
    	}
    }
    
    private boolean aiLevelUp(int round) {
    	int price = p2.getShop().getLevel() + 4 - AIroundsSince;
		System.out.println("AI level up price: " + price);
		if (p2.getGold() >= price && p2.getShop().getLevel() < 5) {
			int prev = p2.getShop().getLevel();
			p2.setGold(p2.getGold() - price);
			p2.getShop().levelUp();
			AIroundsSince = 0;
			if (debug) System.out.println("AI Level up [" + prev + " -> " + p2.getShop().getLevel() + "]");
			setChanged();
			return true;
		}
		return false;
    }
    
    private void aiBuyCard(Card[] selection, int cell) {
		if (debug) System.out.println("AI Buying creature[" + selection[cell].getName() + "]");
		p2.buyCharacter(cell);
		setChanged();
	}
    
    private void aiSellCard(int index) {
		p2.setGold(p2.getGold() + 1);
		Card sold = p2.getBattleField()[index];
		p2.getBattleField()[index] = null;
		if (debug) System.out.println("AI Selling creature[" + sold.getName() + "]");
		setChanged();
	}
    
    private void aiPlayCard(int index) {
		Card next = p2.getBench()[index];
		p2.getBench()[index] = null;
		boolean notFound = true;
		for (int i=0; i<p2.getBattleField().length && notFound; i++) {
			if (p2.getBattleField()[i] == null) {
				p2.getBattleField()[i] = p2.getShop().getShop()[0];
				//p2.getBench()[index] = null;
				System.out.println("hello gov");
				notFound = false;
			}
		}
		
		setChanged();
		if (debug) System.out.println("AI Playing creature[" + next.getName() + "]");
		for (int i=0; i<p2.getBattleField().length; i++) {
			if (p2.getBattleField()[i] != null) {
				System.out.println("AI Battleground[" + i + "]: " + p2.getBattleField()[i].getName());
			}
		}
		
	}
    
    public void setDifficulty(int difficulty) {
    	this.difficulty = difficulty;
    }
    
    /**
     * returns the index of the first Champion on a players bench, if 
     * no champions on bench return -1
     * @param player
     */
    private int getFirstOnBench(Player player) {
    	for (int i = 0; i < player.getBench().length; i++) {
    		if (player.getBench()[i] != null) {
    			return i;
    		}
    	}
		return -1;
    }
    
    /**
     * returns player 1
     * @return
     */
    public Player getP1() {
    	return p1;
    }
    
    /**
     * returns player 2
     * @return
     */
    public Player getP2() {
    	return p2;
    }
}
