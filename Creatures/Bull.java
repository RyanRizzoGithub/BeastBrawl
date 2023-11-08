package Creatures;
import src.Card;

public class Bull extends Creature {
	public final String name = "Bull";
	public final int INIT_BULL_HP  = 10;
	public final int INIT_BULL_ATK = 9;
	public final int BULL_PRICE = 5;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Bull() {
		this.hp = INIT_BULL_HP;
		this.atk = INIT_BULL_ATK;
		this.price = BULL_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		card.setPrice(BULL_PRICE);
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

