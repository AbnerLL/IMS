package com.navinfo.core.controller;

import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.service.FileInfoService;
import com.navinfo.core.so.FileInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传下载的Controller
 * Created by luozhihui on 2018/2/25.
 */
@RequestMapping("/file")
@Controller
public class FileInfoController {
    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "fileInfos",method = RequestMethod.GET)
    @ResponseBody
    public Msg search(FileInfoSearch search, PageObject pageObject){
        return null;
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/fileUpload")
    @ResponseBody
    public Msg fileUpload(HttpServletRequest request){
        System.out.print(request);
        return null;
    }

    /**
     * 文件删除
     * @return
     */
    @RequestMapping(value = "fileDelete")
    @ResponseBody
    public Msg fileDelete(HttpServletRequest request){
        System.out.print(request);
        return null;
    }
}
