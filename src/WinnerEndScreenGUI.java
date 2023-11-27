package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WinnerEndScreenGUI extends Application {
		private BorderPane endPage;
		private Scene mainScene;
		private ImageView trophyView;
		private ImageView winnerView;
		private ImageView menuButtonView;
		private ImageView playAgainButtonView;
		private ImageView closeGameButtonView;
		private Button menuButton;
		private Button playAgainButton;
		private Button closeGameButton;
		private Stage stage;
		private String title;
		private Rectangle2D bounds;
		private Scene mainMenuScene;
		private Scene instructScene;
		private StartMenuGUI mainMenuGUI;
		private InstructionPageUI instructGUI;
		private Scene scene;
		private AutoBattlerGUIView gameGUI;
		
		public WinnerEndScreenGUI(Rectangle2D bounds) {
			this.bounds=bounds;
			endPage = new BorderPane();
			BackgroundSize screenSize = new BackgroundSize(bounds.getWidth(), bounds.getHeight(),true, true, true, false);
			Image background = new Image("assets/winBackgroundSmall.png");
		
			BackgroundPosition center = BackgroundPosition.CENTER;
			
			endPage.setBackground(new Background(new BackgroundImage(background, null, null, null, screenSize)));
			VBox menuContents =new VBox();
			Canvas startCanvas = new Canvas();
			stage = new Stage();
			title="winner";
			Image winTextImage = new Image("assets/youWin.png");
			winnerView = new ImageView(winTextImage);
			winnerView.setPreserveRatio(true);
			winnerView.setFitHeight(50);
			winnerView.setSmooth(false);
			menuContents.getChildren().add(winnerView);
			
			Image tropyImage = new Image("assets/trophy.gif");
			trophyView = new ImageView(tropyImage);
			trophyView.setPreserveRatio(true);
			trophyView.setFitHeight(200);
			trophyView.setSmooth(false);
			menuContents.getChildren().add(trophyView);
			
			menuButton = new Button();
			Image menuButtonImage = new Image("assets/menuButton.png");
			menuButtonView = new ImageView(menuButtonImage);
			menuButtonView.setPreserveRatio(true);
			menuButtonView.setFitHeight(50);
			menuButtonView.setSmooth(false);
			menuButton.setGraphic(menuButtonView);
			menuButton.setOnAction(menuButtonHandler);
			menuContents.getChildren().add(menuButton);
			
			playAgainButton = new Button();
			Image playAgainImage = new Image("assets/playAgain.png");
			playAgainButtonView = new ImageView(playAgainImage);
			playAgainButtonView.setPreserveRatio(true);
			playAgainButtonView.setFitHeight(50);
			playAgainButtonView.setSmooth(false);
			playAgainButton.setGraphic(playAgainButtonView);
			playAgainButton.setOnAction(playAgainButtonHandler);
			menuContents.getChildren().add(playAgainButton);
			
			closeGameButton = new Button();
			Image closeGameImage = new Image("assets/closeGame.png");
			closeGameButtonView = new ImageView(closeGameImage);
			closeGameButtonView.setPreserveRatio(true);
			closeGameButtonView.setFitHeight(50);
			closeGameButtonView.setSmooth(false);
			closeGameButton.setGraphic(closeGameButtonView);
			closeGameButton.setOnAction(closeGameButtonHandler);
			menuContents.getChildren().add(closeGameButton);
			
			title ="Main Menu";
			endPage.setCenter(menuContents);
			menuContents.setAlignment(Pos.CENTER);
			menuContents.setSpacing(10);
			
			scene = new Scene(endPage);
		}
		
		@Override
		public void start(Stage stage) throws Exception {
			
		} 
		
		EventHandler<ActionEvent> menuButtonHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	
		    	try {
		    		gameGUI.updateGUI();
		    		stage.close();
		    		//gameGUI.updateGUI();
		    		switchGameView(mainMenuGUI.getScene(),mainMenuGUI.getStage(),mainMenuGUI.getTitle());
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		};
		
		EventHandler<ActionEvent> playAgainButtonHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
	
		    	try {
		    		gameGUI.updateGUI();
		    		stage.close();
		    		mainMenuGUI.getStage().close();
		    		//gameGUI.updateGUI();
		    		switchGameView(gameGUI.getScene(),gameGUI.getStage(),gameGUI.getTitle());
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		};
		
		EventHandler<ActionEvent> closeGameButtonHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	System.exit(0);
		    }
		};
		
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
		public Scene getScene() {
			// TODO Auto-generated method stub
			return scene;
		}
		public Stage getStage() {
			return stage;
		}
		public String getTitle() {
			return title;
		}
		public void setGameGUI(AutoBattlerGUIView gameGUI) {
			this.gameGUI=gameGUI;
		}
		private void switchGameView(Scene gameScene,Stage stage,String title) {
			   stage.setX(bounds.getMinX());
			    stage.setY(bounds.getMinY());
			    stage.setWidth(bounds.getWidth());
			    stage.setHeight(bounds.getHeight());
			    stage.setMaxWidth(bounds.getWidth() * 2);
			stage.setTitle(title);
			stage.setScene(gameScene);
			stage.show();
			
		}
}
