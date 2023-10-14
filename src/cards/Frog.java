package src.cards;
import src.Card;

public class Frog {
	public final String name = "Frog";
	public final int INIT_FROG_HP  = 2;
	public final int INIT_FROG_ATK = 2;
	public final int BOAR_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Frog() {
		this.hp = INIT_FROG_HP;
		this.atk = INIT_FROG_ATK;
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

