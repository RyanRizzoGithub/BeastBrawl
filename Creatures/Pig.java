package Creatures;
import src.Card;

public class Pig extends Creature {
	public final String name = "Pig";
	public final int INIT_PIG_HP  = 8;
	public final int INIT_PIG_ATK = 8;
	public final int PIG_PRICE = 4;

	private int hp;
	private int atk;
	private boolean isDead;
	
	public Pig() {
		this.hp = INIT_PIG_HP;
		this.atk = INIT_PIG_ATK;
		this.price = PIG_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		card.setPrice(PIG_PRICE);
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
	
	@Override
	public boolean isDead() {
		return this.isDead;
	}
}

