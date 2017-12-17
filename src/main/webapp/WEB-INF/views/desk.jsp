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
    <title>系统桌面</title>
    <%--针对手机屏幕的设置--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <%--基础css--%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <h4>
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">员工分布</a>
                        </h4>
                    </div>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in"  aria-labelledby="headingOne">
                    <div class="panel-body">
                        <div id="empDistrDiv" style="width: 100%;height:400px;"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <h4><a>性别统计</a></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--基础js文件--%>
<script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
<script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/ECharts/3.8.4/echarts.common.min.js"></script>
<script type="text/javascript">
    //基于准备好的dom，初始化echarts实例
    var empDistrChart=echarts.init(document.getElementById('empDistrDiv'));

    // 指定图表的配置项和数据
    option = {
        title : {
            text: '人员分布情况',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:234, name:'联盟广告'},
                    {value:135, name:'视频广告'},
                    {value:1548, name:'搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    empDistrChart.setOption(option);
    //员工分布
    function loadEmpDistribution(){
        $.getJSON("${basePath}/charts/empDistribution",null,function(result){
            console.log(result.data);
        })
    }
    //员工性别分布
    function loadGenderDistribution(){

    }
    //司龄统计
    function loadSLingStat(){

    }
    $(function(){
        //加载图形
        setTimeout(function(){
            loadEmpDistribution();
            loadGenderDistribution();
            loadSLingStat();
        },2000);
    });
</script>
</body>
</html>
