package Core.DataBase;

import Core.Model.Project;
import Core.Model.User;
import Core.Model.UserProject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ProjectDatabase {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public ProjectDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(project);
        transaction.commit();
    }

    public void updateProject(Project updatedProject) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(updatedProject);
        transaction.commit();
    }

//    public void removeProject(String projectTitle) {
//        // Remove the project from the list or database based on the title
//        projects.removeIf(project -> project.getTitle().equals(projectTitle));
//    }

    public ArrayList<Project> getAllProject() {
        Session session = sessionFactory.openSession();
        List projects = session.createQuery("FROM Project").list();
        return new ArrayList<>(projects);
    }


    public Project getProjectByTitle(String projectTitle) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Project.class);
        criteria.add(Restrictions.eq("title", projectTitle));
        return (Project) criteria.uniqueResult();

    }

    public List<Project> findProjectsByTitle(String searchTitle) {
        // Find projects that match the searchTitle
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Project.class);
        criteria.add(Restrictions.like("title", "%" + searchTitle + "%"));
        return criteria.list();

    }
    public void assignProjectToUser(User user, Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        UserProject userProject = new UserProject(user, project);
        session.persist(userProject);

        transaction.commit();

    }
}
