package example.com;


import JFigure.com.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

//todo:przesylanie wyboru koloru pionkow oraz nazwy gracza

public class MainWindow extends JFrame {

    private JPanel topPanel=new JPanel();
    private JPanel bottomPanel=new JPanel();
    private BackgroundPanel mainPanel =new BackgroundPanel(ImageIO.read(new File("wood_texture.jpg")),BackgroundPanel.SCALED);

    MainWindow() throws IOException {
        setTitle("Okno Główne");
        setSize(new Dimension(720,520));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setMainPanel(setTopPanel(), setBottomPanel());
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String[] arg) throws IOException {
        new ChessboardWindow();
//        new MainWindow();

    }


    private void setMainPanel(JPanel topPanel, JPanel bottomPanel)  {

            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(topPanel,BorderLayout.LINE_START);
            mainPanel.add(bottomPanel,BorderLayout.LINE_END);
            add(mainPanel);


    }
    private JPanel setTopPanel(){

        GroupLayout topLayout=new GroupLayout(topPanel);

        JLabel sign=new JLabel();
        JTextPane playerOneNickname=new JTextPane();
        JTextPane playerTwoNickname=new JTextPane();
        JComboBox<String> playerOnePawn=new JComboBox<String>();
        JComboBox<String> playerTwoPawn=new JComboBox<String>();


        playerOneNickname.setPreferredSize(new Dimension(120,33));
        playerOneNickname.setText("Gracz 1");
        playerOneNickname.setFont(new Font("Arial",1,15));

        playerTwoNickname.setPreferredSize(new Dimension(120,33));
        playerTwoNickname.setText("Gracz 2");
        playerTwoNickname.setFont(new Font("Arial",1,15));

        playerOnePawn.setPreferredSize(new Dimension(120,33));
        sign.setText("Ustawienia Rozgrywki");
        sign.setFont(new Font("Arial",1,20));

        //todo: ustawienie layoutu


        topPanel.setLayout(topLayout);
        topPanel.setPreferredSize(new Dimension(720,260));
        topPanel.add(sign);
        topPanel.add(playerOneNickname);
        topPanel.add(playerTwoNickname);
        topPanel.add(playerOnePawn);
        topPanel.add(playerTwoPawn);
        return topPanel;

    }
    private JPanel setBottomPanel(){

        bottomPanel.setPreferredSize(new Dimension(720,260));
        return bottomPanel;

    }
}
