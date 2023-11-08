package Creatures;
import src.Card;

public class Frog extends Creature {
	public final String name = "Frog";
	public final int INIT_FROG_HP  = 2;
	public final int INIT_FROG_ATK = 2;
	public final int FROG_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Frog() {
		this.hp = INIT_FROG_HP;
		this.atk = INIT_FROG_ATK;
		this.price = FROG_PRICE;
		this.isDead = false;
	}
	
	@Override
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		card.setPrice(FROG_PRICE);
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
	
	public void hpAdjust(int amount) { 
		this.hp += amount;
		if (this.hp <= 0) {
			this.isDead = true;
		}
	}
	
	public void atkAdjust(int amount) {
		this.hp += amount; 
	}
	
	public boolean isDead() {
		return this.isDead;
	}
}

