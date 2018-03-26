package com.navinfo.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.DateUtil;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import com.navinfo.core.dao.FileInfoMapper;
import com.navinfo.core.entity.FileInfo;
import com.navinfo.core.entity.FileInfoExample;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.service.FileInfoService;
import com.navinfo.core.so.FileInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/2/25.
 */
@Service("fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {
    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 根据查询条件构建example
     * @param search
     * @return
     */
    private FileInfoExample createSearchExample(FileInfoSearch search){
        FileInfoExample example=new FileInfoExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getModuleId())){
            example.getOredCriteria().get(0).andModuleIdEqualTo(search.getModuleId());
        }
        if (StringUtil.notNull(search.getObjectId())){
            example.getOredCriteria().get(0).andObjectIdEqualTo(search.getObjectId());
        }
        if (StringUtil.notNull(search.getFileName())){
            example.getOredCriteria().get(0).andFileNameEqualTo(search.getFileName());
        }
        if (StringUtil.notNull(search.getFileExname())){
            example.getOredCriteria().get(0).andFileExnameEqualTo(search.getFileExname());
        }
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andFileNameLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 分页查询
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findFileInfoByPage(FileInfoSearch search, PageObject pageObject) {
        FileInfoExample example=this.createSearchExample(search);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<FileInfo> fileInfoList=this.fileInfoMapper.selectByExample(example);
        return new PageInfo(fileInfoList);
    }

    /**
     * 保存文件
     * @param path 文件存放的路径
     * @param singleFile 文件封装类
     */
    public void saveFileInfo(String path, MultipartFile singleFile, SysUser sysUser) {
        try {
            String oldName=singleFile.getOriginalFilename();
            String fileExName=oldName.substring(oldName.indexOf(".")+1);
            String newName= UUID.randomUUID().toString();
            File targetFile=new File(path,newName+"."+fileExName);
    //      如果父路径不存在则创建
            if (!targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdirs();
            }
            singleFile.transferTo(targetFile);
            //将文件记录插入表中
            FileInfo fileInfo=new FileInfo();
            fileInfo.setUuid(newName);
            fileInfo.setObjectId(sysUser.getId());
            fileInfo.setOldFileName(oldName.substring(0,oldName.indexOf(".")));
            fileInfo.setFileSize(Long.toString(targetFile.length()));
            fileInfo.setFileExname(fileExName);
            fileInfo.setUploadDate(new Date());
            fileInfo.setFilePath(path+File.separator+newName+"."+fileExName);
            fileInfoMapper.insertSelective(fileInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param fileId
     * @return
     */
    public boolean deleteFileInfo(String fileId) {
        return this.fileInfoMapper.deleteByPrimaryKey(fileId)!=0;
    }

}
