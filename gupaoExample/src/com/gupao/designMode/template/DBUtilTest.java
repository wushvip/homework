package com.gupao.designMode.template;

import java.sql.Connection;

public class DBUtilTest {

    public static void main(String[] args) {
        try{
        Connection conn = DBUtil.getConn();
        System.out.println(conn);
        conn.close();
        System.out.println("数据库连接关闭");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
