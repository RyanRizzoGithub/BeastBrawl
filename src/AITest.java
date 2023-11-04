package src;

public class AITest {
	public static void main(String[] args) {
		AI ai = new AI(1);
		for (int i=1; i<25; i++) {
			ai.shopPhase(i);
		}
	}
}
