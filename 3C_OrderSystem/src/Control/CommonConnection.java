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
    
    /**
     * 
     * @return 
     */
    public  static  java.sql.Connection getSQLConn()
    {
        return dbCon;
    }

    public static java.sql.Connection getMSQLConn()
    {
        return mysqlConn;
    }    
 
    /**
     * @return - not used, commented out
     */
    public static void initialize_Connection_MYSQL()
    {
            
            try{
                mysqlConn = java.sql.DriverManager.getConnection(MYSQLconnectionUrl,MYSQLusername, MYSQLpassword);                
            } catch (java.sql.SQLException e){System.err.println(e); }

           // return mysqlConn;
        }

    /**
     * @return - not used yet, commented out
     */
    public static void initialize_Connection_SQL() 
    {      
            
            try{
                sqlConn = java.sql.DriverManager.getConnection(SQLconnectionUrl,SQLusername, SQLpassword);
            } catch (java.sql.SQLException e){System.err.println(e); }

            
          //  return sqlConn;
        }
    }
    
