package BattleshipGame.Engine;

import BattleshipGame.Entity.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;


public class GameMaster {
    public Player player;
    public Enemy enemy;

    private Board board;
    public Board boardShots;
    private Board enemyBoard;
    private Board enemyBoardShots;

    private SetupLogic setupLogic;
    private SettingsLogic settingsLogic;

    public static BackgroundMusic music;
    public static Sound bum;
    private boolean isMusicOn;
    private boolean isEffectOn;

    private  final File file=new File("./src/BattleshipGame/resources/enemyBoard.cfg"); //src/BattleshipGame/resources/enemyBoard.cfg
    public static boolean playerTurn;

    public  Rectangle[][] recList;
    public  Rectangle[][] recListShots;

    public GameMaster()
    {
        player=new Player();
        enemy=new Enemy();
        enemyBoard=new Board(enemy.createBoard(file),20);
        board=new Board(20);
        boardShots=new Board();
        setupLogic=new SetupLogic();
        settingsLogic=new SettingsLogic();

        music=new BackgroundMusic("./sounds/sound.mp3");
        music.start();
        isMusicOn=true;

        bum=new Sound("./sounds/bum.mp3");
        isEffectOn=true;

        recList = new Rectangle[10][10];
        recListShots = new Rectangle[10][10];

        playerTurn=true;

    }

    public Board getBoard() {
        return board;
    }
    public Board getBoardShots(){return boardShots;}
    public Board getEnemyBoard(){return enemyBoard;}
    public Board getEnemyBoardShots(){return enemyBoardShots;}
    public SetupLogic getSetupLogic() {
        return setupLogic;
    }
    public SettingsLogic getSettingsLogic() {
        return settingsLogic;
    }
    public static BackgroundMusic getMusic() {
        return music;
    }
    public static Sound getBum() { return bum; }

    public boolean isMusicOn() {
        return isMusicOn;
    }
    public boolean isEffectOn(){ return isEffectOn; }


    public void setSound(boolean musicOn, Sound obj) {
        if(musicOn)
            obj.start();
        else
            obj.stop();

        if(obj.equals(bum))
            isEffectOn=musicOn;
        else
            isMusicOn=musicOn;
    }


    public void analyzeBoard1() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {


                if (board.getCell(i, j).isShip()) {
                    recList[i][j].setFill(Color.RED);

                } else if (board.getCell(i, j).isShipDestroy())
                    recList[i][j].setFill(Color.GREY);
                else if(board.getCell(i,j).isShot())
                    recList[i][j].setFill(Color.ORANGE);
                    else
                    recList[i][j].setFill(Color.BLUE);
            }
    }

    public void analyzeBoard2() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (boardShots.getCell(i, j).isShipDestroy())
                    recListShots[i][j].setFill(Color.DARKGREEN);
                else if (boardShots.getCell(i, j).isShot())
                    recListShots[i][j].setFill(Color.YELLOW);
                else
                    recListShots[i][j].setFill(Color.BLUE);

            }
    }

    public void initialize()
    {

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {

                Rectangle rec = new Rectangle(50, 50);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(3);
                recList[i][j] = rec;

                Rectangle rec2 = new Rectangle(50, 50);
                recListShots[i][j] = rec2;

                rec2.setStroke(Color.BLACK);
                rec2.setStrokeWidth(3);




            }
        analyzeBoard1();
        analyzeBoard2();
        enemyBoardShots=new Board();

    }

public boolean checkHit(int X, int Y, Board board)
{
    if(board.getCell(X,Y).isShip())
    {
        board.getCell(X,Y).setShip(false);
        board.getCell(X,Y).setShipDestroy(true);
        board.shipsAlive--;
        return true;
    }
    else
        return false;

}

public void enemyTurn()
{

    enemy.makeMove();
    System.out.println("move made");
    analyzeBoard1();
    analyzeBoard2();

}

public void restart()
{

    enemy=new Enemy();
    enemyBoard=new Board(enemy.createBoard(file),20);
    board=new Board(20);
    boardShots=new Board();
    setupLogic=new SetupLogic();
    settingsLogic=new SettingsLogic();
}





}
