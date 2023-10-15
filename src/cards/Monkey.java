package src.cards;
import src.Card;

public class Monkey {
	public final String name = "Monkey";
	public final int INIT_MONKEY_HP  = 5;
	public final int INIT_MONKEY_ATK = 6;
	public final int MONKEY_PRICE = 3;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Monkey() {
		this.hp = INIT_MONKEY_HP;
		this.atk = INIT_MONKEY_ATK;
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
