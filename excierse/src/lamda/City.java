package lamda;

import java.io.*;
import java.math.BigDecimal;

/**
 * @Author: wushuai
 * @Date: 2019/8/1 11:02
 * @Description:
 */
public class City implements Cloneable,Serializable{

    private int id;

    private long personNum;

    private BigDecimal GDP;

    public City(int id,long personNum,BigDecimal GDP){
        this.id = id;
        this.personNum = personNum;
        this.GDP = GDP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPersonNum() {
        return personNum;
    }

    public void setPersonNum(long personNum) {
        this.personNum = personNum;
    }

    public BigDecimal getGDP() {
        return GDP;
    }

    public void setGDP(BigDecimal GDP) {
        this.GDP = GDP;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    public Object deepClone() {
        try {
            // 写入字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            //读出字节流
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
