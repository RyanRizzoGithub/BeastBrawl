package src.cards;
import src.Card;

public class Duck {
	public final String name = "Duck";
	public final int INIT_DUCK_HP  = 1;
	public final int INIT_DUCK_ATK = 2;
	public final int DUCK_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Duck() {
		this.hp = INIT_DUCK_HP;
		this.atk = INIT_DUCK_ATK;
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

