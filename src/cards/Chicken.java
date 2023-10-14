package src.cards;
import src.Card;

public class Chicken {
	public final String name = "Chicken";
	public final int INIT_CHICKEN_HP  = 2;
	public final int INIT_CHICKEN_ATK = 2;
	public final int CHICKEN_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Chicken() {
		this.hp = INIT_CHICKEN_HP;
		this.atk = INIT_CHICKEN_ATK;
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


