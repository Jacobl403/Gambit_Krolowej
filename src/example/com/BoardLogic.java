package example.com;

import JFigure.com.*;




public class BoardLogic {
    private Figure[][] board=new Figure[8][8];

    BoardLogic(BoardLogic another){
        this.board=another.board;
    }

    public BoardLogic(Player firstPlayer, Player secondPlayer){

        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <8 ; j++) {
                board[i][j]=null;
            }
        }
        for (int i = 0; i <16 ; i++) {
            board[firstPlayer.getYPos(i)][firstPlayer.getXPos(i)]= firstPlayer.figures.get(i);
            board[secondPlayer.getYPos(i)][secondPlayer.getXPos(i)]= secondPlayer.figures.get(i);

        }

    }



    public Figure getFigure(int y,int x){
        return board[y][x];
    }
    public Figure[][] getBoard() {
        return board;
    }

    public void update(int y,int x,Figure figure,Player currPlayer){
        board[y][x]=figure;
        board[figure.getY()][figure.getX()]=null;
        currPlayer.updateFigure(figure.getY(),figure.getX(),y,x);
        figure.setY(y);
        figure.setX(x);
        currPlayer.getEnemy().deleteFigure(y, x);


    }
public Figure updateForSim(int y,int x,Figure figure,Player currPlayer){
    board[y][x]=figure;
    board[figure.getY()][figure.getX()]=null;
    currPlayer.updateFigure(figure.getY(),figure.getX(),y,x);
    figure.setY(y);
    figure.setX(x);
    return currPlayer.getEnemy().deleteFigForSim(y, x);

}


    public void castle(Figure king, Figure rock){
        board[rock.getY()][rock.getX()]=null;
        board[king.getY()][king.getX()]=null;
        if (rock.getX()==0){
             king.setX(king.getX()-2);
             rock.setX(rock.getX()+3);

        }else {
            king.setX(king.getX()+2);
            rock.setX(rock.getX()-2);
        }
        board[king.getY()][king.getX()]=king;
        board[rock.getY()][rock.getX()]=rock;
    }

    public void check(Player firstPlayer, Player secondPlayer){

        boolean firstPlayerCheck=false;
        boolean secondPlayerCheck=false;

        for (Figure figure : firstPlayer.figures){
                figure.getMoves(board);
                for (Coords coords : figure.getBeatingMoves()){
                    if (board[coords.getY()][coords.getX()]instanceof King &&board[coords.getY()][coords.getX()].getPlayer()!=figure.getPlayer() ){
                        secondPlayer.setCheckFlag(true);
                        secondPlayerCheck=true;

                    }

                }
        }

        for (Figure figure : secondPlayer.figures){
            figure.getMoves(board);
            for (Coords coords : figure.getBeatingMoves()){
                if (board[coords.getY()][coords.getX()]instanceof King &&board[coords.getY()][coords.getX()].getPlayer()!=figure.getPlayer() ){
                    firstPlayer.setCheckFlag(true);
                    firstPlayerCheck=true;
                }
            }
        }
        if (!firstPlayerCheck)firstPlayer.setCheckFlag(false);
        if (!secondPlayerCheck)secondPlayer.setCheckFlag(false);

    }



}


