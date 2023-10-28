package src.Creatures;
import src.Card;

public class Horse extends Creature {
	public final String name = "Horse";
	public final int INIT_HORSE_HP  = 10;
	public final int INIT_HORSE_ATK = 10;
	public final int HORSE_PRICE = 5;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Horse() {
		this.hp = INIT_HORSE_HP;
		this.atk = INIT_HORSE_ATK;
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


