package BattleshipGame.Engine;

import BattleshipGame.Entity.Sound;
import BattleshipGame.Main;
import javafx.scene.image.Image;

public class SettingsLogic {
    Image on;
    Image off;

    public SettingsLogic() {
        on = new Image("./images/on.png");
        off = new Image("./images/off.png");

    }

    public Image soundChange(boolean check, Sound sound) {
        if (check) {
            Main.gameMaster.setSound(false, sound);
            return off;
        }
        else {
            Main.gameMaster.setSound(true, sound);
            return on;
        }
    }

}
