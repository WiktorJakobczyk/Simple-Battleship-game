package BattleshipGame.Entity;

import javafx.scene.media.MediaPlayer;

public class BackgroundMusic extends  Sound{

    public BackgroundMusic(String url)
    {
        super(url);
        getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
    }
}
