<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/9/25
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
    <title>员工列表</title>
</head>
<body>
<div class="container-fluid">
    <%--查询面板--%>
    <div class="collapse" id="search_collapse">
        <div class="well">
            <div class="row">
                <div class="col-sm-11">
                    <form class="form-inline" id="search_form">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="empIdSearch" class="control-label">员工编号:</label>
                                    <input type="text" class="form-control" id="empIdSearch" name="empId"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <label for="empNameSearch" class="control-label">员工姓名:</label>
                                    <input type="text" class="form-control" id="empNameSearch" name="empName"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                <label for="empEntryAge_search_input1" class="control-label">员工司龄:</label>
                                <span>
                                    <input type="text" class="form-control" id="empEntryAge_search_input1" name="empEntryAgeStart" style="width:37%" placeholder="司龄区间"/>&nbsp;
                                    <input type="text" class="form-control" id="empEntryAge_search_input2" name="empEntryAgeEnd" style="width:37%" placeholder="司龄区间"/>
                                </span>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="height: 5px"></div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <label for="empDep_search_select" class="control-label">所属部门:</label>
                                    <select class="form-control" id="empDep_search_select" name="empDept">
                                        <option value="">---选择部门---</option>
                                        <option value="数据库制作部">数据库制作部</option>
                                        <option value="数据库制作部西安分部">数据库制作部西安分部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <label for="empSec_search_select" class="control-label">所属科室:</label>
                                    <select class="form-control" id="empSec_search_select" name="empSec">
                                        <option value="">---选择所属科室---</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <label for="empHiredateStartSearch" class="control-label">入职时间:</label>
                                    <span>
                                        <div class="input-group input-group-sm" style="width: 77%;">
                                            <input type="text" class="form-control datepicker"  id="empHiredateStartSearch" name="empHiredateStart"/>
                                            <div class="input-group-addon"><span class="fa fa-calendar fa-sm"></span></div>
                                            <input type="text" class="form-control datepicker" id="empHiredateEndSearch" name="empHiredateEnd" aria-label="入职时间止"/>
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                        </div>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="height: 5px"></div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <label for="empPost_search_select" class="control-label">职务名称:</label>
                                    <select class="form-control" id="empPost_search_select" name="empPost">
                                        <option value="">---选择职务名称---</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="empSex_search_select" class="control-label">员工性别:</label>
                                    <select class="form-control" id="empSex_search_select" name="empSex">
                                        <option value="">---选择员工性别---</option>
                                        <option value="男">男</option>
                                        <option value="女">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <div >
                                        <div class="btn btn-primary btn-sm" id="search_btn">查询</div>
                                        <div class="btn btn-default btn-sm" id="search_reset_btn">清空</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                </form>
            </div>
            </div>
        </div>
    </div>

    <%--自定义表格工具栏--%>
    <div id="toolbar" class="btn-group">
        <shrio:hasPermission name="emp:add">
            <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="emp:edit">
            <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="emp:delete">
            <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
        </shrio:hasPermission>
        <shrioDiy:hasAnyPermission name="emp:export,emp:export:section,emp:export:dept">
            <button id="export_btn" class="btn btn-success"><span class="fa fa-file-excel-o"></span></span>导出excel</button>
        </shrioDiy:hasAnyPermission>
        <button id="search_toggle_btn" class="btn btn-info"><span class="fa fa-search"></span>综合查询</button>
    </div>
    <%--表格数据--%>
    <table id="table_list"></table>
</div>
<%--modal模态框(员工添加)--%>
<div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;添加员工</h4>
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
                                <label class="col-sm-2 control-label">员工性别</label>
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" name="empSex" id="empSex_insert_radio" value="男" checked="checked">男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="empSex" id="empSex_insert_radio2" value="女"> 女
                                    </label>
                                </div>
                                <label for="empHiredate_insert_input" class="col-sm-2 control-label">入职时间</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control datepicker" name="empHiredate" id="empHiredate_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empEntryAge_insert_input" class="col-sm-2 control-label">员工司龄</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="number" name="empEntryAge" id="empEntryAge_insert_input"/>
                                </div>
                                <label for="empDep_insert_select" class="control-label col-sm-2">所属部门</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="empDep_insert_select" name="empDept">
                                        <option value="数据库制作部">数据库制作部</option>
                                        <option value="数据库制作部西安分部">数据库制作部西安分部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empSec_insert_select" class="control-label col-sm-2">所属科室</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="empSec_insert_select" name="empSec">
                                    </select>
                                </div>
                                <label for="empPost_insert_select" class="control-label col-sm-2">职务名称</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="empPost_insert_select" name="empPost">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group  form-group-sm">
                                <label for="empTel_insert_input" class="control-label col-sm-2">联系方式</label>
                                <div class="col-sm-10">
                                    <input type="tel" class="form-control" id="empTel_insert_input" name="empTel"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empAddress_insert_input" class="control-label col-sm-2">现住地址</label>
                                <div class="col-sm-10">
                                    <textarea id="empAddress_insert_input" class="form-control" name="empAddress" rows="3" ></textarea>
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
<%--modal模态框(员工修改)--%>
<div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2"><span class="fa fa-pencil-square-o fa-lg"></span>&nbsp;员工修改</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="edit_form">
                            <div class="form-group form-group-sm">
                                <label for="empId_update_p" class="col-sm-2 control-label">员工编号</label>
                                <div class="col-sm-4">
                                    <p class="form-control-static" id="empId_update_p"></p>
                                </div>
                                <label for="empName_update_input" class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_update_input" placeholder="员工姓名">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">员工性别</label>
                                <div class="col-sm-4">
                                    <label class="radio-inline">
                                        <input type="radio" name="empSex" id="empSex_update_radio" value="男" checked="checked">男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="empSex" id="empSex_update_radio2" value="女"> 女
                                    </label>
                                </div>
                                <label for="empHiredate_update_input" class="col-sm-2 control-label">入职时间</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control datepicker" name="empHiredate" id="empHiredate_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empEntryAge_update_input" class="col-sm-2 control-label">员工司龄</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="number" name="empEntryAge" id="empEntryAge_update_input"/>
                                </div>
                                <label for="empDep_update_select" class="control-label col-sm-2">所属部门</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="empDep_update_select" name="empDept">
                                        <option value="数据库制作部">数据库制作部</option>
                                        <option value="数据库制作部西安分部">数据库制作部西安分部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empSec_update_select" class="control-label col-sm-2">所属科室</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="empSec_update_select" name="empSec">
                                    </select>
                                </div>
                                <label for="empPost_update_select" class="control-label col-sm-2">职务名称</label>
                                <div class="col-sm-4">
                                    <select class="form-control" id="empPost_update_select" name="empPost">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empTel_update_select" class="control-label col-sm-2">联系方式</label>
                                <div class="col-sm-10">
                                    <input type="tel" class="form-control" id="empTel_update_select" name="empTel"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="empAddress_update_textarea" class="control-label col-sm-2">现住地址</label>
                                <div class="col-sm-10">
                                    <textarea id="empAddress_update_textarea" class="form-control" name="empAddress" rows="3"></textarea>
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
<script>
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
            url:"${basePath}/emps",
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
            uniqueId: "empId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            searchOnEnterKey:true,               //按entry键搜索
            showExport:false,                    //显示导出
            exportDataType:"all",               //导出方式selected、all、basic
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"empId",
                title:"员工编号"
            },{
                field:"empName",
                title:"员工姓名"
            },{
                field:"empSex",
                title:"性别"
            },{
                field:"empHiredate",
                title:"入职时间",
                formatter:dateFormatter
            },{
                field:"empEntryAge",
                title:"员工司龄",
                formatter:calEntryAge
            },{
                field:"empPost",
                title:"职务名称"
            },{
                field:"empSec",
                title:"科室"
            },{
                field:"empDept",
                title:"部门"
            },{
                field:"empTel",
                title:"联系方式"
            },{
                field:"empAddress",
                title:"现住地址"
            }]
        });
    }
    //计算工龄
    function calEntryAge(value,row,index){
        var millis = new Date()-new Date(row.empHiredate);
        var betweenYears = millis/(3600*24*365*1000)+"";
        return betweenYears.substring(0,betweenYears.indexOf(".") != -1?betweenYears.indexOf("."):betweenYears.length);
    }
    //显示模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.弹出模态框
        $("#add_modal").modal();
        //3.初始化下拉选
        $("#empDep_insert_select").change();
    });
    //展开或收缩查询面板
    $("#search_toggle_btn").click(function(){
        $("#search_collapse").collapse('toggle');
    });
    //保存按钮
    $("#save_btn").click(function(){
        //1.表单验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/emp",
            type:"POST",
            data:$("#add_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("处理成功");
                    //关闭新增模态框
                    $("#add_modal").modal("toggle");
                    //刷新列表并翻到最后一页
                    $("#table_list").bootstrapTable("refresh",{
                        pageNumber:$("#table_list").bootstrapTable("getOptions").totalRows
                    })
                }else{
                    alert("处理失败");
                }
            }
        });
    });
    //编辑按钮
    $("#edit_btn").click(function(){
        //获取选中的行
        var selections=$("#table_list").bootstrapTable("getSelections");
        if(selections.length==1){
            //获取指定员工信息
            getEmpById(selections[0].empId);
            //显示编辑模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("选择单独一条记录");
        }
    });
    //根据ID获取单个信息
    function getEmpById(id){
        $.ajax({
            url:"${basePath}/emp/"+id,
            type:"GET",
            dataType:"json",
            success:function (result) {
                if(result.code==1){
                    show_emp_update_info(result.extend.emps);
                }else{
                    alert("请求失败！");
                }
            }
        });
    }
    //显示编辑界面的员工信息
    function show_emp_update_info(empInfo){
        $("#empId_update_p").text(empInfo[0].empId);
        $("#empName_update_input").val(empInfo[0].empName);
        $("#edit_modal input[name=empSex]").val([empInfo[0].empSex]);
        $("#empHiredate_update_input").val(dateFormatter(new Date(empInfo[0].empHiredate)));
        $("#empEntryAge_update_input").val(empInfo[0].empEntryAge);
        $("#empDep_update_select").val(empInfo[0].empDept);
        //触发部门下拉选的单击事件
        $("#empDep_update_select").change();
        $("#empSec_update_select").val(empInfo[0].empSec);
        //触发科室下拉选的单击事件
        $("#empSec_update_select").change();
        $("#empPost_update_select").val(empInfo[0].empPost);
        $("#empTel_update_select").val(empInfo[0].empTel);
        $("#empAddress_update_textarea").val(empInfo[0].empAddress);
        //给更新按钮添加个update-id属性用来存储主键empId
        $("#update_btn").attr("edit-id",empInfo[0].empId);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单校验
        //2.发送请求
        $.ajax({
            url:"${basePath}/emp/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#edit_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("处理成功");
                    //关闭修改模态框
                    $("#edit_modal").modal("toggle");
                    //刷新当前的列表
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("处理失败");
                }
            }
        });
    });
    //删除按钮
    $("#del_btn").click(function(){
        var selects=$("#table_list").bootstrapTable("getSelections");
        if(selects.length>0){
            if(confirm("是否删除"+selects.length+"条数据")){
                //删除数据
                var ids="";
                $(selects).each(function(){
                    ids+=this.empId+",";
                })
                //去除多余的分隔符
                ids=ids.substring(0,ids.length-1);
                del_emp(ids);
            }
        }else{
            alert("至少选择一条记录");
        }
    });
    //删除用户
    function del_emp(ids){
        $.ajax({
            url:"${basePath}/emp/"+ids,
            type:"DELETE",
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("成功删除");
                    //刷新列表
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("处理失败");
                }
            }
        });
    }
    //部门职位、科室、职务下拉选
    var deptMap={
        "数据库制作部":{
            "部门":["部门经理","部门副经理","部门经理助理","部门行政助理"],
            "北京项目一组":["数据工程师","项目经理助理","项目经理"],
            "北京项目二组":["数据工程师","项目经理助理","项目经理"],
            "北京项目三组":["数据工程师","项目经理助理","项目经理"],
            "北京项目四组":["数据工程师","项目经理助理","项目经理"],
            "武汉项目组":["数据工程师","项目经理助理","项目经理"],
            "互联网小组":["数据工程师","项目经理助理","项目经理"],
            "代理店":["数据工程师","项目经理助理","项目经理"],
            "品质管理室（北京）":["室经理","室副经理","品质工程师"],
            "生产管理室（北京）":["室经理","室副经理","生产管理工程师"],
            "技术管理室（北京）":["室经理","室副经理","技术工程师"]
        },
        "数据库制作部西安分部":{
            "部门":["部门经理","部门副经理","部门行政助理"],
            "西安项目一组":["数据工程师","项目经理助理","项目经理"],
            "西安项目二组":["数据工程师","项目经理助理","项目经理"],
            "西安项目三组":["数据工程师","项目经理助理","项目经理"],
            "西安项目四组":["数据工程师","项目经理助理","项目经理"],
            "西安项目五组":["数据工程师","项目经理助理","项目经理"],
            "西安项目六组":["数据工程师","项目经理助理","项目经理"],
            "综合项目组":["数据工程师","项目经理助理","项目经理"],
            "品质管理室（西安）":["室经理","室副经理","品质工程师"],
            "生产管理室（西安）":["室经理","室副经理","生产管理工程师"],
            "技术管理室（西安）":["室经理","室副经理","技术工程师"]
        }
    };
    //部门修改关联科室下拉选（修改界面）
    $("#empDep_update_select").change(function(){
        $("#empSec_update_select").empty();
        var secMap=deptMap[this.value];
        for(var key in secMap){
            $("#empSec_update_select").append("<option value='"+key+"'>"+key+"</option>");
        }
        //触发科室的单击事件
        $("#empSec_update_select").change();
    });
    //科室修改关联职务下拉选（修改界面）
    $("#empSec_update_select").change(function(){
        $("#empPost_update_select").empty();
        var deptSelected=$("#empDep_update_select").val();
        var secSelected=$("#empSec_update_select").val();
        var postArray=deptMap[deptSelected][secSelected];
        for(var index in postArray){
            $("#empPost_update_select").append("<option value='"+postArray[index]+"'>"+postArray[index]+"</option>")
        }
    });
    //部门修改关联科室下拉选（新增界面）
    $("#empDep_insert_select").change(function(){
        $("#empSec_insert_select").empty();
        var secMap=deptMap[this.value];
        for(var key in secMap){
            $("#empSec_insert_select").append("<option value='"+key+"'>"+key+"</option>");
        }
        //触发科室的单击事件
        $("#empSec_insert_select").change();
    });
    //科室修改关联职务下拉选(新增界面)
    $("#empSec_insert_select").change(function(){
        $("#empPost_insert_select").empty();
        var deptSelected=$("#empDep_insert_select").val();
        var secSelected=$("#empSec_insert_select").val();
        var postArray=deptMap[deptSelected][secSelected];
        for(var index in postArray){
            $("#empPost_insert_select").append("<option value='"+postArray[index]+"'>"+postArray[index]+"</option>")
        }
    });
    //部门修改关联科室下拉选（查询界面）
    $("#empDep_search_select").change(function(){
        $("#empSec_search_select").empty();
        $("#empSec_search_select").append("<option value=''>---选择所属科室---</option>")
        var secMap=deptMap[this.value];
        for(var key in secMap){
            $("#empSec_search_select").append("<option value='"+key+"'>"+key+"</option>");
        }
        //触发科室的单击事件
        $("#empSec_search_select").change();
    });
    //科室修改关联职务下拉选(查询界面)
    $("#empSec_search_select").change(function(){
        $("#empPost_search_select").empty();
        $("#empPost_search_select").append("<option value=''>---选择职务名称---</option>");
        var deptSelected=$("#empDep_search_select").val();
        var secSelected=$("#empSec_search_select").val();
        var postArray=deptMap[deptSelected][secSelected];
        for(var index in postArray){
            $("#empPost_search_select").append("<option value='"+postArray[index]+"'>"+postArray[index]+"</option>")
        }
    });
    //查询按钮
    $("#search_btn").click(function () {
        $("#table_list").bootstrapTable("refresh");
    });
    $("#search_reset_btn").click(function () {
        $("#search_form")[0].reset();
    });
    //导出excel按钮
    $("#export_btn").click(function () {
        window.location.href="${basePath}/empExcel?"+$("#search_form").serialize();
    });
</script>
</body>
</html>

