package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ʹ�õ�ǰ�����������ݿ�����
 * @author Administrator
 *
 */
public class DBUtil {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/infinite";
		username = "root";
		password = "123456";
		System.out.println(driver);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
	}
	
	/**
	 * ��ȡһ�����ݿ�����
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		//1��������
		Class.forName(driver);
		
		//2��ȡ����
		Connection conn 
			= DriverManager.getConnection(
				url,username,password
			);
		
		return conn;
	}
	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConnection();
		System.out.println("connected success!");
	}
}






