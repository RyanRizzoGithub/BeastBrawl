package src;

import java.util.LinkedList;
import java.util.Random;
import Creatures.Creature;

public class AI {
	private boolean debug = true;
	
	private int difficulty;
	private int health;
	private int coins;
	private int roundsSince;
	private Shop shop;
	private LinkedList<Card> hand;
	private LinkedList<Card> board;
	
	public AI(int difficulty) {
		this.difficulty = difficulty;
		this.health = 25;
		this.coins = 0;
		this.roundsSince = 0;
		this.shop = new Shop();
		this.hand = new LinkedList<Card>();
		this.board = new LinkedList<Card>();
	}
	
	public void shopPhase(int round) {
		// Increment the ai's coins
		this.coins = round; 
		if (coins > 10) coins = 10;
		shop.rollShop();
		
		if (debug) System.out.println("################################################");
		if (debug) System.out.println("AI SHOP PHASE #" + round);
		if (debug) System.out.println("coin(s): " + coins);
		if (debug) System.out.println("level:   " + shop.getLevel());
		if (debug) System.out.println("shop:    " + shop.toString());
		if (debug) System.out.println();
		
		
		Random rand = new Random(); 

		// If this is an stupid difficulty ai
		if (difficulty == 0) {
			// Check if we can level up
			if (!levelUp(round)) {
			
				// Create a shop selection
				Card[] selection = shop.getShop();
				
				// Buy as many cards as we can
				for (int i=0; i<selection.length; i++) {
					if (selection[i].getPrice() <= coins) {
						buyCard(selection[i]);
					}
				}
			}
				
			// Determine if we should sell any cards
			if (board.size() == 10 && hand.size() > 0) {
				// Sell a random card
				sellCard(rand.nextInt(hand.size()));
				
			}
			
			// Play a random card
			if (hand.size() > 0) {
				playCard(rand.nextInt(hand.size()));
			}
		}
		
		// If this is an easy difficulty ai
		if (difficulty == 1) {
			// Check if we can level up
			if (!levelUp(round)) {
							
				// Create a shop selection
				Card[] selection = shop.getShop();
				
				// Buy the best card in the shop (based on hp + atk)
				Card bestBuy = selection[0];
				for (int i=0; i<selection.length; i++) {
					if (selection[i].getHp() + selection[i].getAtk() > 
					bestBuy.getHp() + bestBuy.getAtk()) {
						bestBuy = selection[i];
					}
				}
				buyCard(bestBuy);
			}
			
			// Determine if we should sell any cards
			if (board.size() == 10 && hand.size() > 0) {
				// Sell our worst card (based on hp + atk)
				int bestSell = 0;
				for (int i=0; i<board.size(); i++) {
					if (board.get(i).getHp() + board.get(i).getAtk() < 
					board.get(bestSell).getHp() + board.get(bestSell).getAtk()) {
						bestSell = i;
					}
				}
			sellCard(bestSell);
			}
			
			// Play the best card
			if (hand.size() > 0) {
				int bestPlay = 0;
				for (int i=0; i<hand.size(); i++) {
					if (hand.get(i).getHp() + hand.get(i).getAtk() > 
					hand.get(bestPlay).getHp() + hand.get(bestPlay).getAtk()) {
						bestPlay = i;
					}
				}
				playCard(bestPlay);
			}
		}
		roundsSince++;
		if (debug) System.out.println("Coin(s):   " + coins);
		if (debug) System.out.println();
	}
	
	private void buyCard(Card card) {
		if (debug) System.out.println("Buying creature[" + card.getName() + "]");
		coins -= card.getPrice();
		hand.add(card);
	}
	
	private void sellCard(int index) {
		coins += 1;
		Card sold = board.remove(index);
		if (debug) System.out.println("Selling creature[" + sold.getName() + "]");
	}
	
	private void playCard(int index) {
		Card next = hand.remove(index);
		board.add(next);
		if (debug) System.out.println("Playing creature[" + next.getName() + "]");
	}
	
	private boolean levelUp(int round) {
		int price = shop.getLevel() + 4 - roundsSince;
		System.out.println("level up price: " + price);
		if (coins >= price && shop.getLevel() < 5) {
			int prev = shop.getLevel();
			coins -= price;
			shop.levelUp();
			roundsSince = 0;
			if (debug) System.out.println("Level up [" + prev + " -> " + shop.getLevel() + "]");
			return true;
		}
		return false;
	}
	
	public LinkedList<Card> getBoard() {
		return this.board;
	}
}
