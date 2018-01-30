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
    <title>工作日志</title>
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
                <h4 class="modal-title" id="myModalLabel">添加日志</h4>
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
                                <label for="empName_insert_input" class="col-sm-2 control-label">员工名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_insert_input" placeholder="员工名称">
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
                                        <option value="正常上班">正常上班</option>
                                        <option value="延时加班">延时加班</option>
                                        <option value="公休加班">公休加班</option>
                                        <option value="节假日加班">节假日加班</option>
                                        <option value="出差">出差</option>
                                        <option value="待工">待工</option>
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
                                <label for="workHours_insert_input" class="col-sm-2 control-label">工作用时</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="workHours" id="workHours_insert_input" placeholder="如：9:00-11:00"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workContent_insert_textarea" class="col-sm-2 control-label">工作内容</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="4" name="workContent" id="workContent_insert_textarea"></textarea>
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
                <h4 class="modal-title" id="myEditModalLabel">修改日志</h4>
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
                                <label for="empName_update_input" class="col-sm-2 control-label">员工名称</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_update_input" placeholder="员工名称">
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
                                        <option value="正常上班">正常上班</option>
                                        <option value="延时加班">延时加班</option>
                                        <option value="公休加班">公休加班</option>
                                        <option value="节假日加班">节假日加班</option>
                                        <option value="出差">出差</option>
                                        <option value="待工">待工</option>
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
                                <label for="workHours_update_input" class="col-sm-2 control-label">工作用时</label>
                                <div class="col-sm-4">
                                    <input class="form-control" type="text" name="workHours" id="workHours_update_input" placeholder="如：9:00-11:00"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workContent_update_textarea" class="col-sm-2 control-label">工作内容</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="4" name="workContent" id="workContent_update_textarea"></textarea>
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
<script src="${basePath}/js/bootstrapTable/1.2.4/extensions/export/tableExport.js"></script>
<script src="${basePath}/js/bootstrapTable/1.2.4/extensions/export/bootstrap-table-export.js"></script>
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
            showExport:true,                    //显示导出
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
                field:"workHours",
                title:"工作用时"
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
        //2.弹出模态框并清空表单
        $("#add_modal").modal("show");
    });
    //保存数据
    $("#save_btn").click(function(){
        //1.表单数据验证
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
    }
    //在修改模态框上显示对象
    function showEditDataDetail(obj){
        $("#empId_update_p").text(obj.empId);
        $("#empName_update_input").val(obj.empName);
        $("#section_update_select").val(obj.section);
        $("#workType_update_select").val(obj.workType);
        $("#workDate_update_input").val(dateFormatter(obj.workDate));
        $("#workHours_update_input").val(obj.workHours);
        $("#workContent_update_textarea").val(obj.workContent);
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
    }
</script>
</body>
</html>
