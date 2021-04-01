package jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 *JDBCTemplate
 */
public class DBUtil {
    private static  String driver = "";
    private static String url = "";
    private static String userName = "";
    private static String passWord = "";

    private static Connection  conn = null;
    //第一步：加载静态资源
    static{
        try{
            Properties property = new Properties();
//            this.getClass().getResourceAsStream("classpath:db.properties");
            property.load(new FileInputStream("db.properties"));
            driver = property.getProperty("DRIVER");
            url = property.getProperty("URL");
            userName = property.getProperty("USERNAME");
            passWord = property.getProperty("PASSWORD");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //第二步：创建数据库连接，打开连接
    public static Connection getConn() throws ClassNotFoundException,SQLException {
        //1.加载数据库驱动
        Class.forName(driver);
        //2.获取连接
        conn = DriverManager.getConnection(url,userName,passWord);
        return conn;
    }

    //第三步：创建欲编辑sql语句
    //第四步：执行操作
    public static ResultSet excuteSql(String sql) throws SQLException {
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        ResultSet result = preparedStatement.execute();
        Statement statement = conn.createStatement();
        return statement.executeQuery(sql);
    }

    //第五步：关闭连接
    public static void closeConn() throws SQLException {
        if(conn !=null){
            conn.close();
        }

    }

}
