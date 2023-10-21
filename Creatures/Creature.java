package src.Creatures;

import src.Card;

public class Creature {
	protected String name;
	protected int hp;
	protected int atk;
	protected int price;
	protected boolean isDead;

	public int getHp() { return hp; }

	public void adjustHp(int amount) { hp += amount; }
	
	public int getAtk() { return atk; }
	
	public void adjustAtk(int amount) { atk += amount; }
	
	public int getPrice() { return price; };
	
	public boolean isDead() { return isDead; }
	
	public Card getCard() {
		Card card = new Card();
		card.setName("Empty");
		card.setHp(this.hp);
		card.setAtk(this.atk);
		return card;
	}

}

