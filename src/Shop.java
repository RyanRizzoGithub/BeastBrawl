package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Creatures.Creature;

public class Shop {
	private Card[] shop;
	private Deck deck;

	public Shop() {
		shop = new Card[3];
		deck = new Deck();
	}
	
	/**
	 * gets the current shop relative to the players level, if you want to 
	 * refresh the shop call getShop again and it will generate a new shop
	 * 
	 * @param level the level of the the current player
	 * @return
	 */
	public Card[] getShop() {
		return shop;
	}
	
	public Card[] rerollShop(int level) {
		//TODO this may be incorrect/ we should change to what we want
		shop = deck.drawShop(level);
		return shop;
	}
	
	
	/**
	 * Generates the shop that the player will see on their board in the shop phase.
	 * No duplicates, size of shop is set to 3. Also removes everything from the past shop
	 * 
	 * @param choices the Total champion pool the player can access
	 */
	//TODO the shop needs to be fixed according to us
	private void createShop(int round) {
		Card[] newShop = new Card[3];
		newShop = deck.drawShop(round);
		shop = newShop;
	}
	
	public String toString() {
		String res = "";
		for (int i = 0; i < 3; i++) {
			if (shop[i] != null) {
				res += shop[i].getName();
				res += " ";
			}
		}
		return res;
	}
}


















