package com.navinfo.core.controller;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.FileInfoService;
import com.navinfo.core.so.FileInfoSearch;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 文件上传下载的Controller
 * Created by luozhihui on 2018/2/25.
 */
@Controller
@RequestMapping("/file")
public class FileInfoController extends BaseController{
    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/fileInfos",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(FileInfoSearch search, PageObject pageObject){
        PageInfo pageInfo=this.fileInfoService.findFileInfoByPage(search,pageObject);
        return Msg.success().add("pageInfo", pageInfo);
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/fileUpload")
    @ResponseBody
    public Msg fileUpload(HttpServletRequest request, @RequestParam("attachFile") MultipartFile[] attachFile){
        SysUser currentUser=super.getCurrentUser();
        String path=request.getSession().getServletContext().getRealPath("/upload/file");
        try{
            if (attachFile != null){
                for (MultipartFile singleFile : attachFile){
                    if (!singleFile.isEmpty()){
                        this.fileInfoService.saveFileInfo(path,singleFile,currentUser);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return Msg.failure();
        }
        return Msg.success();
    }

    /**
     * 文件删除
     * @return
     */
    @RequestMapping(value = "/fileDelete")
    @ResponseBody
    public Msg fileDelete(@RequestParam("fileId") String fileId){
        boolean flag = this.fileInfoService.deleteFileInfo(fileId);
        return flag ? Msg.success() : Msg.failure();
    }

    /**
     * 文件的下载
     * @param request
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/fileDownload")
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("fileId") String fileName){
        String path=request.getSession().getServletContext().getRealPath("/upload/file");
        File file=new File(path+File.separator+fileName);
        HttpHeaders headers=null;
        byte[] outData=null;
        try {
            headers=new HttpHeaders();
            //下载显示的文件名，解决中文名称乱码问题
            String downloadFileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
            //通知浏览器以attachment（下载方式）打开文件
            headers.setContentDispositionFormData("attachment",downloadFileName);
            //application/octet-stream:二进制流数据(最常见的文件下载)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            outData=FileUtils.readFileToByteArray(file);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(outData,headers, HttpStatus.CREATED);
    }
}
