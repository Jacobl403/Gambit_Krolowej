package JFigure.com;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Rock extends Figure{
    boolean firstMove=false;
    public Rock(int color,int player,int yPos , int xPos){
        init(color);
        this.x =xPos;
        this.y =yPos;
        this.player=player;
    }
    private void init(int color){
        if(color==WHITE_FIGURE){
            ImageIcon imageicon=new ImageIcon("White_Rock.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);

        }
        if (color==BLACK_FIGURE){
            ImageIcon imageicon=new ImageIcon("Black_Rock.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);
        }
    }

    @Override
    public Vector<Coords> getMoves(Figure[][] board) {

        beatingMoves.clear();
        Vector<Coords> moveDic=new Vector<Coords>();
        if (deffendingBeating.contains(new Coords(NOMOVE,NOMOVE))){deffendingBeating.clear();    beatingMoves=deffendingBeating;}
        if (deffendingMoves.contains(new Coords(NOMOVE,NOMOVE))){ deffendingMoves.clear(); return deffendingMoves;}


        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y + i, x ))break;
            if (board[y+i][x]==null){
                moveDic.add(new Coords(y+i,x));
                continue;
            }
            if (board[y+i][x].getPlayer()!=player){
                beatingMoves.add(new Coords(y+i,x));
                break;
            }
            break;
        }

        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y - i, x ))break;
            if (board[y-i][x]==null){
                moveDic.add(new Coords(y-i,x));
                continue;
            }
            if (board[y-i][x].getPlayer()!=player){
                beatingMoves.add(new Coords(y-i,x));
                break;
            }
            break;
        }

        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y , x + i))break;
            if (board[y][x+i]==null){
                moveDic.add(new Coords(y,x+i));
                continue;
            }
            if (board[y][x+i].getPlayer()!=player){
                beatingMoves.add(new Coords(y,x+i));
                break;
            }
            break;
        }

        for (int i = 1; i <8 ; i++) {
            if (!checkBoundaries(y , x - i))break;
            if (board[y][x-i]==null){
                moveDic.add(new Coords(y,x-i));
                continue;
            }
            if (board[y][x-i].getPlayer()!=player){
                beatingMoves.add(new Coords(y,x-i));
                break;
            }
            break;
        }
    //roszada
        if (!deffendingBeating.isEmpty()){beatingMoves=deffendingBeating;}
        if (!deffendingMoves.isEmpty()){  return deffendingMoves;}
        return moveDic;
    }

    @Override
    public void setX(int x) {
        firstMove=true;
        super.setX(x);
    }
    public boolean getFirstMove(){
        return firstMove;
    }
}
