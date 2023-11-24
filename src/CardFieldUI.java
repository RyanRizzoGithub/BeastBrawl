package src;

import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CardFieldUI {
	public HBox box;
	public String name;
	public int num;
	public CardFieldUI() {
		box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.toBack();
		name="";
		num=-1;
	}
	public CardFieldUI(String name,int num) {
		box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.toBack();
		this.name=name;
		this.num=num;
	}
	
	
}