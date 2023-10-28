package src.Creatures;
import src.Card;

public class Deer extends Creature {
	public final String name = "Deer";
	public final int INIT_DEER_HP  = 6;
	public final int INIT_DEER_ATK = 6;
	public final int DEER_PRICE = 3;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Deer() {
		this.hp = INIT_DEER_HP;
		this.atk = INIT_DEER_ATK;
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

