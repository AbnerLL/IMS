package com.navinfo.IMS.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.navinfo.IMS.dto.AccessColumn;
import com.navinfo.IMS.dto.AccessDB;
import com.navinfo.IMS.dto.AccessTable;
import com.navinfo.IMS.service.ImportDetailService;
import com.navinfo.core.dao.DBFieldManageMapper;
import com.navinfo.core.entity.DBFieldManage;
import com.navinfo.core.service.DBFieldManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by luozhihui on 2018/3/28.
 */
@Service("importDetailService")
public class ImportDetailServiceImpl implements ImportDetailService {
    @Autowired
    private DBFieldManageMapper dbFieldManageMapper;
    /**
     * 生成accessFile文件
     * @param file
     * @return
     */
    public AccessDB getAccessFile(File file) {
        if (!file.exists() || !file.isFile()){
            return null;
        }
        AccessDB accessDB = new AccessDB();
        accessDB.setDbName(file.getName());
        Database db = null;
        AccessTable accessTable ;
        List<AccessTable> accessTableList ;
        AccessColumn accessColumn ;
        List<AccessColumn> accessColumnList;
        try{
            //创建Access的数据库
            db = Database.open(file);
            //获取Access数据库中所有表的表名
            Set<String> tableNames = db.getTableNames();
            //创建表集合
            accessTableList = new ArrayList<AccessTable>();
            //遍历每一个表
            for (String tableName : tableNames){
                accessTable = new AccessTable();
                accessTable.setTableName(tableName);
                //创建列集合
                accessColumnList = new ArrayList<AccessColumn>();
                //获取表中的每列
                List<Column> columnList = db.getTable(tableName).getColumns();
                //遍历每列并取列名
                for (Column column : columnList){
                    accessColumn = new AccessColumn();
                    accessColumn.setColumnName(column.getName());
                    accessColumnList.add(accessColumn);
                }
                accessTable.setAccessColumnList(accessColumnList);
                accessTableList.add(accessTable);
            }
            accessDB.setAccessTableList(accessTableList);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null){
                try {
                    db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return accessDB;
    }

    /**
     * 从数据库映射表中获取可导入的表名及字段
     * @return
     */
    public JSONObject getImportOracleDetail() {
        JSONObject json = new JSONObject();
        List<DBFieldManage> dbFieldManageList = this.dbFieldManageMapper.selectByExample(null);
        if (dbFieldManageList ==null || dbFieldManageList.size()==0){
            return json;
        }
        Map<String,Map<String,String>> DBMap = new HashMap<String,Map<String,String>>();
        Map<String,String> descriptMap = new HashMap<String, String>();//用来描述页面的表名下拉选
        Map<String ,String> primaryKeyMap= new HashMap<String, String>();//表名对应的主键字段
        for (DBFieldManage dbFieldManage : dbFieldManageList){
            if (!DBMap.containsKey(dbFieldManage.getTableName())){
                DBMap.put(dbFieldManage.getTableName(),new HashMap<String, String>());
                descriptMap.put(dbFieldManage.getTableName(),dbFieldManage.getTableDescript());
                //默认不设置主键字段
                primaryKeyMap.put(dbFieldManage.getTableName(),"");
            }
        }
        for (DBFieldManage dbFieldManage : dbFieldManageList){
            if (DBMap.containsKey(dbFieldManage.getTableName())){
                if ("0".equals(dbFieldManage.getStatus())){//0为启用，1为禁用，2为主键
                    DBMap.get(dbFieldManage.getTableName()).put(dbFieldManage.getColumnDescript(),dbFieldManage.getColumnName());
                }
            }
            //初始化每个表对应的主键字段
            if(primaryKeyMap.containsKey(dbFieldManage.getTableName())){
                if ("2".equals(dbFieldManage.getStatus()) && StringUtils.isEmpty(primaryKeyMap.get(dbFieldManage.getTableName()))){
                    primaryKeyMap.put(dbFieldManage.getTableName(),dbFieldManage.getColumnName());
                }
            }
        }
        json.put("oracleDB",DBMap);
        json.put("tableDescript",descriptMap);
        json.put("primaryKey",primaryKeyMap);
        return json;
    }
}
