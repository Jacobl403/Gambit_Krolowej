package example.com;



import JFigure.com.Coords;
import JFigure.com.Figure;
import JFigure.com.King;
import JFigure.com.Rock;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class Adapter {


    private static final boolean SUCCESS =true;
    private static final boolean FAILED =false;


    private final BoardLogic gameBoard;
    private Player currentPlayer;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Figure currFigure;
    private final Vector<Color> currentColor=new Vector<Color>();
    private Color pickedFigureColor;



    Adapter(Player firstPlayer, Player secondPlayer){
        firstPlayer.setEnemy(secondPlayer);
        secondPlayer.setEnemy(firstPlayer);
        gameBoard =new BoardLogic(firstPlayer,secondPlayer);

        this.firstPlayer=firstPlayer;
        this.secondPlayer=secondPlayer;
        if (firstPlayer.getTurn())currentPlayer=firstPlayer;
        else currentPlayer=secondPlayer;
        }


    public boolean firstClick(int y,int x,JLabel [][] fields){

            gameBoard.check(firstPlayer,secondPlayer);
            currFigure =gameBoard.getFigure(y,x);
            if (currFigure ==null)return FAILED;
            if (!currentPlayer.afterCheckMoves(currFigure,gameBoard))return FAILED;
            if (currFigure.getMoves(gameBoard.getBoard()).isEmpty()&& currFigure.getBeatingMoves().isEmpty())return FAILED ;


            if (currFigure.getPlayer()!=currentPlayer.position)return FAILED;
            setUpJLabels(fields);

        return SUCCESS;
    }

    public boolean secondClick(int y,int x,JLabel [][] fields){

                previousStateJLabel(fields);

            //bicie

            if (gameBoard.getFigure(y,x)!=null&&gameBoard.getFigure(y, x).getPlayer()!= currFigure.getPlayer() && currFigure.getBeatingMoves().contains(new Coords(y,x)))
            {
                    fields[currFigure.getY()][currFigure.getX()].setIcon(null);
                    fields[y][x].setIcon(currFigure.getFigureIcon());
                     gameBoard.update(y,x, currFigure,currentPlayer);


                    gameBoard.check(firstPlayer,secondPlayer);
                    currentPlayer=Player.changeTurn(firstPlayer, secondPlayer);
                     return SUCCESS;
            }
            //ruch
            if (gameBoard.getFigure(y,x)==null&& currFigure.getMoves(gameBoard.getBoard()).contains(new Coords(y, x))){

                fields[currFigure.getY()][currFigure.getX()].setIcon(null);
                fields[y][x].setIcon(currFigure.getFigureIcon());
                gameBoard.update(y,x, currFigure,currentPlayer);

                gameBoard.check(firstPlayer,secondPlayer);
                currentPlayer=Player.changeTurn(firstPlayer, secondPlayer);

                return SUCCESS;
            }


            //roszada
        if (currFigure instanceof King&& gameBoard.getFigure(y,x) instanceof Rock&& ((King) currFigure).getCastle().contains(new Coords(y,x))){
            Figure rock=gameBoard.getFigure(y,x);

            fields[currFigure.getY()][currFigure.getX()].setIcon(null);
            fields[rock.getY()][rock.getX()].setIcon(null);

            gameBoard.castle(currFigure,rock);

            fields[currFigure.getY()][currFigure.getX()].setIcon(currFigure.getFigureIcon());
            fields[rock.getY()][rock.getX()].setIcon(rock.getFigureIcon());

            gameBoard.check(firstPlayer,secondPlayer);
            currentPlayer=Player.changeTurn(firstPlayer, secondPlayer);
            return SUCCESS;
        }

        return SUCCESS;
    }


    private void setUpJLabels(JLabel [][] fields){

        pickedFigureColor=fields[currFigure.getY()][currFigure.getX()].getBackground();
        fields[currFigure.getY()][currFigure.getX()].setBackground(new Color(100, 109, 100));
        for (Coords coords : currFigure.getMoves(gameBoard.getBoard())) {
            fields[coords.getY()][coords.getX()].setIcon(new ImageIcon("mark.png"));
        }

        for (Coords coords : currFigure.getBeatingMoves()){
            currentColor.add(fields[coords.getY()][coords.getX()].getBackground());

            fields[coords.getY()][coords.getX()].setBackground(new Color(182, 54, 54));

        }

    }

    private void previousStateJLabel(JLabel [][] fields){

        fields[currFigure.getY()][currFigure.getX()].setBackground(pickedFigureColor);
        for (Coords coords : currFigure.getMoves(gameBoard.getBoard())) {
            fields[coords.getY()][coords.getX()].setIcon(null);
        }

        int i=0;

        for (Coords coords : currFigure.getBeatingMoves()){

            fields[coords.getY()][coords.getX()].setBackground(currentColor.get(i));
            i++;
        }
        currentColor.clear();
    }
}
