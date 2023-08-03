package UI.Screen.AddProject;

import Core.DataBase.UserProjectRepository;
import Core.Manager.ProjectManager;
import Core.Model.Project;
import Core.Model.User;
import Core.Model.UserProject;


public class AddProjectController {

    private final ProjectManager projectManager;
    private UserProjectRepository userProjectRepository;

    public AddProjectController(ProjectManager projectManager, UserProjectRepository userProjectRepository) {
        this.projectManager = projectManager;
        this.userProjectRepository = userProjectRepository;
    }

    public void addProject(String title, String description) {
        projectManager.addProject(title, description);
    }

    public void assignProjectToUser(User user, Project project) {
        userProjectRepository.assignProjectToUser(user, project);
    }

}


