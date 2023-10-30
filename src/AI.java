package src;

import java.util.LinkedList;
import java.util.Random;
import Creatures.Creature;

public class AI {
	private boolean debug = true;
	
	private int difficulty;
	private int health;
	private int coins;
	private Shop shop;
	private LinkedList<Card> hand;
	private LinkedList<Card> board;
	
	public AI(int difficulty) {
		this.difficulty = difficulty;
		this.health = 25;
		this.coins = 0;
		this.shop = new Shop();
		this.hand = new LinkedList<Card>();
		this.board = new LinkedList<Card>();
	}
	
	public void shopPhase(int round) {
		this.coins += round;
		Random rand = new Random(); 

		// If this is an stupid difficulty ai
		if (difficulty == 0) {
			// Create a shop selection
			shop.rollShop();
			LinkedList<Card> options = shop.getShop();
			
			// Buy as many cards as we can
			for (int i=0; i<options.size(); i++) {
				if (options.get(i).getPrice() <= coins) {
					buyCard(options.get(i));
				}
			}
			
			// Determine if we should sell any cards
			if (board.size() == 10 && hand.size() > 0) {
				// Sell a random card
				sellCard(rand.nextInt(hand.size()));
				
			}
			
			// Play a random card
			playCard(rand.nextInt(hand.size()));
		}
		
		// If this is an easy difficulty ai
		if (difficulty == 1) {
			// Create a shop selection
			shop.rollShop();
			LinkedList<Card> options = shop.getShop();
			
			// Buy the best card in the shop (based on hp + atk)
			Card bestBuy = options.get(0);
			for (int i=0; i<options.size(); i++) {
				if (options.get(i).getHp() + options.get(i).getAtk() > 
				bestBuy.getHp() + bestBuy.getAtk()) {
					bestBuy = options.get(i);
				}
			}
			buyCard(bestBuy);
			
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
	
	public LinkedList<Card> getBoard() {
		return this.board;
	}
}
