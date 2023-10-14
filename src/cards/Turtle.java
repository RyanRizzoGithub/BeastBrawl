package src.cards;
import src.Card;

public class Turtle {
	public final String name = "Turtle";
	public final int INIT_TURTLE_HP  = 3;
	public final int INIT_TURTLE_ATK = 3;
	public final int TURTLE_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Turtle() {
		this.hp = INIT_TURTLE_HP;
		this.atk = INIT_TURTLE_ATK;
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

