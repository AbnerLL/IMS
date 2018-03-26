package com.navinfo.core.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.core.entity.SysUser;
import com.navinfo.core.so.FileInfoSearch;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luozhihui on 2018/2/25.
 */
public interface FileInfoService {
    PageInfo findFileInfoByPage(FileInfoSearch search, PageObject pageObject);

    void saveFileInfo(String path, MultipartFile singleFile, SysUser sysUser);

    boolean deleteFileInfo(String fileId);
}
