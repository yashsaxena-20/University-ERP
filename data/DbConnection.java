package edu.univ.erp.data;
import java.sql.*;
public class DbConnection
{
    static String PASS="12345678";
    public static Connection getAuth() throws Exception
    {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/auth_db?serverTimezone=UTC","root",PASS);
    }
    public static Connection getErp() throws Exception
    {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/erp_db?serverTimezone=UTC","root",PASS);
    }
}