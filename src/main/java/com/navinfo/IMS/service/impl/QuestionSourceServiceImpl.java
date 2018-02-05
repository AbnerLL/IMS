package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.QuestionSourceMapper;
import com.navinfo.IMS.entity.QuestionSource;
import com.navinfo.IMS.entity.QuestionSourceExample;
import com.navinfo.IMS.service.QuestionSourceService;
import com.navinfo.IMS.so.QuestionSourceSearch;
import com.navinfo.IMS.utils.PageObject;
import com.navinfo.IMS.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by luozhihui on 2018/1/15.
 */
@Service("questionSourceService")
public class QuestionSourceServiceImpl implements QuestionSourceService {
    @Autowired
    private QuestionSourceMapper questionSourceMapper;

    /**
     * 根据查询条件构建查询example
     * @param search
     * @return
     */
    private QuestionSourceExample createSearchExample(QuestionSourceSearch search){
        QuestionSourceExample example=new QuestionSourceExample();
        example.createCriteria();
        if (StringUtil.notNull(search.getEmpId())){
            example.getOredCriteria().get(0).andEmpIdEqualTo(search.getEmpId());
        }
        if (StringUtil.notNull(search.getEmpName())){
            example.getOredCriteria().get(0).andEmpNameEqualTo(search.getEmpName());
        }
        if (StringUtil.notNull(search.getVersion())){
            example.getOredCriteria().get(0).andVersionEqualTo(search.getVersion());
        }
        if (StringUtil.notNull(search.getSection())){
            example.getOredCriteria().get(0).andSectionEqualTo(search.getSection());
        }
        //关键词查询
        if (StringUtil.notNull(search.getKeyword())){
            example.or().andEmpIdLike("%"+search.getKeyword()+"%");
            example.or().andEmpNameLike("%"+search.getKeyword()+"%");
            example.or().andSectionLike("%"+search.getKeyword()+"%");
            example.or().andVersionLike("%"+search.getKeyword()+"%");
        }
        return example;
    }
    /**
     * 根据查询参数分页查询
     * @param questionSourceSearch
     * @param pageObject
     * @return
     */
    public PageInfo findQuestionSourceByPage(QuestionSourceSearch questionSourceSearch, PageObject pageObject){
        QuestionSourceExample example=this.createSearchExample(questionSourceSearch);
        PageHelper.startPage(pageObject.getPageNum(),pageObject.getPageSize());
        return new PageInfo(this.questionSourceMapper.selectByExample(example));
    }
    /**
     * 根据查询参数不分页查询
     * @param questionSourceSearch
     * @return
     */
    public List<QuestionSource> findQuestionSourceBySearch(QuestionSourceSearch questionSourceSearch){
        QuestionSourceExample example=this.createSearchExample(questionSourceSearch);
        return this.questionSourceMapper.selectByExample(example);
    }
    /**
     * 删除一个或多个对象
     * @param ids
     * @return
     */
    public boolean deleteQuestionSourceByIds(String ids) {
        List<String> idList= Arrays.asList(ids.split(","));
        QuestionSourceExample example=new QuestionSourceExample();
        example.createCriteria().andIdIn(idList);
        boolean flag=this.questionSourceMapper.deleteByExample(example)!=0;
        return flag;
    }

    /**
     * 根据主键查询对象
     * @param id
     * @return
     */
    public QuestionSource findQuestionSourceById(String id) {
        return this.questionSourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键动态更新对象
     * @param questionSource
     * @return
     */
    public boolean updateQuestionSource(QuestionSource questionSource) {
        return this.questionSourceMapper.updateByPrimaryKeySelective(questionSource)!=0;
    }

    /**
     * 保存对象
     * @param questionSource
     * @return
     */
    public boolean saveQuestionSource(QuestionSource questionSource) {
        questionSource.setId(UUID.randomUUID().toString());
        return this.questionSourceMapper.insertSelective(questionSource)!=0;
    }

}
