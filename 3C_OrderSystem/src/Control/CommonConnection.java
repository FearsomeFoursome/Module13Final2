/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * Class to handle establishing database connections
 */
package Control;

/**
 * This class includes methods to establish SQL and MySQL database connections.
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
	 * Retrieves the SQL connection.
	 * @return A pointer to the currently open SQL connection.
	 */
	public  static  java.sql.Connection getSQLConn()
	{
		return sqlConn;
	}
	
	/**
	 * Retrieves the MySQL connection.
	 * @return A pointer to the currently open MySQL connection.
	 */
	public static java.sql.Connection getMSQLConn()
	{
		return mysqlConn;
	}    

	/**
	 * Initializes the MySQL connection.
	 */
	public static void initialize_Connection_MYSQL()
	{

		try{
			mysqlConn = java.sql.DriverManager.getConnection(MYSQLconnectionUrl,MYSQLusername, MYSQLpassword);                
		} catch (java.sql.SQLException e){System.err.println(e); }
	}
	
	/**
	 * Initializes the SQL connection.
	 */
	public static void initialize_Connection_SQL() 
	{      

		try{
			sqlConn = java.sql.DriverManager.getConnection(SQLconnectionUrl,SQLusername, SQLpassword);
		} catch (java.sql.SQLException e){System.err.println(e); }
	}
}

