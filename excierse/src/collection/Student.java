package collection;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
public class Student {
    private int id;

    private String name;

    public Student(int id,String name){
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.getId()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Student u = (Student) obj;
        return u.getId() == this.getId();
    }
}
