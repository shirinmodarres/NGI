package UI.Screen.ProjectInfo;

import UI.Component.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class ProjectInfoView extends JPanel {
    ProjectInfoController projectInfoController;
    public ProjectInfoView(){
//        projectInfoController = new ProjectInfoController(projectManager);
        setLayout(null);
        setBounds(85, 15, 700, 570);
        setBackground(new Color(251, 246, 230));

        RoundedButton infoButton = new RoundedButton("Info", 75, 55, 115, 35, new Color(96, 150, 180));
//        infoButton.setBorder(new RoundedBorder(new Color(96,150,180),25,3));
        RoundedButton boardButton = new RoundedButton("Board", 215, 55, 115, 35, new Color(214, 64, 69, 80));
//        boardButton.setBorder(new RoundedBorder(new Color(214,64,69),25,3));
        RoundedButton isssueButton = new RoundedButton("Issue", 355, 55, 115, 35, new Color(103, 141, 88, 80));
//        isssueButton.setBorder(new RoundedBorder(new Color(103,141,88),25,3));
        RoundedButton reportButton = new RoundedButton("Report", 505, 55, 115, 35, new Color(5, 60, 94, 80));
//        reportButton.setBorder(new RoundedBorder(new Color(5,60,94),25,3));



    }


}
