<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/4/8
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>KPI月报</title>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="height: 30px;"></div>
    <div class="row">
        <div class="col-sm-5">
            <div class="panel panel-info">
                <div class="panel panel-heading" style="margin: 0px;">KPI月报数据</div>
                <div class="panel-body" style="padding: 0px;margin-top: 0px;">
                    <div id="toolbar" class="">
                        <form id="search_from" class="form-inline"  style="padding-bottom: 0px;margin-bottom: 0px;">
                            <div class="form-group">
                                <select id="year_search_select" class="form-control">
                                    <option value="2016">2016</option>
                                    <option value="2017">2017</option>
                                    <option value="2018">2018</option>
                                </select>
                                <select id="workType_search_select" class="form-control">
                                    <option value="道路">道路</option>
                                    <option value="设施">设施</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <%--表格数据--%>
                    <table id="table_list"></table>
                </div>
            </div>
        </div>
        <div class="col-sm-7" >
            <div class="panel panel-info">
                <div class="panel panel-heading" style="margin: 0px;">KPI月报展示</div>
                <div class="panel-body" style="padding: 0px;margin-top: 0px;">
                    <div id = "kpiReportEChart" style="width: 90%;height: 400px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/ECharts/3.8.4/echarts.common.min.js"></script>
<script type="text/javascript">
    //设置全局变量，用于渲染报表
    var originalKPIData = [];
    //初始化图表对象
    var kpiReportEChart = echarts.init(document.getElementById('kpiReportEChart'));
    $(function(){
        //初始化表格
        initTable();
        //加载图表
        setTimeout(function () {
            loadKPIReportCharts(originalKPIData);
        },500);
        //修改表格样式
        initTableStyle();
    });
    //表格样式配置
    function initTableStyle(){

    }

    //设置发送请求时的参数，当queryParamsType 为limit时
    // params中的参数为{ search: undefined, sort: undefined, order: "asc", offset: 0, limit: 10 }
    function myQueryParams(params){
        return {
            workType:$("#workType_search_select").val(),
            year:$("#year_search_select").val(),
        };
    }
    //设置从服务器返回的数据rows:数据集合，total总记录数
    function myResponseHandler(result){
        if(result){
            //设置全局变量，用于渲染报表
            originalKPIData = result.extend.pageInfo.list;
            return {
                rows:result.extend.pageInfo.list,
                total:result.extend.pageInfo.total
            };
        }else{
            return {
                rows:[],
                total:0
            };
        }
    }
    //时间格式化参数为当前值、当前行、当前index
    function dateFormatter(value,row,index){
        if(!value){
            return "";
        }
        var currentTime=new Date(value);
        var currentYear=currentTime.getFullYear();
        var currentMonth=currentTime.getMonth()+1;
        var currentDate=currentTime.getDate();
        return currentYear+"/"+currentMonth+"/"+currentDate;
    }
    //初始化表格配置function
    function initTable(){
        $("#table_list").bootstrapTable({
            //获取数据的url
            url:"${basePath}/view/kpiReports",
            method: 'get',//请求方式
            toolbar: '#toolbar',                //工具按钮用哪个容器
            classes:"table table-hover table-bordered table-condensed",        //设置table的class属性
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: myQueryParams,         //传递参数（*）
            responseHandler:myResponseHandler,  //设置解析服务器返回的数据
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
//            pageNumber:1,                       //初始化加载第一页，默认第一页
//            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 400,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
//            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
//            searchOnEnterKey:true,                 //设置为true，则按回车键进行搜索，否则自动搜索
            columns:[{                          //配置各列的属性
                field:"",
                title:"序号",
                formatter:function (value,row,index) {
                    return index+1;
                }
            },{
                field:"auditNum",
                title:"质检量"
            },{
                field:"errorNum",
                title:"错误量",
            },{
                field:"correctRate",
                title:"合格率",
                formatter:function (value,row,index) {
                    return value !== 0 ? value+'%' : value;
                }
            }]
        });
    };
    //加载图表
    function loadKPIReportCharts(data) {
        //解析出需要展示的数据
        var xArray = [];//月份
        var yArray = [];//合格率
        $.each(data,function () {
            xArray.push(this.month);
            yArray.push(this.correctRate);
        });
        var dataJson = {
            xDataArray:xArray,
            yDataArray:yArray,
            year:$("#year_search_select").val(),
            type:$("#workType_search_select").val()
        };
        // 指定图表的配置项和数据
        kpiReportEChart.setOption({
            title : {
                text: dataJson.year+'年KPI情况',
                subtext: '最新统计结果',
                x:'right'
            },
            tooltip : {
                trigger: 'item',
                formatter: '{a} <br/>{b}周 : {c}'
            },
            xAxis : [
                {
                    type: 'category',
                    name: '月份',
                    splitLine: {show: false},
                    data: dataJson.xDataArray
                }
            ],
            yAxis: {
                name: '合格率（%）',
                type:'value',
                min:function (value) {
                    return value.min-0.5;
                },
                max:function (value) {
                    return 100;
                }
            },
            series: [
                {
                    name: '目标值',
                    type: 'line',
                    data: [99.50,99.50,99.50,99.50,99.50,99.50,99.50,99.50,99.50,99.50,99.50,99.50]
                },{
                    name:'实际值',
                    type:'line',
                    data:dataJson.yDataArray
                }
            ]

        });
    };
    $("#workType_search_select,#year_search_select").change(function () {
        //刷新表格数据
        $("#table_list").bootstrapTable("refresh");
        //重新加载报表数据
        setTimeout(function () {
            loadKPIReportCharts(originalKPIData);
        },500);
    });
</script>
</body>
</html>
