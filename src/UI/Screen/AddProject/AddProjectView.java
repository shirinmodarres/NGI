package UI.Screen.AddProject;

import Core.DataBase.UserProjectRepository;
import Core.Manager.ProjectManager;
import UI.Component.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddProjectView extends JPanel {
    AddProjectController addProjectController;

    public AddProjectView(UserProjectRepository userProjectRepository ,ProjectManager projectManager, AddProjectViewEventListener addProjectViewEventListener) {

        addProjectController = new AddProjectController(projectManager,userProjectRepository); // Provide userManager to AddMemberController
        //Setting
        setLayout(null);
        setBounds(85, 15, 700, 570);
        setBackground(new Color(251, 246, 230));
        setVisible(false);

        Font titleFont = new Font("Calibri", Font.PLAIN, 32);
        CustomLabel title = new CustomLabel("Add Project", titleFont, 20, 32, 390, 40);
        add(title);

        Font font = new Font("Calibri", Font.PLAIN, 20);
        CustomLabel name = new CustomLabel("Title:", font, 25, 80, 110, 23);
        add(name);
        CustomTextField nameField = new CustomTextField("Write Your project's title", 20, 103, 260, 40);
        add(nameField);

        CustomLabel members = new CustomLabel("Member:", font, 25, 160, 110, 23);

        add(members);

        CustomLabel description = new CustomLabel("Description:", font, 25, 320, 110, 23);
        add(description);
        CustomTextArea descriptionField = new CustomTextArea("Description...", 20, 350, 390, 100, new Color(147, 191, 207));
        add(descriptionField);

        RoundedButton saveBtn = new RoundedButton("Save", 400, 510, 100, 40, new Color(96, 150, 180));
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extract data from input fields
                String projectName = nameField.getText();
                String description = descriptionField.getText();

                addProjectController.addProject(projectName, description);
                nameField.setText("");
                descriptionField.setText("");
                addProjectViewEventListener.onPageClosed();
            }
        });
        add(saveBtn);

        RoundedButton cancelBtn = new RoundedButton("Cancel", 515, 510, 100, 40, new Color(214, 64, 69));
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                descriptionField.setText("");
                addProjectViewEventListener.onPageClosed();
            }
        });
        add(cancelBtn);
    }

    public interface AddProjectViewEventListener {
        void onPageClosed();
    }
}
