package daos;

import business.Users;

import java.sql.*;
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
                    freeConnection("");
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
                    freeConnection("");
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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users u = null;
        try {
            con = this.getConnection();

            String query = "SELECT * FROM USERS WHERE ID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

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
            System.out.println("An error occurred in the findUserById() method: " + e.getMessage());
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
                    freeConnection(con);
                }
            }
            catch (SQLException e)
            {
                System.out.println("An error occurred when shutting down the findUserById() method: " + e.getMessage());
            }
        }
        return u;
    }

    @Override
    public int addUser(String uname, String email, String pword, String address, String phone, int userType) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int newId = -1;
        try {
            con = this.getConnection();

            String query = "INSERT INTO users(userName, email,password,address,phone,userType) VALUES (?, ?, ?, ?,?,?)";

            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, uname);
            ps.setString(2, email);
            ps.setString(3, pword);
            ps.setString(4, address);
            ps.setString(5,phone);
            ps.setInt(6,userType);

            ps.executeUpdate();

            generatedKeys = ps.getGeneratedKeys();

            if(generatedKeys.next())
            {
                newId = generatedKeys.getInt(1);
            }
        }
        catch (SQLException e)
        {
            System.err.println("\tA problem occurred during the addUser method:");
            System.err.println("\t"+e.getMessage());
            newId = -1;
        }
        finally
        {
            try
            {
                if(generatedKeys != null){
                    generatedKeys.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection("");
                }
            }
            catch (SQLException e)
            {
                System.err.println("A problem occurred when closing down the addUser method:\n" + e.getMessage());
            }
        }
        return newId;
    }

    @Override
    public int deleteUser(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        try {
            con = getConnection();

            String command = "DELETE FROM Users WHERE ID=?";
            ps = con.prepareStatement(command);
            ps.setInt(1, userId);

            rowsAffected = ps.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("An error occurred in the deleteUser() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection("");
                }
            } catch (SQLException e)
            {
                System.out.println("An error occurred when shutting down the deleteUser() method: " + e.getMessage());
            }
        }
        return rowsAffected;
    }

    @Override
    public int amendUser(Users u) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        try {
            con = getConnection();

            String query = "SELECT userID, username FROM users WHERE userID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, u.getUserID());

            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userID");
                if (id != u.getUserID())
                    throw new SQLException("Username " + u.getUserName() + " already exists for another user.");
            }

            String command = "UPDATE users SET username=?, email=?, password=?, address=?, phone=? WHERE userID=?";
            ps = con.prepareStatement(command);
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getAddress());
            ps.setString(5,u.getPhone());
            ps.setInt(6, u.getUserID());

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error occurred in the amendUser() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the amendUser() method: " + e.getMessage());
            }
        }
        return rowsAffected;
    }

    @Override
    public boolean checkUsername(String uname) {
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        return false;
    }


}
