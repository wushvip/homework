package com.chinamobile.rt.ws.bdoc_demo.resource;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 16:28
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.bean.ClusterBean;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 16:28
 * @Description
 * @Since V1.0
 */
@RestController
@RequestMapping(value = "/cluster")
@ApiModel(value = "ClusterResource",description = "集群操作")
public class ClusterResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterBean.class);

    @PostMapping(value = "/keytab/upload",consumes = {"application/json", "multipart/form-data"})
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keytab",value = "keytab文件",required = true,dataType = "file",paramType = "form"),
            @ApiImplicitParam(name = "krb5",value = "krb5文件",required = true,dataType = "file",paramType = "form")
    })
    public String upLoadFile(
            @ApiParam(name = "request",value = "当前servletrequest",required = true) HttpServletRequest request,
            @ApiParam(name = "clusterBean",value = "集群配置信息",required = true) ClusterBean clusterBean){

        System.out.println("clusterBean info :" + clusterBean.toString());

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//        if(null==files || files.isEmpty()){
//            files = ((MultipartHttpServletRequest) request).getFiles("file");
//
//        }
        String filepath = "D:\\upload\\keytab";
        for (MultipartFile file: files){
            String originalFilename = file.getOriginalFilename();
            System.out.println("uploading file name : " + originalFilename);
            File destPath = new File(filepath + originalFilename);
            try {
                file.transferTo(destPath);
                LOGGER.info("upload " + originalFilename + " file succesed");
            } catch (IOException e) {
                e.printStackTrace();
                return "upload " + originalFilename + " file failed!";
            }
        }
       return "success!";
    }

}
