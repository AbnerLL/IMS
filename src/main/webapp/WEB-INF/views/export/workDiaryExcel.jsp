<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/1/30
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../include/export/header_office.jsp"%>
<%@ page isELIgnored ="false" %>
<%
    response.setContentType("application/xls");
    response.setHeader("Content-Disposition",
            "inline; filename=\"WORK_DIARY.xls\"");
%>
<html xmlns:v="urn:schemas-microsoft-com:vml"
      xmlns:o="urn:schemas-microsoft-com:office:office"
      xmlns:x="urn:schemas-microsoft-com:office:excel"
      xmlns="http://www.w3.org/TR/REC-html40">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="ProgId" content="Excel.Sheet"/>
    <meta name="Generator" content="WPS Office ET"/>
    <!--[if gte mso 9]>
    <xml>
        <o:DocumentProperties>
            <o:Created>2018-01-30T20:06:35</o:Created>
            <o:LastAuthor>Louie</o:LastAuthor>
            <o:LastSaved>2018-01-30T20:12:22</o:LastSaved>
        </o:DocumentProperties>
        <o:CustomDocumentProperties>
            <o:KSOProductBuildVer dt:dt="string">2052-10.1.0.7106</o:KSOProductBuildVer>
        </o:CustomDocumentProperties>
    </xml>
    <![endif]-->
    <style>
        <!-- @page
        {margin:0.98in 0.75in 0.98in 0.75in;
            mso-header-margin:0.51in;
            mso-footer-margin:0.51in;}
        tr
        {mso-height-source:auto;
            mso-ruby-visibility:none;}
        col
        {mso-width-source:auto;
            mso-ruby-visibility:none;}
        br
        {mso-data-placement:same-cell;}
        .font0
        {color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font1
        {color:windowtext;
            font-size:12.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font2
        {color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font3
        {color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font4
        {color:#7F7F7F;
            font-size:11.0pt;
            font-weight:400;
            font-style:italic;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font5
        {color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font6
        {color:#3F3F76;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font7
        {color:#44546A;
            font-size:18.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font8
        {color:#44546A;
            font-size:13.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font9
        {color:#9C0006;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font10
        {color:#44546A;
            font-size:15.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font11
        {color:#44546A;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font12
        {color:#0000FF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:underline;
            text-underline-style:single;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font13
        {color:#800080;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:underline;
            text-underline-style:single;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font14
        {color:#FF0000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font15
        {color:#FA7D00;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font16
        {color:#3F3F3F;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font17
        {color:#FA7D00;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font18
        {color:#FFFFFF;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font19
        {color:#000000;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font20
        {color:#006100;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .font21
        {color:#9C6500;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:"宋体";
            mso-generic-font-family:auto;
            mso-font-charset:134;}
        .style0
        {mso-number-format:"General";
            text-align:general;
            vertical-align:middle;
            white-space:nowrap;
            mso-rotate:0;
            mso-pattern:auto;
            mso-background-source:auto;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border:none;
            mso-protection:locked visible;
            mso-style-name:"常规";
            mso-style-id:0;}
        .style16
        {mso-number-format:"_ \0022\00A5\0022* \#\,\#\#0_ \;_ \0022\00A5\0022* \\-\#\,\#\#0_ \;_ \0022\00A5\0022* \0022-\0022_ \;_ \@_ ";
            mso-style-name:"货币[0]";
            mso-style-id:7;}
        .style17
        {mso-pattern:auto none;
            background:#EDEDED;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"20% - 强调文字颜色 3";}
        .style18
        {mso-pattern:auto none;
            background:#FFCC99;
            color:#3F3F76;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border:.5pt solid #7F7F7F;
            mso-style-name:"输入";}
        .style19
        {mso-number-format:"_ \0022\00A5\0022* \#\,\#\#0\.00_ \;_ \0022\00A5\0022* \\-\#\,\#\#0\.00_ \;_ \0022\00A5\0022* \0022-\0022??_ \;_ \@_ ";
            mso-style-name:"货币";
            mso-style-id:4;}
        .style20
        {mso-number-format:"_ * \#\,\#\#0_ \;_ * \\-\#\,\#\#0_ \;_ * \0022-\0022_ \;_ \@_ ";
            mso-style-name:"千位分隔[0]";
            mso-style-id:6;}
        .style21
        {mso-pattern:auto none;
            background:#DBDBDB;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"40% - 强调文字颜色 3";}
        .style22
        {mso-pattern:auto none;
            background:#FFC7CE;
            color:#9C0006;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"差";}
        .style23
        {mso-number-format:"_ * \#\,\#\#0\.00_ \;_ * \\-\#\,\#\#0\.00_ \;_ * \0022-\0022??_ \;_ \@_ ";
            mso-style-name:"千位分隔";
            mso-style-id:3;}
        .style24
        {mso-pattern:auto none;
            background:#C9C9C9;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"60% - 强调文字颜色 3";}
        .style25
        {color:#0000FF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:underline;
            text-underline-style:single;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"超链接";
            mso-style-id:8;}
        .style26
        {mso-number-format:"0%";
            mso-style-name:"百分比";
            mso-style-id:5;}
        .style27
        {color:#800080;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:underline;
            text-underline-style:single;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"已访问的超链接";
            mso-style-id:9;}
        .style28
        {mso-pattern:auto none;
            background:#FFFFCC;
            border:.5pt solid #B2B2B2;
            mso-style-name:"注释";}
        .style29
        {mso-pattern:auto none;
            background:#F4B084;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"60% - 强调文字颜色 2";}
        .style30
        {color:#44546A;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"标题 4";}
        .style31
        {color:#FF0000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"警告文本";}
        .style32
        {color:#44546A;
            font-size:18.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"标题";}
        .style33
        {color:#7F7F7F;
            font-size:11.0pt;
            font-weight:400;
            font-style:italic;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"解释性文本";}
        .style34
        {color:#44546A;
            font-size:15.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border-bottom:1.0pt solid #5B9BD5;
            mso-style-name:"标题 1";}
        .style35
        {color:#44546A;
            font-size:13.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border-bottom:1.0pt solid #5B9BD5;
            mso-style-name:"标题 2";}
        .style36
        {mso-pattern:auto none;
            background:#9BC2E6;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"60% - 强调文字颜色 1";}
        .style37
        {color:#44546A;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border-bottom:1.0pt solid #ACCCEA;
            mso-style-name:"标题 3";}
        .style38
        {mso-pattern:auto none;
            background:#FFD966;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"60% - 强调文字颜色 4";}
        .style39
        {mso-pattern:auto none;
            background:#F2F2F2;
            color:#3F3F3F;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border:.5pt solid #3F3F3F;
            mso-style-name:"输出";}
        .style40
        {mso-pattern:auto none;
            background:#F2F2F2;
            color:#FA7D00;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border:.5pt solid #7F7F7F;
            mso-style-name:"计算";}
        .style41
        {mso-pattern:auto none;
            background:#A5A5A5;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border:2.0pt double #3F3F3F;
            mso-style-name:"检查单元格";}
        .style42
        {mso-pattern:auto none;
            background:#E2EFDA;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"20% - 强调文字颜色 6";}
        .style43
        {mso-pattern:auto none;
            background:#ED7D31;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"强调文字颜色 2";}
        .style44
        {color:#FA7D00;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border-bottom:2.0pt double #FF8001;
            mso-style-name:"链接单元格";}
        .style45
        {color:#000000;
            font-size:11.0pt;
            font-weight:700;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border-top:.5pt solid #5B9BD5;
            border-bottom:2.0pt double #5B9BD5;
            mso-style-name:"汇总";}
        .style46
        {mso-pattern:auto none;
            background:#C6EFCE;
            color:#006100;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"好";}
        .style47
        {mso-pattern:auto none;
            background:#FFEB9C;
            color:#9C6500;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"适中";}
        .style48
        {mso-pattern:auto none;
            background:#D9E1F2;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"20% - 强调文字颜色 5";}
        .style49
        {mso-pattern:auto none;
            background:#5B9BD5;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"强调文字颜色 1";}
        .style50
        {mso-pattern:auto none;
            background:#DDEBF7;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"20% - 强调文字颜色 1";}
        .style51
        {mso-pattern:auto none;
            background:#BDD7EE;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"40% - 强调文字颜色 1";}
        .style52
        {mso-pattern:auto none;
            background:#FCE4D6;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"20% - 强调文字颜色 2";}
        .style53
        {mso-pattern:auto none;
            background:#F8CBAD;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"40% - 强调文字颜色 2";}
        .style54
        {mso-pattern:auto none;
            background:#A5A5A5;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"强调文字颜色 3";}
        .style55
        {mso-pattern:auto none;
            background:#FFC000;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"强调文字颜色 4";}
        .style56
        {mso-pattern:auto none;
            background:#FFF2CC;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"20% - 强调文字颜色 4";}
        .style57
        {mso-pattern:auto none;
            background:#FFE699;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"40% - 强调文字颜色 4";}
        .style58
        {mso-pattern:auto none;
            background:#4472C4;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"强调文字颜色 5";}
        .style59
        {mso-pattern:auto none;
            background:#B4C6E7;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"40% - 强调文字颜色 5";}
        .style60
        {mso-pattern:auto none;
            background:#8EA9DB;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"60% - 强调文字颜色 5";}
        .style61
        {mso-pattern:auto none;
            background:#70AD47;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"强调文字颜色 6";}
        .style62
        {mso-pattern:auto none;
            background:#C6E0B4;
            color:#000000;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"40% - 强调文字颜色 6";}
        .style63
        {mso-pattern:auto none;
            background:#A9D08E;
            color:#FFFFFF;
            font-size:11.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            mso-style-name:"60% - 强调文字颜色 6";}
        td
        {mso-style-parent:style0;
            padding-top:1px;
            padding-right:1px;
            padding-left:1px;
            mso-ignore:padding;
            mso-number-format:"General";
            text-align:general;
            vertical-align:middle;
            white-space:nowrap;
            mso-rotate:0;
            mso-pattern:auto;
            mso-background-source:auto;
            color:windowtext;
            font-size:12.0pt;
            font-weight:400;
            font-style:normal;
            text-decoration:none;
            font-family:宋体;
            mso-generic-font-family:auto;
            mso-font-charset:134;
            border:none;
            mso-protection:locked visible;}
        .xl65
        {mso-style-parent:style0;
            text-align:center;
            font-weight:700;
            mso-font-charset:134;}
        .xl66
        {mso-style-parent:style0;
            text-align:center;
            mso-pattern:auto none;
            background:#9BC2E6;
            font-weight:700;
            mso-font-charset:134;
            border:.5pt solid windowtext;}
        .xl67
        {mso-style-parent:style0;
            mso-number-format:"\@";
            mso-font-charset:134;}
        .xl68
        {mso-style-parent:style0;
            mso-number-format:"\@";
            text-align:center;
            mso-pattern:auto none;
            background:#9BC2E6;
            font-weight:700;
            mso-font-charset:134;
            border:.5pt solid windowtext;}
        .xl69
        {mso-style-parent:style0;
            mso-number-format:"yyyy/m/d\;\@";
            text-align:center;
            mso-pattern:auto none;
            background:#9BC2E6;
            font-weight:700;
            mso-font-charset:134;
            border:.5pt solid windowtext;}
        .xl71
        {mso-style-parent:style0;
            mso-number-format:"yyyy/m/d\;\@";
            mso-font-charset:134;}
        .xl72
        {mso-style-parent:style0;
            mso-number-format:"\@";
            mso-font-charset:134;}
        -->  </style>
    <!--[if gte mso 9]>
    <xml>
        <x:ExcelWorkbook>
            <x:ExcelWorksheets>
                <x:ExcelWorksheet>
                    <x:Name>Sheet1</x:Name>
                    <x:WorksheetOptions>
                        <x:DefaultRowHeight>285</x:DefaultRowHeight>
                        <x:Selected/>
                        <x:Panes>
                            <x:Pane>
                                <x:Number>3</x:Number>
                                <x:ActiveCol>0</x:ActiveCol>
                                <x:ActiveRow>1</x:ActiveRow>
                                <x:RangeSelection>A2</x:RangeSelection>
                            </x:Pane>
                        </x:Panes>
                        <x:ProtectContents>False</x:ProtectContents>
                        <x:ProtectObjects>False</x:ProtectObjects>
                        <x:ProtectScenarios>False</x:ProtectScenarios>
                        <x:PageBreakZoom>100</x:PageBreakZoom>
                        <x:Print>
                            <x:ValidPrinterInfo/>
                            <x:PaperSizeIndex>9</x:PaperSizeIndex>
                        </x:Print>
                    </x:WorksheetOptions>
                </x:ExcelWorksheet>
            </x:ExcelWorksheets>
            <x:ProtectStructure>False</x:ProtectStructure>
            <x:ProtectWindows>False</x:ProtectWindows>
            <x:WindowHeight>8370</x:WindowHeight>
            <x:WindowWidth>19770</x:WindowWidth>
        </x:ExcelWorkbook>
    </xml>
    <![endif]-->
</head>
<body link="blue" vlink="purple">
<table width="1359" border="0" cellpadding="0" cellspacing="0" style='width:1019.25pt;border-collapse:collapse;table-layout:fixed;'>
    <col width="132" style='mso-width-source:userset;mso-width-alt:4224;'/>
    <col width="141" style='mso-width-source:userset;mso-width-alt:4512;'/>
    <col width="117" style='mso-width-source:userset;mso-width-alt:3744;'/>
    <col width="109" style='mso-width-source:userset;mso-width-alt:3488;'/>
    <col width="129" style='mso-width-source:userset;mso-width-alt:4128;'/>
    <col width="126" style='mso-width-source:userset;mso-width-alt:4032;'/>
    <col width="93" style='mso-width-source:userset;mso-width-alt:2976;'/>
    <col width="107" style='mso-width-source:userset;mso-width-alt:3424;'/>
    <col width="114" style='mso-width-source:userset;mso-width-alt:3648;'/>
    <col width="116" style='mso-width-source:userset;mso-width-alt:3712;'/>
    <col width="89" style='mso-width-source:userset;mso-width-alt:2848;'/>
    <col width="86" style='mso-width-source:userset;mso-width-alt:2752;'/>
    <tr height="19" class="xl65" style='height:14.25pt;'>
        <td class="xl66" height="19" width="132" style='height:14.25pt;width:99.00pt;' x:str>员工编号</td>
        <td class="xl66" width="141" style='width:105.75pt;' x:str>员工姓名</td>
        <td class="xl66" width="117" style='width:87.75pt;' x:str>项目组</td>
        <td class="xl69" width="109" style='width:81.75pt;' x:str>工作类型</td>
        <td class="xl66" width="129" style='width:96.75pt;' x:str>工作日期</td>
        <td class="xl66" width="93" style='width:69.75pt;' x:str>工作时间</td>
        <td class="xl66" width="126" style='width:94.50pt;' x:str>工作时长</td>
        <td class="xl66" width="93" style='width:69.75pt;' x:str>工作模块</td>
        <td class="xl66" width="93" style='width:69.75pt;' x:str>工作内容</td>
    </tr>
    <c:forEach var="workDiary" items="${requestScope.workDiaryList}">
        <tr>
            <td class="xl67">${workDiary.empId}</td>
            <td class="x172">${workDiary.empName}</td>
            <td class="x172">${workDiary.section}</td>
            <td class="x172">${workDiary.workType}</td>
            <td class="xl71"><fmt:formatDate value="${workDiary.workDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
            <td class="x172">${workDiary.workTimeStart}-${workDiary.workTimeEnd}</td>
            <td class="x172">${workDiary.workHours}</td>
            <td class="x172">${workDiary.workModule}</td>
            <td class="x172">${workDiary.workContent}</td>
        </tr>
    </c:forEach>
    <![if supportMisalignedColumns]>
    <tr width="0" style='display:none;'>
        <td width="132" style='width:99;'></td>
        <td width="141" style='width:106;'></td>
        <td width="117" style='width:88;'></td>
        <td width="109" style='width:82;'></td>
        <td width="129" style='width:97;'></td>
        <td width="126" style='width:95;'></td>
        <td width="93" style='width:70;'></td>
        <td width="107" style='width:80;'></td>
        <td width="114" style='width:86;'></td>
        <td width="116" style='width:87;'></td>
        <td width="89" style='width:67;'></td>
        <td width="86" style='width:65;'></td>
    </tr>
    <![endif]>
</table>
</body>
</html>

