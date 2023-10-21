package src.Creatures;
import src.Card;

public class Squirrel extends Creature {
	public final int INIT_SQUIRREL_HP  = 4;
	public final int INIT_SQUIRREL_ATK = 4;
	public final int SQUIRREL_PRICE = 2;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Squirrel() {
		this.name = "Squirrel";
		this.hp = INIT_SQUIRREL_HP;
		this.atk = INIT_SQUIRREL_ATK;
		this.price = SQUIRREL_PRICE;
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

