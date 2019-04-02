package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
  private static Connection con ;
  /*
  定义静态方法返回数据库连接对象
   */
  public static Connection getConnection(){
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/mybase";
      String username = "root";
      String password = "123456";
      con = DriverManager.getConnection(url, username, password);
    }catch (Exception ex){
      throw new RuntimeException(ex+"数据库连接失败");
    }
    return con;
  }

  public static Connection getConnectionByProperties() {
    InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties");
    Properties pro = new Properties();
    try{
    pro.load(in);
    String driverclass = pro.getProperty("driverclass");
    String url = pro.getProperty("url");
    String username = pro.getProperty("username");
    String password = pro.getProperty("password");

      Class.forName(driverclass);
      con = DriverManager.getConnection(url, username, password);
    }catch (Exception ex){
      throw new RuntimeException(ex+"数据库连接失败");
    }finally {
      return con;
    }

  }
  public static void close(Connection con, Statement stat, ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException ex) {

      }
    }
    if (stat != null) {
      try {
        stat.close();
      } catch (SQLException ex) {

      }
      if (con != null) {
        try {
          con.close();
        } catch (SQLException ex) {

        }
      }
    }
  }

  public static void close(Connection con,Statement stat){

    if(stat!=null){
      try{
        stat.close();
      }catch(SQLException ex){}
    }

    if(con!=null){
      try{
        con.close();
      }catch(SQLException ex){}
    }
  }
}
