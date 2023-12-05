package daos;

import business.Users;

import java.util.List;

public interface UsersDaoInterface {
    public List<Users> findAllUsers();
    public Users findUserByUsernamePassword(String uname, String pword);
    public Users findUserById(int id);
    public int addUser(String uname, String pword, String fName, String lName);
}
