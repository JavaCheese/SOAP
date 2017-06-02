package example;

import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Created by range on 6/2/17.
 */
public class Connect {
    public static int checkUser(String user, String pass) {
        String url = "jdbc:postgresql://93.125.57.7:5432/shop";
        String username = "range";
        String password = "root";

        int id = -1;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            System.out.println(pass);
            while (resultSet.next()) {

                int id1 = resultSet.getInt("id");
                String login = resultSet.getString("username");
                String passwd = resultSet.getString("password");

                System.out.println("id" + id1);
                System.out.println("username : " + login);
                System.out.println("password : " + passwd);

                if (login.equals(user) && passwd.equals(getHash(pass))) {
                    return id1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static String getHash(String str) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (
                int i = 0;
                i < byteData.length; i++)

            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        // возвращаем MD5-хеш
        return sb.toString();

    }
}
