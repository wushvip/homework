package com.chinamobile.rt.ws.bdoc_demo.resource;/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:40
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.AbstractFood;
import com.chinamobile.rt.ws.bdoc_demo.service.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-31 10:40
 * @Description
 * @Since V1.0
 */
@RestController
@RequestMapping("/dog")
public class DogResource {

    @Autowired
    private Animal animal;

    @RequestMapping(value = "/food")
    public String getFood(String animalType){
        AbstractFood food = animal.eat();
        return animalType + "foodName: " + food.getFoodName() +" price：" + food.getUnitPrice();
    }
}
