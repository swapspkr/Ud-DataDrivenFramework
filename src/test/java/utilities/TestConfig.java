package utilities;
public class TestConfig{


	
	public static String server="smtp.gmail.com";
	public static String from = "corporate@way2automation.com";
	public static String password = "Selenium@123";
	public static String[] to ={"swapspkr@gmail.com"};
	public static String subject = "Test Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath="f:\\screenshot\\error.jpg";
	public static String attachmentName="error.jpg";
	
	
	
	//SQL DATABASE DETAILS	
	public static String driver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	//MYSQL DATABASE DETAILS
	/*
	 * public static String mysqldriver="com.mysql.cj.jdbc.Driver"; public static
	 * String mysqluserName = "xps"; public static String mysqlpassword = "xps@123";
	 * public static String mysqlurl = "jdbc:mysql://localhost:3306/userdb";
	 */
	public static String mysqldriver="com.mysql.cj.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/18thaug2018";
	
	
	
	
	
	
	
	
	
}
