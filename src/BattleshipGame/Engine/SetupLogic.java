package BattleshipGame.Engine;

import BattleshipGame.Entity.Board;
import BattleshipGame.Entity.Ship;
import BattleshipGame.Main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SetupLogic {
    public Rectangle[][] recList;
    private  boolean canPlace;

    public Ship ship;
    int length=0;

    private int ship4;
    private int ship3;
    private int ship2;
    private int ship1;

    public SetupLogic() {
        recList = new Rectangle[10][10];

        ship4 = 0;
        ship3 = 0;
        ship2 = 0;
        ship1 = 0;

        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++) {

                Rectangle rec = new Rectangle(50, 50);
                rec.setStroke(Color.BLACK);
                rec.setStrokeWidth(3);
                recList[i][j]=rec;

                // board1.add(recList[i][j], i, j);

            }
    }

    public Rectangle[][] getRecList() {
        return recList;
    }

    public int getShip4() {
        return ship4;
    }

    public int getShip3() {
        return ship3;
    }

    public int getShip2() {
        return ship2;
    }

    public int getShip1() {
        return ship1;
    }

    public Ship getShip() {
        return ship;
    }

    public void leftClick(int X, int Y)
    {

        if(canPlace) {
            for (int i = 0; i < length; i++)
                if (ship.getOrientation_() == Ship.Orientation_.VERTICAL)
                    Main.gameMaster.getBoard().getCell(X, Y + i).setShip(true);
                else
                    Main.gameMaster.getBoard().getCell(X + i, Y).setShip(true);

            ship = null;

            switch (length) {
                case 4:
                    ship4++;
                    //ship4Count.setText(Integer.toString(1-ship4));
                    break;
                case 3:
                    ship3++;
                    //ship3Count.setText(Integer.toString(2-ship3));
                    break;
                case 2:
                    ship2++;
                    //ship2Count.setText(Integer.toString(3-ship2));
                    break;
                case 1:
                    ship1++;
                    // ship1Count.setText(Integer.toString(4-ship1));
                    break;
            }
        }
    }

    public void orientation(int rowIndex, int colIndex)
    {
        canPlace=true;
        if ((rowIndex <= 10 - length && ship.getOrientation_()== Ship.Orientation_.VERTICAL) ||
                (ship.getOrientation_()== Ship.Orientation_.HORIZONTAL && colIndex<=10-length)) {
            for (int i = 0; i < length; i++) {
                if (ship.getOrientation_() == Ship.Orientation_.VERTICAL) {
                    recList[colIndex][rowIndex + i].setFill(Color.ORANGE);
                    canPlace=true;
                }
                else {
                    recList[colIndex + i][rowIndex].setFill(Color.ORANGE);
                    canPlace=true;
                }
            }
            canPlace=true;
        }
        else {
            for (int i = 0; i < length; i++)
                if (ship.getOrientation_() == Ship.Orientation_.VERTICAL) {
                    recList[colIndex][rowIndex + i].setFill(Color.PURPLE);
                    canPlace=false;
                }
                else {
                    recList[colIndex + i][rowIndex].setFill(Color.PURPLE);
                    canPlace=false;
                }

        }
        checkNearby(colIndex,rowIndex);



    }
    private void checkNearby(int colIndex, int rowIndex)
    {
        System.out.println("Shpi: "+length);

        for (int i = 0; i < length; i++) {
            if (ship.getOrientation_() == Ship.Orientation_.VERTICAL) {
                if (
                        Main.gameMaster.getBoard().getCell(colIndex, rowIndex + i).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex + 1, rowIndex + i).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex - 1, rowIndex + i).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex, rowIndex + i + 1).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex, rowIndex + i - 1).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex - 1, rowIndex + i - 1).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex + 1, rowIndex + i + 1).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex + 1, rowIndex + i - 1).isShip()
                                ||  Main.gameMaster.getBoard().getCell(colIndex - 1, rowIndex + i + 1).isShip()
                ) {
                    for (int j = 0; j < length; j++) {
                        recList[colIndex][rowIndex + j].setFill(Color.PURPLE);


                    }
                    //flaga=true;
                    canPlace = false;
                }
            } else if (
                    Main.gameMaster.getBoard().getCell(colIndex + i, rowIndex).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i + 1, rowIndex).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i - 1, rowIndex).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i, rowIndex + 1).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i, rowIndex - 1).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i - 1, rowIndex - 1).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i + 1, rowIndex + 1).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i - 1, rowIndex + 1).isShip()
                            ||  Main.gameMaster.getBoard().getCell(colIndex + i + 1, rowIndex - 1).isShip()
            ) {
                for (int j = 0; j < length; j++)
                    recList[colIndex + j][rowIndex].setFill(Color.PURPLE);
                //flaga=true;
                canPlace = false;
            }
        }


    }

    public void select4() {
        if(ship4<1) {
            ship = new Ship(4, true, 1);
            length = ship.getLength_();
        }
    }
    public void select3() {
        if(ship3<2) {
            ship = new Ship(3, true, 1);
            length = ship.getLength_();
        }
    }
    public void select2() {
        if(ship2<3) {
            ship = new Ship(2, true, 1);
            length = ship.getLength_();
        }
    }
    public void select1() {
        if(ship1<4) {
            ship = new Ship(1, true, 1);
            length = ship.getLength_();
        }
    }
    public void reset()
    {
        ship4 = 0;
        ship3 = 0;
        ship2 = 0;
        ship1 = 0;
        ship=null;
    }

}
