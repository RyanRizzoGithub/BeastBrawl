package src;
import java.util.Random;

import Creatures.Bear;
import Creatures.Boar;
import Creatures.Bull;
import Creatures.Bunny;
import Creatures.Cat;
import Creatures.Chicken;
import Creatures.Cow;
import Creatures.Creature;
import Creatures.Deer;
import Creatures.Dog;
import Creatures.Donkey;
import Creatures.Duck;
import Creatures.Fox;
import Creatures.Frog;
import Creatures.Goat;
import Creatures.Horse;
import Creatures.Monkey;
import Creatures.Mouse;
import Creatures.Owl;
import Creatures.Pig;
import Creatures.Sheep;
import Creatures.Snake;
import Creatures.Squirrel;
import Creatures.Tiger;
import Creatures.Turtle;
import Creatures.Wolf;

public class Shop {
	// 1 Cost card index declarations
	public final int MOUSE 		= 0;
	public final int DUCK 		= 1;
	public final int BUNNY 		= 2;
	public final int CHICKEN 	= 3;
	public final int FROG 		= 4;
	
	// 2 Cost card index declarations
	public final int TURTLE 	= 5;
	public final int SHEEP 		= 6;
	public final int DOG 		= 7;
	public final int CAT 		= 8;
	public final int SQUIRREL	= 9;
	
	// 3 Cost card index declarations
	public final int OWL		= 10;
	public final int MONKEY		= 11;
	public final int GOAT		= 12;
	public final int COW		= 13;
	public final int DEER		= 14;
	
	// 4 Cost card index declarations
	public final int WOLF		= 15;
	public final int FOX		= 16;
	public final int DONKEY		= 17;
	public final int PIG		= 18;
	public final int SNAKE		= 19;
	
	// 5 Cost card index declarations
	public final int TIGER		= 20;
	public final int BEAR		= 21;
	public final int BULL		= 22;
	public final int BOAR		= 23;
	public final int HORSE		= 24;
		
	private Card[] shop;
	private Creature deck[];
	private int level;

	public Shop() {
		shop = new Card[0];
		level = 1;
		
		deck = new Creature[25];
		deck[MOUSE] 	= new Mouse();
		deck[DUCK] 		= new Duck();
		deck[BUNNY]		= new Bunny();
		deck[CHICKEN]	= new Chicken();
		deck[FROG]		= new Frog();
		deck[TURTLE]	= new Turtle();
		deck[SHEEP]		= new Sheep();
		deck[DOG]		= new Dog();
		deck[CAT]		= new Cat();
		deck[SQUIRREL]	= new Squirrel();
		deck[OWL]		= new Owl();
		deck[MONKEY]	= new Monkey();
		deck[GOAT]		= new Goat();
		deck[COW]		= new Cow();
		deck[DEER]		= new Deer();
		deck[WOLF]		= new Wolf();
		deck[FOX]		= new Fox();
		deck[DONKEY]	= new Donkey();
		deck[PIG]		= new Pig();
		deck[SNAKE]		= new Snake();
		deck[TIGER]		= new Tiger();
		deck[BEAR]		= new Bear();
		deck[BULL]		= new Bull();
		deck[BOAR]		= new Boar();
		deck[HORSE]		= new Horse();
	}
	
	public void levelUp() { level++; }
	
	public int getLevel() { return level; }
	
	public Card[] getShop() { return shop; }
	
	public Card[] rollShop() {
		Random rand = new Random(); 
		// If round 1, choose 3 creatures at most 1 cost
		if (level == 1) {
			Card[] selection = new Card[3];
			for (int i=0; i<3; i++) {
				int creature = rand.nextInt(4);
				selection[i] = deck[creature].getCard();
			}
			shop = selection;
		} 
		// If round 2, choose 4 creatures at most 2 cost
		else if (level == 2) {
			Card[] selection = new Card[4];
			for (int i=0; i<4; i++) {
				int creature = rand.nextInt(9);
				selection[i] = deck[creature].getCard();
			}
			shop = selection;
		} 
		// If round 3, choose 5 creatures at most 3 cost
		else if (level == 3) {
			Card[] selection = new Card[5];
			for (int i=0; i<5; i++) {
				int creature = rand.nextInt(14);
				selection[i] = deck[creature].getCard();
			}
			shop = selection;
		} 
		// If round 4, choose 6 creatures at most 4 cost
		else if (level == 4) {
			Card[] selection = new Card[6];
			for (int i=0; i<6; i++) {
				int creature = rand.nextInt(19);
				selection[i] = deck[creature].getCard();
			}
			shop = selection;
		} 
		// If round 5 or higher, choose 8 creatures at any cost
		else {
			Card[] selection = new Card[8];
			for (int i=0; i<8; i++) {
				int creature = rand.nextInt(24);
				selection[i] = deck[creature].getCard();
			}
			shop = selection;
		}
		return shop;
	}
		
	public String toString() {
		String res = "";
		for (int i = 0; i < shop.length; i++) {
			if (shop[i] != null) {
				res += shop[i].getName();
				res += " ";
			}
		}
		return res;
	}
}


















