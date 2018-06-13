<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/1/21
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--静态include,先把jsp页面包含进来再进行编译运行并显示--%>
    <%@include file="../common/head.jsp" %>
    <%@include file="../common/common.jsp"%>
    <title>模块配置</title>
</head>
<body>
<div class="container-fluid">
    <%--自定义表格工具栏--%>
    <div id="toolbar" class="btn-group">
        <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
        <button id="add_permission_btn" type="button" class="btn btn-info"><span class="fa fa-cog" aria- hidden="true" ></span >添加权限</button >
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
                <h4 class="modal-title" id="myModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;新增</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="add_form">
                            <div class="form-group form-group-sm">
                                <label for="moduleId_insert_input" class="col-sm-2 control-label">模块ID</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="moduleId" id="moduleId_insert_input" >
                                </div>
                                <label for="moduleName_insert_input" class="col-sm-2 control-label">模块名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="moduleName" id="moduleName_insert_input" >
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="isShow_insert_select" class="col-sm-2 control-label">是否显示</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="isShow" id="isShow_insert_select">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                                <label for="modulePid_insert_input" class="col-sm-2 control-label">父模块ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="modulePid" id="modulePid_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="sortIndex_insert_input" class="col-sm-2 control-label">排序</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="sortIndex" id="sortIndex_insert_input"/>
                                </div>
                                <label for="iconClass_insert_input" class="col-sm-2 control-label">图标类名</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="iconClass" id="iconClass_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="moduleUrl_insert_input" class="col-sm-2 control-label">模块URL</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="moduleUrl" id="moduleUrl_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="moduleRemark_insert_textarea" class="col-sm-2 control-label">模块备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="moduleRemark" id="moduleRemark_insert_textarea"></textarea>
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
                <h4 class="modal-title" id="myEditModalLabel"><span class="fa fa-pencil-square-o fa-lg"></span>&nbsp;修改</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="edit_form">
                            <div class="form-group form-group-sm">
                                <label for="moduleId_update_p" class="col-sm-2 control-label">模块ID</label>
                                <div class="col-sm-4">
                                    <p class="form-control-static" id="moduleId_update_p"></p>
                                </div>
                                <label for="moduleName_update_input" class="col-sm-2 control-label">模块名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="moduleName" id="moduleName_update_input" >
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="isShow_update_select" class="col-sm-2 control-label">是否显示</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="isShow" id="isShow_update_select">
                                        <option value="1">是</option>
                                        <option value="0">否</option>
                                    </select>
                                </div>
                                <label for="modulePid_update_input" class="col-sm-2 control-label">父模块ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="modulePid" id="modulePid_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="sortIndex_update_input" class="col-sm-2 control-label">排序</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="sortIndex" id="sortIndex_update_input"/>
                                </div>
                                <label for="iconClass_update_input" class="col-sm-2 control-label">图标类名</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="iconClass" id="iconClass_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="moduleUrl_update_input" class="col-sm-2 control-label">模块URL</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="moduleUrl" id="moduleUrl_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="moduleRemark_update_textarea" class="col-sm-2 control-label">模块备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="moduleRemark" id="moduleRemark_update_textarea"></textarea>
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
<%--modal模态框(新增)--%>
<div class="modal fade" id="add_permission_modal" tabindex="-1" role="dialog" aria-labelledby="myAddPermissionModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myAddPermissionModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;新增权限</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="add_permission_form">
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label" for="permissionName_insert_input">权限ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="permissionName" id="permissionName_insert_input"/>
                                </div>
                                <label class="col-sm-2 control-label" for="description_insert_input">权限名称</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="description" id="description_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label" for="moduleId_permission_insert_input">模块ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control disabled" name="moduleId" id="moduleId_permission_insert_input" readonly="readonly"/>
                                </div>
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="status" id="status_insert_select">
                                        <option value="1">正常</option>
                                        <option value="0">禁用</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal" aria-label="Close">取消</button>
                <button type="button" class="btn btn-primary btn-sm" id="permission_save_btn" aria-label="save">保存</button>
            </div>
        </div>
    </div>
</div>
<%--modal模态框(修改)--%>
<div class="modal fade" id="edit_permission_modal" tabindex="-1" role="dialog" aria-labelledby="myEditPermissionModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myEditPermissionModalLabel"><span class="fa fa-pencil-square-o fa-lg"></span> 修改权限</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="edit_permission_form">
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label" for="permissionName_update_input">权限ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="permissionName" id="permissionName_update_input"/>
                                </div>
                                <label class="col-sm-2 control-label" for="description_update_input">权限名称</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="description" id="description_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label" for="moduleId_permission_update_input">模块ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="moduleId" id="moduleId_permission_update_input" readonly="readonly"/>
                                </div>
                                <label class="col-sm-2 control-label">状态</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="status" id="status_update_select">
                                        <option value="1">正常</option>
                                        <option value="0">禁用</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal" aria-label="Close">取消</button>
                <button type="button" class="btn btn-primary btn-sm" id="permission_update_btn" aria-label="update">更新</button>
            </div>
        </div>
    </div>
</div>
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

    //设置发送请求时的参数，当queryParamsType 为limit时
    // params中的参数为{ search: undefined, sort: undefined, order: "asc", offset: 0, limit: 10 }
    function myQueryParams(params){
        return {
            pageSize:this.pageSize,       //每页的记录行数
            pageNum:this.pageNumber,     //当前页数
            sort:params.sort,
            order:params.order,
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
            url:"${basePath}/sysModules",
            method: 'get',//请求方式
            toolbar: '#toolbar',                //工具按钮用哪个容器
//            classes:"table table-hover table-bordered",        //设置table的class属性
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
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
            uniqueId: "moduleId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: true,                   //是否显示父子表
            searchOnEnterKey:true,                 //设置为true，则按回车键进行搜索，否则自动搜索
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"moduleId",
                title:"模块ID"
            },{
                field:"moduleName",
                title:"模块名称"
            },{
                field:"isShow",
                title:"是否显示",
                formatter:function(value,row,index){
                    if("1"==value){
                        return "是";
                    }else if("0"==value){
                        return "否";
                    }else{
                        return "";
                    }
                }
            },{
                field:"modulePid",
                title:"父模块ID",
                sortable:true,
                sortName:"module_pid"
            },{
                field:"moduleUrl",
                title:"模块URL"
            },{
                field:"iconClass",
                title:"图标类名"
            },{
                field:"moduleRemark",
                title:"模块备注"
            },{
                field:"sortIndex",
                title:"排序"
            }],
            formatSearch:function(){
                return "搜索:模块名、父模块ID";
            },
            onExpandRow:function(index,row,$detail){
                initSubTable(index,row,$detail);
            }
        });
    }
    //初始化子表格配置function
    function initSubTable(index,row,$detail){
        var moduleId=row.moduleId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //获取数据的url
            url:"${basePath}/permissionByModuleId/"+moduleId,
            method: 'get',//请求方式
//            toolbar: '#toolbar',                //工具按钮用哪个容器
//            classes:"table table-hover table-bordered",        //设置table的class属性
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
//            pagination: true,                   //是否显示分页（*）
//            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: myQueryParams,         //传递参数（*）
            responseHandler:function(result){
                return {
                    rows:result.extend.entities,
                    total:result.extend.entities.length
                }
            },  //设置解析服务器返回的数据
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
//            search: true,                       //是否显示表格搜索
//            strictSearch: true,
//            showColumns: true,                  //是否显示所有的列
//            showRefresh: true,                  //是否显示刷新按钮
//            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
//            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "permissionId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
//            detailView: true,                   //是否显示父子表
//            searchOnEnterKey:true,                 //设置为true，则按回车键进行搜索，否则自动搜索
            columns:[                         //配置各列的属性
            {
                field:"permissionName",
                title:"权限ID"
            },{
                field:"description",
                title:"权限名称"
            },{
                field:"status",
                title:"状态",
                formatter:function(value,row,index){
                    if("1"==value){
                        return "正常";
                    }else if("0"==value){
                        return "禁用";
                    }else{
                        return "";
                    }
                }
            },{
                field:"moduleId",
                title:"操作",
                align:"center",
                width:"20%",
                events:operateEvents,
                formatter:operateFormatter
                }]
        });
    }
    //子表的操作按钮
    function operateFormatter(value,row,index){
        //当前行的主键
        var editBtn="<button id='permission_edit_btn' class='btn btn-primary'>修改</button>";
        var deleteBtn="<button id='permission_del_btn' class='btn btn-danger'>删除</button>";
        return editBtn+"&nbsp;"+deleteBtn;
    }
    //重写操作监听
    window.operateEvents={
        'click #permission_edit_btn':function(e,value,row,index){
            showEditPermissionModal(row);
        },
        'click #permission_del_btn':function(e,value,row,index){
            deletePermission(row);
        }
    };
    //显示权限编辑模态框
    function showEditPermissionModal(row){
        $("#edit_permission_modal").modal("toggle");
        //清空模态框
        $("#edit_permission_form")[0].reset();
        //初始化模态框
        $("#permissionName_update_input").val(row.permissionName);
        $("#description_update_input").val(row.description);
        $("#moduleId_permission_update_input").val(row.moduleId);
        $("#status_update_select").val(row.status);
        $("#permission_update_btn").attr("data-id",row.permissionId);
    }
    //更新权限
    $("#permission_update_btn").click(function () {
        $.ajax({
            url:"${basePath}/permission/"+$(this).attr("data-id"),
            type:"PUT",
            data:$("#edit_permission_form").serialize(),
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    alert("更新成功");
                    //关闭模态框
                    $("#edit_permission_modal").modal("toggle");
                    //刷新权限列表
                    $("#table_list").bootstrapTable("refresh");
                }
            },
            error:function (e) {
                alert("处理异常！异常代码："+e.status);
            }
        });
    });
    //添加权限按钮
    $("#add_permission_btn").click(function () {
        //判断是否选择了一条记录
        var selections=$("#table_list").bootstrapTable("getSelections")
        if(1==selections.length){
            //清空模态框
            $("#add_permission_form")[0].reset();
            //初始化模态框ID属性
            $("#moduleId_permission_insert_input").val(selections[0].moduleId);
            //弹出模态框
            $("#add_permission_modal").modal("toggle");
        }else{
            alert("选择单独一条记录");
        }
    });
    //添加权限的保存按钮
    $("#permission_save_btn").click(function () {
        $.ajax({
            url:"${basePath}/permission",
            type:"POST",
            data:$("#add_permission_form").serialize(),
            dataType:"json",
            success:function (result) {
                if(1==result.code){
                    alert("添加成功");
                    //隐藏模态框
                    $("#add_permission_modal").modal("toggle");
                    //刷新列表
                    $("#table_list").bootstrapTable("refresh");
                }
            },
            error:function (e) {
                alert("处理异常！异常代码："+e.status);
            }
        });
    });
    //删除权限
    function deletePermission(row){
        if(!confirm("确认要删除【"+row.description+"】权限吗？")){
            return;
        }
        $.ajax({
            url:"${basePath}/permission/"+row.permissionId,
            type:"DELETE",
            dataType:"json",
            success:function (result) {
                if(1==result.code){
                    alert("删除成功");
                    //刷新表格
                    $("#table_list").bootstrapTable("refresh");
                }
            },
            error:function (e) {
                alert("处理异常！异常代码："+e.status);
            }
        });
    }
    //显示新增模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.弹出模态框并清空表单
        $("#add_modal").modal("toggle");
    });
    //保存数据
    $("#save_btn").click(function(){
        //1.表单数据验证
        //2.发送ajax请求
        $.ajax({
            url:"${basePath}/sysModule",
            type:"POST",
            data:$("#add_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("处理成功");
                    $("#add_modal").modal("toggle");
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
            initEditDataDetailById(selections[0].moduleId);
            //显示模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("选择单独一条记录");
        }
    });
    //根据ID查询对应数据并显示与编辑modal上
    function initEditDataDetailById(dataId){
        $.ajax({
            url:"${basePath}/sysModule/"+dataId,
            type:"GET",
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    showEditDataDetail(result.extend.entities[0]);
                }else{
                    alert("请求失败");
                }
            }
        })
    }
    //在修改模态框上显示对象
    function showEditDataDetail(obj){
        $("#moduleId_update_p").text(obj.moduleId);
        $("#moduleName_update_input").val(obj.moduleName);
        $("#isShow_update_input").val(obj.isShow);
        $("#modulePid_update_input").val(obj.modulePid);
        $("#moduleUrl_update_input").val(obj.moduleUrl);
        $("#sortIndex_update_input").val(obj.sortIndex);
        $("#iconClass_update_input").val(obj.iconClass);
        $("#moduleRemark_update_textarea").val(obj.moduleRemark);
        $("#update_btn").attr("data-id",obj.moduleId);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单数据验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/sysModule/"+$(this).attr("data-id"),
            type:"PUT",
            data:$("#edit_form").serialize(),
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    alert("更新成功");
                    //隐藏模态框
                    $("#edit_modal").modal("toggle");
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
                    ids+=this.moduleId+",";
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
            url:"${basePath}/sysModule/"+ids,
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
    }
</script>
</body>
</html>
