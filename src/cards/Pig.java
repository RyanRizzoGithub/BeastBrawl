package src.cards;
import src.Card;

public class Pig {
	public final String name = "Pig";
	public final int INIT_PIG_HP  = 8;
	public final int INIT_PIG_ATK = 8;
	public final int PIG_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Pig() {
		this.hp = INIT_PIG_HP;
		this.atk = INIT_PIG_ATK;
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
