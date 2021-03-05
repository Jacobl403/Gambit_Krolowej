package JFigure.com;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class Queen extends Figure{
    int color;
    public Queen(int color,int player,int yPos , int xPos){
        init(color);
        this.x =xPos;
        this.y =yPos;
        this.player=player;
        this.color=color;
    }
    private void init(int color){
        if(color==WHITE_FIGURE){
            ImageIcon imageicon=new ImageIcon("White_Queen.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);

        }
        if (color==BLACK_FIGURE){
            ImageIcon imageicon=new ImageIcon("Black_Queen.png");
            Image image=imageicon.getImage();
            image=image.getScaledInstance(90,90,Image.SCALE_SMOOTH);
            this.figureIcon=new ImageIcon(image);
        }
    }

    @Override
    public Vector<Coords> getMoves(Figure[][] board) {
            beatingMoves.clear();
        if (deffendingBeating.contains(new Coords(NOMOVE,NOMOVE))){deffendingBeating.clear();    beatingMoves=deffendingBeating;}
        if (deffendingMoves.contains(new Coords(NOMOVE,NOMOVE))){ deffendingMoves.clear(); return deffendingMoves;}

            Rock rock=new Rock(color,player,y,x);
            Bishop bishop=new Bishop(color,player,y,x);
            Vector<Coords>movesDoc=rock.getMoves(board);
            movesDoc.addAll(bishop.getMoves(board));

            this.beatingMoves=rock.getBeatingMoves();
            this.beatingMoves.addAll(bishop.getBeatingMoves());
        if (!deffendingBeating.isEmpty()){beatingMoves=deffendingBeating;}
        if (!deffendingMoves.isEmpty()){  return deffendingMoves;}
            return movesDoc;
    }
}
