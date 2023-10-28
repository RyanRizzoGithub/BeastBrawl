package src.Creatures;
import src.Card;

public class Mouse extends Creature {
	public final String name = "Mouse";
	public final int INIT_MOUSE_HP  = 1;
	public final int INIT_MOUSE_ATK = 1;
	public final int MOUSE_PRICE = 1;
	
	public Mouse() {
		this.hp = INIT_MOUSE_HP;
		this.atk = INIT_MOUSE_ATK;
		this.isDead = false;
	}
	
	@Override
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

