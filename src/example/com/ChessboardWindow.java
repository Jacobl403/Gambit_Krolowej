package example.com;

import JFigure.com.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ChessboardWindow extends JFrame implements MouseListener {
    private final JPanel Board =new JPanel();
    private final JPanel timerPanel =new JPanel();
    private final JLabel [][] fields =new JLabel[8][8];

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final Adapter adapter;
    private boolean secondClick =false;
    ChessboardWindow(){
        firstPlayer=new Player("Kuba",Figure.BLACK_FIGURE,Player.TOP_PLAYER);
        secondPlayer=new Player("Kasia", Figure.WHITE_FIGURE,Player.BOTTOM_PLAYER);
        adapter=new Adapter(firstPlayer, secondPlayer);

        setLayout(new BorderLayout(10,0));
        add(setBoard(),BorderLayout.WEST);
//        add(setTimerPanel(),BorderLayout.EAST);
        setTitle("Szachownica");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }
    private JPanel setBoard(){
        Board.setPreferredSize(new Dimension(720,720));

        Board.setLayout(new FlowLayout(0,0,0));
        for (int i=0;i<8;i++){
            for (int j = 7; j >= 0; j--) {

                fields[i][j]=new JLabel();

                fields[i][j].setHorizontalAlignment(JLabel.CENTER);
                fields[i][j].setPreferredSize(new Dimension(90,90));
                fields[i][j].setOpaque(true);
                fields[i][j].setBackground(new Color(181, 136, 99));
                fields[i][j].addMouseListener(this);

                if (i%2==0&&j%2!=0||(i%2!=0&&j%2==0)){
                    fields[i][j].setBackground(new Color(240, 217, 181));
                }
            Board.add(fields[i][j],FlowLayout.LEFT);
            }
        }

        for (int i = 0; i <16; i++) {
            fields[firstPlayer.getYPos(i)][firstPlayer.getXPos(i)].setIcon(firstPlayer.getFigureIcon(i));
            fields[secondPlayer.getYPos(i)][secondPlayer.getXPos(i)].setIcon(secondPlayer.getFigureIcon(i));
        }

        return Board;
    }
    private  JPanel setTimerPanel(){
        timerPanel.setPreferredSize(new Dimension(505,580));
        timerPanel.setBackground(Color.RED);
        return timerPanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j < 8; j++) {

                if (e.getSource()==fields[i][j]){
                    if (!secondClick){
                         boolean result=adapter.firstClick(i,j,fields);
                         if (result) secondClick=true;
                    }else {
                       boolean result= adapter.secondClick(i,j,fields);
                       if (result) secondClick=false;
                    }

                    break;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

