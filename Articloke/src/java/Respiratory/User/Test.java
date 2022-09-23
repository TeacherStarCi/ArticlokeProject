
package Respiratory.User;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Date date=new Date(2000, 11, 1);
        UserDTO usernew= new UserDTO("aaa", "a123@gmail.com", "", "nguyen van", "a", "", "User", date, "", "", "");
        //UserDAO.addUser(usernew);
        System.out.println(UserDAO.getUsers("").size());
        //System.out.println(UserDAO.updateUser("aaa", usernew));
        //System.out.println(UserDAO.removeUser("aaa"));     
    }
}
