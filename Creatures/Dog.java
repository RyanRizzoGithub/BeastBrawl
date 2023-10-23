package Creatures;
import src.Card;

public class Dog extends Creature {
	public final int INIT_DOG_HP  = 4;
	public final int INIT_DOG_ATK = 3;
	public final int DOG_PRICE = 2;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Dog() {
		this.name = "Dog";
		this.hp = INIT_DOG_HP;
		this.atk = INIT_DOG_ATK;
		this.price = DOG_PRICE;
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

