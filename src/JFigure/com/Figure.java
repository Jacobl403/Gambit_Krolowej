package JFigure.com;


import javax.swing.*;
import java.util.HashMap;
import java.util.Vector;


public abstract class Figure {
    public static final int WHITE_FIGURE=1;
    public static final int BLACK_FIGURE=0;
    public static final int NOMOVE=20;

    ImageIcon figureIcon;
    int x;
    int y;
    int player;
    Vector<Coords> beatingMoves=new Vector<Coords>();
    Vector<Coords>deffendingMoves=new Vector<Coords>();
    Vector<Coords>deffendingBeating=new Vector<Coords>();
    public Vector<Coords> getMoves(Figure[][] board){ return null;}


   boolean checkBoundaries(int y,int x){
       return y >= 0 && y < 8 && x >= 0 && x <8;
   }

    private void init(int color){}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlayer() { return player; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void addDeffendingBeating(Coords coords){this.deffendingBeating.add(coords);}
    public void clearDeffendingBeating(){this.deffendingBeating.clear(); }

    public Vector<Coords> getDeffendingBeating() {
        return deffendingBeating;
    }
    public Vector<Coords> getDeffendingMoves() {
        return deffendingMoves;
    }
    public void addDeffendingMoves(Coords coords){
        this.deffendingMoves.add(coords);
    }
    public void clearDeffendingMoves(){this.deffendingMoves.clear();}
    public Vector<Coords> getBeatingMoves() { return beatingMoves; }

    public ImageIcon getFigureIcon() {
        return figureIcon;
    }
}
