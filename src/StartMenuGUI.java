package src;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
//mport src.AutoBattlerGUIView.SceneSizeChangeListener;
import javafx.stage.StageStyle;

public class StartMenuGUI extends Application {
	private BorderPane menuPage;
	private AutoBattlerGUIView gameGUI;
	private InstructionPageUI instructGUI;
	private BorderPane backPane;
	private Button startButton;
	private Button instructButton;
	private Button closeButton;
	private RadioButton easyButton;
	private RadioButton normalButton;
	private RadioButton hardButton;
	private ImageView startButtonView;
	private ImageView instructPageView;
	private ImageView closeButtonView;
	private WinnerEndScreenGUI winGUI;
	private LoserEndScreenGUI loseGUI;
	private Scene mainScene;
	private Stage stage;
	private String title;
	private int buttonScale;
	private int difficulty;
	
	public StartMenuGUI() {
		menuPage = new BorderPane();
		Image background = new Image("assets/Background.png");
		buttonScale= 35;
		difficulty = 0;
		
		menuPage.setMinWidth(800);
		menuPage.setMinHeight(700);
		VBox menuContents =new VBox();
		Canvas startCanvas = new Canvas();
		startCanvas.getGraphicsContext2D().setImageSmoothing(false);
		title ="Main Menu";
		
		Image easyImage = new Image("assets/easy.png");
		easyButton = new RadioButton(); 
		easyButton.setGraphic(new ImageView(easyImage));
		easyButton.setSelected(true);
		
		Image normalImage = new Image("assets/normal.png");
	    normalButton = new RadioButton(); 
	    normalButton.setGraphic(new ImageView(normalImage));
	    
	    Image hardImage = new Image("assets/hard.png");
	    hardButton = new RadioButton();
	    hardButton.setGraphic(new ImageView(hardImage));
	    
	    ToggleGroup tg = new ToggleGroup(); 
		easyButton.setToggleGroup(tg);
		normalButton.setToggleGroup(tg);
		hardButton.setToggleGroup(tg);
		
		startButton = new Button();
		Image startImage = new Image("assets/startGame.png");
		startButtonView = new ImageView(startImage);
		startButtonView.setPreserveRatio(true);
		startButtonView.setLayoutX(buttonScale);
		startButtonView.setLayoutY(buttonScale);
		startButtonView.setFitHeight(buttonScale);
		startButtonView.setSmooth(false);
		startButton.setGraphic(startButtonView);
		
		instructButton = new Button();
		Image instructImage = new Image("assets/helpText.png");
		instructPageView = new ImageView(instructImage);
		instructPageView.setLayoutX(buttonScale);
		instructPageView.setLayoutY(buttonScale);
		instructPageView.setPreserveRatio(true);
		instructPageView.setFitHeight(buttonScale);
		instructButton.setGraphic(instructPageView);
		
		closeButton = new Button();
		Image closeImage = new Image("assets/closeGameMenu.png");
		closeButtonView = new ImageView(closeImage);
		closeButtonView.setLayoutX(buttonScale);
		closeButtonView.setLayoutY(buttonScale);
		closeButtonView.setPreserveRatio(true);
		closeButtonView.setFitHeight(buttonScale);
		closeButton.setGraphic(closeButtonView);
		
		// creates title
		Image titleImage = new Image("assets/beastBrawler.png");
		ImageView titleView = new ImageView(titleImage);
		titleView.setPreserveRatio(true);
		titleView.setFitHeight(200);
		Text gameTitle = new Text("Auto Battler");
		gameTitle.setFont(new Font("Arial", 50));
		gameTitle.setFill(Color.WHITE);
		
		menuContents.getChildren().add(titleView);
		menuContents.getChildren().add(easyButton);
		menuContents.getChildren().add(normalButton);
		menuContents.getChildren().add(hardButton);
		menuContents.getChildren().add(startButton);
		menuContents.getChildren().add(closeButton);
		menuContents.getChildren().add(instructButton);
		
		menuContents.setSpacing(30);
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		gameGUI= new AutoBattlerGUIView(bounds);
		gameGUI.setBounds(bounds);
		gameGUI.setMainMenuGUI(this);
		instructGUI = new InstructionPageUI(bounds);
		instructGUI.setBounds(bounds);
		instructGUI.setMainGUI(this);
		instructGUI.setGameGUI(gameGUI);
		gameGUI.setInscructionPageGUI(instructGUI);
		
		loseGUI = new LoserEndScreenGUI(bounds);
		loseGUI.setBounds(bounds);
		loseGUI.setMainMenuGUI(this);
		loseGUI.setGameGUI(gameGUI);
		loseGUI.setInscructionPageGUI(instructGUI);
		gameGUI.setLoseGUI(loseGUI);
		winGUI = new WinnerEndScreenGUI(bounds);
		winGUI.setBounds(bounds);
		winGUI.setMainMenuGUI(this);
		winGUI.setGameGUI(gameGUI);
		gameGUI.setWinGUI(winGUI);
		//loseGUI.setBounds(bounds);
		//loseGUI.setMainMenuGUI(this);
		//loseGUI.setInscructionPageGUI(instructGUI);
		
		BackgroundSize screenSize = new BackgroundSize(bounds.getWidth(), bounds.getHeight(),true, true, true, true);
		menuPage.setBackground(new Background(new BackgroundImage(background, null, null, null, screenSize)));
		menuPage.setCenter(menuContents);
		menuContents.setAlignment(Pos.CENTER);
		
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		this.stage =stage;
		stage.setTitle(title);
		mainScene = new Scene(menuPage);
		gameGUI.setMainMenuScene(mainScene);
		instructGUI.setMainScene(mainScene);
		loseGUI.setMainMenuScene(mainScene);
		// adds action to start game button
		startButton.setOnMouseClicked(e -> {
			this.stage.close();
			switchGameView(gameGUI.getScene(),stage,gameGUI.getTitle(),gameGUI.getGameBoard());
		});
		// adds action to instruction page button
		instructButton.setOnMouseClicked(e ->{
			this.stage.close();
			switchGameView(instructGUI.getScene(),stage,instructGUI.getTitle(),instructGUI.getGamePage());
		});
		
		closeButton.setOnMouseClicked(e ->{
			this.stage.close();
		});
		
		easyButton.setOnMouseClicked(e ->{
			System.out.println("Easy Difficulty Selected");
			this.difficulty = 0;
			gameGUI.setDifficulty(difficulty);
		});
		
		normalButton.setOnMouseClicked(e ->{
			System.out.println("Normal Difficulty Selected");
			this.difficulty = 1;
			gameGUI.setDifficulty(difficulty);
		});
		
		hardButton.setOnMouseClicked(e ->{
			System.out.println("Hard Difficulty Selected");
			this.difficulty = 2;
			gameGUI.setDifficulty(difficulty);
		});
		
		stage.setScene(mainScene);
		// sets the size of the scene to fit the current computers screen
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
	    stage.setX(bounds.getMinX());
	    stage.setY(bounds.getMinY());
	    stage.setWidth(bounds.getWidth());
	    stage.setHeight(bounds.getHeight());
	    stage.setMaxWidth(bounds.getWidth() * 2);
	    stage.show();

	}
	
	public void switchGameView(Scene gameScene,Stage stage,String title,BorderPane pane) {
		stage.setTitle(title);
		stage.setScene(gameScene);
		stage.show();

	}
	public Scene getScene() {
		return mainScene;
	}
	public Stage getStage() {
		return stage;
	}
	public String getTitle() {
		return title;
	}
	public BorderPane getPage() {
		return menuPage;
	}
}