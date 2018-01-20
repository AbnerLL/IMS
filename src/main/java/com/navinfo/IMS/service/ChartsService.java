package com.navinfo.IMS.service;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/17.
 */
public interface ChartsService {
    List findEmpAddressGroup();

    List findEmpGenderGroup();

    List findEmpSLingStat();

    List findPoiQualityRate();

    List findRoadQualityRate();
}
