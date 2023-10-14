package src.cards;
import src.Card;

public class Mouse {
	public final String name = "Mouse";
	public final int INIT_MOUSE_HP  = 1;
	public final int INIT_MOUSE_ATK = 1;
	public final int MOUSE_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Mouse() {
		this.hp = INIT_MOUSE_HP;
		this.atk = INIT_MOUSE_ATK;
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

