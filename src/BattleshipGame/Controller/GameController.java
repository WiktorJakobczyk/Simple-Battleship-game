package BattleshipGame.Controller;

import BattleshipGame.Engine.GameMaster;
import BattleshipGame.Entity.Board;
import BattleshipGame.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;


public class GameController {
    private boolean once;
    private boolean gameAlive;
    public enum GAMESTATE{PLAY,WIN, LOSE, STOP}
    public GAMESTATE currentState;

    @FXML
    private GridPane board1;
    @FXML
    private GridPane board2;
    @FXML
    private Text textTime;
    @FXML
    private Text nrYourShips;
    @FXML
    private Text nrEnemyShips;
    @FXML
    private ImageView smile;


    public void analyzeBoard1() {
        Main.gameMaster.analyzeBoard1();
        Main.gameMaster.analyzeBoard2();
        if(once) {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                    board2.add(Main.gameMaster.recList[i][j], i, j);
                    board1.add(Main.gameMaster.recListShots[i][j], i, j);
                }
            once=false;
            }

    }


    public void initialize(Board boardShips) {
        gameAlive=true;
        currentState=GAMESTATE.PLAY;
        smile.setImage(new Image("./images/green.png"));
        textTime.setText("BATTLE!");
        once=true;
        analyzeBoard1();

        board1.setOnMouseClicked(evt -> {

        if(Main.gameMaster.playerTurn &&  gameAlive) {
            Node target = evt.getPickResult().getIntersectedNode();
            int colIndex = GridPane.getColumnIndex(target);
            int rowIndex = GridPane.getRowIndex(target);


        if (Main.gameMaster.getBoardShots().getCell(colIndex, rowIndex).isShot() || Main.gameMaster.getBoardShots().getCell(colIndex, rowIndex).isShipDestroy())                  // Jesli ta pozycja była już 'strzelona' to zakoncz.
            return;

        Main.gameMaster.player.shot(colIndex, rowIndex);
        analyzeBoard1();
        if(Main.gameMaster.isEffectOn()) {
               GameMaster.bum.start();
               GameMaster.bum.reset();
        }


}
if(gameAlive)
    Main.gameMaster.enemyTurn();

        });

        ThreadGame threadG=new ThreadGame();
        threadG.start();


    }

    @FXML
    private Button surrenderBtn;
    @FXML
    private HBox tr;

    Button yes=new Button("TAK");
    Button no=new Button("NIE");

    private void addYesNo()
    {
        yes.setStyle("-fx-background-color: linear-gradient(to bottom, #999999, #d9d9d9);\n" +
                "  -fx-border-insets: 5;\n" +
                "  -fx-background-radius: 3;\n" +
                "  -fx-border-radius: 3;\n" +
                "  -fx-border-color: #404040;\n" +
                "  -fx-border-style: solid;\n" +
                "  -fx-border-width: 5;\n" +
                "  -fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0);\n" +
                "  -fx-background-insets: 5;");
        no.setStyle("-fx-background-color: linear-gradient(to bottom, #999999, #d9d9d9);\n" +
                "  -fx-border-insets: 5;\n" +
                "  -fx-background-radius: 3;\n" +
                "  -fx-border-radius: 3;\n" +
                "  -fx-border-color: #404040;\n" +
                "  -fx-border-style: solid;\n" +
                "  -fx-border-width: 5;\n" +
                "  -fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0);\n" +
                "  -fx-background-insets: 5;");
        tr.getChildren().add(yes);
        tr.getChildren().add(no);
    }
    @FXML
    public void surrender()
    {


            surrenderBtn.setText("Na pewno?");
           addYesNo();
            yes.setOnMouseClicked(evt -> {
                currentState=GAMESTATE.LOSE;

            });

            no.setOnMouseClicked(evt -> {
                    tr.getChildren().remove(yes);
                    tr.getChildren().remove(no);
                    surrenderBtn.setText("PODDAJ SIE");
            });


        }

    public void restart()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/setUpFX.fxml"));
            Parent root = fxmlLoader.load();
            SetupController controller = fxmlLoader.<SetupController>getController();

            Main.gameMaster.restart();
            controller.initialize();
            Main.window.getScene().setRoot(root);
        }
        catch (IOException e) {
            System.out.println("ERROR PLAY");
            e.printStackTrace();
        }

    }
    private void makeRestart()
    {
        surrenderBtn.setText("RESTART?");
        yes.setOnMouseClicked(evt -> {
            restart();

        });
    }

    private void DateThread()
    {

        while (gameAlive)
        {



            Platform.runLater(() -> //switches to GUI Thread

            {
                if(currentState==GAMESTATE.PLAY){
                nrYourShips.setText(Integer.toString(Main.gameMaster.getBoard().shipsAlive));
                nrEnemyShips.setText(Integer.toString(Main.gameMaster.getEnemyBoard().shipsAlive));

                    if(Main.gameMaster.getBoard().shipsAlive<=0)
                        currentState= GAMESTATE.LOSE;
                    else if(Main.gameMaster.getEnemyBoard().shipsAlive<=0)
                        currentState=GAMESTATE.WIN;
                }
                else if(currentState==GAMESTATE.WIN){
                    textTime.setText("YOU WIN");
                    addYesNo();
                gameAlive=false;
                    makeRestart();}
                else if(currentState==GAMESTATE.LOSE){
                    addYesNo();
                    textTime.setText("YOU LOSE");
                gameAlive=false;
                    makeRestart();

                }

            });

            try
            {
                Thread.sleep(20);
            } catch (InterruptedException iex)
            {
                System.out.println("RUNLATER ERROR");
            }

        }

    }

    class ThreadGame extends Thread {

        public void run(){
            System.out.println("THREAD GAME!");
            try {
                DateThread();

            }
            catch(Exception e)
            {
                System.out.println("THREADGAME ERROR");
            }
        }



    }
}
