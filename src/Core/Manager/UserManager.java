package Core.Manager;

import Core.DataBase.UserDatabase;
import Core.Model.User;
import Core.Model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    static private ArrayList<User> users;
    UserDatabase userDatabase ;

    public UserManager(UserDatabase userDatabase) {
        this.users = new ArrayList<>();
        this.userDatabase = userDatabase;
    }

    public ArrayList<User> addUser(String nickname, String email, String password, Role role) {
        User newUser = new User(nickname, email, password, role);
//        orm.getRepository("User").insert(newUser);
        users.add(newUser);
        userDatabase.saveUser(newUser);
        return users;
    }

    public void updateUser( String nickname, String email, String password,Role role) {
        User userToUpdate = findUserByName(nickname);
        if (userToUpdate != null) {
            userToUpdate.setName(nickname);
            userToUpdate.setEmail(email);
            userToUpdate.setPassword(password);
            userToUpdate.setRole(role);
//            orm.getRepository("User").update(userToUpdate);
            userDatabase.updateUser(userToUpdate);
        }
    }

//    public void removeUser(int userName) {
//        users.removeIf(user -> user.getName().equals(userName));
//        userDatabase.removeUser(userName);
//    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null; // User not found
    }
    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null; // User not found
    }
    public User isValidUser(String email, String password) {
        User user = userDatabase.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user; // Valid user
        }

        return null; // Invalid user
    }
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
}
