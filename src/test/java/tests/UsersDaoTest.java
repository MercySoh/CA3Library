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
    void findUserById() {
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