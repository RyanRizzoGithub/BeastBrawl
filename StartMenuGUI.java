package src;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartMenuGUI extends Application {
	private BorderPane menuPage;
	private AutoBattlerGUIView gameGUI;
	private BorderPane backPane;
	private Button startButton;
	private ImageView startButtonView;
	private Scene mainScene;
	private Stage stage;
	private String title;
	public StartMenuGUI() {
		menuPage = new BorderPane();
		Image background = new Image("Background.png");
		menuPage.setBackground(new Background(new BackgroundImage(background, null, null, null, null)));
		menuPage.setMinWidth(800);
		menuPage.setMinHeight(900);
		VBox menuContents =new VBox();
		Canvas startCanvas = new Canvas();
		startCanvas.getGraphicsContext2D().setImageSmoothing(false);
		title ="Main Menu";
		//creates start button
		Image startImage = new Image("startButton.png");
		startButtonView = new ImageView(startImage);
		startButtonView.setPreserveRatio(true);
		startButtonView.setFitHeight(50);
		startButtonView.setSmooth(false);
		// creates title
		Text gameTitle = new Text("Auto Battler");
		gameTitle.setFont(new Font("Arial", 50));
		gameTitle.setFill(Color.WHITE);
		menuContents.getChildren().add(gameTitle);
		menuContents.getChildren().add(startButtonView);
		menuContents.setSpacing(100);
		gameGUI= new AutoBattlerGUIView();
		gameGUI.setMainMenuGUI(this);
		
		menuPage.setCenter(menuContents);
		menuContents.setAlignment(Pos.CENTER);
		
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		this.stage =stage;
		stage.setTitle(title);
		mainScene = new Scene(menuPage);
		startButtonView.setOnMouseClicked(e -> switchGameView(gameGUI.getScene(),stage,gameGUI.getTitle()));
		gameGUI.setMainMenuScene(mainScene);
		stage.setScene(mainScene);
		stage.show();
		
	}
	private void switchGameView(Scene gameScene,Stage stage,String title) {
		// TODO Auto-generated method stub
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
}
