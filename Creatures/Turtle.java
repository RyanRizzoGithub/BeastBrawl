package Creatures;
import src.Card;

public class Turtle extends Creature {
	public final String name = "Turtle";
	public final int INIT_TURTLE_HP  = 3;
	public final int INIT_TURTLE_ATK = 3;
	public final int TURTLE_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Turtle() {
		this.hp = INIT_TURTLE_HP;
		this.atk = INIT_TURTLE_ATK;
		this.price = TURTLE_PRICE;
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

