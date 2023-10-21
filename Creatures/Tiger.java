package src.Creatures;
import src.Card;

public class Tiger extends Creature {
	public final int INIT_TIGER_HP  = 9;
	public final int INIT_TIGER_ATK = 9;
	public final int TIGER_PRICE = 5;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Tiger() {
		this.name = "Tiger";
		this.hp = INIT_TIGER_HP;
		this.atk = INIT_TIGER_ATK;
		this.price = TIGER_PRICE;
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

