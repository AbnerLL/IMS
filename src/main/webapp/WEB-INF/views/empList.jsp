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
    <%@include file="head.jsp"%>
    <title>员工列表</title>
    <%--IE=edge告诉IE使用最新的引擎渲染网页，chrome=1则可以激活Chrome Frame--%>
    <%--<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />--%>
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
    <%--查询面板--%>
    <div class="collapse" id="search_collapse">
        <form class="form-inline">
            <div class="form-group">
                <label for="empIdSearch" class="control-label">编号</label>
                <input type="text" class="form-control" id="empIdSearch" name="empId"/>
            </div>
            <div class="form-group">
                <label for="empNameSearch" class="control-label">姓名</label>
                <input type="text" class="form-control" id="empNameSearch" name="empName"/>
            </div>
            <div class="form-group">
                <label for="empHiredateStartSearch" class="control-label">入职时间</label>
                <div class="input-group" >
                    <input type="text" class="form-control datepicker"  id="empHiredateStartSearch" name="empHiredateStart"/>
                    <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                </div> -
                <div class="input-group">
                    <input type="text" class="form-control datepicker" id="empHiredateEndSearch" name="empHiredateEnd" aria-label="入职时间止"/>
                    <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                </div>
            </div>
            <div class="form-group">
                <label for="empEntryageSearch" class="control-label">司龄</label>
                <input type="number" class="form-control" id="empEntryageSearch" name="empEntryage"/>
            </div>
            <div class="form-group">
                <label for="empPostSearch" class="control-label">职务</label>
                <select class="form-control" id="empPostSearch" name="empPost"></select>
            </div>
            <div class="form-group">
                <label for="empDepSearch" class="control-label">科室</label>
                <select class="form-control" id="empDepSearch" name="empDep"></select>
            </div>
            <div class="form-group">
                <label for="empSecSearch" class="control-label">所属部门</label>
                <select class="form-control" id="empSecSearch" name="empSec"></select>
            </div>
            <div class="btn btn-success" id="search_btn">查询</div>
            <div class="btn btn-default" id="search_reset_btn">清空</div>
        </form>
    </div>

    <%--自定义表格工具栏--%>
    <div id="toolbar" class="btn-group">
        <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
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
                <h4 class="modal-title" id="myModalLabel">添加员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="add_form">
                    <div class="form-group">
                        <label for="empId_insert_input" class="col-sm-2 control-label">员工编号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="empId" id="empId_insert_input" placeholder="员工编号">
                            <%--<span class="help-block"></span>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empName_insert_input" class="col-sm-2 control-label">员工姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="empName" id="empName_insert_input" placeholder="员工姓名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-8">
                            <label class="radio-inline">
                                <input type="radio" name="empSex" id="empSex_insert_radio" value="男" checked="checked">男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="empSex" id="empSex_insert_radio2" value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empHiredate_insert_input" class="col-sm-2 control-label">入职时间</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control datepicker" name="empHiredate" id="empHiredate_insert_input"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empEntryAge_insert_input" class="col-sm-2 control-label">司龄</label>
                        <div class="col-sm-8">
                            <input class="form-control" type="number" name="empEntryAge" id="empEntryAge_insert_input"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empPost_insert_select" class="control-label col-sm-2">职务名称</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="empPost_insert_select" name="empPost">
                                <option value="部门经理">部门经理</option>
                                <option value="科室经理">科室经理</option>
                                <option value="项目经理">项目经理</option>
                                <option value="项目助理">项目助理</option>
                                <option value="数据工程师">数据工程师</option>
                                <option value="质量工程师">质量工程师</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empSec_insert_select" class="control-label col-sm-2">所属科室</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="empSec_insert_select" name="empSec">
                                <option value="品质管理室">品质管理室</option>
                                <option value="项目一组">项目一组</option>
                                <option value="项目二组">项目二组</option>
                                <option value="项目三组">项目三组</option>
                                <option value="项目四组">项目四组</option>
                                <option value="项目五组">项目五组</option>
                                <option value="项目六组">项目六组</option>
                                <option value="武汉项目组">武汉项目组</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empDep_insert_select" class="control-label col-sm-2">所属部门</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="empDep_insert_select" name="empDept">
                                <option value="北京数据库制作部">北京数据库制作部</option>
                                <option value="武汉数据库制作部">武汉数据库制作部</option>
                                <option value="西安数据库制作部">西安数据库制作部</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empTel_insert_input" class="control-label col-sm-2">联系方式</label>
                        <div class="col-sm-8">
                            <input type="tel" class="form-control" id="empTel_insert_input" name="empTel"/>
                        </div>
                    </div>
                </form>
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
                <h4 class="modal-title" id="myModalLabel2">员工修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_form">
                    <div class="form-group">
                        <label for="empId_update_p" class="col-sm-2 control-label">员工编号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" id="empId_update_p"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empName_update_input" class="col-sm-2 control-label">员工姓名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="empName" id="empName_update_input" placeholder="员工姓名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-8">
                            <label class="radio-inline">
                                <input type="radio" name="empSex" id="empSex_update_radio" value="男" checked="checked">男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="empSex" id="empSex_update_radio2" value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empHiredate_update_input" class="col-sm-2 control-label">入职时间</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control datepicker" name="empHiredate" id="empHiredate_update_input"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empEntryAge_update_input" class="col-sm-2 control-label">司龄</label>
                        <div class="col-sm-8">
                            <input class="form-control" type="number" name="empEntryAge" id="empEntryAge_update_input"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empPost_update_select" class="control-label col-sm-2">职务名称</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="empPost_update_select" name="empPost">
                                <option value="部门经理">部门经理</option>
                                <option value="科室经理">科室经理</option>
                                <option value="项目经理">项目经理</option>
                                <option value="项目助理">项目助理</option>
                                <option value="数据工程师">数据工程师</option>
                                <option value="质量工程师">质量工程师</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empSec_update_select" class="control-label col-sm-2">所属科室</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="empSec_update_select" name="empSec">
                                <option value="品质管理室">品质管理室</option>
                                <option value="项目一组">项目一组</option>
                                <option value="项目二组">项目二组</option>
                                <option value="项目三组">项目三组</option>
                                <option value="项目四组">项目四组</option>
                                <option value="项目五组">项目五组</option>
                                <option value="项目六组">项目六组</option>
                                <option value="武汉项目组">武汉项目组</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empDep_update_select" class="control-label col-sm-2">所属部门</label>
                        <div class="col-sm-8">
                            <select class="form-control" id="empDep_update_select" name="empDept">
                                <option value="北京数据库制作部">北京数据库制作部</option>
                                <option value="武汉数据库制作部">武汉数据库制作部</option>
                                <option value="西安数据库制作部">西安数据库制作部</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="empTel_update_select" class="control-label col-sm-2">联系方式</label>
                        <div class="col-sm-8">
                            <input type="tel" class="form-control" id="empTel_update_select" name="empTel"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="update_btn" type="button" class="btn btn-primary">更新</button>
            </div>
        </div>
    </div>
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
                title:"员工司龄"
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
            }]
        });
    }
    //显示模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.弹出模态框
        $("#add_modal").modal();
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
        $("#empPost_update_select").val(empInfo[0].empPost);
        $("#empDep_update_select").val(empInfo[0].empDept);
        $("#empSec_update_select").val(empInfo[0].empSec);
        $("#empTel_update_select").val(empInfo[0].empTel);
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
</script>
</body>
</html>

