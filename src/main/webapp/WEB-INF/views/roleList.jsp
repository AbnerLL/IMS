<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/10/27
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色列表</title>
    <%@include file="head.jsp"%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${basePath}/css/bootstrapTable/1.2.4/bootstrap-table.min.css"  rel="stylesheet" />
</head>
<body>
    <div class="container-fluid">
        <%--工具栏--%>
        <div id="toolbar" class="btn-group">
            <button id="add_btn" type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="edit_btn" type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-pencil" aria- hidden="true" ></span >编辑
            </button >
            <button id="del_btn" type="button" class="btn btn-danger">
                <span class="glyphicon glyphicon-remove" aria- hidden="true" ></span >删除
            </button >
        </div>
            <%--数据列表--%>
        <table id="table_list"></table>
    </div>

    <%--角色新增模态框--%>
    <div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加角色</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="add_form">
                        <div class="form-group">
                            <label for="roleName_insert_input" class="col-sm-2 control-label">角色名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="roleName_insert_input" name="roleName">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description_insert_textarea" class="col-sm-2 control-label">角色描述</label>
                            <div class="col-sm-8">
                                <textarea class="form-control col-sm-8" rows="3" name="description" id="description_insert_textarea"></textarea>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">取消</button>
                    <button type="button" class="btn btn-primary" aria-label="save" id="save_btn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <%--角色修改模态框--%>
    <div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">编辑角色</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="edit_form">
                        <div class="form-group">
                            <label for="roleName_edit_input" class="col-sm-2 control-label">角色名称</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="roleName_edit_input" name="roleName">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description_edit_textarea" class="col-sm-2 control-label">角色描述</label>
                            <div class="col-sm-8">
                                <textarea class="form-control col-sm-8" rows="3" name="description" id="description_edit_textarea"></textarea>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">取消</button>
                    <button type="button" class="btn btn-primary" aria-label="save" id="update_btn">更新</button>
                </div>
            </div>
        </div>
    </div>
    <script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
    <script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
    <script src="${basePath}/js/bootstrapTable/1.2.4/bootstrap-table.min.js"></script>
    <script src="${basePath}/js/bootstrapTable/1.2.4/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript">
        $(function(){
            initTable();
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
        //时间格式化
        function dateFormatter(value,row,index){
            return new Date(value).toLocaleString();
        }
        //表格初始化设置
        function initTable(){
            $("#table_list").bootstrapTable({
                url: "${basePath}/roles",                         //直接从本地数据初始化表格
                method: 'get',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: myQueryParams,          //传递参数（*）
                responseHandler:myResponseHandler,   //用于指定从服务器获取的数据作为解析的实参
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: true,                       //是否显示表格搜索
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列
                uniqueId:"roleId",                  //设置主键
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                searchOnEnterKey:true,              //设置按回车键执行搜索，否则自动搜索
//                  height:400,
//          selectItemName: 'parentItem',
//                  fixedColumns: true,
//                  fixedNumber: 6,
//                  //注册加载子表的事件。注意下这里的三个参数！
//                  onExpandRow: function (index, row, $detail) {
//                      InitSubTable(index, row, $detail);
//                  },
                columns: [{
                    checkbox: true
                }, {
                    field: 'roleId',
                    title: '角色ID'

                },{
                    field: 'roleName',
                    title: '角色名称'

                }, {
                    field:"description",
                    title:"角色描述"

                }]
            })
        }
        //新增按钮
        $("#add_btn").click(function(){
            //清空表单
            $("#add_modal form")[0].reset();
            //弹出模态框
            $("#add_modal").modal("toggle");
        });
        //保存按钮
        $("#save_btn").click(function(){
            //1.表单验证
            //2.发送请求
            $.ajax({
                url:"${basePath}/role",
                type:"POST",
                data:$("#add_form").serialize(),
                dataType:"json",
                success:function(result){
                    if(result.code==1){
                        alert("处理成功");
                        //关闭模态框
                        $("#add_modal").modal("toggle");
                        //刷新列表
                        // 设置pageNumber的值是为了翻到最后一页，如果不知道最后一页是多少那么设置到总记录数就够了
                        //后台对分页的参数做了合理化设置，即使传入的参数超过实际值，也会正常取到值
                        $("#table_list").bootstrapTable("refresh",{
                            pageNumber:$("#table_list").bootstrapTable("getOptions").totalRows
                        });
                    }else{
                        alert("处理失败");
                    }
                }
            })
        });
        //编辑按钮显示数据
        $("#edit_btn").click(function(){
            var selections=$("#table_list").bootstrapTable("getSelections");
            if(selections.length==1){
                //1.获取角色信息
                getRoleById(selections[0].roleId);
                //2.弹出编辑框
                $("#edit_modal").modal("toggle");
            }else{
                alert("请选择单条数据");
                return;
            }
        });
        //获取角色对象
        function getRoleById(roleId){
            $.ajax({
                url:"${basePath}/role/"+roleId,
                type:"GET",
                dataType:"json",
                success:function(result){
                    if(result.code==1){
                        //显示数据
                        show_role_update_info(result.extend.roles)
                    }
                }
            });
        }
        //在编辑界面显示数据
        function show_role_update_info(roleInfo){
            $("#edit_form input[name=roleName]").val(roleInfo.roleName);
            $("#edit_form textarea[name=description]").val(roleInfo.description);
            //给更新按钮添加一个属性
            $("#update_btn").attr("edit-id",roleInfo.roleId);
        }
        //更新按钮
        $("#update_btn").click(function(){
            //1.表单校验
            //2.发送请求
            $.ajax({
                url:"${basePath}/role/"+$(this).attr("edit-id"),
                type:"PUT",
                data:$("#edit_form").serialize(),
                dataType:"json",
                success:function(result){
                    if(result.code==1){
                        alert("处理成功");
                        //关闭模态框
                        $("#edit_modal").modal("toggle");
                        //刷新数据
                        $("#table_list").bootstrapTable("refresh");
                    }else{
                        alert("处理失败");
                    }
                }
            });
        })
        //删除按钮
        $("#del_btn").click(function(){
            var selects=$("#table_list").bootstrapTable("getSelections");
            if(selects.length>0){
                if(confirm("是否删除"+selects.length+"条数据")){
                    //删除数据
                    var ids="";
                    $(selects).each(function(){
                        ids+=this.roleId+",";
                    })
                    //去除多余的分隔符
                    ids=ids.substring(0,ids.length-1);
                    del_roles(ids);
                }
            }else{
                alert("至少选择一条记录");
            }
        });
        //删除一个或多个角色
        function del_roles(ids){
            $.ajax({
                url:"${basePath}/role/"+ids,
                type:"DELETE",
                dataType:"json",
                success:function(result){
                    if(result.code==1){
                        alert("处理成功");
                        //刷新数据
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
