package UI.Screen.ProjectPanel;

import Core.DataBase.IssueDatabase;
import Core.Manager.BoardManager;
import Core.Manager.IssueManager;
import Core.Manager.ProjectAssignments;
import Core.Model.Project;
import UI.Component.RoundedBorder;
import UI.Component.RoundedButton;
import UI.Screen.BoardPanel.BoardPanelView;
import UI.Screen.Issue.IssueController;
import UI.Screen.Issue.IssueView;
import UI.Screen.ProjectInfo.ProjectInfoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectPanelView extends JPanel {
    private JPanel contentPanel;
    private JPanel currentPanel;
    private ProjectInfoView projectInfoView;
    private RoundedButton infoButton;
    private RoundedButton boardButton;
    private RoundedButton isssueButton;
    private RoundedButton reportButton;
    private Project project;

    public ProjectPanelView(Project project) {
        this.project = project; // Store the project
        setLayout(null);
        setBounds(85, 15, 700, 570);
        setVisible(false);
        setBackground(new Color(251, 246, 230));

        // Header Tabs
        infoButton = createTabButton("Info", 75, 55, new Color(96, 150, 180));
        boardButton = createTabButton("Board", 215, 55, new Color(214, 64, 69));
        isssueButton = createTabButton("IssueDB", 355, 55, new Color(103, 141, 88));
        reportButton = createTabButton("Report", 505, 55, new Color(5, 60, 94));

        add(infoButton);
        add(boardButton);
        add(isssueButton);
        add(reportButton);

        // Content Panel
        contentPanel = new JPanel();
        contentPanel.setBounds(0, 100, getWidth(), getHeight() - 100);
        setBackground(new Color(251, 246, 230));
        contentPanel.setLayout(new CardLayout());
        add(contentPanel);

        // Initial panel
        projectInfoView =new ProjectInfoView(project);
        currentPanel = projectInfoView; // Replace with your specific panel
        contentPanel.add(currentPanel);
    }

    private RoundedButton createTabButton(String text, int x, int y, Color color) {
        RoundedButton button = new RoundedButton(text, x, y, 115, 35, color);
        button.setBorder(new RoundedBorder(color, 25, 7));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanelForButton(button);
            }
        });

        return button;
    }

    private void showPanelForButton(RoundedButton button) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        contentPanel.remove(currentPanel);

        if (button == infoButton) {
            currentPanel=projectInfoView;

        } else if (button == boardButton) {
            BoardManager boardManager = new BoardManager();
            BoardPanelView boardPanelView = new BoardPanelView(boardManager, project); // Pass the project
            currentPanel = boardPanelView;
        } else if (button == isssueButton) {
            IssueDatabase issueDatabase=IssueDatabase.getInstance();
            IssueManager issueManager=IssueManager.getInstance();
            IssueController issueController=new IssueController();
            IssueView issueView=new IssueView(issueManager,project);


            currentPanel =issueView; // Replace with the IssueDB panel
        } else if (button == reportButton) {
            currentPanel = new JPanel(); // Replace with the Report panel
        }

        currentPanel.setBackground(new Color(251,246,230));
        contentPanel.add(currentPanel);
        cardLayout.show(contentPanel, button.getText());

        resetTabButtonTransparency();
        button.setBackground(button.getBackground().brighter()); // Increase transparency
    }

    private void resetTabButtonTransparency() {
        infoButton.setBackground(new Color(96, 150, 180)); // Original color
        boardButton.setBackground(new Color(214, 64, 69));
        isssueButton.setBackground(new Color(103, 141, 88));
        reportButton.setBackground(new Color(5, 60, 94));
    }
}
