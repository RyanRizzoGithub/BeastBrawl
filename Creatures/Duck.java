package src.Creatures;
import src.Card;

public class Duck extends Creature {
	public final String name = "Duck";
	public final int INIT_DUCK_HP  = 1;
	public final int INIT_DUCK_ATK = 2;
	public final int DUCK_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Duck() {
		this.hp = INIT_DUCK_HP;
		this.atk = INIT_DUCK_ATK;
		this.price = DUCK_PRICE;
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

