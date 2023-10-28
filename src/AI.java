package src;

import java.util.LinkedList;
import java.util.Random;
import src.Creatures.Creature;

public class AI {
	private int difficulty;
	private int health;
	private int coins;
	private Deck deck;
	private LinkedList<Card> hand;
	private LinkedList<Card> board;
	
	public AI(int difficulty) {
		this.difficulty = difficulty;
		this.health = 25;
		this.coins = 0;
		this.deck = new Deck();
		this.hand = new LinkedList<Card>();
		this.board = new LinkedList<Card>();
	}
	
	public void shopPhase(int round) {
		this.coins += round;
		Random rand = new Random(); 
		Creature[] sel = new Creature[1];
		sel[0] = deck.deck[deck.MOUSE];
		System.out.println("hp: " + sel[0].getHp());
		/*
		// If this is an easy difficulty ai
		if (difficulty == 0) {
			// Create a shop selection
			Creature[] selection = deck.drawShop(round);
			
			// Buy as many cards as we can
			for (int i=0; i<selection.length; i++) {
				if (selection[i].getCard().getPrice() <= coins) {
					
				}
			}
		}
		*/
	}
	
	public LinkedList<Card> getBoard() {
		return this.board;
	}
}
