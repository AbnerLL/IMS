package com.navinfo.IMS.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于向页面ajax请求返回状态，消息以自定义参数
 * Created by luozhihui on 2017/9/24.
 */
public class Msg {
    //状态码(自定义状态)1代表成功，2代表失败
    private int code;
    //消息
    private String message;
    //扩展数据
    private Map<String, Object> extend=new HashMap<String,Object>();
    //构造两个简单的方法
    public static Msg success(){
        Msg msg=new Msg();
        msg.setCode(1);
        msg.setMessage("处理成功");
        return msg;
    }

    public static Msg failure(){
        Msg msg=new Msg();
        msg.setCode(2);
        msg.setMessage("处理失败");
        return msg;
    }
    //添加一个快捷的添加参数的方法
    public Msg add(String key,Object value){
        this.extend.put(key, value);
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
