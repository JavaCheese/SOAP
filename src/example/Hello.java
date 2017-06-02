package example;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.sql.*;

/**
 * Created by BAS on 26.05.17.
 */
@WebService()
public class Hello {

  @WebMethod
  public int returnClientID(String user, String pass) {
      System.out.println("User " + user);
      System.out.println("Password " + pass);
    return Connect.checkUser(user, pass);
  }
  public static void main(String[] argv) {

//      System.out.println(String.valueOf(Connect.checkUser("max", "root")));
    Object implementor = new Hello();
    String address = "http://192.168.100.3:8081/Hello";
    Endpoint.publish(address, implementor);

  }
}

