package com.navinfo.IMS.service;

import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.entity.QuestionSource;
import com.navinfo.IMS.so.QuestionSourceSearch;
import com.navinfo.IMS.utils.PageObject;

import java.util.List;

/**
 * Created by luozhihui on 2018/1/15.
 */
public interface QuestionSourceService {

    PageInfo findQuestionSourceByPage(QuestionSourceSearch questionSourceSearch, PageObject pageObject);

    /**
     * 根据查询参数不分页查询
     * @param questionSourceSearch
     * @return
     */
    public List<QuestionSource> findQuestionSourceBySearch(QuestionSourceSearch questionSourceSearch);

    boolean deleteQuestionSourceByIds(String ids);

    QuestionSource findQuestionSourceById(String id);

    boolean updateQuestionSource(QuestionSource questionSource);

    boolean saveQuestionSource(QuestionSource questionSource);
}
