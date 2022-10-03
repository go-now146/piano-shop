/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanntk.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBUtils implements Serializable{
       public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SE150874_SU22";
        Connection conn =  DriverManager.getConnection(url, "sa", "12345");
        return conn;
//       public static Connection makeConnection() throws 
//               SQLException,NamingException {
//        Context currentContext= new InitialContext();
//        Context tomcatContext=(Context) currentContext.lookup("java:comp/env");
//        DataSource dataSource =(DataSource) tomcatContext.lookup("Web10w");
//        Connection conn =dataSource.getConnection();
//       return conn;
//    }
    
}
}
