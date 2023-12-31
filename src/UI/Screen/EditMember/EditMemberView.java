package UI.Screen.EditMember;

import Core.Manager.UserManager;
import Core.Model.Role;
import Core.Model.User;
import UI.Component.CustomLabel;
import UI.Component.CustomTextField;
import UI.Component.DropdownField;
import UI.Component.RoundedButton;
import UI.Screen.Member.MemberView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditMemberView extends JPanel {
    EditMemberController editMemberController;

    public EditMemberView(UserManager userManager, EditMemberViewEventListener editMemberViewEventListener) {
        editMemberController = new EditMemberController(userManager);
        //setting
        setVisible(true);
        setLayout(null);
        setBounds(0, 0, 700, 570);
        setBackground(new Color(251, 0, 0));

        Font titleFont = new Font("Calibri", Font.PLAIN, 32);
        CustomLabel title = new CustomLabel("Edit Members", titleFont, 20, 32, 390, 40);
        title.setText(title.getText());
        Font font = new Font("Calibri", Font.PLAIN, 20);
        CustomLabel name = new CustomLabel("Name:", font, 25, 80, 110, 23);
        CustomTextField nameField = new CustomTextField("Write Your Name", 20, 103, 260, 40);

        CustomLabel email = new CustomLabel("Email:", font, 385, 80, 110, 23);
        CustomTextField emailField = new CustomTextField("Write Your Email", 380, 103, 260, 40);

        CustomLabel password = new CustomLabel("Password:", font, 25, 160, 110, 23);
        CustomTextField passwordField = new CustomTextField("You Should write 8 Character ", 20, 183, 260, 40);

        CustomLabel role = new CustomLabel("Role:", font, 385, 160, 110, 23);
        ArrayList<String> roleName = new ArrayList<>();
        for (Role r : Role.values()) {
            switch (r) {
                case PO -> roleName.add("PO");
                case TESTER -> roleName.add("TESTER");
                case DEVELOPER -> roleName.add("DEVELOPER");
            }
        }
        DropdownField roleDropDown = new DropdownField(roleName.toArray(new String[0]), 380, 183, 260, 40);

        CustomLabel project = new CustomLabel("Project:", font, 25, 240, 110, 23);


        add(title);
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
                editMemberController.editUser(name, email, password, role);
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                roleDropDown.setSelectedIndex(0); // Assuming the first item is a default selection
                editMemberViewEventListener.PageClosed();
            }
        });


        RoundedButton cancelBtn = new RoundedButton("Cancel", 515, 510, 100, 40, new Color(214, 64, 69));

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                roleDropDown.setSelectedIndex(0); // Assuming the first item is a default selection
                editMemberViewEventListener.PageClosed();
            }
        });

        add(title);
        add(email);
        add(emailField);
        add(name);
        add(nameField);
        add(password);
        add(passwordField);
        add(role);
        add(roleDropDown);
        add(project);
        add(saveBtn);
        add(cancelBtn);

    }

    public interface EditMemberViewEventListener {
        void PageClosed();

    }

}
