package reflect;

import anotation.MyApplication;

/**
 * @Author: wushuai
 * @Date: $date $time
 * @Description:
 */
@MyApplication()
public class ResQuota {

    public ResQuota(){

    }
    private float a = 1;

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }
}
