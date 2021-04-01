/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-22 15:23
 * @Description
 * @Since V1.0
 */

import com.exmple.serverlt.bean.Foo;
import com.exmple.serverlt.bean.Hobby;
import com.exmple.serverlt.bean.ShoesFactory;
import com.exmple.serverlt.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-03-22 15:23
 * @Description
 * @Since V1.0
 */

public class ApplicationTest {

    @Test
    public void gernerateBean(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User bean = (User) applicationContext.getBean("user1");
        System.out.println(bean.toString());
    }
    @Test
    public void testCustemMethod(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        Foo bean = ac.getBean(Foo.class);
        System.out.println(bean);


        System.out.println("test sucesss!");
    }


    @Test
    public void testStaticFactory(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ShoesFactory bean = ac.getBean(ShoesFactory.class);
        System.out.println(bean);
    }


}
