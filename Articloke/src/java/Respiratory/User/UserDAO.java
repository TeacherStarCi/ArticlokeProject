package Respiratory.User;

import Support.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Serializable {

    public static UserDTO getUserUsername(String username_)
            throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM User_ WHERE Username = ?";
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username_);
                res = pre.executeQuery();
                while (res.next()) {
                    String username = res.getString("Username");
                    String email = res.getString("Email");
                    String password = res.getString("Password");
                    String firstName = res.getString("FirstName");
                    String lastName = res.getString("LastName");
                    String picture = res.getString("Picture");
                    String role = res.getString("Role");
                    Date DOB = res.getDate("DOB");
                    String address = res.getString("Address");
                    String organization = res.getString("Organization");
                    String phoneNumber = res.getString("PhoneNumber");

                    user = new UserDTO(username, email, password, firstName, lastName, picture, role, DOB, address, organization, phoneNumber);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return user;
    }

    public static UserDTO getUserUsernamePassword(String username_, String password_)
            throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM User_ WHERE Username = ? AND Password = ?";
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username_);
                pre.setString(2, password_);
                res = pre.executeQuery();
                while (res.next()) {
                    String username = res.getString("Username");
                    String email = res.getString("Email");
                    String password = res.getString("Password");
                    String firstName = res.getString("FirstName");
                    String lastName = res.getString("LastName");
                    String picture = res.getString("Picture");
                    String role = res.getString("Role");
                    Date DOB = res.getDate("DOB");
                    String address = res.getString("Address");
                    String organization = res.getString("Organization");
                    String phoneNumber = res.getString("PhoneNumber");

                    user = new UserDTO(username, email, password, firstName, lastName, picture, role, DOB, address, organization, phoneNumber);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return user;
    }

    public static List<UserDTO> getUsers(String username_)
            throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM User_ WHERE Username LIKE ?";
        List<UserDTO> users = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, "%" + username_ + "%");
                res = pre.executeQuery();
                while (res.next()) {
                    String username = res.getString("Username");
                    String email = res.getString("Email");
                    String password = res.getString("Password");
                    String firstName = res.getString("FirstName");
                    String lastName = res.getString("LastName");
                    String picture = res.getString("Picture");
                    String role = res.getString("Role");
                    Date DOB = res.getDate("DOB");
                    String address = res.getString("Address");
                    String organization = res.getString("Organization");
                    String phoneNumber = res.getString("PhoneNumber");
                    if (users == null) {
                        users = new ArrayList<>();
                    }
                    users.add(new UserDTO(username, email, password, firstName, lastName, picture, role, DOB, address, organization, phoneNumber));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return users;
    }

    public static boolean addUser(UserDTO user)
            throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO User_(Username, Email, Password, FirstName, LastName, Picture, Role, DOB, Address,Organization, PhoneNumber )"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, user.getUsername());
                pre.setString(2, user.getEmail());
                pre.setString(3, user.getPassword());
                pre.setString(4, user.getFirstName());
                pre.setString(5, user.getLastName());
                pre.setString(6, user.getPicture());
                pre.setString(7, user.getRole());
                pre.setDate(8, user.getDOB());
                pre.setString(9, user.getAddress());
                pre.setString(10, user.getOrganization());
                pre.setString(11, user.getPhoneNumber());
                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }

            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return flag;
    }

    public static boolean removeUser(String username) throws SQLException, ClassNotFoundException {
        String SQL = "DELETE User_ WHERE Username = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                int affectRow = pre.executeUpdate();
                if (affectRow > 0) {
                    flag = true;
                }
            }
        } finally {

            if (con != null) {
                con.close();
            }
        }
        if (pre != null) {
            pre.close();
        }
        return flag;
    }

    public static boolean updateUser(String username_, UserDTO updateUser) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE User_ SET Email = ?, Password = ?, firstName = ?, lastName = ?, "
                + "Picture = ?, Role = ?, DOB = ?, Address = ?, "
                + "Organization = ?, PhoneNumber = ? WHERE Username = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, updateUser.getEmail());
                pre.setString(2, updateUser.getPassword());
                pre.setString(3, updateUser.getFirstName());
                pre.setString(4, updateUser.getLastName());
                pre.setString(6, updateUser.getPicture());
                pre.setString(6, updateUser.getRole());
                pre.setDate(7, updateUser.getDOB());
                pre.setString(8, updateUser.getAddress());
                pre.setString(9, updateUser.getOrganization());
                pre.setString(10, updateUser.getPhoneNumber());
                pre.setString(11, updateUser.getUsername());

                int affectRow = pre.executeUpdate();
                if (affectRow > 0) {
                    flag = true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pre != null) {
                pre.close();
            }

        }
        return flag;
    }
}
