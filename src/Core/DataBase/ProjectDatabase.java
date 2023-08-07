package Core.DataBase;

import Core.Model.Project;
import Core.ModelDb.ProjectDB;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ProjectDatabase {
    private SessionFactory sessionFactory;

    public ProjectDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    private ProjectDB convertToDbModel(Project project) {
        ProjectDB projectDB = new ProjectDB();
        projectDB.setId(project.getId());
        projectDB.setTitle(project.getTitle());
        projectDB.setDescription(project.getDescription());
        return projectDB;
    }

    private Project convertToAppModel(ProjectDB projectDB) {
        Project project = new Project();
        project.setId(projectDB.getId());
        project.setTitle(projectDB.getTitle());
        project.setDescription(projectDB.getDescription());
        return project;
    }
    public void saveProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectDB projectDB = convertToDbModel(project);
        session.persist(projectDB);
        transaction.commit();
    }

    public void updateProject(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProjectDB projectDB = convertToDbModel(project);
        session.merge(projectDB);
        transaction.commit();
    }

    public List<Project> getAllProjects() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(ProjectDB.class);
        List<ProjectDB> projectDBList = criteria.list();
        List<Project> projectList = new ArrayList<>(projectDBList.size()); // Initialize with the expected size
        for (ProjectDB projectDB : projectDBList) {
            Project project = convertToAppModel(projectDB);
            projectList.add(project);
        }
        return projectList;
    }



    public ProjectDB getProjectByTitle(String projectTitle) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(ProjectDB.class);
        criteria.add(Restrictions.eq("title", projectTitle));
        return (ProjectDB) criteria.uniqueResult();
    }

    public List<ProjectDB> findProjectsByTitle(String searchTitle) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(ProjectDB.class);
        criteria.add(Restrictions.like("title", "%" + searchTitle + "%"));
        return criteria.list();
    }
}
