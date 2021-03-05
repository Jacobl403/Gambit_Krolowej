package JFigure.com;



import javax.swing.*;
import java.awt.*;

import java.util.Vector;

public class Bishop extends Figure {

    public Bishop(int color,int player,int yPos , int xPos){
        init(color);
        this.x =xPos;
        this.y =yPos;
        this.player=player;
    }
    private void init(int color){
        if(color==WHITE_FIGURE){
            ImageIcon imageicon=new ImageIcon("White_Bishop.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);

        }
        if (color==BLACK_FIGURE){
            ImageIcon imageicon=new ImageIcon("Black_Bishop.png");
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

        // ruchy do przodu i do tyłu po przekątnych

        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y + i, x + i)){break;}

            if (board[y+i][x+i]==null){

                moveDic.add(new Coords(y+i,x+i));
                continue;
            }
            if (board[y+i][x+i].getPlayer()!=player){
                beatingMoves.add(new Coords(y+i,x+i));
                break;
            }

            break;
        }
        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y - i, x - i))break;

            if (board[y-i][x-i]==null){
                moveDic.add(new Coords(y-i,x-i));
                continue;
            }
            if (board[y-i][x-i].getPlayer()!=player){
                beatingMoves.add(new Coords(y-i,x-i));
                break;
            }
            break;
        }
        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y + i, x - i))break;

            if (board[y+i][x-i]==null){
                moveDic.add(new Coords(y+i,x-i));
                continue;
            }
            if (board[y+i][x-i].getPlayer()!=player){
                beatingMoves.add(new Coords(y+i,x-i));
                break;
            }
            break;
        }
        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y - i, x + i))break;

            if (board[y-i][x+i]==null){
                moveDic.add(new Coords(y-i,x+i));
                continue;
            }
            if (board[y-i][x+i].getPlayer()!=player){
                beatingMoves.add(new Coords(y-i,x+i));
                break;
            }
            break;
        }
        if (!deffendingBeating.isEmpty()){beatingMoves=deffendingBeating;}
        if (!deffendingMoves.isEmpty()){  return deffendingMoves;}
        return moveDic;
    }

}

//            moveDic.put(y-i,x-i);
//            moveDic.put(y+i,x-i);
//            moveDic.put(y-i,x+i);