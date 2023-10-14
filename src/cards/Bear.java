package src.cards;
import src.Card;

public class Bear {
	public final String name = "Bear";
	public final int INIT_BEAR_HP  = 9;
	public final int INIT_BEAR_ATK = 10;
	public final int BEAR_PRICE = 5;
	
	private int hp;
	private int atk;
	boolean isDead;
	
	public Bear() {
		this.hp = INIT_BEAR_HP;
		this.atk = INIT_BEAR_ATK;
		this.isDead = false;
	}
	
	public Card getCard() {
		Card bearCard = new Card();
		bearCard.setName(this.name);
		bearCard.setHp(this.hp);
		bearCard.setAtk(this.atk);
		return bearCard;
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
