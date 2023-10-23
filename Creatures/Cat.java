package Creatures;
import src.Card;

public class Cat extends Creature {
	public final int INIT_CAT_HP  = 4;
	public final int INIT_CAT_ATK = 4;
	public final int CAT_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Cat() {
		this.name = "Cat";
		this.hp = INIT_CAT_HP;
		this.atk = INIT_CAT_ATK;
		this.price = CAT_PRICE;
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
	
	@Override
	public boolean isDead() {
		return this.isDead;
	}
}

