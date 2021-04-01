package jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DBUtilTest {

    public static void main(String[] args) {
        try{
            Properties prop = new Properties();
            prop.load(new FileInputStream("db.properties"));
            Connection conn = DBUtil.getConn();
            System.out.println(conn);
            conn.close();
            System.out.println("数据库连接关闭");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
