package JFigure.com;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class King extends Figure {
    boolean firstMove=false;
    Vector<Coords> castle=new Vector<Coords>(2);
    public King(int color,int player,int yPos , int xPos){
        init(color);
        this.x =xPos;
        this.y =yPos;
        this.player=player;
    }
    private void init(int color){
        if(color==WHITE_FIGURE){
            ImageIcon imageicon=new ImageIcon("White_King.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);

        }
        if (color==BLACK_FIGURE){
            ImageIcon imageicon=new ImageIcon("Black_King.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);
        }
    }

    @Override
    public Vector<Coords> getMoves(Figure[][] board) {
        Vector<Coords> moveDic=new Vector<Coords>();
        beatingMoves.clear();
        if (deffendingBeating.contains(new Coords(NOMOVE,NOMOVE))){deffendingBeating.clear();beatingMoves=deffendingBeating;}
        if (deffendingMoves.contains(new Coords(NOMOVE,NOMOVE))){ deffendingMoves.clear();  return deffendingMoves;}

        for (int i = -1; i <=1 ; i++) {
            for (int j = -1; j <=1 ; j++) {
                if (!checkBoundaries(y+i,x+j))continue;
                
                if (board[y+i][x+j]==null){
                    moveDic.add(new Coords(y+i,x+j));
                }
                if (board[y+i][x+j]!=null&&board[y+i][x+j].getPlayer()!=player){
                    beatingMoves.add(new Coords(y+i,x+j));

                }
            }
        }
        //roszada
        if (!firstMove){
            for (int i = 1; i <=3 ; i++) {

                if (board[y][x-i]!=null)break;
                if (board[y][x-4] instanceof Rock && !((Rock) board[y][x - 4]).getFirstMove())castle.add(new Coords(y,x-4));
            }
            for (int i = 1; i <=2 ; i++) {
                if (board[y][x+i]!=null)break;
                if (board[y][x+3] instanceof Rock && !((Rock) board[y][x + 3]).getFirstMove()) castle.add(new Coords(y,x+3));
            }

        }
        if (!deffendingBeating.isEmpty()){beatingMoves=deffendingBeating;}
        if (!deffendingMoves.isEmpty()){  return deffendingMoves;}
        return moveDic;
    }

    @Override
    public void setX(int x) {
        firstMove=true;
        super.setX(x);
    }

    public Vector<Coords> getCastle() {
        return castle;
    }
}
