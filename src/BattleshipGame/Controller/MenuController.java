package BattleshipGame.Controller;


import BattleshipGame.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button btnStart;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnExit;


    public void initialize() {

        btnStart.setOnMouseMoved(e ->{btnStart.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btnStart.setOnMouseExited(e->{btnStart.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btnStart.setOnMouseClicked(e->{ playClicked(); });

        btnOptions.setOnMouseMoved(e ->{ btnOptions.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);");});
        btnOptions.setOnMouseExited(e->{ btnOptions.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btnOptions.setOnMouseClicked(e->{ settingsClicked();});

        btnExit.setOnMouseMoved(e ->{ btnExit.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);"); });
        btnExit.setOnMouseExited(e->{btnExit.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        btnExit.setOnMouseClicked(e->{exitClicked();});
    }


    public void exitClicked()
    {

        Platform.exit();
        System.exit(0);
    }


    public void playClicked()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/setUpFX.fxml"));
            Parent root = fxmlLoader.load();
            SetupController controller = fxmlLoader.<SetupController>getController();
            Main.window.getScene().setRoot(root);
        }
        catch (IOException e) {
            System.out.println("ERROR PLAY");
            e.printStackTrace();
        }

    }


    public void settingsClicked()
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/settingsFX.fxml"));
            Parent root = fxmlLoader.load();
            SettingsController controller = fxmlLoader.<SettingsController>getController();
            Main.window.getScene().setRoot(root);
        }
        catch (IOException e)
        {
            System.out.println("ERROR SETTINGS");
            e.printStackTrace();

        }



    }


}
