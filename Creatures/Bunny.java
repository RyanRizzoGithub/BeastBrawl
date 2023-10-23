package Creatures;
import src.Card;

public class Bunny extends Creature{
	public final int INIT_BUNNY_HP  = 2;
	public final int INIT_BUNNY_ATK = 1;
	public final int BUNNY_PRICE = 1;
	
	private String name;
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Bunny() {
		this.name = "Bunny";
		this.hp = INIT_BUNNY_HP;
		this.atk = INIT_BUNNY_ATK;
		this.price = BUNNY_PRICE;
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


