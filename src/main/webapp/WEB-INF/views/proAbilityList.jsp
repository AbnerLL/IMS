<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/10/29
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>业务能力统计表</title>
    <%@include file="common/head.jsp"%>
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
            <button id="search_toggle_btn" class="btn btn-primary"><span class="fa fa-search"></span>综合查询</button>
        </div>
        <%--表格数据--%>
        <table id="table_list"></table>
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

    //初始化表格配置function
    function initTable(){
        $("#table_list").bootstrapTable({
            //获取数据的url
            url:"${basePath}/proAbilities",
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
                field:"version",
                title:"版本号"
            },{
                field:"empId",
                title:"员工编号"
            },{
                field:"empName",
                title:"员工姓名"
            },{
                field:"rMarkLog",
                title:"道路前期录入能力"
            },{
                field:"rMarkTest",
                title:"道路前期检验能力"
            },{
                field:"chPoiLog",
                title:"设施（中文）录入能力"
            },{
                field:"chPoiTest",
                title:"设施（中文）检验能力"
            },{
                field:"enPoiLog",
                title:"设施（英文）录入能力"
            },{
                field:"enPoiTest",
                title:"设施（英文）检验能力"
            },{
                field:"roadItemLog",
                title:"道路后期录入能力"
            },{
                field:"roadItemTest",
                title:"道路后期检验能力"
            },{
                field:"poiItemLog",
                title:"设施后期录入能力"
            },{
                field:"poiItemTest",
                title:"设施后期检验能力"
            },{
                field:"depthInfoLog",
                title:"深度信息录入能力"
            },{
                field:"depthInfoTest",
                title:"深度信息检验能力"
            },{
                field:"pointLog",
                title:"点位录入能力"
            },{
                field:"pointTest",
                title:"点位检验能力"
            },{
                field:"agencyLog",
                title:"代理店录入能力"
            },{
                field:"agencyTest",
                title:"代理店检验能力"
            }]
        });
    }
    //显示模态框
    $("#add_btn").click(function(){
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
            getSelectedInfo(selections[0].dataId);
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
        $("#update_btn").attr("edit-id",obj.dataId);
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
                    ids+=this.dataId+",";
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