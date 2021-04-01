package com.chinamobile.cmss.bcse.user.bean;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.ws.rs.PathParam;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.chinamobile.cmss.bcse.tool.config.Config;
import com.chinamobile.cmss.bcse.tool.config.ParamVerifyConf;
import com.chinamobile.cmss.bcse.validate.annotation.InInclude;
import com.chinamobile.cmss.bcse.validate.annotation.Paging;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupA;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupB;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupC;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupD;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupE;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupF;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupG;
import com.chinamobile.cmss.bcse.validate.annotation.VerifyBean.GroupH;

/**
 * 
 * @ClassName: UserBean 
 * @Description: 用户bean
 * @author: lijingjing
 * @date: 2017年3月7日 下午4:39:39
 */
@Paging(pageIndex="pageIndex",pageNum="pageNum")
public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@GroupSequence({GroupB.class,GroupC.class})
	public interface loginGroup{
		
	}
	
	@GroupSequence({GroupA.class})
	public interface updateGroup{
		
	}
	@GroupSequence({GroupD.class,GroupE.class})
	public interface addGroup{
		
	}
	
	@GroupSequence({GroupA.class,GroupC.class,GroupH.class})
	public interface upPwdGroup{
		
	}
	
	
	@NotEmpty(message="userId"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupA.class)
    private String userId;
	//被操作对象
	@NotEmpty(message="id"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupG.class)
	private String id;
	@PathParam("userName")
	@NotEmpty(message="用户名"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupB.class)
    private String userName;
	private String oaUserName;
	@PathParam("password")
	@NotEmpty(message="密码"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupC.class)
    private String password;
	@NotEmpty(message="新密码"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupH.class)
    private String updatePassword;
	@Email(message="请输入合法邮箱",groups=GroupD.class)
    private String userMail;
	@NotEmpty(message="角色"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupF.class)
	@InInclude(rangeValue={Config.ROLE_ADMIN,Config.ROLE_COMMON},message="role"+ParamVerifyConf.PARAMS_NOT_IN_INCLUDE,groups=GroupF.class)
    private String role=Config.ROLE_COMMON;
    @NotEmpty(message="用户状态"+ParamVerifyConf.PARAMS_NOTEMPTY,groups=GroupE.class)
    @InInclude(rangeValue={"0","1"},message="userStatus"+ParamVerifyConf.PARAMS_NOT_IN_INCLUDE,groups=GroupE.class)
    private String userStatus;
    
    private String userTel;
    
    private String searchName;
    
    private int pageIndex = 1;
    private int pageNum = 10;
    private int startNum = 0;
    private String secretKey;
    
    
   



	public String getOaUserName() {
		return oaUserName;
	}

	public void setOaUserName(String oaUserName) {
		this.oaUserName = oaUserName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public String getUpdatePassword() {
		return updatePassword;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }
}