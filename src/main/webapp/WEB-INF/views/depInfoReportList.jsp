<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/11/26
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="head.jsp"%>
    <title>深度信息品质周报</title>
    <%--针对手机屏幕的设置--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <%--基础css--%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet"/>
    <%--bootstrap表格的css--%>
    <link href="${basePath}/css/bootstrapTable/1.2.4/bootstrap-table.min.css" rel="stylesheet"/>
    <%--字体和图标的css--%>
    <link href="${basePath}/css/font-awesome.min2.css" rel="stylesheet"/>
    <%--日期插件的css--%>
    <link href="${basePath}/css/bootstrap-datepicker3.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container-fluid">
    <%--表格数据--%>
    <table id="table_list"></table>
</div>
<%--基础js文件--%>
<script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
<script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<%--bootstrap表格的js文件及本地化文件--%>
<script src="${basePath}/js/bootstrapTable/1.2.4/bootstrap-table.min.js"></script>
<%--本地化文件要放在bootstrap文件下--%>
<script src="${basePath}/js/bootstrapTable/1.2.4/locale/bootstrap-table-zh-CN.min.js"></script>
<%--日期插件js文件及本地化文件--%>
<script src="${basePath}/js/bootstrap-datepicker/1.6.4/bootstrap-datepicker.min.js"></script>
<script src="${basePath}/js/bootstrap-datepicker/1.6.4/locale/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript">
    $(function(){
        //初始化表格
        initTable();
        //修改表格样式
        initTableStyle();
    });
    //表格样式配置
    function initTableStyle(){

    }
    //设置日期
    $(".datepicker").datepicker({
        autoclose:true,
        format:"yyyy/mm/dd",
        language:"zh-CN"
    });

    //设置发送请求时的参数，当queryParamsType 为limit时
    // params中的参数为{ search: undefined, sort: undefined, order: "asc", offset: 0, limit: 10 }
    function myQueryParams(params){
        return {
            pageSize:this.pageSize,       //每页的记录行数
            pageNum:this.pageNumber,     //当前页数
            keyword:params.search
        };
    }
    //设置从服务器返回的数据rows:数据集合，total总记录数
    function myResponseHandler(result){
        if(result){
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
            url:"${basePath}/depInfoReports",
            method: 'get',//请求方式
            toolbar: '#toolbar',                //工具按钮用哪个容器
//            classes:"table table-hover table-bordered",        //设置table的class属性
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: myQueryParams,         //传递参数（*）
            responseHandler:myResponseHandler,  //设置解析服务器返回的数据
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
//            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"version",
                title:"作业版本"
            },{
                field:"section",
                title:"项目组"
            },{
                field:"workerId",
                title:"作业员编号",
            },{
                field:"worker",
                title:"作业员"
            },{
                field:"workDate",
                title:"质检日期",
                formatter:dateFormatter
            },{
                field:"depInfo",
                title:"深度信息通用"
            },{
                field:"dcomAuditNum",
                title:"深度信息通用质检量"
            },{
                field:"dcomErrorNum",
                title:"深度信息通用错误量"
            },{
                field:"dcomPassRate",
                title:"深度信息通用合格率"
            },{
                field:"dpAuditNum",
                title:"深度信息停车场质检量"
            },{
                field:"dpErrorNum",
                title:"深度信息停车场错误量"
            },{
                field:"dpPassRate",
                title:"深度信息停车场合格率"
            },{
                field:"dcarAuditNum",
                title:"深度信息汽车租赁质检量"
            },{
                field:"dcarErrorNum",
                title:"深度信息汽车租赁错误量"
            },{
                field:"dcarPassNum",
                title:"深度信息汽车租赁合格率"
            }]
        });
    }
</script>
</body>
</html>
