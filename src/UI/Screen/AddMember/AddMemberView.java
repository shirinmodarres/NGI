package UI.Screen.AddMember;

import Core.Manager.UserManager;
import Core.Model.Role;
import UI.Component.CustomTextField;
import UI.Component.CustomLabel;
import UI.Component.DropdownField;
import UI.Component.RoundedButton;
import UI.Screen.AddProject.AddProjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddMemberView extends JPanel {

    AddMemberController addMemberController;

    public AddMemberView(UserManager userManager, AddMemberViewEventListener addMemberViewEventListener) {

        addMemberController = new AddMemberController(userManager); // Provide userManager to AddMemberController
        //Setting
        setLayout(null);
        setBounds(85, 15, 700, 570);
        setBackground(new Color(251, 246, 230));
        setVisible(true);

        Font titleFont = new Font("Calibri", Font.PLAIN, 32);
        CustomLabel title = new CustomLabel("Add Members", titleFont, 20, 32, 390, 40);
        add(title);

        Font font = new Font("Calibri", Font.PLAIN, 20);
        CustomLabel name = new CustomLabel("Name:", font, 25, 80, 110, 23);
        add(name);

        CustomTextField nameField = new CustomTextField("Write Your Name", 20, 103, 260, 40);
        add(nameField);

        CustomLabel email = new CustomLabel("Email:", font, 385, 80, 110, 23);
        add(email);

        CustomTextField emailField = new CustomTextField("Write Your Email", 380, 103, 260, 40);
        add(emailField);

        CustomLabel password = new CustomLabel("Password:", font, 25, 160, 110, 23);
        add(password);

        CustomTextField passwordField = new CustomTextField("You Should write 8 Character ", 20, 183, 260, 40);
        add(passwordField);

        CustomLabel role = new CustomLabel("Role:", font, 385, 160, 110, 23);
        add(role);

        ArrayList<String> roleName = new ArrayList<>();
        for (Role r : Role.values()) {
            switch (r) {
                case PO -> roleName.add("PO");
                case TESTER -> roleName.add("TESTER");
                case DEVELOPER -> roleName.add("DEVELOPER");
            }
        }
        DropdownField roleDropDown = new DropdownField(roleName.toArray(new String[0]), 380, 183, 260, 40);
        add(roleDropDown);

        CustomLabel project = new CustomLabel("Project:", font, 25, 240, 110, 23);
        add(project);

        RoundedButton saveBtn = new RoundedButton("Save", 400, 510, 100, 40, new Color(96, 150, 180));
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extract data from input fields
                String name = nameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String selectedRole = (String) roleDropDown.getSelectedItem();
                Role role = Role.valueOf(selectedRole.toUpperCase());

                // Call the controller method to add the user
                addMemberController.addUser(name, email, password, role);

                // Assuming the first item is a default selection
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                roleDropDown.setSelectedIndex(0);
                addMemberViewEventListener.onPageClosed();

            }
        });
        add(saveBtn);

        RoundedButton cancelBtn = new RoundedButton("Cancel", 515, 510, 100, 40, new Color(214, 64, 69));
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                roleDropDown.setSelectedIndex(0); // Assuming the first item is a default selection
                addMemberViewEventListener.onPageClosed();
            }
        });
        add(cancelBtn);

    }
    public interface AddMemberViewEventListener {
        void onPageClosed();
    }
}
