package src.Creatures;
import src.Card;

public class Cat extends Creature {
	public final String name = "Cat";
	public final int INIT_CAT_HP  = 4;
	public final int INIT_CAT_ATK = 4;
	public final int CAT_PRICE = 2;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Cat() {
		this.hp = INIT_CAT_HP;
		this.atk = INIT_CAT_ATK;
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
