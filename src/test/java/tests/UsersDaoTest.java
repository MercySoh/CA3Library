package tests;

import business.Book;
import business.Users;
import daos.UsersDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsersDaoTest {

    @Test
    void findAllUsers_Found() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findAllUsers_Found");
        ArrayList<Users> result = (ArrayList<Users>) usersDao.findAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void findUserByUsernamePassword() {
    }

    @Test
    void findUserById_Found() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findUserById_Found");
        int userID = 1;
        Users expResult = new Users(1,"jerry", "jerry@gmail.com", "$2a$10$K.uvVBVFs1HRMO83Y6Er0.Qx6CTy40VJf38TgkA7csG1.ecyKctUC", "address", "231030213", 0);
        Users result = usersDao.findUserById(userID);
        assertEquals(expResult, result);
    }

    @Test
    void findUserById_NotFound() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("findUserById_NotFound");
        int userID = 2500;
        Users expResult = null;
        Users result = usersDao.findUserById(userID);
        assertEquals(expResult, result);
    }

    @Test
    void addUser_6args() {
       UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("addUser_6args");
        String userName = "Leo Zen";
        String email = "leo234@gmail.com";
        String password = "234Leo";
        String address = "address";
        String phone = "0881234567";
        int userType = 0;

        int result = usersDao.addUser(userName,email,password,address,phone,userType);
        assertTrue((result > 0));

        if (result != -1) {
            System.out.println("Method returned appropriately, confirming database changed by trying to remove what was added");
            int rowsDeleted = usersDao.deleteUser(result);
            assertEquals(rowsDeleted, 1);
        }
    }


    @Test
    void addUser_Users() {
        UsersDao usersDao = new UsersDao("ca3librarytest");
        System.out.println("addUser_Users");
        String userName = "Ray Shawn";
        String email = "ray000@gmail.com";
        String password = "ray000";
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


    @Test
    void deleteUser() {
    }

    @Test
    void amendUser() {
    }

    @Test
    void checkUsername() {
    }

    @Test
    void checkEmail() {
    }
}