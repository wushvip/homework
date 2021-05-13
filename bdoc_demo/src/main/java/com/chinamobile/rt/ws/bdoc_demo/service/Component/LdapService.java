package com.chinamobile.rt.ws.bdoc_demo.service.Component;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-13 20:00
 * @Description
 * @Since V1.0
 */
public interface LdapService extends Component {

    public boolean createUserAndGroup(String user,String groupBean);

    public boolean createUser();

    public void getByUserName(String userName);

    public void updateUser();

    public boolean deleteUser(String userName);


}
