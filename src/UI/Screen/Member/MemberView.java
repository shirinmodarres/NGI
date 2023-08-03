package UI.Screen.Member;

import Core.Manager.UserManager;
import Core.Model.User;
import UI.Component.*;
//import UI.Screen.EditMember.EditMemberView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberView extends JPanel {
    private final Font textFont = new Font("Calibri", Font.PLAIN, 18);
    private final Font subTextFont = new Font("Calibri", Font.PLAIN, 14);
    private final Font titleFont = new Font("Calibri", Font.PLAIN, 32);
    RoundedBorder border = new RoundedBorder(new Color(147, 191, 207), 6, 3);
    private MemberController memberController;
    JPanel memberPanel = new JPanel();


    public MemberView(UserManager userManager, ActionListener addMemberEvent) {

        memberController = new MemberController(userManager); // Provide userManager to AddMemberController

        //Setting
        setVisible(false);
        setLayout(null);
        setBounds(85, 15, 700, 570);
        setBackground(new Color(251, 246, 230));
        setVisible(true);

        //Header
        CustomLabel title = new CustomLabel("Members", titleFont, 20, 32, 390, 40);
        add(title);
        ImageButton searchMemberIcon = new ImageButton("img/search-member.png", 500, 32, 44, 44);
        add(searchMemberIcon);

        CustomTextField searchField = new CustomTextField("Search..", 554, 44, 130, 30);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String searchText = searchField.getText().trim();
                    if (!searchText.isEmpty()) {
                        findUser(searchText);
                    }
                }
            }
        });
        add(searchField);

        //Add members card
        JPanel addMemberPlace = createAddMemberPanel(addMemberEvent);

        pageIsEmpty(userManager);

        add(addMemberPlace);
    }

    public void pageIsEmpty(UserManager userManager) {
        memberPanel.setLayout(null);
        JScrollPane memberScrollPane = new JScrollPane(memberPanel);
        memberScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        memberScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(memberScrollPane);
        //Membership Card
        generateMemberPlacePanels();

    }

    private JPanel createAddMemberPanel(ActionListener addMemberEvent) {
        JPanel addMemberPlace = new JPanel();
        addMemberPlace.setBorder(border);
        addMemberPlace.setLayout(null);
        addMemberPlace.setVisible(true);
        addMemberPlace.setBackground(new Color(255, 255, 255, 25));
        addMemberPlace.setBounds(20, 103, 175, 212);

        ImageButton addMemberIcon = new ImageButton("img/add.png", 65, 82, 44, 44);
        addMemberIcon.addActionListener(addMemberEvent);
        addMemberPlace.add(addMemberIcon);

        return addMemberPlace;
    }

    private void findUser(String searchText) {
        User foundUser = memberController.findUserByName(searchText);
        if (foundUser != null) {
            System.out.println("User found: " + foundUser);
        } else {
            System.out.println("User not found.");
        }
    }

    private void generateMemberPlacePanels() {
        int x = 20;
        int y = 103;
        int padding = 20;
        int containerWidth = 700;
        int currentX = x;
        int currentY = y;

        //Generate Membership card
        for (User user : memberController.getUserManager().getUserDatabase().getAllUsers()) {
            currentX += 175 + padding;
            if (currentX + 175 + padding > containerWidth) {
                currentX = x;
                currentY += 212 + padding;
            }

            JPanel memberPlace = new JPanel();
            memberPlace.setBorder(border);
            memberPlace.setLayout(null);
            memberPlace.setBackground(new Color(255, 255, 255, 60));
            memberPlace.setBounds(currentX, currentY, 175, 212);
            add(memberPlace);

            ImageButton editBtn = new ImageButton("img/edit.png", 140, 10, 25, 25);
//            editBtn.addActionListener(editMemberEvent);
            memberPlace.add(editBtn);

            Circle circle = new Circle(Color.BLACK, 40, 105, 55, 55);
            memberPlace.add(circle);

            CustomLabel name = new CustomLabel(user.getName(), textFont, 40, 105, 100, 25);
            name.setHorizontalAlignment(JLabel.CENTER);
            memberPlace.add(name);

            CustomLabel position = new CustomLabel(user.getRole().toString(), subTextFont, 40, 135, 100, 25);
            position.setHorizontalAlignment(JLabel.CENTER);
            memberPlace.add(position);
            memberPlace.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println(user);
                }
            });
        }

        // Update the preferred size of MemberView based on the number of members
        int rowCount = (memberController.getUserManager().getAllUsers().size() + 2) / 3;
        int totalHeight = rowCount * (212 + padding) + y;
        setPreferredSize(new Dimension(containerWidth, totalHeight));
    }
}