package src.cards;
import src.Card;

public class Squirrel {
	public final String name = "Squirrel";
	public final int INIT_SQUIRREL_HP  = 4;
	public final int INIT_SQUIRREL_ATK = 4;
	public final int SQUIRREL_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Squirrel() {
		this.hp = INIT_SQUIRREL_HP;
		this.atk = INIT_SQUIRREL_ATK;
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

