package BattleshipGame.Controller;

import BattleshipGame.Engine.GameMaster;
import BattleshipGame.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;

public class SettingsController {

    @FXML
    private ImageView soundImg;
    @FXML
    private ImageView effectsImg;
    @FXML
    private Button backBtn;

    public void initialize()
    {
        soundImg.setOnMouseClicked(e->{ soundChange();});
        effectsImg.setOnMouseClicked(e->{effectsChange();});
        backBtn.setOnMouseMoved(e ->{ backBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(1, 30, 80, 1), 24, 0.4, 0, 0);"); });
        backBtn.setOnMouseExited(e->{backBtn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.4, 0, 0);");});
        backBtn.setOnMouseClicked(e->{backClicked();});


    }


    public void backClicked()
    {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menuFX.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            Main.window.getScene().setRoot(root);
        }
        catch (IOException e)
        {
            System.out.println("SETTINGS BACK ERROR");

        }

    }


    public void soundChange()
    {
        soundImg.setImage(Main.gameMaster.getSettingsLogic().soundChange(Main.gameMaster.isMusicOn(), GameMaster.getMusic()));



    }


    public void effectsChange()
    {
        effectsImg.setImage(Main.gameMaster.getSettingsLogic().soundChange(Main.gameMaster.isEffectOn(), GameMaster.getBum()));


    }
}
