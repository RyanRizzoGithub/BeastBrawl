package src.cards;
import src.Card;

public class Dog {
	public final String name = "Boar";
	public final int INIT_DOG_HP  = 4;
	public final int INIT_DOG_ATK = 3;
	public final int DOG_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Dog() {
		this.hp = INIT_DOG_HP;
		this.atk = INIT_DOG_ATK;
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

