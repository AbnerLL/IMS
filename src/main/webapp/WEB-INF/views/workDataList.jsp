<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/10/8
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>作业信息表</title>
    <%@include file="common/head.jsp"%>
    <%--IE=edge告诉IE使用最新的引擎渲染网页，chrome=1则可以激活Chrome Frame--%>
    <meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1" />
    <%--针对手机屏幕的设置--%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <%--基础css--%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet"/>
    <%--bootstrap表格的css--%>
    <link href="${basePath}/css/bootstrapTable/1.2.4/bootstrap-table.min.css" rel="stylesheet"/>
    <%--字体和图标的css--%>
    <link href="${basePath}/css/font-awesome.min2.css" rel="stylesheet"/>

    <%--基础js文件--%>
    <script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
    <script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
    <%--bootstrap表格的js文件及本地化文件--%>
    <script src="${basePath}/js/bootstrapTable/1.2.4/bootstrap-table.min.js"></script>
    <%--本地化文件要放在bootstrap文件下--%>
    <script src="${basePath}/js/bootstrapTable/1.2.4/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <%--自定义表格工具栏--%>
        <div id="toolbar" class="btn-group">
            <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
            <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
            <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
            <button id="import_btn" class="btn btn-info"><span class="fa fa-file-excel-o"></span>导入excel</button>
            <button id="export_btn" class="btn btn-info"><span class="fa fa-file-excel-o"></span>导出excel</button>
            <button id="search_toggle_btn" class="btn btn-primary"><span class="fa fa-search"></span>综合查询</button>
        </div>
        <%--表格数据--%>
            <table id="table_list"></table>
    </div>
    <%--新增模态框--%>
    <div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">作业新增</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="add_form">
                        <div class="form-group ">
                            <label for="version_insert_input" class="col-sm-3 control-label">版本号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="version" id="version_insert_input">
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="empId_insert_input" class="col-sm-3 control-label">员工编号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control " name="empId" id="empId_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="empName_insert_input" class="col-sm-3 control-label">员工姓名</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="empName" id="empName_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="workType_insert_select" class="col-sm-3 control-label">作业类型</label>
                            <div class="col-sm-7">
                                <select class="form-control" id="workType_insert_select" name="workType">
                                    <option value="点位">点位</option>
                                    <option value="代理店">代理店</option>
                                    <option value="道路图标">道路图标</option>
                                    <option value="中文名称">中文名称</option>
                                    <option value="中文地址">中文地址</option>
                                    <option value="英文名称">英文名称</option>
                                    <option value="英文地址">英文地址</option>
                                    <option value="深度信息">深度信息</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="quantity_insert_input" class="col-sm-3 control-label">作业量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" max="5" name="quantity" id="quantity_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="quality_insert_input" class="col-sm-3 control-label">质检量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="quality" id="quality_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="qError_insert_input" class="col-sm-3 control-label">质检错误量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="qError" id="qError_insert_input">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label for="qRate_insert_input" class="col-sm-3 control-label">质检品质率</label>--%>
                            <%--<div class="col-sm-7">--%>
                                <%--<div class="input-group">--%>
                                    <%--<input type="text" class="form-control" name="qRate" id="qRate_insert_input" readonly>--%>
                                    <%--<div class="input-group-addon">%</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="monitor_insert_input" class="col-sm-3 control-label">监察量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="monitor" id="monitor_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mError_insert_input" class="col-sm-3 control-label">监察错误量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="mError" id="mError_insert_input">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label for="mRate_insert_input" class="col-sm-3 control-label">监察品质率</label>--%>
                            <%--<div class="col-sm-7">--%>
                                <%--<div class="input-group">--%>
                                    <%--<input type="text" class="form-control" name="mRate" id="mRate_insert_input" readonly>--%>
                                    <%--<div class="input-group-addon">%</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="majorError_insert_input" class="col-sm-3 control-label">重大品质事故</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="majorError" id="majorError_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="efficiency_insert_input" class="col-sm-3 control-label">作业功效</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="efficiency" id="efficiency_insert_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="qEfficiency_insert_input" class="col-sm-3 control-label">质检功效</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="qEfficiency" id="qEfficiency_insert_input">
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
    <%--作业修改模态框--%>
    <div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">作业编辑</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="edit_form">
                        <div class="form-group ">
                            <label for="version_update_input" class="col-sm-3 control-label">版本号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="version" id="version_update_input">
                            </div>
                        </div>
                        <div class="form-group ">
                            <label for="empId_update_input" class="col-sm-3 control-label">员工编号</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control " name="empId" id="empId_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="empName_update_input" class="col-sm-3 control-label">员工姓名</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="empName" id="empName_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="workType_update_select" class="col-sm-3 control-label">作业类型</label>
                            <div class="col-sm-7">
                                <select class="form-control" id="workType_update_select" name="workType">
                                    <option value="点位">点位</option>
                                    <option value="代理店">代理店</option>
                                    <option value="道路图标">道路图标</option>
                                    <option value="中文名称">中文名称</option>
                                    <option value="中文地址">中文地址</option>
                                    <option value="英文名称">英文名称</option>
                                    <option value="英文地址">英文地址</option>
                                    <option value="深度信息">深度信息</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="quantity_update_input" class="col-sm-3 control-label">作业量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" max="5" name="quantity" id="quantity_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="quality_update_input" class="col-sm-3 control-label">质检量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="quality" id="quality_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="qError_update_input" class="col-sm-3 control-label">质检错误量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="qError" id="qError_update_input">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label for="qRate_insert_input" class="col-sm-3 control-label">质检品质率</label>--%>
                        <%--<div class="col-sm-7">--%>
                        <%--<div class="input-group">--%>
                        <%--<input type="text" class="form-control" name="qRate" id="qRate_insert_input" readonly>--%>
                        <%--<div class="input-group-addon">%</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="monitor_update_input" class="col-sm-3 control-label">监察量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="monitor" id="monitor_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mError_update_input" class="col-sm-3 control-label">监察错误量</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="mError" id="mError_update_input">
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label for="mRate_insert_input" class="col-sm-3 control-label">监察品质率</label>--%>
                        <%--<div class="col-sm-7">--%>
                        <%--<div class="input-group">--%>
                        <%--<input type="text" class="form-control" name="mRate" id="mRate_insert_input" readonly>--%>
                        <%--<div class="input-group-addon">%</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <label for="majorError_update_input" class="col-sm-3 control-label">重大品质事故</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="majorError" id="majorError_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="efficiency_update_input" class="col-sm-3 control-label">作业功效</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="efficiency" id="efficiency_update_input">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="qEfficiency_update_input" class="col-sm-3 control-label">质检功效</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="qEfficiency" id="qEfficiency_update_input">
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
</body>
</html>
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
    //设置发送请求时的参数，当queryParamsType 为limit时
    // params中的参数为{ search: undefined, sort: undefined, order: "asc", offset: 0, limit: 10 }
    function myQueryParams(params){
        return {
            pageSize:this.pageSize,       //每页的记录行数
            pageNum:this.pageNumber     //当前页数
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

    //初始化表格配置function
    function initTable(){
        $("#table_list").bootstrapTable({
            //获取数据的url
            url:"${basePath}/workDatas",
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
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
//            clickToSelect: true,                //是否启用点击选中行
//            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "workDataId",                     //每一行的唯一标识，一般为主键列
//            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"version",
                title:"版本号"
            },{
                field:"empId",
                title:"员工编号"
            },{
                field:"empName",
                title:"员工姓名"
            },{
                field:"quantity",
                title:"作业量"
            },{
                field:"quality",
                title:"质检量"
            },{
                field:"qError",
                title:"质检错误量"
            },{
                field:"qRate",
                title:"质检品质率",
                formatter:function(value,row,index){
                    if(""!=value){
                        var num=new Number(value)*100;
                        return num.toFixed(2)+"%";
                    }
                }
            },{
                field:"monitor",
                title:"监察量"
            },{
                field:"mError",
                title:"监察错误量"
            },{
                field:"mRate",
                title:"监察品质率",
                formatter:function(value,row,index){
                    if(""!=value){
                        var num=new Number(value)*100;
                        return num.toFixed(2)+"%";
                    }
                }
            },{
                field:"majorError",
                title:"重大品质事故"
            },{
                field:"efficiency",
                title:"作业功效"
            },{
                field:"qEfficiency",
                title:"质检功效"
            },{
                field:"workType",
                title:"作业类型"
            }]
        });
    }
    //显示模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.弹出模态框
        $("#add_modal").modal("toggle");
    });
    //保存按钮
    $("#save_btn").click(function(){
        //1.表单验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/workData",
            type:"POST",
            data:$("#add_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("处理成功");
                    //关闭新增模态框
                    $("#add_modal").modal("toggle");
                    //刷新列表数据并跳到最后一页
                    $("#table_list").bootstrapTable("refresh",{
                        pageNumber:$("#table_list").bootstrapTable("getOptions").totalRows
                    })
                }else{
                    alert("处理失败");
                }
            }
        })
    });
    //编辑按钮
    $("#edit_btn").click(function(){
        //1.判断是否选中数据
        var selections=$("#table_list").bootstrapTable("getSelections")
        if(selections.length==1){
            //2.查询选中的数据并回显至编辑模态框
            getSelectedInfo(selections[0].workDataId);
            //3.显示模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("请选择单条数据");
            return;
        }
    });
    //根据id获取当前对象的信息
    function getSelectedInfo(id){
        $.ajax({
            url:"${basePath}/workData/"+id,
            type:"GET",
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    //1.编辑数据回显
                    edit_data_echo(result.extend.workDatas);
                }else{

                }
            }
        });
    }
    //将查询出来的数据进行回显
    function edit_data_echo(obj){
        console.log(obj);
        $("#version_update_input").val(obj.version);
        $("#empId_update_input").val(obj.empId);
        $("#empName_update_input").val(obj.empName);
        $("#workType_update_select").val([obj.workType]);
        $("#quantity_update_input").val(obj.quantity);
        $("#quality_update_input").val(obj.quality);
        $("#qError_update_input").val(obj.qError);
        $("#monitor_update_input").val(obj.monitor);
        $("#mError_update_input").val(obj.mError);
        $("#majorError_update_input").val(obj.majorError);
        $("#efficiency_update_input").val(obj.efficiency);
        $("#qEfficiency_update_input").val(obj.qEfficiency);

        //在更新按钮上绑定主键
        $("#update_btn").attr("edit-id",obj.workDataId);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单校验
        //2.发送请求
        $.ajax({
            url:"${basePath}/workData/"+$(this).attr("edit-id"),
            type:"PUT",
            data:$("#edit_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("处理成功");
                    //关闭模态框
                    $("#edit_modal").modal("toggle");
                    //刷新当前页面
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
                    ids+=this.workDataId+",";
                })
                //去除多余的分隔符
                ids=ids.substring(0,ids.length-1);
                del_data(ids);
            }
        }else{
            alert("至少选择一条记录");
        }
    });
    //列表中的数据
    function del_data(id){
        $.ajax({
            url:"${basePath}/workData/"+id,
            type:"DELETE",
            dataType:"json",
            success:function (result) {
                if(result.code==1){
                    alert("处理成功");
                    //刷新列表
                    $("#table_list").bootstrapTable("refresh");
                }else{
                    alert("处理失败");
                }
            },
        });
    }
</script>