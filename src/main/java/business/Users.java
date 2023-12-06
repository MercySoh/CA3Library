package business;

import java.util.Objects;

public class Users {
//    userID int NOT NULL AUTO_INCREMENT,
//    userName varchar(50) NOT NULL UNIQUE,
//    email varchar(255) NOT NULL UNIQUE,
//    password varchar(80) NOT NULL,
//    address varchar(80),
//    phone varchar(20), (int)
//    userType int not null,
//    primary key(userID)

    private int userID;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private int userType;

    public Users() {
    }

    public Users(String userName, String email, String password, String address, String phone, int userType) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.userType = userType;
    }

    public Users(int userID, String userName, String email, String password, String address, String phone, int userType) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userID == users.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                '}';
    }
}
