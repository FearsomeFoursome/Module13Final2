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

    private static java.sql.Connection dbCon = null;
    private static String jdbcDriver;
    private static String connectionUrl;
    private static String username;
    private static String password;
    boolean sql_flag;
    
    
    public CommonConnection(boolean sql_flag_a)
    {
        sql_flag = sql_flag_a;
        
        if(sql_flag == true)
        {   //SQL
            jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            connectionUrl = "jdbc:sqlserver://localhost";
            username = "sa";
            password = "password";
        }
        else
        {   //MYSQL
            jdbcDriver = "com.mysql.jdbc.Driver";
            connectionUrl = "jdbc:mysql://oak.safesecureweb.com:3306/nianbrandsco?zeroDateTimeBehavior=convertToNull";
            username = "store";
            password = "testDB1234!";
        }    
        initialize();    
    }
    
    /**
     *
     */
    public static void initialize()
    {
        try{
            Class.forName(jdbcDriver);
            
            try{
                dbCon = java.sql.DriverManager.getConnection(connectionUrl,username, password);
            } catch (java.sql.SQLException e){System.err.println(e); }
        }catch(ClassNotFoundException e){
            System.err.println(e);
        }
    }
    public java.sql.Connection getConnection()
    {
        return dbCon;
    }
}
