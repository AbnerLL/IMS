<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/2/27
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>设施品质</title>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
</head>
<body>
<div class="container-fluid">
    <%--导航栏--%>
    <%@include file="include/workQualityTab.jsp"%>
    <%--查询框--%>
    <div class="collapse" id="search_collapse">
        <div class="well">
            <div class="row">
                <div class="col-sm-11">
                    <form id="search_form" class="form-inline" role="form">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="workerId_search_input" class="control-label">员工编号:</label>
                                    <input type="text" class="form-control" id="workerId_search_input" name="workerId"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="worker_search_input" class="control-label">员工姓名:</label>
                                    <input type="text" class="form-control" id="worker_search_input" name="worker"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="section_search_select" class="control-label">项目组:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <select class="form-control" name="section" id="section_search_select">
                                        <option value="">---请选择项目组---</option>
                                        <option value="部门">部门</option>
                                        <option value="北京项目一组">北京项目一组</option>
                                        <option value="北京项目二组">北京项目二组</option>
                                        <option value="北京项目三组">北京项目三组</option>
                                        <option value="北京项目四组">北京项目四组</option>
                                        <option value="北京项目五组">北京项目五组</option>
                                        <option value="武汉项目组">武汉项目组</option>
                                        <option value="互联网小组">互联网小组</option>
                                        <option value="代理店">代理店</option>
                                        <option value="品质管理室（北京）">品质管理室（北京）</option>
                                        <option value="生产管理室（北京）">生产管理室（北京）</option>
                                        <option value="技术管理室（北京）">技术管理室（北京）</option>
                                        <option value="西安项目一组">西安项目一组</option>
                                        <option value="西安项目二组">西安项目二组</option>
                                        <option value="西安项目三组">西安项目三组</option>
                                        <option value="西安项目四组">西安项目四组</option>
                                        <option value="西安项目五组">西安项目五组</option>
                                        <option value="西安项目六组">西安项目六组</option>
                                        <option value="综合项目组">综合项目组</option>
                                        <option value="品质管理室（西安）">品质管理室（西安）</option>
                                        <option value="生产管理室（西安）">生产管理室（西安）</option>
                                        <option value="技术管理室（西安）">技术管理室（西安）</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="height: 5px;"></div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="version_search_input" class="control-label">作业版本:</label>
                                    <input type="text" class="form-control" id="version_search_input" name="version" />
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="statDateStart_search_input" class="control-label">统计日期:</label>
                                    <span>
                                        <div class="input-group input-group-sm" style="width: 77%;">
                                            <input type="text" class="form-control datepicker" name="statDateStart" id="statDateStart_search_input" />
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                            <input type="text" class="form-control datepicker" name="statDateEnd" id="statDateEnd_search_input" />
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                        </div>
                                    </span>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="btn btn-primary btn-sm" id="search_btn">查询</div>
                                <div class="btn btn-default btn-sm" id="search_reset_btn">清空</div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="toolbar" class="btn-group">
        <button id="export_btn" class="btn btn-success"><span class="fa fa-file-excel-o"></span></span>导出excel</button>
        <button id="search_toggle_btn" class="btn btn-info"><span class="fa fa-search"></span>综合查询</button>
    </div>
    <%--表格数据--%>
    <table id="table_list"></table>
</div>
</body>
<script>
    $(function(){
        //初始化表格
        initTable();
        //修改表格样式
        initTableStyle();
        //初始化tab选项
        changeTabSelect();
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
//        return {
//            pageSize:this.pageSize,       //每页的记录行数
//            pageNum:this.pageNumber,     //当前页数
//            keyword:params.search
//        };
        var searchWord=params.search ? params.search:'';
        return $("#search_form").serialize()+'&pageSize='+this.pageSize+'&pageNum='+this.pageNumber+'&keyword='+searchWord;
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
            url:"${basePath}/poiQuality",
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
//            uniqueId: "empId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            searchOnEnterKey:true,               //按entry键搜索
//            showExport:false,                    //显示导出
//            exportDataType:"all",               //导出方式selected、all、basic
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"version",
                title:"版本"
            },{
                field:"workerId",
                title:"员工编号"
            },{
                field:"worker",
                title:"员工姓名"
            },{
                field:"section",
                title:"项目组"
            },{
                field:"cpPassRate",
                title:"中文名称合格率"
            },{
                field:"caPassRate",
                title:"中文地址合格率"
            },{
                field:"epPassRate",
                title:"英文名称合格率"
            },{
                field:"eaPassRate",
                title:"英文地址合格率"
            }]
        });
    };
    //修改tab的选项
    function changeTabSelect(){
        //去除li上的class
        $("ul li[class='active']").removeClass("active");
        $("#poiQuality").addClass("active");
    }
    //控制collapse
    $("#search_toggle_btn").click(function(){
        $("#search_collapse").collapse("toggle");
    });
    //查询
    $("#search_btn").click(function(){
        $("#table_list").bootstrapTable("refresh")
    });
    //清空
    $("#search_reset_btn").click(function () {
        $("#search_form")[0].reset();
    });
    //导出excel按钮
    $("#export_btn").click(function () {
        window.location.href="${basePath}/poiQualityExcel?"+$("#search_form").serialize();
    });
</script>
</html>
