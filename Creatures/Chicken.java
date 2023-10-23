package Creatures;
import src.Card;

public class Chicken extends Creature {
	public final int INIT_CHICKEN_HP  = 2;
	public final int INIT_CHICKEN_ATK = 2;
	public final int CHICKEN_PRICE = 1;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Chicken() {
		this.name = "Chicken";
		this.hp = INIT_CHICKEN_HP;
		this.atk = INIT_CHICKEN_ATK;
		this.price = CHICKEN_PRICE;
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
	public String getName() { return name; }
	
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


