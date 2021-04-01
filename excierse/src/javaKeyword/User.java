package javaKeyword;

import java.io.*;

/**
 * @autor ws
 * @description
 * @date 2021/3/17 10:40
 **/
public class User implements Serializable {

    private static final long serialVersionUID = -6267992724411320636L;

    private String username;

    private transient String passsword;


    public User(){}

    public User(String username,String passsword){
        this.username = username;
        this.passsword = passsword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

}
