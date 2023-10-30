package src.Creatures;

import src.Card;

public class Creature {
	protected int hp;
	protected int atk;
	protected int price;
	protected boolean isDead;
	protected Creature creature;

	public int getHp() { return hp; }

	public void adjustHp(int amount) { hp += amount; }
	
	public Card getCard() {
		Card card = new Card();
		card.setName("Empty");
		card.setHp(this.hp);
		card.setAtk(this.atk);
		return card;
	}

}

