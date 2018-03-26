<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/3/25
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试用例</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
<div>helloworld</div>
<select id="container">
    <option value="">----请选择----</option>
</select>
<script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    var appPath="${basePath}";
    $(function(){
        $("#container").weboption({search:"工作类型"});
    })
</script>
<script src="${basePath}/js/weboption.js" type="text/javascript"></script>
</body>
</html>
