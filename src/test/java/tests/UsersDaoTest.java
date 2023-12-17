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
    void addUser() {
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