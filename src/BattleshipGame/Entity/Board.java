package BattleshipGame.Entity;

public class Board {
    private Cell[][] board;
    public int shipsAlive;

    public Cell getCell(int X, int Y)
    {
        if(X>=0 && X<10 && Y>=0 && Y<10 )
            return board[X][Y];
        else
            return  new Cell(false,false,false);

    }

    public Board(Board obj, int shipsAlive)
    {

        this.board=obj.board;
        this.shipsAlive=shipsAlive;

    }

    public Board() {
        this.board=new Cell[10][10];
        this.shipsAlive=0;

        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
                board[i][j]=new Cell(false,false,false);
    }
    public Board(int shipsAlive) {
        this.board=new Cell[10][10];
        this.shipsAlive=shipsAlive;

        for(int i=0; i<10; i++)
            for(int j=0; j<10; j++)
                board[i][j]=new Cell(false,false,false);
    }
}