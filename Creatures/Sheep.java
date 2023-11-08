package Creatures;

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
		this.price = SHEEP_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		card.setPrice(SHEEP_PRICE);
		return card;
	}
	
	@Override
	public int getHp() { return hp; }

	@Override
	public void adjustHp(int amount) { hp += amount; }
	
	@Override
	public int getAtk() { return atk; }
	
	@Override
	public void adjustAtk(int amount) { atk += amount; }
	
	@Override
	public int getPrice() { return price; };
	
	public boolean isDead() {
		return this.isDead;
	}
}
