package Creatures;
import src.Card;

public class Horse extends Creature {
	public final int INIT_HORSE_HP  = 10;
	public final int INIT_HORSE_ATK = 10;
	public final int HORSE_PRICE = 5;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Horse() {
		this.name = "Horse";
		this.hp = INIT_HORSE_HP;
		this.atk = INIT_HORSE_ATK;
		this.price = HORSE_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		card.setPrice(HORSE_PRICE);
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


