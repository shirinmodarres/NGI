







package UI.Screen.Board;

import Core.Manager.BoardManager;
import Core.Model.Board;
import Core.Model.Project;
import UI.Component.*;
import UI.Screen.AddBoard.AddBoardView;
import UI.Screen.AddProject.AddProjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static UI.Component.Colors.getRandomColor;

public class BoardView extends JPanel {
    private final Font textFont = new Font("Calibri", Font.PLAIN, 20);
    private final Font subTextFont = new Font("Calibri", Font.PLAIN, 14);
    private final Font titleFont = new Font("Calibri", Font.PLAIN, 32);

    BoardController boardController;
    JPanel boardPanel = new JPanel();
    AddBoardView addBoardView;

    public BoardView(BoardManager boardManager) {
        boardController = new BoardController(boardManager);
        setLayout(null);
        setBounds(85, 15, 700, 570);
        setBackground(new Color(251, 246, 230));

        RoundedButton infoButton = new RoundedButton("Info", 75, 55, 115, 35, new Color(96, 150, 180, 80));
//        infoButton.setBorder(new RoundedBorder(new Color(96,150,180),25,3));
        RoundedButton boardButton = new RoundedButton("Board", 215, 55, 115, 35, new Color(214, 64, 69));
//        boardButton.setBorder(new RoundedBorder(new Color(214,64,69),25,3));
        RoundedButton isssueButton = new RoundedButton("Issue", 355, 55, 115, 35, new Color(103, 141, 88, 80));
//        isssueButton.setBorder(new RoundedBorder(new Color(103,141,88),25,3));
        RoundedButton reportButton = new RoundedButton("Report", 505, 55, 115, 35, new Color(5, 60, 94, 80));
//        reportButton.setBorder(new RoundedBorder(new Color(5,60,94),25,3));


        ImageButton searchBoardIcon = new ImageButton("img/search.png", 440, 105, 44, 44);
        CustomTextField searchField = new CustomTextField("Search..", 484, 110, 130, 30);
        JPanel addBoardPlace = createAddBoardPanel();

        if (!boardManager.getAllBoards().isEmpty()) {
            boardPanel.setLayout(null);
            JScrollPane boardScrollPane = new JScrollPane(boardPanel);
            boardScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            boardScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            add(boardScrollPane);

            generateBoardPlacePanels();
        }


        add(addBoardPlace);
        add(infoButton);
        add(reportButton);
        add(boardButton);
        add(isssueButton);
        add(searchBoardIcon);
        add(searchField);
        setVisible(true);

    }

    private JPanel createAddBoardPanel() {
        JPanel addBoardPlace = new JPanel();
        addBoardPlace.setBorder(new RoundedBorder(new Color(147, 191, 207), 6, 3));
        addBoardPlace.setLayout(null);
        addBoardPlace.setVisible(true);
        addBoardPlace.setBackground(new Color(255, 255, 255, 25));

        addBoardPlace.setBounds(75, 165, 550, 60);
        ImageButton addBoardIcon = new ImageButton("img/add.png", 259, 175, 44, 44);


        addBoardIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBoardView = new AddBoardView(boardController.getBoardManager());
                addBoardView.setVisible(true);
                add(addBoardView);
                repaint();
                showPopup();
            }
        });
        addBoardPlace.add(addBoardIcon);
        add(addBoardIcon);
        return addBoardPlace;
    }

    private void showPopup() {
        // Set the visibility of the popup panel to true to show it
        AddBoardView addBoardView = new AddBoardView(boardController.getBoardManager());

        // Show the popup as a modal dialog (blocks input to other windows)
        addBoardView.setModal(true);
//        addBoardView.setLocationRelativeTo(this); // Center the popup relative to the BoardView
        addBoardView.setVisible(true);
    }

    private void generateBoardPlacePanels() {
        int x = 20;
        int y = 103;

        int cardWidth = 550;
        int cardHeight = 60;
        int cardSpacing = 30;
        int containerWidth = cardWidth + 2 * x;
        int currentX = x;
        int currentY = y;
        Color color = getRandomColor();

        for (Board board : boardController.getBoardManager().getAllBoards()) {
            JPanel boardPlace = new JPanel();
            boardPlace.setBorder(new RoundedBorder(color, 6, 3));
            boardPlace.setLayout(null);
            boardPlace.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 60));
            boardPlace.setBounds(currentX, currentY, cardWidth, cardHeight);

            CustomLabel date = new CustomLabel(board.getFormattedDate().toString(), subTextFont, 15, 15, 100, 25);
            Circle circle = new Circle(color, 70, 50, 55, 55);
            CustomLabel title = new CustomLabel(board.getName(), textFont, 80, 50, 110, 25);
            title.setHorizontalAlignment(JLabel.CENTER);

            boardPlace.add(circle);
            boardPlace.add(title);
            boardPlace.add(date);

            add(boardPlace);

            currentY += cardHeight + cardSpacing;
        }

        // Update the preferred size of MemberView based on the number of cards
        int totalHeight = currentY + y;
        setPreferredSize(new Dimension(containerWidth, totalHeight));
    }


}


