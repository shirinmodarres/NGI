package Core.DataBase;

import Core.Model.Project;
import Core.Model.User;
import Core.Model.UserProject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserProjectRepository {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public UserProjectRepository(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    public void assignProjectToUser(User user, Project project) {
        Session session = sessionFactory.openSession();

        UserProject userProject = new UserProject(user, project);
        session.persist(userProject);
    }

    public void unassignProjectFromUser(User user, Project project) {
        Session session = sessionFactory.openSession();

        UserProject userProject = findByUserAndProject(user, project);
        if (userProject != null) {
            session.delete(userProject);
        }
    }

    public List<UserProject> getAssignedProjectsForUser(User user) {
        return findByUser(user);
    }

//    public void printAssignments() {
//        List assignments = session
//                .createCriteria(UserProject.class)
//                .list();
//
//        for (UserProject userProject : assignments) {
//            System.out.println(userProject.getUser().getName() + " is assigned to project: " + userProject.getProject().getTitle());
//        }
//    }

    public UserProject findByUserAndProject(User user, Project project) {
        Session session = sessionFactory.openSession();

        return (UserProject) session
                .createCriteria(UserProject.class)
                .add(Restrictions.eq("user", user))
                .add(Restrictions.eq("project", project))
                .setMaxResults(1)
                .uniqueResult();
    }

    public List findByUser(User user) {
        Session session = sessionFactory.openSession();

        return session
                .createCriteria(UserProject.class)
                .add(Restrictions.eq("user", user))
                .list();
    }

    // Add other methods as needed for your use cases
}
