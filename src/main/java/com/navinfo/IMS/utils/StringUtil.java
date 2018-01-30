package com.navinfo.IMS.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
/**
 * 字符串处理类
 */
public class StringUtil {

    /**
     * 以type为分界符分离str字符串,返回分离后的数组
     *
     * @param str 待处理字符串
     * @param type 分界符
     * @return array 处理后的字符串数组
     */
    public static String[] split(String str, String type) {

        int begin = 0;
        int pos = 0;
        String tempstr = "";
        String[] array = null;
        Vector vec = null;
        int len = str.trim().length();
        str = str + type;

        if (len > 0) {
            while (str.length() > 0) {

                if (vec == null) {
                    vec = new Vector();
                }

                pos = str.indexOf(type, begin);
                tempstr = str.substring(begin, pos);
                str = str.substring(pos + 1, str.length());
                vec.add(tempstr);
            }
        }
        if (vec != null) {
            array = new String[vec.size()];

            for (int i = 0; i < vec.size(); i++) {
                array[i] = (String) vec.get(i);
            }
        } else {
            array = new String[0];
        }

        return array;
    }

    /**
     * 按长度把字符串前补0
     *
     * @param s 需要补位字符串对象
     * @return len 该字符串的长度
     */
    public static String len(String s, int len) {

        if (!notNull(s)) {
            s = "";
        }

        int length = len - s.length();
        for (int i = 0; i < length; i++) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 判断字符串是否为空
     *
     * @param s 需要判断字符串对象
     * @return true 表示不为空 false 为空
     * @throws java.lang.Exception
     */
    public static boolean notNull(String s) {

        if (s == null || s.trim().length() <= 0 || "".equals(s) || "null".equals(s)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判定N个字符串是否为空
     * @param strs
     * @return
     */
    public static boolean notNull(String... strs) {
        for(String str:strs){
            if(!notNull(str)){
                return false;
            }
        }
        return true;
    }

    /**
     * 判定字符串是否为空
     * @param s
     * @return
     */
    public static boolean isNull(String s) {
        return !notNull(s);
    }

    /**
     * 判定N个字符串是否为空
     * @param strs
     * @return
     */
    public static boolean isNull(String... strs) {
        return !notNull(strs);
    }

    /**
     * 返回一个布尔值，表示两个字符串是否相等
     * @param str1 字符串对象
     * @param str2 字符串对象
     * @return false 表示不相等 true 相等
     * @throws java.lang.Exception
     */
    public static boolean equals(String str1, String str2) {
        if(str1==null){
            str1="";
        }
        if(str2==null){
            str2="";
        }
        return str1.trim().equals(str2.trim());
    }

    /**
     * 替换source中的str1为str2
     *
     * @param source 待替换的字符串
     * @param str1 待替换的字符
     * @param str2 替换的字符
     * @return java.lang.String 替换后的字符串
     */
    public static String replace(String source, char str1, String str2) {
        return replace(source, String.valueOf(str1), str2);
    }

    /**
     * 替换source中的str1为str2
     *
     * @param source 待替换的字符串
     * @param str1 待替换的字符
     * @param str2 替换的字符
     * @return java.lang.String 替换后的字符串
     */
    public static String replace(String source, String str1, String str2) {
        if (source == null) {
            return "";
        }
        String desc = "";
        int posstart = 0;
        int posend = source.indexOf(str1, 0);
        int len = source.length();
        while (posend >= 0 && posstart < len) {
            desc += source.substring(posstart, posend) + str2;
            posstart = posend + str1.length();
            posend = source.indexOf(str1, posstart);
        }
        if (posstart < len) {
            desc += source.substring(posstart, len);
        }
        return desc;
    }

    /**
     * 替换source中的"\n"为"换行符"
     *
     * @param content 待替换的字符串
     * @return java.lang.String 替换后的字符串
     */
    public static String replace(String content) {
        String[] tempValue = null;
        if (content != null) {
            if (content.indexOf("\n") != -1) {
                tempValue = StringUtil.split(content, "\n");
            }
            if (tempValue != null && tempValue.length > 0) {
                content = "";
                for (int i = 0; i < tempValue.length; i++) {
                    content += tempValue[i] + "<br>";
                }
                content = content.substring(0, content.length() - 4);
            }
        }
        return content;
    }

    /**
     * 将字符串列表转换成字符串数组
     * @param list List 字符串列表
     * @return String[]
     */
    public static String[] list2Array(List list) {
        String[] strs = new String[list.size()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = (String) list.get(i);
        }
        return strs;
    }

    /**
     * 将字符串数组转换成字符串列表
     * @param array String[] array 字符串数组
     * @return List 字符串列表
     */
    public static List array2List(String[] array) {
        List list = null;
        if (array != null) {
            list = new ArrayList();
            for (int i = 0; i < array.length; i++) {
                list.add(array[i].trim());
            }
        }
        return list;
    }

    /**
     * 过滤Html的特殊字符
     */
    public static String htmlEscape(String in) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            switch (c) {
                case '"':
                    out.append("&quot;");
                    break;
                case '&':
                    out.append("&amp;");
                    break;
                case '<':
                    out.append("&lt;");
                    break;
                case '>':
                    out.append("&gt;");
                    break;
                default:
                    out.append(c);
                    break;
            }
        }
        return out.toString();
    }

    /**
     * 去掉字符串里面的html代码。<br>
     * 要求数据要规范，比如大于小于号要配套,否则会被集体误杀。
     *
     * @param content
     *          内容
     * @return 去掉后的内容
     */
    public static String stripHtml(String content) {
        // <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
        // <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
        // 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
        // 还原HTML
        // content = HTMLDecoder.decode(content);
        //


        return content;
    }

    /**
     * 返回字符串左边指定长度的字符串
     * @param str
     * @param len
     * @return
     */
    public static String left(String str, int len) {
        if (str == null || str.equals("") || len <= 0) {
            return "";
        }
        if (str.length() < len) {
            return str;
        }

        return str.substring(0, len);
    }

    /**
     * 从字符串中抽取左边第一个数字
     * @param str  待抽取的字符串
     * @param defaultValue 缺省值，如果没有抽取到数字，则返回这个值
     * @return
     */
    public static String getFirstLeftNumber(String str, String defaultValue) {
        if (str == null || str.trim().length() == 0) {
            return defaultValue;
        }
        boolean intoNum = false;
        String retStr = "";
        char s;
        for (int i = 0; i < str.length(); i++) {
            s = str.charAt(i);
            if ("0123456789０１２３４５６７８９".indexOf(s) >= 0) {   //包含数字
                retStr += java.lang.Character.getNumericValue(s);
                if (intoNum == false) {   //开始进入数字
                    intoNum = true;
                }
            } else {    //不包含数字
                if (intoNum == true) {   //之前是数字，现在不是数字
                    break;
                }
            }
        }

        if (retStr.trim().length() == 0) {
            return defaultValue;
        }
        return retStr;
    }

    /**
     * 全角转半角
     *  @param  input String.
     *  @return  半角字符串
     */
    public static String ToDBC(String input) {
        try {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '\u3000') {
                    c[i] = ' ';
                } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                    c[i] = (char) (c[i] - 65248);
                }
            }
            String returnString = new String(c);
            return returnString;
        } catch (Exception ex) {
            return input;
        }

    }

    /**
     * 半角转全角
     *  @param  input String.
     *  @return  全角字符串.
     */
    public static String ToSBC(String input) {
        try {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == ' ') {
                    c[i] = '\u3000';
                } else if (c[i] < '\177') {
                    c[i] = (char) (c[i] + 65248);
                }
            }
            return new String(c);
        } catch (Exception ex) {
            return input;
        }

    }

    /**
     * 根据空格进行分割，返回数组
     * @param str
     * @return
     */
    public static String[] splitBlank(String str){
        return str.trim().replaceAll("[ 　]+", ";").split(";");
    }


    /**
     * 返回结果
     * @param obj reg
     * @param reg
     * @return
     */
    public static String getString(Object obj,String reg) {
        if(obj==null){
            return reg;
        }
        return notNull(obj.toString())?obj.toString():reg;
    }
    public static String getRandomStr(){
        java.util.Random r=new java.util.Random();
        return ""+Math.abs(r.nextInt());
    }
}

