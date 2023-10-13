package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AutoBattlerGUIView extends Application implements Observer {

	private AutoBattlerModel model;
	private AutoBattlerController controller;
	private BorderPane gameBoard;
	private VBox topPlayer;
	private VBox bottomPlayer;
	private HBox topChampions;
	private HBox bottomChampions;
	private VBox championsCombined;
	private Pair[] moveCards;

	public AutoBattlerGUIView() {
		model = new AutoBattlerModel();
		model.addObserver(this);
		controller = new AutoBattlerController(model);
		gameBoard = new BorderPane();
		championsCombined = new VBox(8);
		moveCards = new Pair[2];
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Genshin Auto Battler");
		Image background = new Image("Background.png");
		gameBoard.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		createTopPlayer();
		createBottomPlayer();
		createTopChamp();
		createBottomChamp();
		championsCombined.getChildren().addAll(topChampions,bottomChampions);
		gameBoard.setCenter(championsCombined);
		// window.setCenter(mainBoard());
		Scene scene = new Scene(gameBoard);
		stage.setScene(scene);
		stage.show();

	}

	/**
	 * helper method that creates the top players empty card slots and
	 * contains an event handler for clicking when players swap cards.
	 * player cards by default are stackpanes with a transparent rectangle
	 * and when a card is placed on top of card it is just added to stackpane
	 */
	private void createTopChamp() {
		topChampions = createChampSlots();
		topChampions.setOnMouseClicked((event) -> {
			int childIndex = findChild(event.getX());
			if(moveCards[0] == null) {
				Pair pair = new Pair(topChampions, childIndex);
				moveCards[0] = pair;
			}else {
				Pair pair = new Pair(topChampions, childIndex);
				moveCards[1] = pair;
				swapCards();
			}
			
		});
	}
	
	/**
	 * helper method that creates the bottom players empty card slots and
	 * contains an event handler for clicking when players swap cards.
	 * player cards by default are stackpanes with a transparent rectangle
	 * and when a card is placed on top of card it is just added to stackpane
	 */
	private void createBottomChamp() {
		bottomChampions = createChampSlots();
		bottomChampions.setOnMouseClicked((event) -> {
			int childIndex = findChild(event.getX());
			if(moveCards[0] == null) {
				Pair pair = new Pair(bottomChampions, childIndex);
				moveCards[0] = pair;
			}else {
				Pair pair = new Pair(bottomChampions, childIndex);
				moveCards[1] = pair;
				swapCards();
			}
			
		});
	}
	
	private HBox createChampSlots() {
		HBox tempBox = new HBox(8);
		for (int col = 0; col < 7; col++) {
			Rectangle placeHolder = new Rectangle(125,125,Color.TRANSPARENT);
			
			//how a card may be put on the board later, is used for now for testing
			Image emptyCard = new Image("baseCard.png");
			ImageView pic = new ImageView();
			pic.setPreserveRatio(true);
			pic.setImage(emptyCard);
			pic.setFitHeight(125);
			
			
			StackPane backgroundCard = new StackPane(placeHolder);
			backgroundCard.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN,null,null)));

			tempBox.getChildren().add(backgroundCard);
		}
		return tempBox;
	}
	
	/**
	 * helper method for event that takes where the user clicked 
	 * and finds the index of the child node of where the the user clicked
	 * 
	 * @param click, double that is the x-coordinate of where user clicked
	 * @return index, int that is the index of what node was clicked.
	 */
	private int findChild(double click) {
		double start = 0;
		double end = 125;
		for(int index = 0; index < 7; index++) {
			if(click >= start && click <= end) {
				return index;
			}
			start += 125 + 8;
			end += 125 + 8;	
		}
		return -1;
		
	}
	
	/**
	 * helper method that once two cards have been clicked will swap
	 * the two cards chosen. Uses the Pair[] moveCards to determine what 
	 * children to move and where
	 */
	private void swapCards() {
		//can add if shop take money
		StackPane pane0 = (StackPane) moveCards[0].cards.getChildren().get(moveCards[0].index);
		System.out.println(pane0.getChildren().size());
		StackPane pane1 = (StackPane) moveCards[1].cards.getChildren().get(moveCards[1].index);
		System.out.println(pane1.getChildren().size());
		if(pane0.equals(pane1)) {
			return;
		}
		if(pane0.getChildren().size() == 1 && pane1.getChildren().size()==1) {
			return;			
		}else if(pane0.getChildren().size() == 1) {
			pane0.getChildren().add(pane1.getChildren().remove(1));
		}else if(pane1.getChildren().size() == 1) {
			pane1.getChildren().add(pane0.getChildren().remove(1));
		}else {
			Node node0 = pane0.getChildren().remove(1);
			Node node1 = pane1.getChildren().remove(1);
			pane0.getChildren().add(node1);
			pane1.getChildren().add(node0);
		}
		moveCards = new Pair[2];
	}
	
	/**
	 * helper method that creates the to player slot and creates the top player 
	 * bench. be more descriptive soon and make it look nice
	 */
	private void createTopPlayer() {
		topPlayer = new VBox(10);
		Rectangle placeHolder = new Rectangle(125,125,Color.TRANSPARENT);
		HBox topBench = createBench();
		topBench.setAlignment(Pos.CENTER);
		topBench.setOnMouseClicked((event)->{
			int childIndex = findChild(event.getX());
			if(moveCards[0] == null) {
				Pair pair = new Pair(topBench, childIndex);
				moveCards[0] = pair;
			}else {
				Pair pair = new Pair(topBench, childIndex);
				moveCards[1] = pair;
				swapCards();
			}
		});
		
		Image player1 = new Image("baseCard.png");
		ImageView pic1 = new ImageView();
		pic1.setPreserveRatio(true);
		pic1.setImage(player1);
		pic1.setFitHeight(140);
		Rectangle champ1Back = new Rectangle(140,120, Color.SADDLEBROWN);
		StackPane back1 = new StackPane(champ1Back,pic1);
		
		topPlayer.getChildren().addAll(topBench,back1);
		gameBoard.setTop(topPlayer);
		gameBoard.setAlignment(pic1, Pos.TOP_CENTER);
	}
	
	/**
	 * helper method that creates the to player slot and creates the top player 
	 * bench. be more descriptive soon and make it look nice
	 */
	private void createBottomPlayer() {
		bottomPlayer = new VBox(10);
		HBox bottomBench = createBench();
		bottomBench.setAlignment(Pos.CENTER);
		bottomBench.setOnMouseClicked((event) -> {
			int childIndex = findChild(event.getX());
			if(moveCards[0] == null) {
				Pair pair = new Pair(bottomBench, childIndex);
				moveCards[0] = pair;
			}else {
				Pair pair = new Pair(bottomBench, childIndex);
				moveCards[1] = pair;
				swapCards();
			}
		});
		//this is the player card that is by itself
		Image player2 = new Image("baseCard.png");
		ImageView pic2 = new ImageView();
		pic2.setPreserveRatio(true);
		pic2.setImage(player2);
		pic2.setFitHeight(140);
		Rectangle champ2Back = new Rectangle(140,120, Color.SADDLEBROWN);
		StackPane back2 = new StackPane(champ2Back,pic2);
		
		bottomPlayer.getChildren().addAll(back2, bottomBench);
		gameBoard.setBottom(bottomPlayer);
		gameBoard.setAlignment(pic2, Pos.BOTTOM_CENTER);
		
	}
	
	/**
	 * helper method that creates an Hbox of how the bench will look like
	 * 
	 * @return tempBox, Hbox that will be used as a bench for one of the players
	 */
	private HBox createBench() {
		HBox tempBox = new HBox();
		for (int col = 0; col < 7; col++) {
			Rectangle placeHolder = new Rectangle(125,125,Color.TRANSPARENT);
			
			//how a card may be put on the board later is used for now for testing
			Image emptyCard = new Image("baseCard.png");
			ImageView pic = new ImageView();
			pic.setPreserveRatio(true);
			pic.setImage(emptyCard);
			pic.setFitHeight(125);
			
			
			StackPane backgroundCard = new StackPane(placeHolder,pic);
			backgroundCard.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN,null,null)));

			tempBox.getChildren().add(backgroundCard);
		}
		return tempBox;
			
	}

	private Image getImage(String filePath) throws IOException {
		InputStream stream = new FileInputStream(filePath);
		Image image = new Image(stream);
		return image;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * This class was made for the purpose of helping swap cards
	 * so an object will hold two types one being an HBox and the other
	 * is an integer that is the index to a specific child.
	 *
	 */
	private class Pair{
		public HBox cards;
		public int index;
		
		/**
		 * constructor method for object Pair
		 * @param newCards, an Hbox containing cards
		 * @param num, an index num to find a specific child node.
		 */
		public Pair(HBox newCards, int num) {
			cards = newCards;
			index = num;
		}
	}

}