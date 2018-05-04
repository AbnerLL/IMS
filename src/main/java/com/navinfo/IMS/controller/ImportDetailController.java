package com.navinfo.IMS.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.navinfo.IMS.dto.AccessDB;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.ImportDetailService;
import net.sourceforge.jdbcutils.importer.AccessImporter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 数据导入的Controller
 * Created by luozhihui on 2018/3/28.
 */
@RestController
@RequestMapping("/import")
public class ImportDetailController {
    @Autowired
    private ImportDetailService importDetailService;
    /**
     * 获取导入的文件信息
     * @return
     */
    @RequestMapping(value = "/importFileDetail")
    public Msg getImportFileDetail(HttpServletRequest request){
        String basePath = request.getSession().getServletContext().getRealPath("/upload/importFile");
        File baseDir = new File(basePath);
        if (!baseDir.exists()){
            return Msg.success().add("info","file does not exists");
        }
        File[] childrenFile = baseDir.listFiles();
        List<AccessDB> accessDBList = new ArrayList<AccessDB>();
        //用来存储已导文件
        Map<String,String> logMap = new HashMap<String, String>();
        //用来存储文件的下载路径
        Map<String,String> downloadMap = new HashMap<String, String>();
        for (File file : childrenFile){
            if (file.exists() && file.isFile()){
                String fileName = file.getName();
                String downloadUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/importFile/"+fileName;
                downloadMap.put(fileName, downloadUrl);
                String fileExname = fileName.substring(fileName.indexOf(".")+1);
                if ("accdb;mdb".indexOf(fileExname) !=-1){
                    accessDBList.add(this.importDetailService.getAccessFile(file));
                }
            }
            //获取已经导入的文件的日志信息
            if (file.exists() && file.isDirectory()){
                for (File itemFile : file.listFiles()){
                    if ("log.log".equals(itemFile.getName())){
                        String downUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/importFile";
                        logMap.put(file.getName(),downUrl+"/"+file.getName()+"/"+itemFile.getName());
                        break;
                    }
                }
            }
        }
        return Msg.success().add("info",accessDBList).add("logInfo",logMap).add("downloadInfo",downloadMap);
    }

    /**
     * 获取数据库映射表中可供导入的表名以及详情
     * @return
     */
    @RequestMapping("/importOracleDetail")
    public Msg getOracleDetail(){
        JSONObject jsonObject = this.importDetailService.getImportOracleDetail();
        return Msg.success().add("info",jsonObject);
    }

    /**
     * 导入Access数据
     * @return
     */
    @RequestMapping("/importAccessData")
    public Msg importAccessData(HttpServletRequest request,String fileName,String sheetName,String orclTableName,String[] accessField,String[] orclField,String primaryKey){
        String basePath = request.getSession().getServletContext().getRealPath("/upload/importFile");
        try {
            File baseDir = new File(basePath);
            if (!baseDir.exists()){
                return Msg.failure().add("info","dir does not exists");
            }
            List fileList = Arrays.asList(baseDir.list());
            if (!fileList.contains(fileName)){
                return Msg.failure().add("info","importFile does not exists");
            }
            String[] generatorColumns=new String[1];//指定自动生成的键
            if (!StringUtils.isEmpty(primaryKey)){
                generatorColumns[0] = primaryKey;
            }
            String[] databaseDefaultColumns={};
            String[] databaseDefaultColumnValues={};
            AccessImporter accessImporter = new AccessImporter();
            accessImporter.setFileName(basePath+File.separator+fileName);
            accessImporter.setAccessTable(sheetName);
            accessImporter.setAccessColumns(accessField);
            accessImporter.setDatabaseTable(orclTableName);
            accessImporter.setDatabaseColumns(orclField);
            //设置自动生成的字段
            accessImporter.setGeneratorColumns(generatorColumns);
            //设置默认字段
            accessImporter.setDatabaseDefaultColumns(databaseDefaultColumns);
            //设置默认字段的值
            accessImporter.setDatabaseDefaultColumnValues(databaseDefaultColumnValues);
            //设置日志路径
            String fileRealName = fileName.substring(0,fileName.indexOf("."));
            //已原文件名为日志目录名
            String logPath = basePath+File.separator+fileRealName+File.separator;
            File logDir = new File(logPath);
            if (!logDir.exists()){
                logDir.mkdirs();
            }
            //用于存放导入生成的配置文件
            accessImporter.setLogUrl(logPath);
            //用于存放导入过程中失败原因的log文件
            accessImporter.setBadFileName(logPath+"bad.log");
            //用于存放导入过程中的log
            accessImporter.setLogFileName(logPath+"log.log");
            //开始导入
            accessImporter.beginImport();
        }catch (Exception e){
            e.printStackTrace();
            return Msg.failure().add("info","an error occurs");
        }
        return Msg.success();
    }

    /**
     * 根据文件名删除导入文件及其日志
     * @param request
     * @param fileName
     * @return
     */
    @RequestMapping("/deleteImportFile")
    public Msg deleteFile(HttpServletRequest request , String fileName){
        try{
            String realPath = request.getSession().getServletContext().getRealPath("/upload/importFile");
            //删除日文件
            File importFile = new File(realPath+File.separator+ fileName+".accdb");
            if (importFile.exists() && importFile.isFile()){
                importFile.delete();
            }
            //删除日志
            File importLog = new File(realPath+File.separator+ fileName);
            if (importLog.exists() && importLog.isDirectory()){
                FileUtils.deleteDirectory(importFile);
            }
            return Msg.success();
        }catch (Exception e){
            e.printStackTrace();
            return Msg.failure();
        }
    }

    /**
     * 上传导入文件
     * @param request
     * @param multipartFiles
     * @return
     */
    @RequestMapping("/uploadImportFile")
    public Msg uploadImportFile(HttpServletRequest request , @RequestParam("attachFile") MultipartFile[] multipartFiles){
        String basePath = request.getSession().getServletContext().getRealPath("/upload/importFile");
        File targetDir  = new File(basePath);
        List<String> originalFileName = Arrays.asList(targetDir.list());
        for (MultipartFile multipartFile : multipartFiles){
            //带扩展名的文件名
            String newFileName = multipartFile.getOriginalFilename();
            //不带扩展名的文件名
            String newFileNameSub = newFileName.substring(0,newFileName.indexOf("."));
            //删除同名的文件
            if (originalFileName.contains(newFileName)){
                new File (basePath+File.separator+newFileName);
            }
            //先删除文件夹中同名的文件夹（即log文件夹）
            if (originalFileName.contains(newFileNameSub)){
                try {
                    FileUtils.deleteDirectory(new File (basePath+File.separator+newFileNameSub));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //保存新文件
            try {
                if (!multipartFile.isEmpty()){
                    multipartFile.transferTo(new File(targetDir+File.separator+newFileName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Msg.success();
    }
}