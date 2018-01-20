<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/10/29
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>版本前考核成绩表</title>
    <%@include file="head.jsp"%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/css/bootstrapTable/1.2.4/bootstrap-table.min.css" rel="stylesheet"/>
    <link href="${basePath}/css/font-awesome.min2.css" rel="stylesheet"/>

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
            <%--<button id="search_toggle_btn" class="btn btn-default"><span class="fa fa-search"></span>综合查询</button>--%>
        </div>
        <%--表格数据--%>
        <table id="table_list"></table>
    </div>
    <%--新增模态框--%>
    <div class="modal fade" id="add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">新增成绩</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="add_form">
                        <div class="form-group">
                            <label for="version_insert_input" class="control-label col-sm-2">版本号</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="version" id="version_insert_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="empId_insert_input" class="control-label col-sm-2">员工编号</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="empId" id="empId_insert_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="empName_insert_input" class="control-label col-sm-2">员工姓名</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="empName" id="empName_insert_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="paperGrade_insert_input" class="control-label col-sm-2">笔试成绩</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="paperGrade" id="paperGrade_insert_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comGradeRoad_insert_input" class="control-label col-sm-2">道路成绩（机试）</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="comGradeRoad" id="comGradeRoad_insert_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comGradePoi_insert_input" class="control-label col-sm-2">道路成绩（机试）</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="comGradePoi" id="comGradePoi_insert_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="totalGrade_insert_input" class="control-label col-sm-2">综合成绩</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="totalGrade" id="totalGrade_insert_input"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="save_btn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <%--修改模态框--%>
    <div class="modal fade" id="edit_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">修改成绩</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="edit_form">
                        <div class="form-group">
                            <label for="version_update_input" class="control-label col-sm-2">版本号</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="version" id="version_update_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="empId_update_input" class="control-label col-sm-2">员工编号</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="empId" id="empId_update_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="empName_update_input" class="control-label col-sm-2">员工姓名</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="empName" id="empName_update_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="paperGrade_update_input" class="control-label col-sm-2">笔试成绩</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="paperGrade" id="paperGrade_update_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comGradeRoad_update_input" class="control-label col-sm-2">道路成绩（机试）</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="comGradeRoad" id="comGradeRoad_update_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="comGradePoi_update_input" class="control-label col-sm-2">道路成绩（机试）</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="comGradePoi" id="comGradePoi_update_input"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="totalGrade_update_input" class="control-label col-sm-2">综合成绩</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="totalGrade" id="totalGrade_update_input"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="update_btn">保存</button>
                </div>
            </div>
        </div>
    </div>
    <%--js文件--%>
    <script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
    <script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
    <script src="${basePath}/js/bootstrapTable/1.2.4/bootstrap-table.min.js"></script>
    <script src="${basePath}/js/bootstrapTable/1.2.4/locale/bootstrap-table-zh-CN.min.js"></script>

</body>
</html>
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
            url:"${basePath}/testGrades",
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
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
//            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"testDate",
                title:"版本号",
                formatter:dateFormatter
            },{
                field:"empId",
                title:"员工编号"
            },{
                field:"empName",
                title:"员工姓名"
            },{
                field:"paperGrade",
                title:"笔试成绩"
            },{
                field:"comGradeRoad",
                title:"道路成绩（机试）"
            },{
                field:"comGradePoi",
                title:"设施成绩（机试）"
            },{
                field:"totalGrade",
                title:"综合成绩"
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
            url:"${basePath}/testGrade",
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
            getSelectedInfo(selections[0].id);
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
            url:"${basePath}/testGrade/"+id,
            type:"GET",
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    //1.编辑数据回显
                    edit_data_echo(result.extend.data);
                }else{

                }
            }
        });
    }
    //将查询出来的数据进行回显
    function edit_data_echo(obj){
        $("#version_update_input").val(obj.version);
        $("#empId_update_input").val(obj.empId);
        $("#empName_update_input").val(obj.empName);
        $("#paperGrade_update_input").val(obj.paperGrade);
        $("#comGradeRoad_update_input").val(obj.comGradeRoad);
        $("#comGradePoi_update_input").val(obj.comGradePoi);
        $("#totalGrade_update_input").val(obj.totalGrade);
        //在更新按钮上绑定主键
        $("#update_btn").attr("edit-id",obj.id);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单校验
        //2.发送请求
        $.ajax({
            url:"${basePath}/testGrade/"+$(this).attr("edit-id"),
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
                    ids+=this.id+",";
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
            url:"${basePath}/testGrade/"+id,
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