package src.Creatures;

import src.Card;

public class Sheep extends Creature {
	public final String name = "Sheep";
	public final int INIT_SHEEP_HP  = 3;
	public final int INIT_SHEEP_ATK = 4;
	public final int SHEEP_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Sheep() {
		this.hp = INIT_SHEEP_HP;
		this.atk = INIT_SHEEP_ATK;
		this.isDead = false;
	}
	
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		return card;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
}
