/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author Bella Belova
 */
public class CommonConnection {
    
    private static final String MYSQLjdbcDriver = "com.mysql.jdbc.Driver";
    private static final String MYSQLconnectionUrl = "jdbc:mysql://oak.safesecureweb.com:3306/nianbrandsco?zeroDateTimeBehavior=convertToNull";
    private static final String MYSQLusername = "store";
    private static final String MYSQLpassword = "testDB1234!";
    private static java.sql.Connection mysqlConn;
    
    private static java.sql.Connection sqlConn = null;
    private static final String SQLjdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    private static final String SQLconnectionUrl = "jdbc:sqlserver://localhost";
    private static final String SQLusername = "sa";
    private static final String SQLpassword = "password";

    private static java.sql.Connection dbCon = null;
    
    
    
    public static void initialize_Connection_MYSQL()
    {
        try{
            Class.forName(MYSQLjdbcDriver);
            
            try{
                dbCon = java.sql.DriverManager.getConnection(MYSQLconnectionUrl,MYSQLusername, MYSQLpassword);                
            } catch (java.sql.SQLException e){System.err.println(e); }
        }catch(ClassNotFoundException e){
            System.err.println(e);
        }
    }
    
    public static void initialize_Connection_SQL() 
    {      
        try{
            Class.forName(SQLjdbcDriver);
            
            try{
                dbCon = java.sql.DriverManager.getConnection(SQLconnectionUrl,SQLusername, SQLpassword);
            } catch (java.sql.SQLException e){System.err.println(e); }
        }catch(ClassNotFoundException e){
            System.err.println(e);
        }
    }

    
    

    
    public  static  java.sql.Connection getSQLConn()
    {
        return sqlConn;
    }

    public static java.sql.Connection getMSQLConn()
    {
        return mysqlConn;
    }

    
}
