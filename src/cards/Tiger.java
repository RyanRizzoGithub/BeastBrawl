package src.cards;
import src.Card;

public class Tiger {
	public final String name = "Tiger";
	public final int INIT_TIGER_HP  = 9;
	public final int INIT_TIGER_ATK = 9;
	public final int TIGER_PRICE = 5;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Tiger() {
		this.hp = INIT_TIGER_HP;
		this.atk = INIT_TIGER_ATK;
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

