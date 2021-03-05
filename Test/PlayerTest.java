import JFigure.com.Figure;
import example.com.BoardLogic;
import example.com.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    public void update_Info_About_Player_Figures(){
        //given
        Player firstPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        Player secondPlayer=new Player("Kuba", Figure.BLACK_FIGURE,Player.TOP_PLAYER);
        firstPlayer.setEnemy(secondPlayer);

        BoardLogic gameBoard=new BoardLogic(firstPlayer,secondPlayer);
        Figure[][] board=gameBoard.getBoard();
        //when
        int beforeBeating=secondPlayer.getFigures().size();

        gameBoard.update(6,2,gameBoard.getFigure(1,2),firstPlayer);

        int afterBeating=secondPlayer.getFigures().size();

        //result
        Assertions.assertNotEquals(beforeBeating,afterBeating);
    }
    @Test
    public void test_change_turn(){
        Player firstPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        Player secondPlayer=new Player("Kuba", Figure.BLACK_FIGURE,Player.TOP_PLAYER);

        Assertions.assertTrue(firstPlayer.getTurn());
        Assertions.assertFalse(secondPlayer.getTurn());

        Player.changeTurn(firstPlayer,secondPlayer);

        Assertions.assertFalse(firstPlayer.getTurn());
        Assertions.assertTrue(secondPlayer.getTurn());


    }
}
