package src;

import java.util.Random;
import src.Creatures.Bear;
import src.Creatures.Boar;
import src.Creatures.Bull;
import src.Creatures.Bunny;
import src.Creatures.Cat;
import src.Creatures.Chicken;
import src.Creatures.Cow;
import src.Creatures.Creature;
import src.Creatures.Deer;
import src.Creatures.Dog;
import src.Creatures.Donkey;
import src.Creatures.Mouse;
import src.Creatures.Owl;
import src.Creatures.Pig;
import src.Creatures.Sheep;
import src.Creatures.Snake;
import src.Creatures.Squirrel;
import src.Creatures.Tiger;
import src.Creatures.Turtle;
import src.Creatures.Wolf;
import src.Creatures.Duck;
import src.Creatures.Fox;
import src.Creatures.Frog;
import src.Creatures.Goat;
import src.Creatures.Horse;
import src.Creatures.Monkey;

public class Deck {
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
	
	public Creature deck[];
	
	public Deck() {												
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
	
	public Creature[] drawShop(int round) {
		Random rand = new Random(); 
		// If round 1, choose 3 creatures at most 1 cost
		if (round == 1) {
			Creature[] selection = new Creature[3];
			for (int i=0; i<3; i++) {
				int creature = rand.nextInt(4);
				selection[i] = deck[creature];
			}
			return selection;
		} 
		// If round 2, choose 4 creatures at most 2 cost
		if (round == 2) {
			Creature[] selection = new Creature[4];
			for (int i=0; i<4; i++) {
				int creature = rand.nextInt(9);
				selection[i] = deck[creature];
			}
			return selection;
		} 
		// If round 3, choose 5 creatures at most 3 cost
		if (round == 3) {
			Creature[] selection = new Creature[5];
			for (int i=0; i<5; i++) {
				int creature = rand.nextInt(14);
				selection[i] = deck[creature];
			}
			return selection;
		} 
		// If round 4, choose 6 creatures at most 4 cost
		if (round == 4) {
			Creature[] selection = new Creature[6];
			for (int i=0; i<6; i++) {
				int creature = rand.nextInt(19);
				selection[i] = deck[creature];
			}
			return selection;
		} 
		// If round 5 or higher, choose 8 creatures at any cost
		else {
			Creature[] selection = new Creature[8];
			for (int i=0; i<8; i++) {
				int creature = rand.nextInt(24);
				selection[i] = deck[creature];
			}
			return selection;
		}
		
	}
}