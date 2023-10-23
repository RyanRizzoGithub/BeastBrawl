package Creatures;
import src.Card;

public class Donkey extends Creature {
	public final String name = "Donkey";
	public final int INIT_DONKEY_HP  = 8;
	public final int INIT_DONKEY_ATK = 7;
	public final int DONKEY_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Donkey() {
		this.hp = INIT_DONKEY_HP;
		this.atk = INIT_DONKEY_ATK;
		this.price = DONKEY_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
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

