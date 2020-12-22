package BattleshipGame.Controller;

import BattleshipGame.Entity.Ship;
import BattleshipGame.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;

public class SetupController {
    @FXML
    private GridPane boardShips;
    @FXML
    private Text ship4Count;
    @FXML
    private Text ship3Count;
    @FXML
    private Text ship2Count;
    @FXML
    private Text ship1Count;
    @FXML
    private Text warning;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;

    @FXML
    private Button btnReady;



    public void analyzeBoard1()
    {

        boardShips.getChildren().clear();
        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++) {
                if(Main.gameMaster.getBoard().getCell(i,j).isShip())
                    Main.gameMaster.getSetupLogic().getRecList()[i][j].setFill(Color.RED);
                else
                    Main.gameMaster.getSetupLogic().getRecList()[i][j].setFill(Color.BLUE);


                boardShips.add(Main.gameMaster.getSetupLogic().getRecList()[i][j], i, j);
            }
    }



    public void initialize() {
        btn1.setOnMouseMoved(e ->{    btn1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btn1.setOnMouseExited(e->{ btn1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btn1.setOnMouseClicked(e->{ Main.gameMaster.getSetupLogic().select1();});

        btn2.setOnMouseMoved(e ->{    btn2.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btn2.setOnMouseExited(e->{ btn2.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btn2.setOnMouseClicked(e->{ Main.gameMaster.getSetupLogic().select2();});

        btn3.setOnMouseMoved(e ->{    btn3.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btn3.setOnMouseExited(e->{ btn3.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btn3.setOnMouseClicked(e->{ Main.gameMaster.getSetupLogic().select3();});

        btn4.setOnMouseMoved(e ->{    btn4.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btn4.setOnMouseExited(e->{ btn4.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btn4.setOnMouseClicked(e->{ Main.gameMaster.getSetupLogic().select4();});

        btnReady.setOnMouseMoved(e ->{    btnReady.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btnReady.setOnMouseExited(e->{ btnReady.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});



        analyzeBoard1();
        boardShips.setOnMouseClicked(evt -> {

            if (evt.getButton() == MouseButton.PRIMARY) {
                Node target = evt.getPickResult().getIntersectedNode();
                int colIndex = GridPane.getColumnIndex(target);
                int rowIndex = GridPane.getRowIndex(target);
                Main.gameMaster.getSetupLogic().leftClick(colIndex,rowIndex);
                analyzeBoard1();
                setShipsCount();

            }
            else if (evt.getButton() == MouseButton.SECONDARY)
            {
                analyzeBoard1();
                Node target = evt.getPickResult().getIntersectedNode();
                int colIndex = GridPane.getColumnIndex(target);
                int rowIndex = GridPane.getRowIndex(target);

                if(Main.gameMaster.getSetupLogic().getShip().getOrientation_()== Ship.Orientation_.VERTICAL)
                    Main.gameMaster.getSetupLogic().getShip().setOrientation_(Ship.Orientation_.HORIZONTAL);
                else
                    Main.gameMaster.getSetupLogic().getShip().setOrientation_(Ship.Orientation_.VERTICAL);


               Main.gameMaster.getSetupLogic().orientation(rowIndex,colIndex);
            }
        });

        boardShips.setOnMouseMoved(evt->{
            if(Main.gameMaster.getSetupLogic().getShip()!=null) {
                try {
                    analyzeBoard1();
                    warning.setText("Umieść statki na planszy!");
                    Node target = evt.getPickResult().getIntersectedNode();
                    int colIndex = GridPane.getColumnIndex(target);
                    int rowIndex = GridPane.getRowIndex(target);
                    System.out.println(colIndex + "\n");

                    Main.gameMaster.getSetupLogic().orientation(rowIndex, colIndex);
                }
                catch(Exception e)
                {
                    System.out.println("ERROR");
                }

            }


        });


    }


    public void setShipsCount()
    {

        ship4Count.setText(Integer.toString(1-Main.gameMaster.getSetupLogic().getShip4()));
        ship3Count.setText(Integer.toString(2-Main.gameMaster.getSetupLogic().getShip3()));
        ship2Count.setText(Integer.toString(3-Main.gameMaster.getSetupLogic().getShip2()));
        ship1Count.setText(Integer.toString(4-Main.gameMaster.getSetupLogic().getShip1()));
    }
    public void readyClicked()
    {

        if(     Main.gameMaster.getSetupLogic().getShip4()!=1 || Main.gameMaster.getSetupLogic().getShip3()!=2 ||
                Main.gameMaster.getSetupLogic().getShip2()!=3 || Main.gameMaster.getSetupLogic().getShip1()!=4) {

            warning.setText("WSZYSTKIE STAKI MUSZĄ BYĆ UMIESZCZONE NA PLANSZY!");
            return;
        }


  try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gameFX.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      GameController controller = fxmlLoader.<GameController>getController();
      Main.gameMaster.initialize();

      controller.initialize(Main.gameMaster.getBoard());

      Main.window.getScene().setRoot(root);
  }

   catch (IOException e) {
      e.printStackTrace();
  }
    }


}
