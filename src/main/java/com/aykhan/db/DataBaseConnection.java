package com.aykhan.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
  //remote
  private final static String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/nczsfxop";
  private final static String username = "nczsfxop";
  private final static String password = "H9PsJYGyOKuk5Xs32h3XnZE6BrO_v7Bc";
  // local
//  private final static String url = "jdbc:postgresql://localhost:5432/tinder";
//  private final static String username = "postgres";
//  private final static String password = "ayxan123";


  private static Connection conn;

  public static Connection getConnection() {
    if (conn != null) return conn;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      return conn = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }
}
