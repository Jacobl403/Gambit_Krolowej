package example.com;

import JFigure.com.*;

import javax.swing.*;
import java.util.Vector;

public class Player {


    public static final int TOP_PLAYER=4;
    public static final int BOTTOM_PLAYER=5;

    private Player enemy;
    public String nickname;
    private boolean checkFlag=false;
    private  boolean turn =false;
    int position;
    Vector<Figure> figures=new Vector<Figure>();

    Player(Player copyPlayer){
        this.nickname= copyPlayer.nickname;
        this.position=copyPlayer.position;
        this.enemy=copyPlayer.enemy;
        this.checkFlag=copyPlayer.checkFlag;
        this.turn=copyPlayer.turn;
        this.figures= copyPlayer.figures;
    }

    public Player(String nickname, int color, int position){
        this.nickname =nickname;
        this.position=position;
        setFigures(color);
        if (color==Figure.WHITE_FIGURE)turn=true;
    }

    private void setFigures(int color){
        if(position==TOP_PLAYER){
            for (int i = 0; i <8 ; i++) {
                figures.add(new Pawn(color,TOP_PLAYER,6,i));
            }
            for (int i = 0; i <2 ; i++) {
                figures.add(new Rock(color,TOP_PLAYER,7,i*7));
                figures.add(new Knight(color,TOP_PLAYER,7,1+5*i));
                figures.add(new Bishop(color,TOP_PLAYER,7,2+3*i));
            }
            figures.add(new Queen(color,TOP_PLAYER,7,3));
            figures.add(new King(color,TOP_PLAYER,7,4));
        }
        if (position==BOTTOM_PLAYER){
            for (int i = 0; i <8 ; i++) {
                figures.add(new Pawn(color,BOTTOM_PLAYER,1,i));
            }
            for (int i = 0; i <2 ; i++) {
                figures.add(new Rock(color,BOTTOM_PLAYER,0,i*7));
                figures.add(new Knight(color,BOTTOM_PLAYER,0,1+5*i));
                figures.add(new Bishop(color,BOTTOM_PLAYER,0,2+3*i));
            }
            figures.add(new Queen(color,BOTTOM_PLAYER,0,3));
            figures.add(new King(color,BOTTOM_PLAYER,0,4));
        }
    }

    public Icon getFigureIcon(int i){
        return figures.get(i).getFigureIcon();
    }

    public int getXPos(int i){
        return figures.get(i).getX();
    }

    public int getYPos(int i){
        return figures.get(i).getY();
    }
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public Player getEnemy() {
        return enemy;
    }

    public boolean getTurn(){ return turn;}

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public Vector<Figure> getFigures() {
        return figures;
    }

    public static Player changeTurn(Player firstPlayer,Player secondPlayer){
                if (firstPlayer.getTurn()){
                    firstPlayer.setTurn(false);
                    secondPlayer.setTurn(true);
                    return secondPlayer;
                }else {
                    secondPlayer.setTurn(false);
                    firstPlayer.setTurn(true);
                    return firstPlayer;
                }
    }







    public void updateFigure(int oldY, int oldX, int newY , int newX){
        for (Figure figure : figures){
            if (figure.getY()==oldY&&figure.getX()==oldX){
                figure.setY(newY);
                figure.setX(newX);
                break;
            }
        }
    }

    public void deleteFigure(int y,int x){
        for (Figure figure : figures){
            if (figure.getY()==y&&figure.getX()==x){
                figures.remove(figure);
                break;
            }
        }
    }
    public boolean afterCheckMoves(Figure pickedFigure, BoardLogic gameBoard){

        //szukamy czy figura nalezy do gracza
        boolean contain=false;
        for (Figure figure : figures){
            if (figure==pickedFigure)contain=true;
        }
        if (!contain)return false;

        pickedFigure.clearDeffendingMoves();
        pickedFigure.clearDeffendingBeating();

        //ruch mozlowy bo nie ma checka
        if(!checkFlag)return true;


       boolean checkFlagclone= this.checkFlag;
        //przygotowanie kopi obiektów
        Player cloneEnemy=new Player(enemy);
        BoardLogic cloneBoard=new BoardLogic(gameBoard);
        Player cloneCurrent=new Player(this);
        cloneCurrent.setEnemy(cloneEnemy);


        pickedFigure.getMoves(gameBoard.getBoard());
        Coords previousFigurePosition=new Coords(pickedFigure.getY(),pickedFigure.getX());


        for (Coords coords : pickedFigure.getBeatingMoves()){
            cloneCurrent.checkFlag =false;
            Figure deleteFig= cloneBoard.updateForSim(coords.getY(),coords.getX(),pickedFigure,cloneCurrent);
            cloneBoard.check(cloneCurrent,cloneEnemy);
            cloneEnemy.checkFlag=false;

            if (!cloneCurrent.checkFlag){
                pickedFigure.addDeffendingBeating(coords);
            }

            cloneBoard.updateForSim(previousFigurePosition.getY(),previousFigurePosition.getX(),pickedFigure,cloneCurrent);
            cloneEnemy.figures.add(deleteFig);
            pickedFigure.setY(previousFigurePosition.getY());
            pickedFigure.setX(previousFigurePosition.getX());
        }
        if (pickedFigure.getDeffendingBeating().isEmpty())pickedFigure.addDeffendingBeating(new Coords(Figure.NOMOVE,Figure.NOMOVE));

      //ruchy blokujące

        for (Coords coords :pickedFigure.getMoves(gameBoard.getBoard())){
            cloneCurrent.checkFlag =false;
            Figure deleteFigure=cloneBoard.updateForSim(coords.getY(),coords.getX(),pickedFigure,cloneCurrent);
            cloneBoard.check(cloneCurrent,cloneEnemy);

            cloneEnemy.setCheckFlag(false);
            if (!cloneCurrent.checkFlag){
                pickedFigure.addDeffendingMoves(coords);
            }

            cloneBoard.updateForSim(previousFigurePosition.getY(),previousFigurePosition.getX(),pickedFigure,cloneCurrent);
             if (deleteFigure!=null) cloneEnemy.figures.add(deleteFigure);
            pickedFigure.setY(previousFigurePosition.getY());
            pickedFigure.setX(previousFigurePosition.getX());
        }
        if (pickedFigure.getDeffendingMoves().isEmpty()) {

            pickedFigure.addDeffendingMoves(new Coords(Figure.NOMOVE, Figure.NOMOVE));
        }


        this.checkFlag =checkFlagclone;

        return true;


    }
    public Figure deleteFigForSim(int y, int x){
        for (Figure figure : figures){
            if (figure.getY()==y&&figure.getX()==x){
                figures.remove(figure);
                return figure;
            }
        }
        return null;
    }

}




