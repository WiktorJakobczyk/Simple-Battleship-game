package BattleshipGame.Entity;

public class Ship {
    public enum Orientation_{VERTICAL, HORIZONTAL};

    private int length_;
    boolean isAlive;
    private int ID_;
    private Ship.Orientation_ orientation_;

    public Ship(int length_, boolean isAlive, int ID_) {
        this.length_ = length_;
        this.isAlive = isAlive;
        this.ID_=ID_;
        this.orientation_= Ship.Orientation_.VERTICAL;
    }
    public Ship(int length_, int ID_) {
        this.length_ = length_;
        this.isAlive = true;
        this.ID_=ID_;
        this.orientation_= Ship.Orientation_.VERTICAL;
    }

    public Ship(Ship obj)
    {
        this.length_ = obj.length_;
        this.isAlive = obj.isAlive;
        this.ID_=obj.ID_;
        this.orientation_=obj.orientation_;

    }

    public void setOrientation_(Ship.Orientation_ orientation_) {
        this.orientation_ = orientation_;
    }

    public Ship.Orientation_ getOrientation_()
    {

        return orientation_;
    }
    public void destroy()
    {
        this.isAlive=false;

    }
    public int getID()
    {
        return ID_;

    }

    public int getLength_()
    {
        return length_;
    }



}