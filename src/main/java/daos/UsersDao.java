package daos;

import business.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends Dao implements UsersDaoInterface {
    public UsersDao(String dbName) {
        super(dbName);
    }
    public UsersDao(Connection con) {super(con); }
    @Override
    public List<Users> findAllUsers() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Users> users = new ArrayList<Users>();

        try
        {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM USERS";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int userId = rs.getInt("userID");
                String username = rs.getString("userName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int userType = rs.getInt("userType");
                Users u = new Users(userId, username, email, password, address,phone,userType);
                users.add(u);
            }
        }
        catch (SQLException e)
        {
            System.out.println("An error occurred in the findAllUsers() method: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection();
                }
            }
            catch (SQLException e)
            {
                System.out.println("An error occurred when shutting down the findAllUsers() method: " + e.getMessage());
            }
        }
        return users;
    }

    @Override
    public Users findUserByUsernamePassword(String uname, String pword) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pword);

            rs = ps.executeQuery();
            if (rs.next())
            {
                int userId = rs.getInt("userID");
                String username = rs.getString("userName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int userType = rs.getInt("userType");
                u = new Users(userId, username, email, password, address,phone,userType);
            }
        }
        catch (SQLException e)
        {
            System.out.println("An error occurred in the findUserByUsernamePassword() method: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection();
                }
            }
            catch (SQLException e)
            {
                System.out.println("An error occurred when shutting down the findUserByUsernamePassword() method: " + e.getMessage());
            }
        }
        return u;
    }

    @Override
    public Users findUserById(int id) {
        return null;
    }

    @Override
    public int addUser(String uname, String pword, String fName, String lName) {
        return 0;
    }
}
