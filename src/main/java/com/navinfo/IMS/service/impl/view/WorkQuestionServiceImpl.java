package com.navinfo.IMS.service.impl.view;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.view.WorkQuestionMapper;
import com.navinfo.IMS.dto.view.PoiQuestionView;
import com.navinfo.IMS.dto.view.RoadQuestionView;
import com.navinfo.IMS.service.view.WorkQuestionService;
import com.navinfo.IMS.so.PoiQuestionSearch;
import com.navinfo.IMS.so.RoadQuestionSearch;
import com.navinfo.IMS.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luozhihui on 2018/2/28.
 */
@Service("workQuestionService")
public class WorkQuestionServiceImpl implements WorkQuestionService {

    @Autowired
    private WorkQuestionMapper workQuestionMapper;

    /**
     * 分页查询（道路问题）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findRoadQuestionByPage(RoadQuestionSearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<RoadQuestionView> roadQuestionViewList=this.workQuestionMapper.selectRoadQuestion(search);
        return new PageInfo(roadQuestionViewList);
    }

    /**
     * 分页查询（设施问题）
     * @param search
     * @param pageObject
     * @return
     */
    public PageInfo findPoiQuestionByPage(PoiQuestionSearch search, PageObject pageObject) {
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        List<PoiQuestionView> poiQuestionViewList=this.workQuestionMapper.selectPoiQuestion(search);
        return new PageInfo(poiQuestionViewList);
    }

    /**
     * 不分页查询（道路问题）
     * @param search
     * @return
     */
    public List<RoadQuestionView> findRoadQuestion(RoadQuestionSearch search) {
        return this.workQuestionMapper.selectRoadQuestion(search);
    }

    /**
     * 不分页查询（设施问题）
     * @param search
     * @return
     */
    public List<PoiQuestionView> findPoiQuestion(PoiQuestionSearch search) {
        return this.workQuestionMapper.selectPoiQuestion(search);
    }
}
