<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/1/16
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
    <link href="${basePath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <title>工作日志</title>
</head>
<body>
<div class="container-fluid">
    <%--查询框--%>
    <div class="collapse" id="search_collapse">
        <div class="well">
            <div class="row">
                <div class="col-sm-11">
                    <form id="search_form" class="form-inline" role="form">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="empId_search_input" class="control-label">员工编号:</label>
                                    <input type="text" class="form-control" id="empId_search_input" name="empId"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="empName_search_input" class="control-label">员工姓名:</label>
                                    <input type="text" class="form-control" id="empName_search_input" name="empName"/>
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
                                    <label for="workType_search_select" class="control-label">工作类型:</label>
                                    <select class="form-control" name="workType" id="workType_search_select">
                                        <option value="">---选择工作类型---</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="workModule_search_select" class="control-label">工作模块</label>
                                    <select class="form-control" name="workModule" id="workModule_search_select">
                                        <option value="">---选择工作模块---</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="empId_search_input" class="control-label">工作日期:</label>
                                    <span>
                                        <div class="input-group input-group-sm" style="width: 77%;">
                                            <input type="text" class="form-control datepicker" name="workDateStart" id="workDateStart_search_input" />
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                            <input type="text" class="form-control datepicker" name="workDateEnd" id="workDateEnd_search_input" />
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                        </div>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="height: 5px;"></div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="workTimeStart_search_input" class="control-label">工作时间:</label>
                                    <div class="input-group input-group-sm" style="width: 77%;">
                                        <input class = "form-control datetimepicker" name="workTimeStart" id="workTimeStart_search_input"/>
                                        <div class="input-group-addon "><span class="fa fa-calendar"></span></div>
                                        <input class = "form-control datetimepicker" name="workTimeEnd" id="workTimeEnd_search_input"/>
                                        <div class="input-group-addon "><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-sm-offset-4">
                                <div class="btn btn-primary btn-sm" id="search_btn">查询</div>
                                <div class="btn btn-default btn-sm" id="search_reset_btn">清空</div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%--自定义表格工具栏--%>
    <div id="toolbar" class="btn-group">
        <shrio:hasPermission name="workDiary:add">
            <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="workDiary:edit">
                <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="workDiary:delete">
            <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
        </shrio:hasPermission>
        <button id="export_btn" class="btn btn-success"><span class="fa fa-file-excel-o"></span></span>导出excel</button>
        <button id="search_toggle_btn" class="btn btn-info"><span class="fa fa-search"></span>综合查询</button>
    </div>
    <%--表格数据--%>
    <table id="table_list"></table>
</div>
<%--modal模态框(新增)--%>
<div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;添加日志</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="add_form">
                            <div class="form-group form-group-sm">
                                <label for="empId_insert_input" class="col-sm-2 control-label">员工编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empId" id="empId_insert_input" placeholder="员工编号">
                                </div>
                                <label for="empName_insert_input" class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_insert_input" placeholder="员工姓名">
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="section_insert_select" class="col-sm-2 control-label">项目组</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="section" id="section_insert_select">
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
                                <label for="workType_insert_select" class="col-sm-2 control-label">工作类型</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="workType" id="workType_insert_select">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workDate_insert_input" class="col-sm-2 control-label">工作日期</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input class="form-control datepicker" type="text" name="workDate" id="workDate_insert_input"/>
                                        <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                                <label for="workModule_insert_select" class="control-label col-sm-2">工作模块</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="workModule" id="workModule_insert_select">

                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workTimeStart_insert_input" class="col-sm-2 control-label">工作时间(起)</label>
                                <div class="col-sm-4 ">
                                    <div class="input-group">
                                        <input class = "form-control datetimepicker" name="workTimeStart" id="workTimeStart_insert_input"/>
                                        <div class="input-group-addon "><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                                <label for="workTimeEnd_insert_input" class="col-sm-2 control-label">工作时间(止)</label>
                                <div class="col-sm-4 ">
                                    <div class="input-group">
                                        <input class = "form-control datetimepicker" name="workTimeEnd" id="workTimeEnd_insert_input"/>
                                        <div class="input-group-addon "><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workContent_insert_textarea" class="col-sm-2 control-label">工作内容</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="workContent" id="workContent_insert_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="remark_insert_textarea" class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="remark" id="remark_insert_textarea"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="save_btn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<%--modal模态框(修改)--%>
<div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myEditModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myEditModalLabel"><span class="fa fa-pencil-square-o"></span>&nbsp;修改日志</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="edit_form">
                            <div class="form-group form-group-sm">
                                <label for="empId_update_p" class="col-sm-2 control-label">员工编号</label>
                                <div class="col-sm-4">
                                    <p type="text" class="form-control-static" name="empId" id="empId_update_p"></p>
                                </div>
                                <label for="empName_update_input" class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_update_input" placeholder="员工姓名">
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="section_update_select" class="col-sm-2 control-label">项目组</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="section" id="section_update_select">
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
                                <label for="workType_update_select" class="col-sm-2 control-label">工作类型</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="workType" id="workType_update_select">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workDate_update_input" class="col-sm-2 control-label">工作日期</label>
                                <div class="col-sm-4">
                                    <div class="input-group" >
                                        <input class="form-control datepicker" type="text" name="workDate" id="workDate_update_input"/>
                                        <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                                <label for="workModule_update_select" class="control-label col-sm-2">工作模块</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="workModule" id="workModule_update_select">

                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workTimeStart_update_input" class="col-sm-2 control-label">工作时间(起)</label>
                                <div class="col-sm-4 ">
                                    <div class="input-group">
                                        <input class = "form-control datetimepicker" name="workTimeStart" id="workTimeStart_update_input"/>
                                        <div class="input-group-addon "><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                                <label for="workTimeEnd_update_input" class="col-sm-2 control-label">工作时间(止)</label>
                                <div class="col-sm-4 ">
                                    <div class="input-group">
                                        <input class = "form-control datetimepicker" name="workTimeEnd" id="workTimeEnd_update_input"/>
                                        <div class="input-group-addon "><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workContent_update_textarea" class="col-sm-2 control-label">工作内容</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="workContent" id="workContent_update_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="remark_update_textarea" class="col-sm-2 control-label">备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="remark" id="remark_update_textarea"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="update_btn" type="button" class="btn btn-primary">更新</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${basePath}/js/weboption.js"></script>
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
    //设置时间
    $(".datetimepicker").datetimepicker({
        autoclose:true,
        format:"hh:ii",
        language:"zh-CN",
        startView:1,
        minView:0,
        maxView:1
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
            url:"${basePath}/workDiaries",
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
            showExport:false,                    //显示导出
            exportDataType:"all",               //导出方式selected、all、basic
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"empId",
                title:"员工ID"
            },{
                field:"empName",
                title:"员工姓名"
            },{
                field:"section",
                title:"项目组"
            },{
                field:"workType",
                title:"工作类型",
            },{
                field:"workDate",
                title:"工作日期",
                formatter:dateFormatter
            },{
                field:"",
                title:"工作时间段",
                formatter:function (value,row,index) {
                    if(row.workTimeStart || row.workTimeEnd){
                        return row.workTimeStart+"-"+row.workTimeEnd;
                    }
                }
            },{
                field:"workHours",
                title:"工作用时"
            },{
                field:"workModule",
                title:"工作模块"
            },{
                field:"workContent",
                title:"工作内容"
            },{
                field:"remark",
                title:"备注"
            }],
            formatSearch:function(){
                return "搜索:姓名或项目组";
            }
        });
    }
    //显示新增模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.初始化用户的基本数据
        initUserInfo();
        initCurrentDate();
        //3.弹出模态框并清空表单
        $("#add_modal").modal("show");
    });
    //初始化用户的基本数据
    function initUserInfo(){
        $.ajax({
            url:"${basePath}/currentEmp",
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (1==result.code){
                    loadUserInfo(result.extend.entities[0]);
                }
            }
        });
    }
    function loadUserInfo(user) {
        $("#empId_insert_input").val(user.empId);
        $("#empName_insert_input").val(user.empName);
        $("#section_insert_select").val(user.empSec);
    }
    /**
     * 初始化当前日期
     */
    function initCurrentDate(){
        var  currentTime = new Date();
        var currentYear=currentTime.getFullYear();
        var currentMonth=currentTime.getMonth()+1;
        var currentDate=currentTime.getDate();
        $("#workDate_insert_input").val(currentYear+"/"+currentMonth+"/"+currentDate);
    }
    //校验form表单数据
    function validate_add_form(){
    }
    //保存数据
    $("#save_btn").click(function(){
        //1.表单数据验证
//        if (!validate_add_form()){
//            return;
//        };
        //2.发送ajax请求
        $.ajax({
            url:"${basePath}/workDiary",
            type:"POST",
            data:$("#add_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("处理成功");
                    $("#add_modal").modal("hide");
                    //刷新数据
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("处理失败");
                }
            },
            error:function(e){
                alert("处理异常！异常代码："+e.status);
            }
        });
    });
    //显示修改模态框
    $("#edit_btn").click(function(){
        //判断是否选择了一条记录
        var selections=$("#table_list").bootstrapTable("getSelections")
        if(1==selections.length){
            //初始化数据
            initEditDataDetailById(selections[0].id);
            //显示模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("选择单独一条记录");
        }
    });
    //根据ID查询对应数据并显示与编辑modal上
    function initEditDataDetailById(dataId){
        $.ajax({
            url:"${basePath}/workDiary/"+dataId,
            type:"GET",
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    showEditDataDetail(result.extend.workDiaries[0]);
                }else{
                   alert("请求失败");
                }
            },
            error:function(e){
                alert("处理异常！异常代码："+e.status);
            }
        })
    };
    //在修改模态框上显示对象
    function showEditDataDetail(obj){
        $("#empId_update_p").text(obj.empId);
        $("#empName_update_input").val(obj.empName);
        $("#section_update_select").val(obj.section);
        $("#workType_update_select").val(obj.workType);
        $("#workModule_update_select").val(obj.workModule);
        $("#workDate_update_input").val(dateFormatter(obj.workDate));
        $("#workHours_update_input").val(obj.workHours);
        $("#workTimeStart_update_input").val(obj.workTimeStart);
        $("#workTimeEnd_update_input").val(obj.workTimeEnd);
        $("#workContent_update_textarea").val(obj.workContent);
        $("#remark_update_textarea").val(obj.remark);
        $("#update_btn").attr("data-id",obj.id);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单数据验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/workDiary/"+$(this).attr("data-id"),
            type:"PUT",
            data:$("#edit_form").serialize(),
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    alert("更新成功");
                    //隐藏模态框
                    $("#edit_modal").modal("hide");
                    //刷新列表
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("更新失败！");
                }
            },
            error:function(e){
                alert("处理异常！异常代码："+e.status);
            }
        });
    });
    //删除按钮
    $("#del_btn").click(function(){
        var selects=$("#table_list").bootstrapTable("getSelections")
        if(selects.length>0){
            if(confirm("是否删除"+selects.length+"条数据")){
                //删除数据
                var ids="";
                $(selects).each(function(){
                    ids+=this.id+",";
                })
                //去除多余的分隔符
                ids=ids.substring(0,ids.length-1);
                deleteDataFun(ids);
            }
        }else{
            alert("请至少选择一条数据");
        }
    });
    //删除指定数据
    function deleteDataFun(ids){
        $.ajax({
            url:"${basePath}/workDiary/"+ids,
            type:"DELETE",
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    alert("删除成功！");
                    //刷新页面
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("删除失败！");
                }
            },
            error:function(e){
                alert("处理异常！异常代码："+e.status);
            }
        });
    };
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
        window.location.href="${basePath}/workDiaryExcel?"+$("#search_form").serialize();
    });
    //初始化下拉选
    $("#workType_search_select,#workType_insert_select,#workType_update_select").weboption({search:{dictType:"工作类型"},append:true});
    //工作模块下拉选初始化
    $("#workModule_search_select,#workModule_insert_select,#workModule_update_select").weboption({search:{dictType:"工作模块"},append:true});
</script>
</body>
</html>
