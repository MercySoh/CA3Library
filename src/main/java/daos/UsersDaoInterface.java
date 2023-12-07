package daos;

import business.Users;

import java.util.List;

public interface UsersDaoInterface {
    public List<Users> findAllUsers();
    public Users findUserByUsernamePassword(String uname, String pword);
    public Users findUserById(int id);
    public int addUser(String uname,String email, String pword, String address, String phone, int userType);
    public int deleteUser(int userId);
    public int amendUser(Users u) ;
    public boolean checkUsername(String uname);
    public boolean checkEmail(String email);
}
