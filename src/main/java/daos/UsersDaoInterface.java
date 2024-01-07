package daos;

import business.Users;

import java.util.List;

public interface UsersDaoInterface {

    /**
     * findAllUsers method able to list out all user.
     * @return a list of User
     */
    public List<Users> findAllUsers();


    public Users findUserByUsernamePassword(String uname, String pword);
    /**
     * findUserById method able to get user by userId.
     * @return that userId's user detail
     */
    public Users findUserById(int id);

    /**
     * addUser(with 6 args) method able to register a new user.
     * userID will increase automatic.
     *
     * @param uname is user's name
     * @param email is user's email
     * @param pword is user's password
     * @param address is user's address
     * @param phone is user's phone
     * @param userType is type of user

     * @return int of user id if added else added fail will return -1
     */
    public int addUser(String uname,String email, String pword, String address, String phone, int userType);
    /**
     * addUser(with Users) method able to register a new user.
     * userID will increase automatic.
     *
     * @param newUser is the new user's detail to be added

     * @return int of user id if added else added fail will return -1
     */
    public int addUser(Users newUser);
    /**
     * deleteUser method able to delete a user by userId
     *
     * @param userId is the user's id to be deleted

     * @return an int after deleted else return 0 when no rows are affected by the deleted.
     */
    public int deleteUser(int userId);
    /**
     * amendUser method allow user to update their detail.
     *userID will not be able to change.
     *
     * @param u is the user's detail that able to change.

     * @return an int after update else return 0 when no rows are affected by the update.
     */
    public int amendUser(Users u) ;
    /**
     * checkUsername(unique) method able to check username that already register.
     *
     * @param uname is the user's name that want to be checked.

     * @return true when the username is register else return false .
     */
    public boolean checkUsername(String uname);
    /**
     * checkEmail(unique) method able to check email that already register.
     *
     * @param email is the user's email that want to be checked.

     * @return true when the email is register else return false .
     */
    public boolean checkEmail(String email);
}
