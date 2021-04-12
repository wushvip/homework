package com.chinamobile.rt.ws.bdoc_demo.resource;/**
 * @Title
 * @Author Administrator
 * @Date 2021-04-06 16:28
 * @Description
 * @Since V1.0
 */

import com.chinamobile.rt.ws.bdoc_demo.annotation.DataForm;
import com.chinamobile.rt.ws.bdoc_demo.bean.ClusterBean;
import io.swagger.annotations.*;
import javafx.scene.media.Media;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    @PostMapping(value = "/keytab/upload",
            consumes = {"multipart/form-data"},produces = {"application/json"} )
    @ApiOperation(value = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keytab",value = "keytab文件", dataType = "file", paramType = "form"),
            @ApiImplicitParam(name = "krb5",value = "krb5文件", dataType = "file", paramType = "form")
    })
    public String upLoadFile(
            @ApiParam(name = "request",value = "当前servletrequest",required = true) HttpServletRequest request,
            @ApiParam(name = "clusterBean",value = "clusterBean",required = true,allowMultiple = true)ClusterBean clusterBean
            ){

        System.out.println("clusterBean info :" + clusterBean.toString());

        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = params.getFileMap();
//        if(null==files || files.isEmpty()){
//            files = ((MultipartHttpServletRequest) request).getFiles("file");
//
//        }
        String filepath = "D:\\upload\\keytab\\";
        Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
        for (Map.Entry fileEntry:entries ){
            MultipartFile file = (MultipartFile) fileEntry.getValue();

            String originalFilename = file.getOriginalFilename();
            System.out.println("uploading file name : " + originalFilename);
            File destPath = new File(filepath + originalFilename);
            try {
                file.transferTo(destPath);
//                destPath.delete();
                LOGGER.info("upload " + originalFilename + " file succesed");
            } catch (IOException e) {
                e.printStackTrace();
                return "upload " + originalFilename + " file failed!";
            }
        }
       return "success!";
    }


    /**
    * @Titile create
    * @Author Administrator
    * @Date 2021/4/7 19:23
    * @Description TODO
    * @Param [files, userName, password]
    * @Return java.lang.String
    * @Since V1.0
    */
    @PostMapping(value = "/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiOperation(value = "createCluster")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keytab",value = "keytab文件", dataType = "file", paramType = "form"),
            @ApiImplicitParam(name = "krb5",value = "krb5文件", dataType = "file", paramType = "form")
    })
    public String create(
            @ApiParam(name = "request",value = "当前servletrequest",required = true) HttpServletRequest request,
            @ApiParam(name = "userName",value = "userName")@RequestParam String userName,
            @ApiParam(name = "password",value = "password")@RequestParam String password){
        System.out.println("userName: " + userName + "password: " + password);

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        if(files !=null && !files.isEmpty()){
            files.forEach((item)->{
                System.out.println(item.getOriginalFilename());
            });
        }
        return "server receive sucess!";
    }

    /***
    * @Titile update
    * @Author Administrator
    * @Date 2021/4/9 9:43
    * @Description 集群修改
    * @Param [clusterBean] [request]
    * @Return java.lang.String
    * @Since V1.0
    */

    @PostMapping(value = "/update",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "updateCluster")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keytab",value = "keytab文件", dataType = "file", paramType = "form"),
            @ApiImplicitParam(name = "krb5",value = "krb5文件", dataType = "file", paramType = "form")
    })
    public String update(
            @ApiParam(name = "request",value = "当前servletrequest",required = true) HttpServletRequest request,
            @ApiParam(name = "clusterBean",value = "集群配置",required = true) ClusterBean clusterBean){






        return "server receive sucess!";
    }


}
