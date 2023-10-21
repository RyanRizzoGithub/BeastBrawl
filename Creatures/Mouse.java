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
		this.price = MOUSE_PRICE;
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
	
	@Override
	public String getName() { return name; }
	
	@Override
	public int getHp() { return hp; }

	@Override
	public void adjustHp(int amount) { hp += amount; }
	
	@Override
	public int getAtk() { return atk; }
	
	@Override
	public void adjustAtk(int amount) { atk += amount; }
	
	@Override
	public int getPrice() { return price; };
	
	@Override
	public boolean isDead() {
		return this.isDead;
	}
}

