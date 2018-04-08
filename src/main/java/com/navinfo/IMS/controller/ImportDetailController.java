package com.navinfo.IMS.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.navinfo.IMS.dto.AccessDB;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.service.ImportDetailService;
import net.sourceforge.jdbcutils.importer.AccessImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        for (File file : childrenFile){
            if (file.exists() && file.isFile()){
                String fileName = file.getName();
                String fileExname = fileName.substring(fileName.indexOf(".")+1);
                if ("accdb;mdb".indexOf(fileExname) !=-1){
                    accessDBList.add(this.importDetailService.getAccessFile(file));
                }
            }
        }
        return Msg.success().add("info",accessDBList);
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
            String[] generatorColumns={};//指定自动生成的键
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
            String logPath = basePath+File.separator+fileRealName+"logs"+File.separator;
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
}