package Core.DataBase;

import Core.Model.User;
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
import java.util.Arrays;
import java.util.List;

public class UserDatabase {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    public UserDatabase(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    public void updateUser(User updatedUser) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.merge(updatedUser);
        transaction.commit();
    }

//    public void removeUser(int userId) {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = null;
//            try {
//                transaction = session.beginTransaction();
//                User user = session.get(User.class, userId);
//                if (user != null) {
//                    session.delete(user);
//                }
//                transaction.commit();
//            } catch (Exception e) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                e.printStackTrace();
//            } finally {
//                session.close();
//            }
//        }


    public ArrayList<User> getAllUsers() {
     Session session = sessionFactory.openSession();
            List users = session.createQuery("FROM User").list();
            return new ArrayList<>(users);
        }
    public User findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));

        return (User) criteria.uniqueResult();
    }




}






