package Creatures;
import src.Card;

public class Monkey extends Creature {
	public final int INIT_MONKEY_HP  = 5;
	public final int INIT_MONKEY_ATK = 6;
	public final int MONKEY_PRICE = 3;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Monkey() {
		this.name = "Monkey";
		this.hp = INIT_MONKEY_HP;
		this.atk = INIT_MONKEY_ATK;
		this.isDead = false;
	}
	
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
	
	@Override
	public boolean isDead() {
		return this.isDead;
	}
}

