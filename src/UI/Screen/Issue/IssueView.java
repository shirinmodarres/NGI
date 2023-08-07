package UI.Screen.Issue;

import Core.Manager.IssueManager;
import Core.Model.Project;
import UI.Component.CustomTextField;
import UI.Component.ImageButton;

import javax.swing.*;
import java.awt.*;

public class IssueView extends JPanel {
    public IssueView(IssueManager issueManager, Project project){
        setLayout(null);
        setBounds(15, 0, 700, 600);
        setBackground(new Color(251, 246, 230));

        ImageButton searchBoardIcon = new ImageButton("img/search.png", 430, 35, 44, 44);
        CustomTextField searchField = new CustomTextField("Search..", 479, 35, 130, 30);

    }


}
