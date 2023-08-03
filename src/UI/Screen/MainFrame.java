package UI.Screen;

import Core.Manager.BoardManager;
import UI.Screen.AddBoard.AddBoardView;
//import UI.Screen.EditMember.EditMemberView;
import UI.Screen.Board.BoardController;
import UI.Screen.Board.BoardView;
import UI.Screen.LogIn.LoginController;
import UI.Screen.LogIn.LoginView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {



    public MainFrame() {
        setTitle("Mdrs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,814, 638);
        setBackground(Color.getHSBColor(0, 0, 0));


        MainPanelView mainPanelView=new MainPanelView();

        BoardManager boardManager=new BoardManager();
        BoardController boardController=new BoardController(boardManager);
        BoardView boardView=new BoardView(boardManager);
        AddBoardView addBoardView=new AddBoardView(boardManager);


        setLayout(null);
        add(mainPanelView);


        setVisible(true);
    }


}
