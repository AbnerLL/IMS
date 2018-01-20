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
                        <div id="addressGroupDiv" style="width: 100%;height:400px;"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-5">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <h4>
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">性别统计</a>
                        </h4>
                    </div>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse in"  aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <div id="genderGroupDiv" style="width: 100%;height:400px;"></div>
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
    var empAddressGroupCharts=echarts.init(document.getElementById('addressGroupDiv'));
    //
    var empGenderGroupCharts=echarts.init(document.getElementById('genderGroupDiv'));
    //员工分布
    function loadEmpAddressGroup(){
        $.getJSON("${basePath}/charts/empAddressGroup",null,function(result){
            console.log(result.extend.data);
            var legendData=new Array();
            var seriesData=new Array();
            var addressResult=result.extend.data;
            for(var i=0;i<addressResult.length ;i++){
                var item=new Object();
                item.name=addressResult[i].ADDRESS;
                item.value=addressResult[i].NUM;
                legendData.push(addressResult[i].ADDRESS);
                seriesData.push(item);
            }
            // 指定图表的配置项和数据
            empAddressGroupCharts.setOption({
                title : {
                    text: '人员分布情况',
                    subtext: '最新统计结果',
                    x:'right'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: legendData
                },
                series : [
                    {
                        name: '所占比例',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:seriesData,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        })
    }
    //员工性别分布
    function loadGenderGroup(){
        $.getJSON("${basePath}/charts/empGenderGroup",null,function(result){
            var legendData=new Array();
            var seriesData=new Array();
            var addressResult=result.extend.data;
            for(var i=0;i<addressResult.length ;i++){
                var item=new Object();
                item.name=addressResult[i].GENDER;
                item.value=addressResult[i].NUM;
                legendData.push(addressResult[i].GENDER);
                seriesData.push(item);
            }
            // 指定图表的配置项和数据
            empGenderGroupCharts.setOption({
                title : {
                    text: '员工性别分布情况',
                    subtext: '最新统计结果',
                    x:'right'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: legendData
                },
                series : [
                    {
                        name: '所占比例',
                        type: 'pie',
                        radius : ['35%',"55%"],
                        center: ['50%', '60%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data:seriesData,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        })
    }
    //司龄统计
    function loadSLingStat(){

    }
    $(function(){
        //加载图形
        setTimeout(function(){
            loadEmpAddressGroup();
            loadGenderGroup();
            loadSLingStat();
        },500);
    });
</script>
</body>
</html>
