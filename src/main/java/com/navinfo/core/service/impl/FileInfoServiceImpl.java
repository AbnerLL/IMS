package com.navinfo.core.service.impl;

import com.navinfo.core.dao.FileInfoMapper;
import com.navinfo.core.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luozhihui on 2018/2/25.
 */
@Service("fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {
    @Autowired
    private FileInfoMapper fileInfoMapper;
}
