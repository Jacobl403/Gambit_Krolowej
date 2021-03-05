package JFigure.com;

import example.com.Player;

import javax.swing.*;
import java.awt.*;

import java.util.Vector;


public class Pawn extends Figure {
    boolean firstMove=false;
   public Pawn(int color,int player,int yPos , int xPos){
        init(color);
        this.x =xPos;
        this.y =yPos;
        this.player=player;
    }

  private void init(int color){
       if(color==WHITE_FIGURE){
           ImageIcon imageicon=new ImageIcon("White_Pawn.png");
           Image image=imageicon.getImage();
           image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
           this.figureIcon=new ImageIcon(image);

       }
       if (color==BLACK_FIGURE){
           ImageIcon imageicon=new ImageIcon("Black_Pawn.png");
           Image image=imageicon.getImage();
           image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
           this.figureIcon=new ImageIcon(image);
       }
  }

    @Override
    public Vector<Coords> getMoves(Figure[][] board) {

        Vector<Coords> moveDic=new Vector<Coords>();
        beatingMoves.clear();
        if (deffendingBeating.contains(new Coords(NOMOVE,NOMOVE))){deffendingBeating.clear();    beatingMoves=deffendingBeating;}
        if (deffendingMoves.contains(new Coords(NOMOVE,NOMOVE))){ deffendingMoves.clear(); return deffendingMoves;}


        if (player== Player.TOP_PLAYER){
            if (checkBoundaries(y-1,x)&&board[y -1][x]==null)moveDic.add(new Coords(y-1,x));

            if (!firstMove&&board[y-2][x]==null&&board[y-1][x]==null) moveDic.add(new Coords(y -2, x));

            for (int i = -1; i <=1 ; i=i+2) {
                if (!checkBoundaries(y-1,x+1*i))continue;
                if (board[y -1][x +i]!=null&&board[y -1][x + i].getPlayer()!=player) beatingMoves.add(new Coords(y -1, x +i));
            }

        }

        if (player==Player.BOTTOM_PLAYER){

            if (checkBoundaries(y +1, x)&&board[y +1][x]==null)moveDic.add(new Coords(y +1, x));
            if (!firstMove&&board[y +2][x]==null&&board[y+1][x]==null) moveDic.add(new Coords(y +2, x));

            for (int i = -1; i <=1 ; i=i+2) {
                if (!checkBoundaries(y+1,x+i))continue;
                if (board[y +1][x +i]!=null&&board[y +1][x +i].getPlayer()!=player) beatingMoves.add(new Coords(y +1, x +i));
            }
        }

        if (!deffendingBeating.isEmpty()){beatingMoves=deffendingBeating;}
        if (!deffendingMoves.isEmpty()){  return deffendingMoves;}

        return moveDic;
    }

    @Override
    public void setX(int x) {
       if (!firstMove)firstMove=true;
        super.setX(x);
    }
}
