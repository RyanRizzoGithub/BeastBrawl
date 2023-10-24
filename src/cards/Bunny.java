package src.cards;
import src.Card;

public class Bunny {
	public final String name = "Bunny";
	public final int INIT_BUNNY_HP  = 2;
	public final int INIT_BUNNY_ATK = 1;
	public final int BUNNY_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Bunny() {
		this.hp = INIT_BUNNY_HP;
		this.atk = INIT_BUNNY_ATK;
		this.isDead = false;
	}
	
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		return card;
	}
	
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


