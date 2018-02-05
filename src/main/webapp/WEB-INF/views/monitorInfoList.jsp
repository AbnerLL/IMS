<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/11/26
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
    <title>监察信息</title>
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
                                    <label for="worker_search_input" class="control-label">作业员:</label>
                                    <input class="form-control" type="text" name="worker" id="worker_search_input"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="version_search_input" class="control-label">作业版本:</label>
                                    <input class="form-control" type="text" name="version" id="version_search_input"/>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="workType_search_select" class="control-label">作业类型:</label>
                                    <select class="form-control" name="workType" id="workType_search_select">
                                        <option value="">---选择作业类型---</option>
                                        <option value="道路图标">道路图标</option>
                                        <option value="中文名称">中文名称</option>
                                        <option value="中文地址">中文地址</option>
                                        <option value="英文">英文</option>
                                        <option value="深度信息">深度信息</option>
                                        <option value="代理店">代理店</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="height: 5px;"></div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="section_search_select" class="control-label">项目组:</label>
                                    <select class="form-control" name="section" id="section_search_select">
                                        <option value="">----选择作业组----</option>
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
                            <div class="col-sm-4">
                                <div class="form-group form-group-sm">
                                    <label for="monitorDateStart_search_input" class="control-label">监察日期:</label>
                                    <span>
                                        <div class="input-group input-group-sm" style="width: 77%;">
                                            <input type="text" class="form-control datepicker" name="monitorDateStart" id="monitorDateStart_search_input" />
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                            <input type="text" class="form-control datepicker" name="monitorDateEnd" id="monitorDateEnd_search_input" />
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                        </div>
                                    </span>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="form-group  form-group-sm">
                                    <div class="btn btn-primary btn-sm" id="search_btn">查询</div>
                                    <div class="btn btn-default btn-sm" id="search_reset_btn">清空</div>
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
        <shrio:hasPermission name="monitorInfo:add">
            <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="monitorInfo:edit">
            <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="monitorInfo:delete">
            <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
        </shrio:hasPermission>
        <shrioDiy:hasAnyPermission name="monitorInfo:export,monitorInfo:export:section,monitorInfo:export:dept">
            <button id="export_btn" class="btn btn-success"><span class="fa fa-file-excel-o"></span></span>导出excel</button>
        </shrioDiy:hasAnyPermission>
        <button id="search_toggle_btn" class="btn btn-info"><span class="fa fa-search"></span>综合查询</button>
    </div>
    <%--表格数据--%>
    <table id="table_list"></table>
</div>
<%--modal模态框(添加)--%>
<div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myAddModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myAddModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;新增</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="add_form">
                            <div class="form-group form-group-sm">
                                <label for="workerId_insert_input" class="control-label col-sm-2">作业员编号</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="workerId" id="workerId_insert_input">
                                </div>
                                <label for="worker_insert_input" class="control-label col-sm-2">作业员名称</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="worker" id="worker_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="version_insert_input" class="control-label col-sm-2">作业版本</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="version" id="version_insert_input">
                                </div>
                                <label for="workType_insert_select" class="control-label col-sm-2">作业类型</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="workType" id="workType_insert_select">
                                        <option value="">---选择作业类型---</option>
                                        <option value="道路图标">道路图标</option>
                                        <option value="中文名称">中文名称</option>
                                        <option value="中文地址">中文地址</option>
                                        <option value="英文">英文</option>
                                        <option value="深度信息">深度信息</option>
                                        <option value="代理店">代理店</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="section_insert_select" class="control-label col-sm-2">项目组</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="section" id="section_insert_select">
                                        <option value="">----选择作业组----</option>
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
                                <label for="monitorDate_insert_input" class="control-label col-sm-2">监察日期</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input class="form-control datepicker" type="text" name="monitorDate" id="monitorDate_insert_input"/>
                                        <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="monitorNum_insert_input" class="control-label col-sm-2">监察数量</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="monitorNum" id="monitorNum_insert_input">
                                </div>
                                <label for="mErrorNum_insert_input" class="control-label col-sm-2">监察错误量</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="mErrorNum" id="mErrorNum_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="monitorId_insert_input" class="control-label col-sm-2">监察员ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="monitorId" id="monitorId_insert_input">
                                </div>
                                <label for="monitor_insert_input" class="control-label col-sm-2">监察员名称</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="monitor" id="monitor_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="remark_insert_textarea" class="control-label col-sm-2">备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" name="remark" id="remark_insert_textarea" rows="2"></textarea>
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
                <h4 class="modal-title" id="myEditModalLabel"><span class="fa fa-pencil-square-o"></span>&nbsp;修改</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="edit_form">
                            <div class="form-group form-group-sm">
                                <label for="workerId_update_input" class="control-label col-sm-2">作业员编号</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="workerId" id="workerId_update_input">
                                </div>
                                <label for="worker_update_input" class="control-label col-sm-2">作业员名称</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="worker" id="worker_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="version_update_input" class="control-label col-sm-2">作业版本</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="version" id="version_update_input">
                                </div>
                                <label for="workType_update_select" class="control-label col-sm-2">作业类型</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="workType" id="workType_update_select">
                                        <option value="">---选择作业类型---</option>
                                        <option value="道路图标">道路图标</option>
                                        <option value="中文名称">中文名称</option>
                                        <option value="中文地址">中文地址</option>
                                        <option value="英文">英文</option>
                                        <option value="深度信息">深度信息</option>
                                        <option value="代理店">代理店</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="section_update_select" class="control-label col-sm-2">项目组</label>
                                <div class="col-sm-4">
                                    <select class="form-control" name="section" id="section_update_select">
                                        <option value="">----选择作业组----</option>
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
                                <label for="monitorDate_update_input" class="control-label col-sm-2">监察日期</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input class="form-control datepicker" type="text" name="monitorDate" id="monitorDate_update_input"/>
                                        <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="monitorNum_update_input" class="control-label col-sm-2">监察数量</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="monitorNum" id="monitorNum_update_input">
                                </div>
                                <label for="mErrorNum_update_input" class="control-label col-sm-2">监察错误量</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="mErrorNum" id="mErrorNum_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="monitorId_update_input" class="control-label col-sm-2">监察员ID</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="monitorId" id="monitorId_update_input">
                                </div>
                                <label for="monitor_update_input" class="control-label col-sm-2">监察员名称</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="monitor" id="monitor_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="remark_update_textarea" class="control-label col-sm-2">备注</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" name="remark" id="remark_update_textarea" rows="2"></textarea>
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
            url:"${basePath}/monitorInfos",
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
                field:"monitorDate",
                title:"监察日期",
                formatter:dateFormatter
            },{
                field:"monitor",
                title:"监察员"
            },{
                field:"version",
                title:"作业版本"
            },{
                field:"workType",
                title:"作业类型"
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
                field:"monitorNum",
                title:"监察量"
            },{
                field:"mErrorNum",
                title:"错误量"
            },{
                field:"mPassRate",
                title:"合格率"
            },{
                field:"remark",
                title:"备注"
            }]
        });
    };
    //显示新增模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
//        //2.初始化用户的基本数据
//        initUserInfo();
        //3.弹出模态框并清空表单
        $("#add_modal").modal("show");
    });
    //保存数据
    $("#save_btn").click(function(){
        //1.表单数据验证
        //2.发送ajax请求
        $.ajax({
            url:"${basePath}/monitorInfo",
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
            url:"${basePath}/monitorInfo/"+dataId,
            type:"GET",
            dataType:"json",
            success:function(result){
                if(1==result.code){
                    showEditDataDetail(result.extend.entities[0]);
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
        $("#version_update_input").val(obj.version);
        $("#workType_update_select").val(obj.workType);
        $("#section_update_select").val(obj.section);
        $("#workerId_update_input").val(obj.workerId);
        $("#worker_update_input").val(obj.worker);
        $("#monitorDate_update_input").val(dateFormatter(obj.monitorDate));
        $("#monitorNum_update_input").val(obj.monitorNum);
        $("#mErrorNum_update_input").val(obj.mErrorNum);
        $("#monitorId_update_input").val(obj.monitorId);
        $("#monitor_update_input").val(obj.monitor);
        $("#remark_update_textarea").val(obj.remark);
        $("#update_btn").attr("data-id",obj.id);
    };
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单数据验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/monitorInfo/"+$(this).attr("data-id"),
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
            url:"${basePath}/monitorInfo/"+ids,
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
    //展开或收缩查询面板
    $("#search_toggle_btn").click(function(){
        $("#search_collapse").collapse('toggle');
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
        window.location.href="${basePath}/monitorInfoExcel?"+$("#search_form").serialize();
    });
</script>
</body>
</html>
