package BattleshipGame.Entity;

public class Cell {
    private boolean shot;
    private boolean ship;
    private boolean shipDestroy;



    public Cell(boolean shot, boolean ship, boolean destroy) {
        this.shot = shot;
        this.ship=ship;
        this.shipDestroy=destroy;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public void setShip(boolean ship)
    {
        this.ship=ship;

    }

    public boolean isShip()
    {
        return ship;

    }
    public boolean isShot() {
        return shot;
    }

    public void setShipDestroy(boolean shipDestroy)
    {
        this.shipDestroy=shipDestroy;
    }

    public boolean isShipDestroy(){return shipDestroy;}


}
