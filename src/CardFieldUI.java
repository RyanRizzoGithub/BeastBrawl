package src;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CardFieldUI {
	public HBox box;
	public String name;
	public int num;
	public CardFieldUI() {
		box = new HBox();
		name="";
		num=-1;
	}
	public CardFieldUI(String name,int num) {
		box = new HBox();
		this.name=name;
		this.num=num;
	}
	
	
}