package tests;

import business.Book;
import business.Users;
import daos.UsersDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsersDaoTest {

    /**
     * Test of findAllUsers_Found() method, of class UsersDao.
     */
    @Test
    void findAllUsers_Found() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findAllUsers_Found");
        ArrayList<Users> result = (ArrayList<Users>) usersDao.findAllUsers();
        assertEquals(2, result.size());
    }

    /**
     * Test of findUserByUsernamePassword_Found() method, of class UsersDao.
     */
    @Test
    void findUserByUsernamePassword_Found() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findUserByUsernamePassword_Found");
        String username = "jerry";
        String password = "rippleMMW1$";

        Users expResult = new Users(1,"jerry", "jerry@gmail.com", "$2a$10$BeXVetm90C0MmTtMDkA.u.cB7zz6YYJrQuMPlXnGTF5U/arwzw8K.", "address", "231030213", 0);
        Users result = usersDao.findUserByUsernamePassword(username,password);
        assertEquals(expResult,result);
    }

    /**
     * Test of findUserByUsernamePassword_NotFound() method, of class UsersDao.
     */
    @Test
    void findUserByUsernamePassword_NotFound() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findUserByUsernamePassword_NotFound");
        String username = "Test User";
        String password = "password";

        Users expResult = null;
        Users result = usersDao.findUserByUsernamePassword(username,password);
        assertEquals(expResult,result);
    }

    /**
     * Test of findUserById_Found() method, of class UsersDao.
     */
    @Test
    void findUserById_Found() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findUserById_Found");
        int userID = 1;
        Users expResult = new Users(1,"jerry", "jerry@gmail.com", "$2a$10$K.uvVBVFs1HRMO83Y6Er0.Qx6CTy40VJf38TgkA7csG1.ecyKctUC", "address", "231030213", 0);
        Users result = usersDao.findUserById(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of findUserById_NotFound() method, of class UsersDao.
     */
    @Test
    void findUserById_NotFound() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findUserById_NotFound");
        int userID = 2500;
        Users expResult = null;
        Users result = usersDao.findUserById(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of addUser_6args_withoutDeleted()method, of class UsersDao.
     */
    @Test
    void addUser_6args_withoutDeleted() {
       UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("addUser_6args_withoutDeleted");
        String userName = "Leo Zen";
        String email = "leo234@gmail.com";
        String password = "234Leo";
        String address = "address";
        String phone = "0881234567";
        int userType = 0;

        int result = usersDao.addUser(userName,email,password,address,phone,userType);
        assertTrue((result > 0));
    }

    /**
     * Test of addUser_6args_with Deleted()method, of class UsersDao.
     */
    @Test
    void addUser_6args_withDeleted() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("addUser_6args_withDeleted");
        String userName = "Ray";
        String email = "rayyy@gmail.com";
        String password = "rAy000";
        String address = "address";
        String phone = "0881230971";
        int userType = 0;

        int result = usersDao.addUser(userName,email,password,address,phone,userType);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = usersDao.deleteUser(result);
            assertEquals(rowsDeleted, 1);
        }
    }

    /**
     * Test of addUser_Users_withoutDeleted() method, of class UsersDao.
     */
    @Test
    void addUser_Users_withoutDeleted() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("addUser_Users_withoutDeleted");
        String userName = "Ken";
        String email = "ken@gmail.com";
        String password = "0Ken0";
        String address = "address";
        String phone = "0887872566";
        int userType = 0;
        Users u = new Users(userName,email,password,address,phone,userType);

        int result = usersDao.addUser(u);
        assertTrue((result > 0));

    }

    /**
     * Test of addUser_Users_withDeleted() method, of class UsersDao.
     */
    @Test
    void addUser_Users_withDeleted() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("addUser_Users_withDeleted");
        String userName = "Shawn";
        String email = "Shawn000@gmail.com";
        String password = "Shawn000";
        String address = "address";
        String phone = "0887894561";
        int userType = 0;
        Users u = new Users(userName,email,password,address,phone,userType);

        int result = usersDao.addUser(u);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = usersDao.deleteUser(result);
            assertEquals(rowsDeleted, 1);
        }

    }

    /**
     * Test of deleteUser_ById() method, of class UsersDao.
     */
    @Test
    void deleteUser_ById() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("deleteUserById");

        Users u = new Users(5, "Ken", "ken@gmail.com", "0Ken0", "address", "0887872566", 0);
        int id = u.getUserID();
        int expResult = 1;

        int result = usersDao.deleteUser(id);
        assertEquals(expResult, result);

        if (result == 1) {
            System.out.println("Method returned appropriately, confirming database "
                    + "changed by trying to select what was deleted");
            Users selectedUser = usersDao.findUserById(u.getUserID());
            assertEquals(null, selectedUser);

            if (selectedUser == null) {
                usersDao.addUser(u);
            }
        }
    }

    /**
     * Test of amendUser() method, of class UsersDao.
     */
    @Test
    void amendUser() {
        {
            UsersDao usersDao = new UsersDao("ca3librarytest");
            System.out.println("amendUser_Users");

            int userID =1;
            String userName = "jerry";
            String email = "jerry@gmail.com";
            String password = "rippleMMW1$";
            String address = "address2345";
            String phone = "231030213";
            int userType = 0;
            Users u = new Users(userID,userName,email,password,address,phone,userType);

            int expResult = 1;
            int result = usersDao.amendUser(u);

            assertEquals(expResult, result);

            if (expResult == result) {

                Users expectedUser = new Users(u.getUserID(), u.getUserName(), u.getEmail(),u.getPassword(),u.getAddress(),u.getPhone(),u.getUserType());
                Users resultUser = usersDao.findUserById(u.getUserID());
                assertEquals(resultUser, expectedUser);

                usersDao.amendUser(u);
            }

        }

    }

    /**
     * Test of checkUsername_isPresent() method, of class UsersDao.
     */
    @Test
    void checkUsername_isPresent() {
            UsersDao usersDao = new UsersDao("ca3librarytest");
            System.out.println("checkUsername_isPresent");
        String username = "jerry";
        boolean expResult = true;
        boolean result = usersDao.checkUsername(username);

        assertEquals(expResult, result);

    }

    /**
     * Test of checkUsername_notPresent() method, of class UsersDao.
     */
    @Test
    void checkUsername_notPresent() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("checkUsername_notPresent");
        String username = "Henry";
        boolean expResult = false;

        boolean result = usersDao.checkUsername(username);

        assertEquals(expResult, result);
    }

    /**
     * Test of checkEmail_isPresent() method, of class UsersDao.
     */
    @Test
    void checkEmail_isPresent() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("checkEmail_isPresent");
        String email = "jerry@gmail.com";
        boolean expResult = true;

        boolean result = usersDao.checkEmail(email);

        assertEquals(expResult, result);
    }

    /**
     * Test of checkEmail_notPresent() method, of class UsersDao.
     */
    @Test
    void checkEmail_notPresent() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("checkEmail_notPresent");
        String email = "Henry123@gmail.com";
        boolean expResult = false;

        boolean result = usersDao.checkEmail(email);

        assertEquals(expResult, result);
    }
}