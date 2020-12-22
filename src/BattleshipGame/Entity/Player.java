package BattleshipGame.Entity;

import BattleshipGame.Engine.GameMaster;
import BattleshipGame.Main;

public class Player implements Agent {

    @Override
    public void shot(int X,int Y)
    {
        if(Main.gameMaster.checkHit(X,Y,Main.gameMaster.getEnemyBoard()))
            Main.gameMaster.boardShots.getCell(X,Y).setShipDestroy(true);
        else
            Main.gameMaster.boardShots.getCell(X,Y).setShot(true);

        endTurn();

    }
    @Override
    public void endTurn()
    {
        GameMaster.playerTurn = false;
    }

}
