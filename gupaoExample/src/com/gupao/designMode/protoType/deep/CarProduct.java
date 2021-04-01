package com.gupao.designMode.protoType.deep;

import java.io.Serializable;

public class CarProduct implements Serializable {

    public String address = "a";

    public String productName = "b";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
