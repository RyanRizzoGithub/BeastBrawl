package src.cards;
import src.Card;

public class Owl {
	public final String name = "Owl";
	public final int INIT_OWL_HP  = 5;
	public final int INIT_OWL_ATK = 5;
	public final int OWL_PRICE = 3;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Owl() {
		this.hp = INIT_OWL_HP;
		this.atk = INIT_OWL_ATK;
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

