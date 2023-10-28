package src.Creatures;
import src.Card;

public class Bunny extends Creature{
	public final String name = "Bunny";
	public final int INIT_BUNNY_HP  = 2;
	public final int INIT_BUNNY_ATK = 1;
	public final int BUNNY_PRICE = 1;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Bunny() {
		this.hp = INIT_BUNNY_HP;
		this.atk = INIT_BUNNY_ATK;
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


