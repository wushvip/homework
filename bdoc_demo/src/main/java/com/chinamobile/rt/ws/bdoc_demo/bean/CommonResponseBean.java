package com.chinamobile.rt.ws.bdoc_demo.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 
 * @author xuhaihui
 * @date 2016年5月9日
 * @since BC-BDOC V2.1.0
 * 
 */
@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "运行时异常")
public class CommonResponseBean {
  
  /*操作对象的id*/
  @ApiModelProperty(value="一般返回创建或删除成功的id")
  private int id;
  
  /* 命令执行的状态：failed：失败，running：运行中，successed：完成*/
  @ApiModelProperty(value = "failed：失败，running：运行中，successed：完成")
  private String status;
  
  /*错误码*/
  @ApiModelProperty(value = "错误码")
  private String errorCode;
  
  /*错误描述*/
  @ApiModelProperty(value = "错误描述")
  private String errorDescription;
  
  /*执行进度，配合running状态*/
  @ApiModelProperty(value = "执行进度，配合running状态")
  private float totalProgress;
  
  public CommonResponseBean() {
    
  }
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public float getTotalProgress() {
    return totalProgress;
  }

  public void setTotalProgress(float totalProgress) {
    this.totalProgress = totalProgress;
  }
  
  
}
