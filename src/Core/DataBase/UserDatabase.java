package Core.DataBase;

import Core.Model.Project;
import Core.Model.User;
import Core.ModelDb.ProjectDB;
import Core.ModelDb.UserDB; // Import UserDB instead of User
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static UserDatabase instance; // Singleton instance
    private SessionFactory sessionFactory;

    private UserDatabase() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    private User convertToAppModel(UserDB userDB) {
        User user = new User();
        user.setId(userDB.getId());
        user.setName(userDB.getName());
        user.setId(userDB.getId());
        user.setName(userDB.getName());
        user.setEmail(userDB.getEmail());
        user.setPassword(userDB.getPassword());
        user.setRole(userDB.getRole());
        // Set other properties...
        return user;
    }



    private UserDB convertToDbModel(User user) {
        UserDB userDB = new UserDB();
        userDB.setId(user.getId());
        userDB.setName(user.getName());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        userDB.setRole(user.getRole());

        // Set other properties...
        return userDB;
    }



    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public void saveUser(User user) {
        UserDB userDB=convertToDbModel(user);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(userDB);
        transaction.commit();
    }

    public void updateUser(User updatedUser) {
        UserDB userDB = convertToDbModel(updatedUser);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(userDB);
        transaction.commit();
    }

    public void removeUser(User user) {
        UserDB userDB = convertToDbModel(user);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Soft delete by updating the isDeleted flag
        userDB.setDeleted(false);
        session.update(userDB);

        session.getTransaction().commit();
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDB.class);
        criteria.add(Restrictions.eq("isDeleted", false));

        List<UserDB> userDBList = criteria.list();

        List<User> userList = new ArrayList<>();
        for (UserDB userDB : userDBList) {
            User user = convertToAppModel(userDB);
            userList.add(user);
        }

        return userList;
    }

    public User findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserDB.class);
        criteria.add(Restrictions.eq("email", email));

        UserDB userDB = (UserDB) criteria.uniqueResult();
        if (userDB != null) {
            return convertToAppModel(userDB);
        }

        return null;
    }
}
