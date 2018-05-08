<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/9/24
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>登录</title>
    <link href="${requestScope.basePath}/css/login.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/iCheck/1.0.2/skins/all.css" rel="stylesheet">
</head>
<body>

<div class="login">
    <div class="message">人员综合信息管理-登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" action="${basePath}/toLogin">
        <input name="action" value="login" type="hidden">
        <input id="username" name="username" placeholder="用户名" required="" type="text">
        <hr class="hr15">
        <input id="password" name="password" placeholder="密码" required="" type="password">
        <div class="login-set">
            <label for="rm-password" id="rm-password-lb"><input type="checkbox" id="rm-password">&nbsp;记住密码</label>
            <%--<label for="auto-login" id="auto-login-lb"><input type="checkbox" id="auto-login">&nbsp;自动登录</label>--%>
        </div>
        <input value="登录" style="width:100%;" type="button" id="login_btn">
        <hr class="hr20">
        <!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
    </form>


</div>

<div class="copyright"></div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script type="text/javascript">
    $(function () {
        //初始化复选框、单选框样式
        $("input[type='checkbox']").iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
        //默认取消选中
        $("input[type='checkbox']").iCheck('uncheck');
        //登录设置
        setLogin();
        //判断是否自动登录
    });
    //设置cookie
    function setCookie(cookieName,cvalue,expiredays,path)
    {
        var expireDate=new Date();
        var expireStr="";
        if(expiredays!=null) {
            expireDate.setTime(expireDate.getTime()+(expiredays*24*3600*1000));
            expireStr="; expires="+expireDate.toGMTString();
        }
        pathStr=(path==null)?"; path=/":"; path="+path;
        document.cookie=cookieName+'='+escape(cvalue)+expireStr+pathStr;
    }
    //获取cookie
    function getCookie(cookieName)
    {
        var index=-1;
        if(document.cookie)
            index=document.cookie.indexOf(cookieName);
        if(index==-1) {
            return "";
        } else {
            var iBegin = (document.cookie.indexOf("=", index) +1);
            var iEnd =document.cookie.indexOf(";", index);
            if (iEnd == -1)
            {
                iEnd = document.cookie.length;
            }
            return unescape(document.cookie.substring(iBegin,iEnd));
        }
    }
    //设置登录
    function setLogin(){
        $("#username").val(getCookie("myusername"));
        $("#password").val(getCookie("mypassword"));
        if ("true" == getCookie("rebUser")){
            $("#rm-password").iCheck('check');
        }
//        if ("true" == getCookie("autologin")){
//            $("#auto-login").iCheck('check');
//        }
    }
    //登录
    $("#login_btn").on("click",function () {
        //判断是否记录cookie
        if ($("#rm-password").prop("checked")){
            setCookie("rebUser","true",30);
            setCookie("myusername",$.trim($("#username").val()),30);
            setCookie("mypassword",$.trim($("#password").val()),30);
        }
        //保存自动登录设置
        if ($("#auto-login").prop("checked")){
            setCookie("autologin","true",30);
            setCookie("myusername",$.trim($("#username").val()),30);
            setCookie("mypassword",$.trim($("#password").val()),30);
        }
        $("form").submit();
    })
</script>
</body>
</html>
