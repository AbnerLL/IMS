package com.navinfo.IMS.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by luozhihui on 2017/12/17.
 */
public interface ChartsMapper {

    List<Map<String,Object>> findEmpAddressGroup();

    List<Map<String,Object>> findEmpGenderGroup();

    List<Map<String,Object>> findEmpSLingStat();

    List<Map<String,Object>> findRoadVersionTotalNum();

    List<Map<String,Object>> findPoiVersionTotalNum();

}
