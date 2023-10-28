package src.Creatures;
import src.Card;

public class Duck extends Creature {
	public final String name = "Duck";
	public final int INIT_DUCK_HP  = 1;
	public final int INIT_DUCK_ATK = 2;
	public final int DUCK_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Duck() {
		this.hp = INIT_DUCK_HP;
		this.atk = INIT_DUCK_ATK;
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
