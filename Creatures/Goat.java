package src.Creatures;
import src.Card;

public class Goat extends Creature {
	public final int INIT_GOAT_HP  = 6;
	public final int INIT_GOAT_ATK = 5;
	public final int GOAT_PRICE = 3;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Goat() {
		this.name = "Goat";
		this.hp = INIT_GOAT_HP;
		this.atk = INIT_GOAT_ATK;
		this.price = GOAT_PRICE;
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


