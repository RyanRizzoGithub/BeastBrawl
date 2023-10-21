package src.Creatures;
import src.Card;

public class Cow extends Creature {
	public final int INIT_COW_HP  = 6;
	public final int INIT_COW_ATK = 6;
	public final int COW_PRICE = 3;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Cow() {
		this.name = "Cow";
		this.hp = INIT_COW_HP;
		this.atk = INIT_COW_ATK;
		this.price = COW_PRICE;
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

