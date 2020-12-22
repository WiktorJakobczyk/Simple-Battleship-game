package BattleshipGame;

import BattleshipGame.Engine.GameMaster;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static GameMaster gameMaster;

    static public Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception {



        Parent root = FXMLLoader.load(getClass().getResource("/menuFX.fxml"));
        window=primaryStage;
        window.setTitle("Battleship");
        window.setScene(new Scene(root, 600, 500));
        //window.setFullScreen(true);
        window.show();
        window.setMinWidth(primaryStage.getWidth());
        window.setMinHeight(primaryStage.getHeight());

    }


        public static void main(String[] args) {
            gameMaster=new GameMaster();

        launch(args);

    }

   // public static GameMaster getGameMaster() {
       // return gameMaster;
   // }

}
