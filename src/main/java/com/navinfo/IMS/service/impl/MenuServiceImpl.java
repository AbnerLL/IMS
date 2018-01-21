package com.navinfo.IMS.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.navinfo.IMS.dao.MenuMapper;
import com.navinfo.IMS.dto.Msg;
import com.navinfo.IMS.entity.MenuExample;
import com.navinfo.IMS.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 菜单的业务处理实现类
 * Created by luozhihui on 2017/9/21.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 用来初始化菜单的逻辑
     * 父级菜单的pid均为0
     * @return List
     */
    public List selectMenusByPid(){
        List menuList=menuMapper.selectByPid("0");
        return menuList;
    }

    /**
     * 分页查询
     * @param map
     * @return PageInfo(改对象包含了所有的分页信息)
     */
    public PageInfo findMenuByPage(Map map){
        Integer pageNum=Integer.valueOf((String) map.get("pageNum"));
        Integer pageSize=Integer.valueOf((String) map.get("pageSize"));
        String keyword=(String) map.get("keyword");
        MenuExample example=new MenuExample();
        if(keyword!=null&&!"".equals(keyword)){
            example.createCriteria().andPidLike("%"+keyword+"%");
            example.or().andNameLike("%"+keyword+"%");
        }
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo(this.menuMapper.selectByExample(example));
    }

    /**
     *
     * @return
     */
    @RequestMapping(value="/menu")
    public Msg findMenuById(){
        return Msg.success();
    }
}
