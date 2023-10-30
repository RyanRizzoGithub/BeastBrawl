package src.Creatures;
import src.Card;

public class Boar extends Creature {
	public final String name = "Boar";
	public final int INIT_BOAR_HP  = 10;
	public final int INIT_BOAR_ATK = 10;
	public final int BOAR_PRICE = 5;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Boar() {
		this.hp = INIT_BOAR_HP;
		this.atk = INIT_BOAR_ATK;
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

