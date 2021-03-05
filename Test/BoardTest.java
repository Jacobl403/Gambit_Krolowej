import JFigure.com.Figure;
import JFigure.com.King;
import JFigure.com.Rock;
import example.com.BoardLogic;
import example.com.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void test_Castle_method(){
        //given
        Player firstPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        Player secondPlayer=new Player("Kuba", Figure.BLACK_FIGURE,Player.TOP_PLAYER);
        BoardLogic gameBoard=new BoardLogic(firstPlayer,secondPlayer);
        Figure[][] board=gameBoard.getBoard();

        //when
        gameBoard.castle(board[0][4],board[0][0]);

        //result
        Assertions.assertTrue(board[0][2] instanceof King);
        Assertions.assertTrue(board[0][3] instanceof Rock);

    }
    @Test
    public void test_Check_method(){
        Player firstPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        Player secondPlayer=new Player("Kuba", Figure.BLACK_FIGURE,Player.TOP_PLAYER);
        firstPlayer.setEnemy(secondPlayer);
        secondPlayer.setEnemy(firstPlayer);
        BoardLogic gameBoard=new BoardLogic(firstPlayer,secondPlayer);
        Figure[][] board=gameBoard.getBoard();
        //player black is check

        gameBoard.update(6,4,board[0][3],firstPlayer);

        gameBoard.check(firstPlayer,secondPlayer);
        Assertions.assertTrue(secondPlayer.isCheckFlag());

        gameBoard.update(6,4,board[7][3],secondPlayer);
        gameBoard.check(firstPlayer,secondPlayer);
        Assertions.assertFalse(secondPlayer.isCheckFlag());
    }
}
