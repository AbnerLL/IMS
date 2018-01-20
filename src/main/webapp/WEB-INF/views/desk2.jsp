<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/12/10
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>系统桌面2</title>
    <%--针对手机屏幕的设置--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <%--基础css--%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-4 showLine" style="padding-right: 0px">
            <div class="showLine" style="height: 400px;width: 100%;float: left;">
                <div id="addressGroupDiv" style="width: 100%;height:400px;"></div>
            </div>
        </div>
        <div class="col-sm-4 showLine" style="padding-left: 0px">
            <div class="showLine " style="height: 400px;width: 100%;">
                <div id="genderGroupDiv" style="width: 100%;height:400px;"></div>
            </div>
        </div>
        <div class="col-sm-4 showLine" style="padding-left: 0px;padding-right: 0px;">
            <div class="showLine " style="height: 400px;width: 100%;">
                <div id="sLingStatDiv" style="width: 100%;height:400px;"></div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-6 showLine" style="padding-right: 0px">
            <div class="showLine " style="height: 400px;width: 100%;">
                <div id="roadQualityRateDiv" style="width: 100%;height:400px;"></div>
            </div>
        </div>
        <div class="col-sm-6 showLine" style="padding-right: 0px">
            <div class="showLine " style="height: 400px;width: 100%;">
                <div id="poiQualityRateDiv" style="width: 100%;height:400px;"></div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-6 col-sm-offset-3 showLine">
            <div class="showLine " style="height: 400px;width: 100%;">
                <div id="empAptitudeDiv" style="width: 100%;height:400px;"></div>
            </div>
        </div>
    </div>
</div>
<%--基础js文件--%>
<script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
<script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/ECharts/3.8.4/echarts.common.min.js"></script>
<script type="text/javascript">
    var basePath="${basePath}";
    $(function(){
        //加载图形
        setTimeout(function(){
            loadEmpAddressGroup();
            loadGenderGroup();
            loadSLingStat();
            loadRoadQualityRate();
            loadPoiQualityRate();
            loadEmpAptitudeRoad();
        },500);
    });
</script>
</body>
</html>
<script type="text/javascript" src="${basePath}/js/desk.js"></script>