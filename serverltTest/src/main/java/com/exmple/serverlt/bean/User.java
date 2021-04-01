package com.exmple.serverlt.bean;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-22 14:26
 * @Description
 * @Since V1.0
 */

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-22 14:26
 * @Description
 * @Since V1.0
 */
public class User {

    public User(Date date){
        this.date = date;
        this.hobby = hobby;
    }

    private String name;

    private int id;

    private Date date;

    private Hobby hobby;

    private Map<String,String> score;

    private List<String> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Map<String, String> getScore() {
        return score;
    }

    public void setScore(Map<String, String> score) {
        this.score = score;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", hobby=" + hobby +
                ", score=" + score +
                ", cities=" + cities +
                '}';
    }
}
