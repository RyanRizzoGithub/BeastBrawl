package src.Creatures;
import src.Card;

public class Wolf extends Creature {
	public final String name = "Wolf";
	public final int INIT_WOLF_HP  = 7;
	public final int INIT_WOLF_ATK = 7;
	public final int WOLF_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Wolf() {
		this.hp = INIT_WOLF_HP;
		this.atk = INIT_WOLF_ATK;
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


