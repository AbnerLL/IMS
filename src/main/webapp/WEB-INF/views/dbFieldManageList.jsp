<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/3/8
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
    <title>导入字段管理</title>
</head>
<body>
<div class="container-fluid">
    <div class="container-fluid">
        <%--自定义表格工具栏--%>
        <div id="toolbar" class="btn-group">
            <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
            <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
            <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
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
                    <h4 class="modal-title" id="myModalLabel"><span class="fa fa-plus fa-lg"></span> 新增</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-11">
                            <form class="form-horizontal" id="add_form">
                                <div class="form-group form-group-sm">
                                    <label for="fieldName_insert_input" class="col-sm-2 control-label">属性名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="fieldName" id="fieldName_insert_input" >
                                    </div>
                                    <label for="fieldDescript_insert_input" class="col-sm-2 control-label">属性描述</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="fieldDescript" id="fieldDescript_insert_input" >
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="fieldClass_insert_select" class="col-sm-2 control-label">属性类型</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="fieldClass" id="fieldClass_insert_select">
                                            <option value=""></option>
                                            <option value="string">字符</option>
                                            <option value="date">日期</option>
                                            <option value="int">数值</option>
                                        </select>
                                    </div>
                                    <label for="objectName_insert_input" class="col-sm-2 control-label">对象名称</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="objectName" id="objectName_insert_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="columnName_insert_input" class="col-sm-2 control-label">字段名称</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="columnName" id="columnName_insert_input"/>
                                    </div>
                                    <label for="columnDescript_insert_input" class="col-sm-2 control-label">字段描述</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="columnDescript" id="columnDescript_insert_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="columnType_insert_select" class="col-sm-2 control-label">字段类型</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="columnType" id="columnType_insert_select">
                                            <option value=""></option>
                                            <option value="varchar2">字符</option>
                                            <option value="date">日期</option>
                                            <option value="int">数值</option>
                                        </select>
                                    </div>
                                    <label for="tableName_insert_input" class="col-sm-2 control-label">表名</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="tableName" id="tableName_insert_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="sortIndex_insert_input" class="col-sm-2 control-label">排序</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="sortIndex" id="sortIndex_insert_input"/>
                                    </div>
                                    <label for="status_insert_select" class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="status" id="status_insert_select">
                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>
                                            <option value="2">主键</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="tableDescript_insert_input" class="col-sm-2 control-label">表名描述</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" name="tableDescript" id="tableDescript_insert_input"/>
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
    <%--modal模态框(编辑)--%>
    <div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2"><span class="fa fa-pencil fa-lg"></span> 编辑</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-11">
                            <form class="form-horizontal" id="edit_form">
                                <div class="form-group form-group-sm">
                                    <label for="fieldName_update_input" class="col-sm-2 control-label">属性名称</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="fieldName" id="fieldName_update_input" >
                                    </div>
                                    <label for="fieldDescript_update_input" class="col-sm-2 control-label">属性描述</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="fieldDescript" id="fieldDescript_update_input" >
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="fieldClass_update_select" class="col-sm-2 control-label">属性类型</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="fieldClass" id="fieldClass_update_select">
                                            <option value=""></option>
                                            <option value="string">字符</option>
                                            <option value="date">日期</option>
                                            <option value="int">数值</option>
                                        </select>
                                    </div>
                                    <label for="objectName_update_input" class="col-sm-2 control-label">对象名称</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="objectName" id="objectName_update_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="columnName_update_input" class="col-sm-2 control-label">字段名称</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="columnName" id="columnName_update_input"/>
                                    </div>
                                    <label for="columnDescript_update_input" class="col-sm-2 control-label">字段描述</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="columnDescript" id="columnDescript_update_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="columnType_update_select" class="col-sm-2 control-label">字段类型</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="columnType" id="columnType_update_select">
                                            <option value=""></option>
                                            <option value="varchar2">字符</option>
                                            <option value="date">日期</option>
                                            <option value="int">数值</option>
                                        </select>
                                    </div>
                                    <label for="tableName_update_input" class="col-sm-2 control-label">表名</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="tableName" id="tableName_update_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="sortIndex_update_input" class="col-sm-2 control-label">排序</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="sortIndex" id="sortIndex_update_input"/>
                                    </div>
                                    <label for="status_update_select" class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="status" id="status_update_select">
                                            <option value="0">启用</option>
                                            <option value="1">禁用</option>
                                            <option value="2">主键</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="tableDescript_update_input" class="col-sm-2 control-label">表名描述</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" name="tableDescript" id="tableDescript_update_input"/>
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
                url:"${basePath}/DBFieldManage/search",
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
                uniqueId: "uuid",                     //每一行的唯一标识，一般为主键列
                showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                searchOnEnterKey:true,               //按entry键搜索
                showExport:false,                    //显示导出
                exportDataType:"all",               //导出方式selected、all、basic
                columns:[{                          //配置各列的属性
                    checkbox:true
                },{
                    field:"fieldName",
                    title:"属性名称"
                },{
                    field:"fieldDescript",
                    title:"属性描述"
                },{
                    field:"fieldClass",
                    title:"属性类型"
                },{
                    field:"objectName",
                    title:"对象名称"
                },{
                    field:"columnName",
                    title:"字段名称"
                },{
                    field:"columnDescript",
                    title:"字段描述"
                },{
                    field:"columnType",
                    title:"字段类型"
                },{
                    field:"tableName",
                    title:"表名"
                },{
                    field:"tableDescript",
                    title:"描述"
                },{
                    field:"sortIndex",
                    title:"排序"
                },{
                    field:"status",
                    title:"状态",
                    formatter:function (value,row,index) {
                        return 0 == value ? "启用" : 1 == value ? "禁用" : "主键";
                    }
                }],
                formatSearch:function () {
                    return "搜索：表名";
                }
            });
        };
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
                url:"${basePath}/DBFieldManage/add",
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
                initEditDataDetailById(selections[0].uuid);
                //显示模态框
                $("#edit_modal").modal("toggle");
            }else{
                alert("选择单独一条记录");
            }
        });
        //根据ID查询对应数据并显示与编辑modal上
        function initEditDataDetailById(dataId){
            $.ajax({
                url:"${basePath}/DBFieldManage/loadAll?uuid="+dataId,
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
            $("#fieldName_update_input").val(obj.fieldName);
            $("#fieldDescript_update_input").val(obj.fieldDescript);
            $("#fieldClass_update_select").val(obj.fieldClass);
            $("#columnName_update_input").val(obj.columnName);
            $("#columnDescript_update_input").val(obj.columnDescript);
            $("#columnType_update_select").val(obj.columnType);
            $("#sortIndex_update_input").val(obj.sortIndex);
            $("#tableName_update_input").val(obj.tableName);
            $("#objectName_update_input").val(obj.objectName);
            $("#status_update_select").val(obj.status);
            $("#tableDescript_update_input").val(obj.tableDescript);
            $("#update_btn").attr("data-id",obj.uuid);
        }
        //更新按钮
        $("#update_btn").click(function(){
            //1.表单数据验证
            //2.发送请求
            $.ajax({
                url:"${basePath}/DBFieldManage/update?uuid="+$(this).attr("data-id"),
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
                        ids+=this.uuid+",";
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
                url:"${basePath}/DBFieldManage/delete?ids="+ids,
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
</div>
</body>
</html>
