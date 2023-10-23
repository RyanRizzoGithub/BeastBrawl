package Creatures;
import src.Card;

public class Snake extends Creature {
	public final String name = "Snake";
	public final int INIT_SNAKE_HP  = 8;
	public final int INIT_SNAKE_ATK = 8;
	public final int SNAKE_PRICE = 4;
	
	private int hp;
	private int atk;
	private boolean isDead;
	
	public Snake() {
		this.hp = INIT_SNAKE_HP;
		this.atk = INIT_SNAKE_ATK;
		this.price = SNAKE_PRICE;
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
	public int getHp() { return hp; }

	@Override
	public void adjustHp(int amount) { hp += amount; }
	
	@Override
	public int getAtk() { return atk; }
	
	@Override
	public void adjustAtk(int amount) { atk += amount; }
	
	@Override
	public int getPrice() { return price; };
	
	public boolean isDead() {
		return this.isDead;
	}
}


