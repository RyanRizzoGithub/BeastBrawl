package src;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InstructionPageUI {
	private BorderPane instructPage;
	private AutoBattlerGUIView gameGUI;
	private StartMenuGUI mainGUI;
	private BorderPane backPane;
	private Button startButton;
	private ImageView exitButtomView;
	private ImageView mainMenuView;
	private Scene scene;
	private Stage stage;
	private String title;
	private Rectangle2D bounds;
	private Scene mainMenuScene;

	public InstructionPageUI(Rectangle2D bounds) {
		instructPage = new BorderPane();
		
		Image background = new Image("assets/Background13.png");
		
		stage = new Stage();
		instructPage.setMinWidth(800);
		instructPage.setMinHeight(700);
		HBox instructMenuBar =new HBox();
		title ="Instruction Page";
		//creates exit button
		
		Image exitImage = new Image("assets/Exit.png");
		exitButtomView = new ImageView(exitImage);
		exitButtomView.setPreserveRatio(true);
		exitButtomView.setFitHeight(30);
		exitButtomView.setSmooth(false);
		// creates main menu button
		Image mainMenuImage  = new Image("assets/mainMenu.png");
		mainMenuView = new ImageView(mainMenuImage);
		mainMenuView.setPreserveRatio(true);
		mainMenuView.setFitHeight(30);
		// sets instruction page title
		Image titleImage = new Image("assets/helpText.png");
		ImageView titleView = new ImageView(titleImage);
		titleView.setPreserveRatio(true);
		titleView.setFitHeight(50);
		// adds children
		instructMenuBar.getChildren().add(mainMenuView);
		instructMenuBar.getChildren().add(titleView);
		instructMenuBar.getChildren().add(exitButtomView);
		instructMenuBar.setSpacing(30);
		// sets Instruction text
		Image instructPic = new Image("assets/instructionPage.png");
        ImageView instructView = new ImageView(instructPic);
        instructView.setPreserveRatio(true);
		// sets background image
		BackgroundSize screenSize = new BackgroundSize(bounds.getWidth(), bounds.getHeight(),true, true, true, true);
		instructPage.setBackground(new Background(new BackgroundImage(background, null, null, null, screenSize)));
		// sets placement of menu bar and text
		instructPage.setTop(instructMenuBar);
		instructPage.setCenter(instructView);
		instructMenuBar.setAlignment(Pos.CENTER);
		// adds actions to exit button to close page 
		exitButtomView.setOnMouseClicked(e -> {
			stage.close();	
		});
		// adds action to mainMenuButton to close mainMenu;
		mainMenuView.setOnMouseClicked(e -> {
			stage.close();
			switchGameView(mainGUI.getScene(),mainGUI.getStage(),mainGUI.getTitle(),mainGUI.getPage());
		
		});
		scene= new Scene(instructPage);
	}
	
	private void switchGameView(Scene gameScene,Stage stage,String title,BorderPane pane) {
		stage.setTitle(title);
		stage.setScene(gameScene);
		stage.show();

	}
	
	public Scene getScene() {
		return scene;
	}
	public Stage getStage() {
		return stage;
	}
	public String getTitle() {
		return title;
	}
	public void setBounds(Rectangle2D newBounds) {
		bounds=newBounds;
	}
	public void setMainGUI(StartMenuGUI mainGUI) {
		this.mainGUI = mainGUI;
	}
	public void setGameGUI(AutoBattlerGUIView gameGUI) {
		this.gameGUI=gameGUI;
	}
	public void setScene(Scene scene) {
		this.scene=scene;
	}
	public void setMainScene(Scene scene) {
		this.mainMenuScene=scene;
	}
	public BorderPane getGamePage() {
		return this.instructPage;
	}
	
	public void setStage(Stage stage) {
		this.stage=stage;
	}
	private void switchGameView(Scene gameScene,Stage stage,String title) {
		
		stage.setTitle(title);
		stage.setScene(gameScene);
		stage.show();
		
	}
	
}
