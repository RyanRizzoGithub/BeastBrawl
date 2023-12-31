package src;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import Creatures.Creature;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.AutoBattlerGUIView.Pair;

public class AutoBattlerGUIView extends Application implements Observer, PropertyChangeListener  {

	private AutoBattlerModel model;
	private AutoBattlerController controller;
	private BorderPane gameBoard;
	private VBox topPlayer;
	private VBox bottomPlayer;
	private CardFieldUI topChampions;
	private CardFieldUI bottomChampions;
	private CardFieldUI cardsForSale;
	private CardFieldUI bottomBench;
	private CardFieldUI topBench;
	private VBox shop;
	private Pair[] moveCards;
	private Pair[] moveCardsClicked;
	private StackPane topStats;
	private StackPane bottomStats;
	private boolean attackPhase;
	private double startX;
	private double startY;
	private int startingIndex;
	private int startingFieldIndex;
	private ArrayList<CardFieldUI> cardFieldList;
	private Scene gameScene;
	private Scene mainMenuScene;
	private Scene instructScene;
	private Scene loseScene;
	private Scene winScene;
	private StartMenuGUI mainMenuGUI;
	private InstructionPageUI instructGUI;
	private LoserEndScreenGUI loseGUI;
	private WinnerEndScreenGUI winGUI;
	private Stage curStage;
	private ImageView mainMenuView;
	private ImageView instructView;
	private String title;
	private Rectangle2D bounds;
	private int cardSlotX;
	private int cardSlotY;
	private int champFieldX;
	private int playerImageY;
	private int difficulty;
	private Button turnButton;
	private Button step;
	private Button endAttack;
	private Button breakGame;
	
	public AutoBattlerGUIView(Rectangle2D bounds) {
		model = new AutoBattlerModel();
		model.addObserver(this);; //this is how you win the bitches
		controller = new AutoBattlerController(model);
		gameBoard = new BorderPane();
		stepButton();
		endAttackButton();
		
		difficulty = 0;
		moveCards = new Pair[2];
		moveCardsClicked = new Pair[2];
		attackPhase = false;
		curStage = new Stage();
		// assings bounds to the size of the screen
		this.bounds = bounds;
		/*Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		double width = resolution.getWidth();
		double height = resolution.getHeight(); 
		double w = width/1920;  // your window width
		double h = height/1080;  // your window height
		Scale scale = new Scale(w, h, 0, 0);
		gameBoard.setPrefSize(w, h);*/
		//stage.setTitle("Genshin Auto Battler");
		cardSlotX=(int) (bounds.getWidth()/20);
		cardSlotY=(int) (bounds.getWidth()/18);
		BackgroundSize screenSize = new BackgroundSize(bounds.getWidth(), bounds.getHeight(),true, true, true, false);
		Image background = new Image("assets/Background13.png");
	
		BackgroundPosition center = BackgroundPosition.CENTER;
		
		gameBoard.setBackground(new Background(new BackgroundImage(background, null, null, 
				new BackgroundPosition(center.getHorizontalSide(),center.getHorizontalPosition(),true,Side.BOTTOM,center.getVerticalPosition(),true), screenSize)));
		title="Auto Battler";
		startGame();
		createTopChamp();
		createBottomChamp();
		createBottomPlayer();
		createTopPlayer();
		createShop();
		// adds fields to list in the order they appear to the player
		cardFieldList = new ArrayList<CardFieldUI>();
		cardFieldList.add(bottomBench);
		cardFieldList.add(bottomChampions);
		cardFieldList.add(topChampions);
		cardFieldList.add(topBench);
		cardFieldList.add(cardsForSale);

		gameBoard.setTop(shop);
		gameBoard.setBottom(bottomPlayer);
		gameBoard.setMargin(bottomPlayer, new Insets(10,10,10,10));
		gameBoard.setMargin(shop, new Insets(10,10,10,10));
		
		

		// if in shop phase make another thing
		gameScene= new Scene(gameBoard);
		
		
		//letterbox(gameScene,gameBoard);
	}
	
	public void setBounds(Rectangle2D newBounds) {
		bounds=newBounds;
	}
	
	public void setMainMenuGUI(StartMenuGUI newGUI) {
		mainMenuGUI = newGUI;
	}
	public void setInscructionPageGUI(InstructionPageUI newGUI) {
		instructGUI = newGUI;
	}
	public void setMainMenuScene(Scene newScene) {
		mainMenuScene = newScene;
	}
	public void setInstructScene(Scene newScene) {
		instructScene = newScene;
	}
	public void setWinScene(Scene newScene) {
		winScene = newScene;
	}
	public void setLoseScene(Scene newScene) {
		loseScene = newScene;
	}
	public void setWinGUI(WinnerEndScreenGUI newGUI) {
		winGUI = newGUI;
	}
	public void setLoseGUI(LoserEndScreenGUI newGUI) {
		loseGUI = newGUI;
	}
	
	public void setStage(Stage newStage) {
		curStage = newStage;
	}
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Auto Battler");
		//curStage =stage;
		//stage.show();

	}

	public BorderPane getGameBoard() {
		return gameBoard;
	}
	
	
	public void startGame() {
		Image endTurnImage = new Image("assets/endTurn.png");
		ImageView endTurnButtonView = new ImageView(endTurnImage);
		endTurnButtonView.setPreserveRatio(true);
		endTurnButtonView.setFitHeight(30);
		endTurnButtonView.setSmooth(false);
		
	    turnButton = new Button();
	    turnButton.setTextFill(Color.BLACK);
	    turnButton.setGraphic(endTurnButtonView);

		turnButton.setOnMouseClicked((event) -> {
			// start attack phase
			attackPhase = true;
			Platform.runLater(() -> attackStart());
			
		});
		

		breakGame = new Button();
		breakGame.setTextFill(Color.BLACK);
		breakGame.setText("BREAK GAME");
		breakGame.setOnMouseClicked((event) -> {
			try {
				int[] arr = new int[1];
				arr[1] = 0;
				
			} catch (Exception e) {
				
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Error");
		        alert.setHeaderText("An error has occured");
		        String content ="ERROR: " + e.toString() + "\n ";
		        content += "an error has occured, the game will now close...";
		        alert.setContentText(content);
		        alert.showAndWait();
		        if (alert.getResult() == ButtonType.OK) {
		            resetGame();
		        }
		        curStage.close();
		        System.exit(1);
	        
			}
		});

	}
	
	private void stepButton() {
		step = new Button();
		step.setText("step");
	    step.setTextFill(Color.BLACK);
	    step.setOnMouseClicked((event) ->{
	    	
	    	controller.startAttackPhase();
	    	//System.out.println("This is player 1 card: " + controller.getCardFight()[0][1]);
	    	//System.out.println("This is player 2 card: " + controller.getCardFight()[1][1]);
	    	//System.out.println(bottomChampions.box.getChildren().toString());
	    	displayInteractions();
	    	if (controller.getIsRoundOver() != 0) {
	    		endAttack.setVisible(true);
	    		step.setVisible(false);
	    		controller.attackPhaseOver();//so you can see how much health the player took at the end of round
	    		displayInteractions();//so it shows the last turn
	    	}
	    });
	}
	
	/*
	 * this is how the cards display who is attacking and who is not
	 */
	private void displayInteractions() {
		int attacking_card = controller.getCardFight()[0][1];
		int defending_card = controller.getCardFight()[1][1];

		//player 1 is attacking player 2
		if(controller.getCardFight()[0][0] == 1) {
			//for card that is attacking
			StackPane attackRoot = (StackPane) bottomChampions.box.getChildren().get(attacking_card);
			if(attackRoot.getChildren().size() == 1) {
				return;
			}
			StackPane card = (StackPane) attackRoot.getChildren().get(1);
			Background attackBack = new Background(new BackgroundFill(Color.LIME, null, null));
			card.setBackground(attackBack);
			//for card that is defending
			StackPane defendRoot = (StackPane) topChampions.box.getChildren().get(defending_card);
			if(defendRoot.getChildren().size() == 1) {
				return;
			}
			StackPane defendCard = (StackPane) defendRoot.getChildren().get(1);
			Background defendBack = new Background(new BackgroundFill(Color.RED, null, null));
			defendCard.setBackground(defendBack);
		}else {
			//for card that is attacking
			StackPane attackRoot = (StackPane) topChampions.box.getChildren().get(attacking_card);
			if(attackRoot.getChildren().size() == 1) {
				return;
			}
			StackPane card = (StackPane) attackRoot.getChildren().get(1);
			Background attackBack = new Background(new BackgroundFill(Color.LIME, null, null));
			card.setBackground(attackBack);
			//for card that is defending
			StackPane defendRoot = (StackPane) bottomChampions.box.getChildren().get(defending_card);
			if(defendRoot.getChildren().size() == 1) {
				return;
			}
			StackPane defendCard = (StackPane) defendRoot.getChildren().get(1);
			Background defendBack = new Background(new BackgroundFill(Color.RED, null, null));
			defendCard.setBackground(defendBack);
		}
	}
	
	private void endAttackButton() {
		endAttack = new Button();
		endAttack.setVisible(false);
		endAttack.setText("End Attack Phase");
		endAttack.setTextFill(Color.BLACK);
		endAttack.setOnMouseClicked((event) -> {
			 //called one more time to distribute coins
			attackPhase = false;
			controller.resetChampionStats();
			System.out.println("attack phase is over here");
			startGame();
			createShop();
			gameBoard.setTop(shop);
			gameBoard.setMargin(shop, new Insets(10,10,10,10));
			step.setVisible(true);
			endAttack.setVisible(false);
			
		});
		
	}

	private void attackStart() {
		gameBoard.setTop(topPlayer);
		gameBoard.setMargin(topPlayer, new Insets(10,10,10,10));
		controller.AIturn();
	    

	}

	/**
	 * helper method that creates the top players empty card slots and contains an
	 * event handler for clicking when players swap cards. player cards by default
	 * are stackpanes with a transparent rectangle and when a card is placed on top
	 * of card it is just added to stackpane
	 */
	private void createTopChamp() {
		topChampions=new CardFieldUI("topChampions",1);
		topChampions.box = createChampSlots();
		topChampions.box.setOnMousePressed((event) -> {
			int childIndex = findChild(event.getSceneX(),false);
			if (moveCards[0] == null) {
				Pair pair = new Pair(topChampions.box, 1, childIndex);
				moveCards[0] = pair;
			} else {
				Pair pair = new Pair(topChampions.box, 1, childIndex);
				moveCards[1] = pair;
				controller.changePosition(moveCards[0].indices, 1, moveCards[1].indices);
				moveCards = new Pair[2];
			}
		});
	}

	/**
	 * helper method that creates the bottom players empty card slots and contains
	 * an event handler for clicking when players swap cards. player cards by
	 * default are stackpanes with a transparent rectangle and when a card is placed
	 * on top of card it is just added to stackpane
	 */
	private void createBottomChamp() {
		bottomChampions=new CardFieldUI("bottomChamp",1);
		bottomChampions.box = createChampSlots();
		
		// saves card when pressed so they can be dragged
		bottomChampions.box.setOnMousePressed((event) -> {
			int childIndex = findChild(event.getSceneX(),false);
			if (moveCards[0] == null) {
				Pair pair = new Pair(bottomChampions.box, 1, childIndex);
				moveCards[0] = pair;
				startingIndex = findChild(event.getSceneX(),false);
				startingFieldIndex=1;
			} else {
				Pair pair = new Pair(bottomChampions.box, 1, childIndex);
				moveCards[1] = pair;
				controller.changePosition(moveCards[0].indices, 1, moveCards[1].indices);
				moveCards = new Pair[2];
			}
		});
		bottomChampions.box.setOnMouseReleased((event)->{	
			if(moveCards[0]!=null) {
				// gets index of the mouse is over
				int childIndex = findChild(event.getSceneX(),false);
				// gets the index of the field the mouse is over
				int fieldIndex=findDroppedField(event.getY(),bottomChampions);
				// if dropped on bench
				if(fieldIndex==0) {
					childIndex = findChild(event.getSceneX(),true);
				}
				// gets field the mouse is over
				if(fieldIndex>=0) {
					CardFieldUI fieldDroppedOn = cardFieldList.get(fieldIndex);
					//sell cards
					if(fieldIndex == 4) { //maybe ask paolo if this messes everything up
						Player p1 = controller.getP1();
						controller.sellChampion(p1, moveCards[0].indices[0], moveCards[0].indices[1]);
						moveCards = new Pair[2];
						return; //maybe works
					}
					else if(childIndex<0 || fieldIndex < 0 || moveCards[0].indices[1]<0) {
						controller.changePosition(moveCards[0].indices, 1, moveCards[0].indices);
					}
					// saves the card and the position of what the mouse was over when released
					Pair pair = new Pair(fieldDroppedOn.box, fieldIndex, childIndex);
					moveCards[1] = pair;
					// swaps
					controller.changePosition(moveCards[0].indices, 1, moveCards[1].indices);
					//System.out.println(" -- moving to "+childIndex+" from "+moveCards[0].indices[1]+" in field "+fieldIndex);
					moveCards = new Pair[2];
				}
			}	
		});
		
	}

	/**
	 * helper method that creates champions cardslots.
	 * 
	 * @return tempBox, HBox cardslots for champions
	 */

	// TODO create labels near player side where it displays the current traits on
	// their board
	// change player to flowplane and set player in center and traits on
	// top,bottom,left,right
	private StackPane createPlayerArea(Player p1, boolean AI) {
		// controller get traits
		Label trait = new Label("Buffs");
		trait.setTextFill(Color.SNOW);
		ImageView pic = new ImageView();
		if (AI) {
			Image player = new Image("assets/AIbaseCard.png");	
			pic.setPreserveRatio(true);
			pic.setImage(player);
		} else {
			Image player = new Image("assets/userBaseCard.png");
			pic.setPreserveRatio(true);
			pic.setImage(player);
		}
		//pic.setFitHeight(cardSlotY);
		Rectangle champ2Back = new Rectangle(cardSlotX, cardSlotY, Color.SADDLEBROWN);

		Label hp = new Label("" + p1.getHealth());
		hp.setTextFill(Color.BLACK);

		// get moneys
		Image money = new Image("assets/coin.png");
		ImageView moneyView = new ImageView(money);
		moneyView.setPreserveRatio(true);
		moneyView.setFitHeight(bounds.getHeight()/42);

		Label moneyText = new Label(Integer.toString(p1.getGold()));
		moneyText.setTextFill(Color.YELLOW);
		moneyText.setOpacity(100);
		
		StackPane back2 = new StackPane(champ2Back, pic, trait, hp, moneyView, moneyText);
		back2.setMargin(trait, new Insets(bounds.getHeight()/7, bounds.getHeight()/18.8, 0, 0));
		back2.setMargin(hp, new Insets(bounds.getHeight()/13.5, bounds.getHeight()/30.08, 0, 0));
		back2.setMargin(moneyView, new Insets(bounds.getHeight()/13.5, 0, 0, bounds.getHeight()/37.6));
		back2.setMargin(moneyText, new Insets(bounds.getHeight()/13.5, 0, 0, bounds.getHeight()/15.04));
		return back2;

	}

	/**
	 * helper method that creates the empty champion spots in front of the player
	 * 
	 * @return tempBox, HBox holds empty spaces for the champion slots
	 */
	private HBox createChampSlots() {
		HBox tempBox = new HBox(10);
		tempBox.setAlignment(Pos.CENTER);
		tempBox.setMaxHeight(cardSlotY);
		tempBox.setMaxWidth(cardSlotX*10);
		//tempBox.setSpacing(1);
		tempBox.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		int height =(int)bounds.getHeight();
		for (int col = 0; col < 10; col++) {
			Rectangle placeHolder = new Rectangle(cardSlotX, cardSlotY, Color.TRANSPARENT);
			StackPane backgroundCard = new StackPane(placeHolder);
			backgroundCard.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
			//backgroundCard.setBorder(Border.stroke(Color.rgb(255,234,160)));
			tempBox.getChildren().add(backgroundCard);
			
		}
		
		return tempBox;
	}

	/**
	 * helper method for event that takes where the user clicked and finds the index
	 * of the child node of where the the user clicked
	 * 
	 * @param click, double that is the x-coordinate of where user clicked
	 * @return index, int that is the index of what node was clicked.
	 */
	private int findChild(double click, boolean isBench) {
		int offset =(int) (bounds.getWidth()-(10*(cardSlotX+15)))/2;
		double start = offset;
		int cardOffset=15;
		double end = start+cardSlotX+cardOffset;
		int max=10;
		// changes offset and max to fit a bench 
		if(isBench) {
			offset =(int) (bounds.getWidth()-(15*(cardSlotX)))/2;
			start = offset;
			end = start+cardSlotX;
			max=15;
			cardOffset=0;
			//System.out.println("--- "+click+"  start = "+start+" end = "+end+" offset = "+offset+"card = "+cardSlotX);
		}
		int index=0;
		for ( index = 0; index < max; index++) {
			if (click >= start && click <= end) {
				
				return index;
			}
			start += cardSlotX;
			end += cardSlotX+cardOffset;
		}
		//System.out.println(index+"  start = "+start+" end = "+end);
		//System.out.println(index);
		return -1;

	}
	/**
	 * Finds the index of a field in regards to cardFieldList.
	 * It does this by getting the y value of where the user clicked on 
	 * the screen. Then it determines which field the user is in and 
	 * returns the index of that field. For example the bottomBench field 
	 * would be index 0 as its on the bottom of the players view.
	 * 
	 * @param click, double that is the y-coordinate of where user clicked
	 * @return index, int that is the index of what field was clicked.
	 */
	private int findField(double click) {
		int index = findFieldHelper(click);
		
		
		return index;
	}
	
	/**
	 * Finds the index of a field a card is dropped on
	 * 
	 * @param click, double that is the y-coordinate of where user clicked
	 * @return index, int that is the index of what field a card was dropped on.
	 */
	private int findDroppedField(double click,CardFieldUI draggedFromField) {
		int getDragggedFieldIndex = GetFeildIndex(draggedFromField);
		return getDragggedFieldIndex+findField(click);
	}
	
	/**
	 * Helper function that calculates the index of a field based on double 
	 * representing where the user clicked in the y direction.
	 * 
	 * @param click, double that is the y-coordinate of where user clicked
	 * @return index, int that is the index of what field was clicked.
	 */
	private int findFieldHelper(double click) {
		double start = 0;
		int fieldOffset = cardSlotY+20;
		double end = fieldOffset;
		// handles moving card on the same field
		if(click<=80 && click>=0) {
			click = Math.abs(click);
		}
		// handles moving cards across fields
		else {
			click=click*-1;
		}
		// determines what field to move card to
		for (int index = 0; index < 7; index++) {
			if (click >= start && click <= end) {
				return index;
			}
			start += fieldOffset;
			end += fieldOffset+50;
		}
		
		return -1;

	}
	
	private int GetFeildIndex(CardFieldUI fieldToFInd) {
		return cardFieldList.indexOf(fieldToFInd);
	}

	/**
	 * helper method that creates the to player slot and creates the top player
	 * bench. be more descriptive soon and make it look nice
	 */
	private void createTopPlayer() {
		topPlayer = new VBox(10);
		
		topPlayer.setAlignment(Pos.CENTER);
		//topPlayer.setSpacing(1);
		topBench = new CardFieldUI("topBench",0);
		topBench.box = createBench();
		topBench.box.setOnMousePressed((event) -> {
			//System.out.println("------------------");
			int childIndex = findChild(event.getX(),false);
			if (moveCards[0] == null) {
				Pair pair = new Pair(topBench.box, 0, childIndex);
				moveCards[0] = pair;
			} else {
				Pair pair = new Pair(topBench.box, 0, childIndex);
				moveCards[1] = pair;
				controller.changePosition(moveCards[0].indices, 1, moveCards[1].indices);
				moveCards = new Pair[2];
			}
		});

		topStats = createPlayerArea(controller.getP2(), true);
		topPlayer.getChildren().addAll( playerAndItems(topStats), topChampions.box);
		topPlayer.getChildren().add(step);
		topPlayer.getChildren().add(endAttack);

	}

	/**
	 * helper method that creates the to player slot and creates the top player
	 * bench. be more descriptive soon and make it look nice
	 */
	private void createBottomPlayer() {
		bottomPlayer = new VBox();
		bottomPlayer.setAlignment(Pos.CENTER);
		
		//bottomPlayer.setSpacing(1);
		bottomBench=new CardFieldUI("bottomBench",0);
		bottomBench.box = createBench();
		bottomBench.box.toBack();
		//bottomBench.box.setAlignment(Pos.CENTER);F
		// saves card when pressed so they can be dragged
		bottomBench.box.setOnMousePressed((event) -> {
			// saves card when pressed
			
			int childIndex = findChild(event.getSceneX(),true);
			//System.out.println("selecting "+childIndex);
			if (moveCards[0] == null) {
				Pair pair = null;
				pair = new Pair(bottomBench.box, 0, childIndex);
				moveCards[0] = pair;
				startingIndex = childIndex;
				startingFieldIndex=0;
			} else {
				Pair pair = new Pair(bottomBench.box, 0, childIndex);
				moveCards[1] = pair;
				controller.changePosition(moveCards[0].indices, 1, moveCards[1].indices);
				moveCards = new Pair[2];
			}
		});
		bottomBench.box.setOnMouseReleased((event)->{	
			//System.out.println(cardFieldList.get(0).name);
			if(moveCards[0]!=null) {
				// gets index of the mouse is over
				int childIndex = findChild(event.getSceneX(),false);
				// gets the index of the field the mouse is over
				int fieldIndex=findDroppedField(event.getY(),bottomBench);
				if(fieldIndex==0) {
					// if card dropped on bench
					//System.out.println("getting from bench");
					childIndex = findChild(event.getSceneX(),true);
				}
				// gets field the mouse is over
				
				if(fieldIndex>=0) {
					CardFieldUI fieldDroppedOn = cardFieldList.get(fieldIndex);
					//sell card if card dropped on top player stuff
					if(fieldIndex == 4) { //maybe ask paolo if this messes everything up
						Player p1 = controller.getP1();
						controller.sellChampion(p1, moveCards[0].indices[0], moveCards[0].indices[1]);
						moveCards = new Pair[2];
					}
					// if card dropped on invalid spot
					else if(childIndex<0 || fieldIndex < 0 || moveCards[0].indices[1]<0 || (childIndex>10 && fieldIndex>0)) {		
						//System.out.println( " tried to swap "+childIndex+" from "+moveCards[0].indices[1]+" in field "+fieldIndex+"  "+fieldDroppedOn.box);
						controller.changePosition(moveCards[0].indices, 1, moveCards[0].indices);
					}
					else {
						// saves the card and the position of what the mouse was over when released
						Pair pair = new Pair(fieldDroppedOn.box, fieldIndex, childIndex);
						moveCards[1] = pair;
						// swaps
						controller.changePosition(moveCards[0].indices, 1, moveCards[1].indices);
						moveCards = new Pair[2];
					}
				}				
			}
		});
		bottomStats = createPlayerArea(controller.getP1(), false);
		FlowPane bottomCards =playerAndItems(bottomStats);
		bottomPlayer.getChildren().addAll(bottomChampions.box, bottomCards, bottomBench.box);

	}

	private FlowPane playerAndItems(StackPane stats) {
		FlowPane flow = new FlowPane();
		flow.setHgap(8);
		flow.setPadding(new Insets(7));
		flow.getChildren().add(stats);	
		flow.setAlignment(Pos.CENTER);
		return flow;
	}
	

	/**
	 * helper method that creates an Hbox of how the bench will look like
	 * 
	 * @return tempBox, Hbox that will be used as a bench for one of the players
	 */
	private HBox createBench() {
		HBox tempBox = new HBox();
		tempBox.setMaxWidth(cardSlotX*15);
		//tempBox.toBack();
		//tempBox.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		int height =(int)bounds.getHeight();
		tempBox.setMaxHeight(cardSlotY);
		tempBox.setAlignment(Pos.CENTER);
		//tempBox.setBorder(Border.stroke(Color.rgb(255,234,160)));
		for (int col = 0; col < 15; col++) {
			Rectangle placeHolder = new Rectangle(cardSlotX, cardSlotY, Color.TRANSPARENT);
			StackPane backgroundCard = new StackPane(placeHolder);
			backgroundCard.setAlignment(Pos.CENTER);
			//backgroundCard.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

			tempBox.getChildren().add(backgroundCard);
		}
		return tempBox;

	}

	// needs to take champ class card
	private StackPane createCard(Card champ, boolean isDraggable) {
		//gets base card pic?
		int height =(int)bounds.getHeight();
		String cardName = ("/assets/" + champ.getName().toLowerCase() + "Card.png");
		Image emptyCard = new Image(cardName);
		ImageView pic = new ImageView();
		pic.setPreserveRatio(true);
		pic.setImage(emptyCard);
		pic.setFitHeight(cardSlotY);
		pic.setFitWidth(cardSlotX);
		
		//TODO check if this gets correct stats
		StackPane pane = new StackPane();
		String attackStr = Integer.toString(champ.getAtk());
		String hpStr = Integer.toString(champ.getHp());
		Label hp = new Label(hpStr);
		hp.setTextFill(Color.BLACK);

		Label attack = new Label(attackStr);
		attack.setTextFill(Color.BLACK);

		Image money = new Image("assets/coin.png");
		ImageView moneyView = new ImageView(money);
		moneyView.setPreserveRatio(true);
		moneyView.setFitHeight(bounds.getHeight()/42);

		Label moneyText = new Label("" + champ.getPrice());
		moneyText.setTextFill(Color.YELLOW);
		moneyText.setOpacity(100);

		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(pic, hp, attack, moneyView, moneyText);
		pane.setMargin(attack, new Insets(cardSlotY/1.25, 0, 0, cardSlotX/2));
		pane.setMargin(hp, new Insets(cardSlotY/1.25, cardSlotX/5, 0, 0));	
		pane.setMargin(moneyView, new Insets(0, bounds.getHeight()/18, cardSlotY/1.25, 0));
		pane.setMargin(moneyText, new Insets(0, bounds.getHeight()/35, cardSlotY/1.25, 0));
		if(isDraggable) {
			pane.setOnMousePressed(e->{
				// saves cards current position when pressed
				int childIndex = findChild(e.getSceneX(),false);
				startX = e.getSceneX()-pane.getTranslateX();
				startY = e.getSceneY()-pane.getTranslateY();
				
				
			});
			// allows card to follow mouse as dragged
			pane.setOnMouseDragged(e->{ 
				// moves card to mouse
				
				pane.setTranslateX(e.getSceneX()-startX);
				pane.setTranslateY(e.getSceneY()-startY);
				pane.setTranslateZ(10);
				
			});
		}
		//pane.setMaxHeight(cardSlotY);
		return pane;
	}

	// how shop is created
	private void createShop() {
		shop = new VBox(10);
		shop.setAlignment(Pos.CENTER);
		shop.setPrefHeight(10);
		shop.setSpacing(1);
		//shop.setMaxWidth(bounds.getWidth());
		controller.startShopPhase();
		Player player = controller.getP1();
		Card[] shopArray = controller.getShop(player);
		cardsForSale = new CardFieldUI("cardsForSale",1);
		cardsForSale.box = createChampSlots();
		//cardsForSale.box.setMaxHeight(10);
		for (int index = 0; index < shopArray.length; index++) {
			StackPane card = createCard(shopArray[index],false);
			StackPane emptySlot = (StackPane) cardsForSale.box.getChildren().get(index);
			
			emptySlot.getChildren().add(card);
		}
		// TODO fix this for sure
		cardsForSale.box.setOnMouseClicked((event) -> {
			int childIndex = findChild(event.getSceneX(),false);
			Player p1 = controller.getP1();
			
			controller.buyCharacter(p1, childIndex);
			Pair playerCards = new Pair(bottomBench.box, 0, findEmptySpot(bottomBench.box));
			moveCards[1] = playerCards;
			Pair shopCards = new Pair(cardsForSale.box, 1, childIndex);
			moveCards[0] = shopCards;
			controller.changePosition(moveCards[0].indices, 2, moveCards[1].indices);
			moveCards = new Pair[2];
			//System.out.println("bought card at "+childIndex);

		});
		cardsForSale.box.setOnDragDropped((event) ->
		{
		System.out.println("This event happend");	
		
		});
		HBox shopArea = createShopArea();
		shop.getChildren().addAll(shopArea, cardsForSale.box);


		// get model shop and read array characters

	}

	private int findEmptySpot(HBox area) {
		int index = 0;
		for (Node node : area.getChildren()) {
			StackPane pane = (StackPane) node;
			if (pane.getChildren().size() == 1) {
				return index;
			}
			index++;
		}
		return index;
	}

	private HBox createShopArea() {
		HBox playerArea = new HBox(1);
		playerArea.setAlignment(Pos.CENTER);
		int height=(int) bounds.getHeight();
		playerArea.setMinHeight(height/10);
		playerArea.setSpacing(30);
		
		Button mainMenuButton = new Button();
		Image mainMenu = new Image("assets/mainMenu.png");
	    mainMenuView = new ImageView(mainMenu);
		mainMenuView.setPreserveRatio(true);
		mainMenuView.setFitHeight(30);
		mainMenuButton.setGraphic(mainMenuView);
		
		mainMenuButton.setOnMouseClicked((event) -> {
			//Stage temp = curStage;
			curStage.close();
			curStage = mainMenuGUI.getStage();
			//temp.close();
			switchGameView(mainMenuGUI.getScene(),mainMenuGUI.getStage(),mainMenuGUI.getTitle(),true);
		});
		playerArea.getChildren().add(mainMenuButton);
		Button instructButton = new Button();
		Image instructPage = new Image("assets/helpText.png");
	    instructView = new ImageView(instructPage);
	    instructView.setPreserveRatio(true);
	    instructView.setFitHeight(30);
	    instructButton.setGraphic(instructView);
	    
		instructButton.setOnMouseClicked((event) -> {
			curStage.close();
			curStage = instructGUI.getStage();
			switchGameView(instructGUI.getScene(),instructGUI.getStage(),instructGUI.getTitle(),false);
		});
		// controller get traits
		Image reroll = new Image("assets/rerollBig.png");
		ImageView viewReroll = new ImageView(reroll);
		viewReroll.setPreserveRatio(true);
		viewReroll.setFitHeight(height/20);
		playerArea.getChildren().add(viewReroll);
		// reroll event
		viewReroll.setOnMouseClicked((event) -> {
			Player p1 = controller.getP1();
			controller.rerollShop(p1);
			update(null,p1);
		});

		Image player = new Image("assets/shopKeeper.jpg");
		ImageView pic = new ImageView();
		pic.setPreserveRatio(true);
		pic.setImage(player);
		pic.setFitHeight(height/20);
		Rectangle champ2Back = new Rectangle(height/20, height/20, Color.SADDLEBROWN);
		StackPane back2 = new StackPane(champ2Back, pic);
		playerArea.getChildren().add(back2);

		Image upgrade = new Image("assets/upgradeBig.png");
		ImageView viewUpgrade = new ImageView(upgrade);
		viewUpgrade.setPreserveRatio(true);
		viewUpgrade.setFitHeight(height/20);
		playerArea.getChildren().add(viewUpgrade);
		Label cost = new Label();
		cost.setText("Upgrade Cost: " + (controller.getP1().getLevel() + 4 - controller.getP1().getRoundsSince()) );
		cost.setTextFill(Color.YELLOW);
		playerArea.getChildren().add(cost);
		playerArea.getChildren().add(turnButton);
		playerArea.getChildren().add(breakGame);
		//playerArea.setAlignment(Pos.CENTER);
		// upgrade handler
		// TODO leveling up rerolls shop
		viewUpgrade.setOnMouseClicked((event) -> {
			Player p1 = controller.getP1();
			controller.levelup(p1);
			update(null, p1);

		});

		
		return playerArea;

	}

	@Override
	public void update(Observable o, Object arg) {
		updateGUI();
	}
	
	public void updateGUI() {
		// should add if arg is player 1
		Player p1 = controller.getP1();
		Card[] champSlots = p1.getBattleField();
		boolean movePlayerCards=true;
		if(attackPhase) {
			movePlayerCards=false;
		}
		remakeHbox(bottomChampions, champSlots,movePlayerCards);
		
		Card[] bench = p1.getBench();
		remakeHbox(bottomBench, bench,movePlayerCards);
		changeStats(1);
		FlowPane fPane = (FlowPane) bottomPlayer.getChildren().get(1);
		if (!attackPhase) {
			Card[] shopCards = controller.getShop(p1);
					
			remakeHbox(cardsForSale, shopCards, false);
						
		}else {
			
			Player p2 = controller.getP2();
			Card[] champSlots2 = p2.getBattleField();
			remakeHbox(topChampions, champSlots2,false);
			changeStats(2);

		}
				
		if(controller.isGameOver()) {
			System.out.println("Game Over!");
				if(p1.getLoser()) {
					System.out.println("Lost");
					curStage.close();
					switchGameView(loseGUI.getScene(),winGUI.getStage(),winGUI.getTitle(),true);
				}
				// player 1 lost
				else {
					System.out.println("Won");
					curStage.close();
					switchGameView(winGUI.getScene(),loseGUI.getStage(),loseGUI.getTitle(),true);
				}
			
			
			resetGame();
			//System.exit(0);
		}

	}
	public void resetGame() {
		model = new AutoBattlerModel();
		model.addObserver(this); //this is how you win the bitches
		controller = new AutoBattlerController(model);
		attackPhase = false;
		controller.resetChampionStats();
		System.out.println("attack phase is over here");
		startGame();
		createShop();
		gameBoard.setTop(shop);
		gameBoard.setMargin(shop, new Insets(10,10,10,10));
		step.setVisible(true);
		endAttack.setVisible(false);
	}
	

private void remakeHbox(CardFieldUI cardArea, Card[] champSlots, boolean isDraggable) {
		
		for (Node node : cardArea.box.getChildren()) {
			StackPane pane = (StackPane) node;
			if (pane.getChildren().size() > 1) {
				pane.getChildren().remove(1);
			}
		}
		for (int index = 0; index < champSlots.length; index++) {
			if (champSlots[index] == null) {
				continue;
			} else {
				
				StackPane card = createCard(champSlots[index],isDraggable);
				StackPane slot = (StackPane) cardArea.box.getChildren().get(index);
				slot.getChildren().add(card);
				// allows cards to moved when pressed
			}
		}
	}

	/**
	 * bottomStats or topStats : [2] = buff label, [3] = hp Label, [5] = money label
	 * champcard stack pane [2] = hp possibly update hp during fight?
	 * 
	 * @param player
	 */
	private void changeStats(int player) {
		if (player == 1) {
			Player p1 = controller.getP1();
			Label money = (Label) bottomStats.getChildren().get(5);
			money.setText("" + p1.getGold());

			Label hp = (Label) bottomStats.getChildren().get(3);
			hp.setText("" + controller.getP1().getHealth());
		}else if(player == 2) {
			
			Player p2 = controller.getP2();
			Label money = (Label) topStats.getChildren().get(5);
			money.setText("" + p2.getGold());
			Label hp = (Label) topStats.getChildren().get(3);
			hp.setText("" + controller.getP2().getHealth());
		}

	}
	public Scene getScene() {
		// TODO Auto-generated method stub
		return gameScene;
	}
	public Stage getStage() {
		return curStage;
	}
	
	private void switchGameView(Scene gameScene,Stage stage,String title, boolean isFullScreen) {
		if(isFullScreen) {
			stage.setX(bounds.getMinX());
			stage.setY(bounds.getMinY());
			stage.setWidth(bounds.getWidth());
			stage.setHeight(bounds.getHeight());
			stage.setMaxWidth(bounds.getWidth() * 2);
		}
		stage.setTitle(title);
		stage.setScene(gameScene);
		stage.show();
		
	}
	public String getTitle() {
		return title;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
		model.setDifficulty(difficulty);
	}

	/**
	 * This class was made for the purpose of helping swap cards so an object will
	 * hold two types one being an HBox and the other is an integer that is the
	 * index to a specific child.
	 *
	 */
	public class Pair {
		public HBox cards;
		public int[] indices;

		/**
		 * constructor method for object Pair
		 * 
		 * @param newCards, an Hbox containing cards
		 * @param num,      an index num to find a specific child node.
		 */
		public Pair(HBox newCards, int isField, int num) {
			cards = newCards;
			indices = new int[] { isField, num };
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		update(null,null);
		
	}

	

}
