package src.Creatures;
import src.Card;

public class Cow extends Creature {
	public final String name = "Cow";
	public final int INIT_COW_HP  = 6;
	public final int INIT_COW_ATK = 6;
	public final int COW_PRICE = 3;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Cow() {
		this.hp = INIT_COW_HP;
		this.atk = INIT_COW_ATK;
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

