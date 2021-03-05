import JFigure.com.Coords;
import JFigure.com.Figure;
import example.com.BoardLogic;
import example.com.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Vector;

public class FigureTest {
    @Test
    public void get_Moves_function_for_initial_position_test(){
        //given
        Player firstPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        Player secondPlayer=new Player("Kuba", Figure.BLACK_FIGURE,Player.TOP_PLAYER);
        BoardLogic gameBoard=new BoardLogic(firstPlayer,secondPlayer);
        Figure[][] board=gameBoard.getBoard();

        //when
            //pawns
        Vector<Coords>pawnMoves=new Vector<Coords>();
        pawnMoves.add(new Coords(2,2)); pawnMoves.add(new Coords(3,2));

        Assertions.assertArrayEquals(board[1][2].getMoves(board).toArray(),pawnMoves.toArray());
            //knights
        Vector<Coords>knightsMove=new Vector<Coords>();
        knightsMove.add(new Coords(2,0)); knightsMove.add(new Coords(2,2));
        Assertions.assertArrayEquals(board[0][1].getMoves(board).toArray(),knightsMove.toArray());
            //before move quin

        Vector<Coords>QuinMoves=new Vector<>();
        Assertions.assertArrayEquals(board[0][3].getMoves(board).toArray(),QuinMoves.toArray());

        Vector<Coords>BishopMoves=new Vector<>();
        Assertions.assertArrayEquals(board[0][2].getMoves(board).toArray(),BishopMoves.toArray());



    }

    @Test
    public void figure_get_Moves_after_changing_position(){
        //given
        Player firstPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        Player secondPlayer=new Player("Kuba", Figure.BLACK_FIGURE,Player.TOP_PLAYER);
        BoardLogic gameBoard=new BoardLogic(firstPlayer,secondPlayer);
        Figure[][] board=gameBoard.getBoard();
        //when
            //rock
        Vector<Coords>rockMoves=new Vector<Coords>();
        Figure rockBlack=board[7][0];
        rockBlack.setY(5); rockBlack.setX(0);

        for (int i = 4; i >=2 ; i--) {
            rockMoves.add(new Coords(i,0));
        }
        for (int i = 1; i <=7 ; i++) {
            rockMoves.add(new Coords(5,i));
        }
        Assertions.assertArrayEquals(rockBlack.getMoves(board).toArray(),rockMoves.toArray());

        Figure bishop=board[0][5];
        bishop.setY(2);bishop.setX(7);
        Vector<Coords>bishopMoves=new Vector<Coords>();
        int x=6;
        for (int y = 3; y <=5 ; y++) {

            bishopMoves.add(new Coords(y,x));
            x=x-1;
            System.out.println(bishop.getMoves(board));
        }
        Assertions.assertArrayEquals(bishop.getMoves(board).toArray(),bishopMoves.toArray());
    }
}
