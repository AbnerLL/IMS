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
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
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
                                        <label for="empId_search_input" class="control-label">员工编号</label>
                                        <input class="form-control" type="week" name="empId" id="empId_search_input">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="empName_search_input" class="control-label">员工姓名</label>
                                        <input class="form-control" type="text" name="empName" id="empName_search_input">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="section_search_select" class="control-label">所属科室</label>
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
                            </div>
                            <div class="row" style="height: 5px;"></div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label class="control-label">笔试成绩</label>
                                        <span>
                                            <input class="form-control" type="text" name="paperGradeStart" style="width: 36%;" placeholder="成绩区间"/>
                                            <input class="form-control" type="text" name="paperGradeEnd" style="width: 36%;" placeholder="成绩区间"/>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label class="control-label">道路成绩</label>
                                        <span>
                                            <input class="form-control" type="text" name="comGradeRoadStart" style="width: 36%;" placeholder="成绩区间"/>
                                            <input class="form-control" type="text" name="comGradeRoadEnd" style="width: 36%;" placeholder="成绩区间"/>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label class="control-label">设施成绩</label>
                                        <span>
                                            <input class="form-control" type="text" name="comGradePoiStart" style="width: 36%;" placeholder="成绩区间"/>
                                            <input class="form-control" type="text" name="comGradePoiEnd" style="width: 36%;" placeholder="成绩区间"/>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="height: 5px;"></div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label class="control-label">综合成绩</label>
                                        <span>
                                            <input class="form-control" type="text" name="totalGradeStart" style="width: 36%;" placeholder="成绩区间"/>
                                            <input class="form-control" type="text" name="totalGradeEnd" style="width: 36%;" placeholder="成绩区间"/>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label class="control-label">考试日期</label>
                                        <span>
                                        <div class="input-group input-group-sm" style="width: 77%;">
                                            <input type="text" class="form-control datepicker" name="testDateStart"/>
                                            <div class="input-group-addon"><span class="fa fa-calendar fa-sm"></span></div>
                                            <input type="text" class="form-control datepicker" name="testDateEnd" aria-label="考试日期"/>
                                            <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                                        </div>
                                    </span>
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
                <shrio:hasPermission name="testGrade:add">
                    <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
                </shrio:hasPermission>
                <shrio:hasPermission name="testGrade:edit">
                    <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
                </shrio:hasPermission>
                <shrio:hasPermission name="testGrade:delete">
                    <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
                </shrio:hasPermission>
                <shrioDiy:hasAnyPermission name="testGrade:export,testGrade:export:section,testGrade:export:dept">
                    <button id="export_btn" class="btn btn-success"><span class="fa fa-file-excel-o"></span></span>导出excel</button>
                </shrioDiy:hasAnyPermission>
                <button id="search_toggle_btn" class="btn btn-info"><span class="fa fa-search"></span>综合查询</button>
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
//            pageNum:this.pageNumber     //当前页数
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
            searchOnEnterKey:true,               //按entry键搜索
            detailView: false,                   //是否显示父子表
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"testDate",
                title:"考核日期",
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
                    edit_data_echo(result.extend.entities[0]);
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
    };
    //控制collapse
    $("#search_toggle_btn").click(function(){
        $("#search_collapse").collapse("toggle");
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
        window.location.href="${basePath}/testGradeExcel?"+$("#search_form").serialize();
    });
</script>