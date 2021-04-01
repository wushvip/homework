package quote.subclass;

/**
*@author wushuai
*@date 20:36 2019/3/21 
*@description 
*@return  
**/ 
public class Teacher {
    private int id;

    private String name;

    /**
     * 
     * @param id
     * @param name
     */
    public Teacher(int id,String name){
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
}
