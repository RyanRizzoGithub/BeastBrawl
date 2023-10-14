package src.cards;
import src.Card;

public class Donkey {
	public final String name = "Donkey";
	public final int INIT_DONKEY_HP  = 8;
	public final int INIT_DONKEY_ATK = 7;
	public final int DONKEY_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Donkey() {
		this.hp = INIT_DONKEY_HP;
		this.atk = INIT_DONKEY_ATK;
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

