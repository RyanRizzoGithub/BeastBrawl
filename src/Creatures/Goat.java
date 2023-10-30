package src.Creatures;
import src.Card;

public class Goat extends Creature {
	public final String name = "Goat";
	public final int INIT_GOAT_HP  = 6;
	public final int INIT_GOAT_ATK = 5;
	public final int GOAT_PRICE = 3;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Goat() {
		this.hp = INIT_GOAT_HP;
		this.atk = INIT_GOAT_ATK;
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


