<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/10/15
  Time: 22:05
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
    <%--自定义表格工具栏--%>
    <div id="toolbar" class="btn-group">
        <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
        <button id="add_role_btn" type="button" class="btn btn-info"><span class="fa fa-cog" aria- hidden="true" ></span >添加角色</button >
    </div>
    <%--表格数据--%>
    <table id="table_list"></table>
</div>
<%--modal模态框(用户添加)--%>
<div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;添加用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="add_form">
                    <div class="form-group">
                        <label for="id_insert_input" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="id" id="id_insert_input" placeholder="用户名">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password_insert_input" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" id="password_insert_input" placeholder="密码">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickname_insert_input" class="col-sm-2 control-label">昵称</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="nickname" id="nickname_insert_input"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">有效</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="status" id="isValid1_insert_input" value="1" checked="checked">是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="isValid2_insert_input" value="0"> 否
                            </label>
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
<%--模态框（用户编辑）--%>
<div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2"><span class="fa fa-pencil-square-o fa-lg"></span>&nbsp;编辑用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_from">
                    <div class="form-group">
                        <label for="username_update_p" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="username_update_p"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password_update_input" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" name="password" id="password_update_input" placeholder="密码">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickname_update_input" class="col-sm-2 control-label">昵称</label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="nickname" id="nickname_update_input"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">有效</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="status" id="isValid1_update_input" value="1" checked="checked">是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="isValid12_update_input" value="0"> 否
                            </label>
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
<%--给用户添加角色的模态框--%>
<div class="modal fade" id="add_role_modal" tabindex="-1" role="dialog" aria-labelledby="myAddRoleModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myAddRoleModalLabel2"><span class="fa fa-user fa-lg"></span>&nbsp;添加角色</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="add_role_from">
                            <div class="form-group">
                                <label for="username_update_p" class="col-sm-2 control-label">角色名</label>
                                <div class="col-sm-10">
                                    <select class="form-control" name="roleId" id="roleId_add_select" multiple>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="save_role_btn" type="button" class="btn btn-primary">添加</button>
            </div>
        </div>
    </div>
</div>
<div class=""></div>
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
    //    $(".datepicker").datepicker({
    //        autoclose:true,
    //        format:"yyyy/mm/dd",
    //        language:"zh-CN"
    //    });

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
    //时间格式化
    function dateFormatter(value,row,index){
        return new Date(value).toLocaleString();
    }

    //初始化表格配置function
    function initTable(){
        $("#table_list").bootstrapTable({
            //获取数据的url
            url:"${basePath}/users",
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
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
//            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: true,                   //是否显示父子表
            searchOnEnterKey:true,              //设置按回车键执行搜索，否则自动搜索
            columns:[{                          //配置各列的属性
                checkbox:true,
            },{
                field:"id",
                title:"用户名"
            },{
                field:"nickname",
                title:"昵称"
            },{
                field:"createTime",
                title:"创建时间",
                formatter:dateFormatter
            },{
                field:"status",
                title:"状态",
                formatter:function(value,row,index){
                    return 1==value?"有效":"无效";
                }
            }
            ],
            onExpandRow:function (index,row,$detail) {
                initSubTable(index,row,$detail);
                //在展开时只勾选中当行（为删除角色关联提供参数）
                initCurrentRowStatus(index);
            }
        });
    }
    function initCurrentRowStatus(index){
        $("#table_list").bootstrapTable("uncheckAll");
        $("#table_list").bootstrapTable("check",index);
    }
    //初始化子表格配置function
    function initSubTable(index,row,$detail){
        var userId=row.id;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //获取数据的url
            url:"${basePath}/role/userRole/"+userId,
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
            uniqueId: "roleId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
//            detailView: true,                   //是否显示父子表
//            searchOnEnterKey:true,                 //设置为true，则按回车键进行搜索，否则自动搜索
            columns:[                         //配置各列的属性
                {
                    field:"roleName",
                    title:"角色ID"
                },{
                    field:"description",
                    title:"角色名称"
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
                    field:"roleId",
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
//        var editBtn="<button id='role_edit_btn' class='btn btn-primary'>修改</button>";
        var deleteBtn="<button id='role_del_btn' class='btn btn-danger'>删除</button>";
        return deleteBtn;
    }
    //重写操作监听
    window.operateEvents={
//        'click #role_edit_btn':function(e,value,row,index){
//            showEditPermissionModal(row);
//        },
        'click #role_del_btn':function(e,value,row,index){
            deleteRole(row);
        }
    };
    //删除角色
    function deleteRole(row) {
        var selected=$("#table_list").bootstrapTable("getSelections");
        if(!confirm("是否删除【"+row.description+"】角色")){
            return ;
        }
        $.ajax({
           url:"${basePath}/roleRel?userId="+selected[0].id+"&roleId="+row.roleId,
           type:"DELETE",
           dataType:"json",
           success:function (result) {
               if (1==result.code){
                   alert("删除成功");
                   $("#table_list").bootstrapTable("refresh");
               }
           }
        });
    }
    //显示模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.弹出模态框
        $("#add_modal").modal();
    });
    //显示查询
    $("#search_toggle_btn").click(function(){
        $("#search_collapse").collapse('toggle');
    });
    //保存数据
    $("#save_btn").click(function(){
        //1.表单验证

        //2.发送ajax请求
        $.ajax({
            url:"${basePath}/user",
            type:"POST",
            data:$("#add_form").serialize(),
            dataType:"json",//指明返回数据的类型
            success:function(result){
                if(result.code==1){
                    alert("保存成功");
                    //关闭模态框
                    $("#add_modal").modal("hide");
                    //刷新列表并翻到最后一页
                    $("#table_list").bootstrapTable("refresh",{
                        pageNumber:$("#table_list").bootstrapTable("getOptions").totalRows
                    })
                }else{
                    alert("保存失败");
                }
            }
        });
    });
    //点击编辑按钮，初始化要更新的数据
    $("#edit_btn").click(function(){
        //获取选中的行
        var selections=$("#table_list").bootstrapTable("getSelections");
        if(selections.length==1){
            //获取指定员工信息
            getUserById(selections[0].id);
            //显示编辑模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("选择单独一条记录");
        }
    });
    //根据ID获取单个信息
    function getUserById(id){
        $.ajax({
            url:"${basePath}/user/"+id,
            type:"GET",
            dataType:"json",
            success:function (result) {
                if(result.code==1){
                    show_user_update_info(result.extend.entities[0]);
                }else{
                    alert("请求失败！");
                }
            }
        });
    }
    //显示编辑界面的员工信息
    function show_user_update_info(userInfo){
        $("#username_update_p").text(userInfo.id);
        $("#password_update_input").val(userInfo.password);
        $("#nickname_update_input").val(userInfo.nickname);
        $("#edit_modal input[name=status]").val([userInfo.status]);
        //给更新按钮添加个update-id属性用来存储userId
        $("#update_btn").attr("edit-id",userInfo.id);
    }
    //点击更新数据
    $("#update_btn").click(function(){
        //1.表单验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/user/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#edit_from").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("更新成功");
                    //关闭模态框
                    $("#edit_modal").modal("toggle");
                    //刷新表格数据
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("更新失败！");
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
                    ids+=this.id+",";
                })
                //去除多余的分隔符
                ids=ids.substring(0,ids.length-1);
                del_user(ids);
            }
        }else{
            alert("至少选择一条记录");
        }
    });
    //删除用户
    function del_user(ids){
        $.ajax({
            url:"${basePath}/user/"+ids,
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
    };
    //添加角色按钮
    $("#add_role_btn").click(function () {
        var userSelected=$("#table_list").bootstrapTable("getSelections");
        if (userSelected.length<1){
            alert("至少选择一条记录");
            return ;
        }
        //清空角色添加的下拉选
        $("#add_role_from select").empty();
        //查询角色
        loadAllRole();
        //显示添加角色的模态框
        $("#add_role_modal").modal("toggle");
    });
    //查询角色
    function loadAllRole(){
        $.ajax({
           url:"${basePath}/roleAll",
           type:"GET",
           dataType:"json",
           success:function (result) {
               if(1==result.code){
                   initRoleSelect(result.extend.roles);
               }
           }
        });
    };
    //初始化角色下拉选
    function initRoleSelect(roles){
        $("#roleId_add_select").append("<option value=''>---可选多个角色---</option>");
        roles.forEach(function (ele) {
            $("#roleId_add_select").append("<option value='"+ele.roleId+"'>"+ele.description+"</option>");
        });
    }
    //保存选中角色按钮
    $("#save_role_btn").click(function(){
        //获取选中的角色
        var userSelected=$("#table_list").bootstrapTable("getSelections");
        var userIdArray=new Array();
        for(var ind in userSelected){
            userIdArray[ind]=userSelected[ind].id;
        }
        var roleSelected=$("#roleId_add_select").val();
        if (roleSelected.length<1){
            alert("请选择至少一条数据");
            return;
        }
        $.ajax({
           url:"${basePath}/addUserRole",
           type:"POST",
           data:"users="+userIdArray+"&roles="+roleSelected,
           dataType:"json",
           success:function (result) {
               if(1==result.code){
                   alert("处理成功");
                   $("#add_role_modal").modal("toggle");
                   $("#table_list").bootstrapTable("refresh");
               }
           }
        });
    });
</script>
</body>
</html>
