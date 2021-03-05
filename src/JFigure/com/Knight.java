package JFigure.com;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Knight extends Figure {
    public Knight(int color,int player,int yPos , int xPos){
        init(color);
        this.x =xPos;
        this.y =yPos;
        this.player=player;

    }

    private void init(int color){
        if(color==WHITE_FIGURE){
            ImageIcon imageicon=new ImageIcon("White_Knight.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);

        }
        if (color==BLACK_FIGURE){
            ImageIcon imageicon=new ImageIcon("Black_Knight.png");
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

        for (int i = -1; i <=1 ; i=i+2) {
            for (int j = -1; j <=1 ; j=j+2) {
                if (!checkBoundaries(y+2*i,x+j)){continue;}

                if (board[y+2*i][x+j]==null){
                    moveDic.add(new Coords(y+2*i,x+j));
                }
                if ((board[y + 2 * i][x + j] != null) &&board[y+2*i][x+j].getPlayer()!=player){
                    beatingMoves.add(new Coords(y+2*i,x+j));

                }

            }
        }
        for (int i = -1; i <=1 ; i=i+2) {
            for (int j = -1; j <=1 ; j=j+2) {
                if (!checkBoundaries(y+i,x+j*2))continue;

                if (board[y+i][x+j*2]==null){
                    moveDic.add(new Coords(y+i,x+j*2));
                }
                if ((board[y+i][x+j*2]!=null)&&board[y+i][x+j*2].getPlayer()!=player){
                    beatingMoves.add(new Coords(y+i,x+j*2));

                }

            }
        }
        if (!deffendingBeating.isEmpty()){beatingMoves=deffendingBeating;}
        if (!deffendingMoves.isEmpty()){  return deffendingMoves;}
        return moveDic;
    }
}
