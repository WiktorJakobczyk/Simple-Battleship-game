package BattleshipGame.Entity;

import BattleshipGame.Engine.GameMaster;
import BattleshipGame.Main;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Stack;

public class Enemy implements  Agent {

    private Stack<HitPair> hitStack;
    private ArrayList<Board>boardsList;
    private boolean shotAroundLast;
    private int numberOfShotsAround;
    private int randomX;
    private int randomY;

    private Stack<Integer> checkList;
    private ArrayList<Integer> options;
    SecureRandom secureRandom=new SecureRandom();

    public Enemy() {
        super();
        hitStack=new Stack<>();
        checkList=new Stack<>();
        options=new ArrayList<>();
        boardsList=new ArrayList<>();
        resetOptions();
        resetCheckList();



    }

    public Board createBoard(File file) {
        int numberOfOptions=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            numberOfOptions=Integer.parseInt(line);
            line=reader.readLine();

            while (line != null) {
                Board boardTemp = new Board();

                for (int i = 0; i < 10; i++) {
                        String he = reader.readLine();
                        String[] str =he.split(" ");


                    for (int z=0; z<10; z++)
                        if (str[z].equals("1"))
                                boardTemp.getCell(z, i).setShip(true);

                }
                line = reader.readLine();
                boardsList.add(boardTemp);

            }

        }
         catch (IOException e) {
            e.printStackTrace();
        }
        int rand=secureRandom.nextInt(numberOfOptions);

        return boardsList.get(rand);
    }
        private void resetOptions()
    {
        for(int i=0; i<options.size(); i++)
             options.remove(i);

        for(int i=0; i<4; i++)
            options.add(i);
       // options.add(0);
       // options.add(1);
        //options.add(2);
        //options.add(3);

    }

    private void resetCheckList()
    {
       while(!checkList.isEmpty())
           checkList.pop();
        int z=0;
        System.out.println("RESET CHECKLIST "+checkList.size());
        for(int i=4; i>0; i--) {
            int x=secureRandom.nextInt(i);
            checkList.add(options.get(x));
            System.out.println("checklist "+checkList.get(z++));
            options.remove(x);
        }
    }

    @Override
    public void shot(int X,int Y) {
        if(Main.gameMaster.getEnemyBoardShots().getCell(X,Y).isShot()) {
            makeMove();
            return;
        }

        if(Main.gameMaster.checkHit(X,Y,Main.gameMaster.getBoard())) {
            Main.gameMaster.getBoard().getCell(X, Y).setShipDestroy(true);
            HitPair lastHit=new HitPair(X,Y);
            hitStack.push(lastHit);
            numberOfShotsAround=0;
            resetOptions();
            resetCheckList();

            shotAroundLast=true;
            System.out.println("ustawiono");
        }
        else
            Main.gameMaster.getBoard().getCell(X,Y).setShot(true);

        Main.gameMaster.getEnemyBoardShots().getCell(X,Y).setShot(true);
        endTurn();

    }

    @Override
    public void endTurn() {
        GameMaster.playerTurn = true;
    }

    public void getPosToShot() {
            randomX = secureRandom.nextInt(10);
            randomY = secureRandom.nextInt(10);
        resetOptions();
        resetCheckList();
        numberOfShotsAround=0;

    }

    public void makeMove(){
        if(!shotAroundLast)
        {
            getPosToShot();
            shot(randomX, randomY);
        }
        else{
            if(hitStack.empty())
            {
                shotAroundLast = false;
                numberOfShotsAround=0;
                resetOptions();
                resetCheckList();
                makeMove();
                return;

            }
            if(numberOfShotsAround>=4) {
                hitStack.pop();
                numberOfShotsAround=0;
                resetOptions();
                resetCheckList();
                makeMove();
                return;
            }
            int rand=checkList.pop();
            System.out.println("RandPOP "+rand+" opt");
            switch(rand)
            {
                case 0:
                    numberOfShotsAround++;
                    shot(hitStack.peek().X-1,hitStack.peek().Y);
                    break;
                case 1:
                    numberOfShotsAround++;
                    shot(hitStack.peek().X+1,hitStack.peek().Y);
                    break;
                case 2:
                    numberOfShotsAround++;
                    shot(hitStack.peek().X,hitStack.peek().Y-1);
                    break;
                case 3:
                    numberOfShotsAround++;
                    shot(hitStack.peek().X,hitStack.peek().Y+1);
                    break;
            }

        }

    }

    private static class HitPair
    {
        int X;
        int Y;

        HitPair(int X, int Y) {
            this.X=X;
            this.Y=Y;
        }

    }

}
