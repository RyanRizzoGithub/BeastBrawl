package src.cards;
import src.Card;

public class Bull {
	public final String name = "Bull";
	public final int INIT_BULL_HP  = 10;
	public final int INIT_BULL_ATK = 9;
	public final int BULL_PRICE = 5;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Bull() {
		this.hp = INIT_BULL_HP;
		this.atk = INIT_BULL_ATK;
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

