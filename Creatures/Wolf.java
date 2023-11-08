package Creatures;
import src.Card;

public class Wolf extends Creature {
	public final String name = "Wolf";
	public final int INIT_WOLF_HP  = 7;
	public final int INIT_WOLF_ATK = 7;
	public final int WOLF_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Wolf() {
		this.hp = INIT_WOLF_HP;
		this.atk = INIT_WOLF_ATK;
		this.price = WOLF_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		card.setPrice(WOLF_PRICE);
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


