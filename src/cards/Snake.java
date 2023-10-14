package src.cards;
import src.Card;

public class Snake {
	public final String name = "Snake";
	public final int INIT_SNAKE_HP  = 8;
	public final int INIT_SNAKE_ATK = 8;
	public final int SNAKE_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Snake() {
		this.hp = INIT_SNAKE_HP;
		this.atk = INIT_SNAKE_ATK;
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


