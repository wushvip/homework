package com.gupao.designMode.protoType.deep;

import java.io.Serializable;
import java.util.Date;

public class Car implements Serializable,Cloneable {

    private int lunzi;

    private String engin;

    private int seat;

    public Date productDate;

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getEngin() {
        return engin;
    }

    public void setEngin(String engin) {
        this.engin = engin;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getLunzi() {
        return lunzi;
    }

    public void setLunzi(int lunzi) {
        this.lunzi = lunzi;
    }
}
