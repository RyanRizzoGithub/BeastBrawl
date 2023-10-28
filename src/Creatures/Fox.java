package src.Creatures;
import src.Card;

public class Fox extends Creature {
	public final String name = "Fox";
	public final int INIT_FOX_HP  = 7;
	public final int INIT_FOX_ATK = 8;
	public final int FOX_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Fox() {
		this.hp = INIT_FOX_HP;
		this.atk = INIT_FOX_ATK;
		this.isDead = false;
	}
	
	public Card getCard() {
		Card card = new Card();
		card.setName(this.name);
		card.setHp(this.hp);
		card.setAtk(this.atk);
		return card;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
}


