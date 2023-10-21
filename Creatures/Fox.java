package src.Creatures;
import src.Card;

public class Fox extends Creature {
	public final String name = "Fox";
	public final int INIT_FOX_HP  = 7;
	public final int INIT_FOX_ATK = 8;
	public final int FOX_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Fox() {
		this.hp = INIT_FOX_HP;
		this.atk = INIT_FOX_ATK;
		this.price = FOX_PRICE;
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


