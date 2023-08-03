package UI.Screen;

import Core.DataBase.ProjectDatabase;
import Core.DataBase.UserDatabase;
import Core.DataBase.UserProjectRepository;
import Core.Manager.ProjectManager;
import Core.Manager.UserManager;
import UI.Component.MenuTabBarView;
import UI.Screen.AddMember.AddMemberView;
import UI.Screen.AddProject.AddProjectView;
import UI.Screen.LogIn.LoginController;
import UI.Screen.LogIn.LoginView;
import UI.Screen.Member.MemberView;
import UI.Screen.Project.ProjectView;
import UI.Screen.ProjectInfo.ProjectInfoView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelView extends JPanel {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    //Panels for members
    UserDatabase userDatabase = new UserDatabase(sessionFactory);
    UserManager userManager = new UserManager(userDatabase);
    //EditMemberView editMemberView = new EditMemberView(userManager);
    LoginController loginController=new LoginController(userManager);
    LoginView loginView = new LoginView(loginController, new LoginView.LoginEventListener() {
        @Override
        public void onLogin() {
            loginView.setVisible(false);
            setVisible(true);
            loginView.performLogin(loginController);

        }
    });
    AddMemberView addMemberView = new AddMemberView(userManager, new AddMemberView.AddMemberViewEventListener() {
        @Override
        public void onPageClosed() {
            addMemberView.setVisible(false);
            memberView.setVisible(true);
            memberView.pageIsEmpty(userManager);
        }
    });
    MemberView memberView = new MemberView(userManager, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            memberView.setVisible(false);
            addMemberView.setVisible(true);
            System.out.println("go to add member view");
        }
    });
    //Panels for project
    UserProjectRepository userProjectRepository = new UserProjectRepository(sessionFactory);
    ProjectDatabase projectDatabase = new ProjectDatabase(sessionFactory);
    ProjectManager projectManager = new ProjectManager(projectDatabase);
    ProjectInfoView projectInfoView = new ProjectInfoView();
    ProjectView projectView = new ProjectView(projectManager, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            projectView.setVisible(false);
            addProjectView.setVisible(true);
            System.out.println("go to add project");
        }
    }, new ProjectView.ProjectViewEventListener() {
        @Override
        public void onProjectClick() {
            projectView.setVisible(false);
            projectInfoView.setVisible(true);

        }
    });
    AddProjectView addProjectView = new AddProjectView(userProjectRepository, projectManager, new AddProjectView.AddProjectViewEventListener() {
        @Override
        public void onPageClosed() {
            addProjectView.setVisible(false);
            projectView.setVisible(true);
            projectView.pageIsEmpty(projectManager);
        }
    });
    MenuTabBarView menuTabBarView = new MenuTabBarView(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            projectView.setVisible(true);
            memberView.setVisible(false);
            addMemberView.setVisible(false);

        }
    }, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            projectView.setVisible(false);
            memberView.setVisible(true);
            addProjectView.setVisible(false);
        }
    }, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.setVisible(true);
        }
    });
    MainPanelView() {
        //Setting
        setBounds(0, 0, 800, 600);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
        setLayout(null);
        add(loginView);
        //Add all panels
        add(memberView);
        add(addMemberView);
//        add(editMemberView);

        add(projectView);
        add(addProjectView);

        add(menuTabBarView);

    }
}
