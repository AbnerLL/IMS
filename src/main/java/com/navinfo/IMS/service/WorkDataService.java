package com.navinfo.IMS.service;

import com.navinfo.IMS.entity.WorkData;

import java.util.List;

/**
 * Created by luozhihui on 2017/10/15.
 */
public interface WorkDataService {

    public List findWorkDatas();

    public WorkData findWorkDataById(String id);

    public boolean insertWorkData(WorkData workData);

    public boolean updateWorkData(WorkData workData);

    public boolean deleteWorkData(String id);
}
