package Creatures;
import src.Card;

public class Bear extends Creature {
	public final int INIT_BEAR_HP  = 9;
	public final int INIT_BEAR_ATK = 10;
	public final int BEAR_PRICE = 5;
	
	private String name;
	private int hp;
	private int atk;
	boolean isDead;
	
	public Bear() {
		this.name = "Bear";
		this.hp = INIT_BEAR_HP;
		this.atk = INIT_BEAR_ATK;
		this.price = BEAR_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card bearCard = new Card();
		bearCard.setName(this.name);
		bearCard.setHp(this.hp);
		bearCard.setAtk(this.atk);
		bearCard.setPrice(BEAR_PRICE);
		return bearCard;
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
