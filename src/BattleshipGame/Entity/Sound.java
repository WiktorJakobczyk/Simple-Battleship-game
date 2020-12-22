package BattleshipGame.Entity;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {

    private MediaPlayer mediaPlayer;
    private String musicFile;

    public Sound(String url) {
        musicFile=url;
        mediaPlayer=new MediaPlayer(new Media(new File(musicFile).toURI().toString()));


    }

    public void start() {
        mediaPlayer.play();
    }
    public void reset()
    {
        mediaPlayer.seek(Duration.ZERO);
    }
    public void stop() {
        mediaPlayer.stop();
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }

    public String getMusicFile() {
        return musicFile;
    }
}
