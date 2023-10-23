package Creatures;
import src.Card;

public class Boar extends Creature {
	public final String name = "Boar";
	public final int INIT_BOAR_HP  = 10;
	public final int INIT_BOAR_ATK = 10;
	public final int BOAR_PRICE = 5;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Boar() {
		this.hp = INIT_BOAR_HP;
		this.atk = INIT_BOAR_ATK;
		this.price = BOAR_PRICE;
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

