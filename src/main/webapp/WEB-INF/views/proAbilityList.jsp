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
                                        <label for="empId_search_input" class="control-label">员工编号:</label>
                                        <input class="form-control" type="text" name="empId" id="empId_search_input"/>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="empName_search_input" class="control-label">员工姓名:</label>
                                        <input class="form-control" type="text" name="empName" id="empName_search_input"/>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="version_search_input" class="control-label">作业版本:</label>
                                        <input class="form-control" type="text" name="version" id="version_search_input"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="height: 5px;"></div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="section_search_select" class="control-label">所属科室:</label>
                                        <select class="form-control" name="section" id="section_search_select">
                                            <option value="">---选择所属科室---</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <div class="form-group form-group-sm">
                                            <label for="proStage_search_select" class="control-label">业务阶段:</label>
                                            <select class="form-control" name="proStage" id="proStage_search_select">
                                                <option value="">---选择业务阶段---</option>
                                                <option value="前期">前期</option>
                                                <option value="后期">后期</option>
                                                <option value="出品">出品</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="proType_search_select" class="control-label">业务类型:</label>
                                        <select class="form-control" name="proType" id="proType_search_select">
                                            <option value="">---选择业务类型---</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="height: 5px;"></div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="logAbility_search_select" class="control-label">录入能力:</label>
                                        <select class="form-control" name="logAbility" id="logAbility_search_select">
                                            <option value="">---选择录入能力---</option>
                                            <option value="1">被指导下完成</option>
                                            <option value="2">独立完成</option>
                                            <option value="3">熟练掌握</option>
                                            <option value="4">精通</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group form-group-sm">
                                        <label for="testAbility_search_select" class="control-label">检验能力:</label>
                                        <select class="form-control" name="testAbility" id="testAbility_search_select">
                                            <option value="">---选择业务类型---</option>
                                            <option value="有">有</option>
                                            <option value="无">无</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="btn btn-primary btn-sm" id="search_btn">查询</div>
                                    <div class="btn btn-default btn-sm" id="search_reset_btn">清空</div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%--自定义表格工具栏--%>
        <div id="toolbar" class="btn-group">
            <shrio:hasPermission name="proAbility:add">
                <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
            </shrio:hasPermission>
            <shrio:hasPermission name="proAbility:edit">
                <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
            </shrio:hasPermission>
            <shrio:hasPermission name="proAbility:delete">
                <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
            </shrio:hasPermission>
            <shrioDiy:hasAnyPermission name="proAbility:export,proAbility:export:section,proAbility:export:dept">
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
                                    <label for="empId_insert_input" class="control-label col-sm-2" >员工编号:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="empId" id="empId_insert_input"/>
                                    </div>
                                    <label for="empName_insert_input" class="control-label col-sm-2">员工姓名:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="empName" id="empName_insert_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="version_insert_input" class="control-label col-sm-2" >作业版本:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="version" id="version_insert_input"/>
                                    </div>
                                    <label for="section_insert_select" class="control-label col-sm-2" >所属科室:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="section" id="section_insert_select">
                                            <option value="">---选择所属科室---</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="proStage_insert_select" class="control-label col-sm-2">业务阶段:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="proStage" id="proStage_insert_select">
                                            <option value="">---选择业务阶段---</option>
                                            <option value="前期">前期</option>
                                            <option value="后期">后期</option>
                                            <option value="出品">出品</option>
                                        </select>
                                    </div>
                                    <label for="proType_insert_select" class="control-label col-sm-2">业务类型:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="proType" id="proType_insert_select">
                                            <option value="">---选择业务类型---</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="logAbility_insert_select" class="control-label col-sm-2">录入能力:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="logAbility" id="logAbility_insert_select">
                                            <option value="">---选择录入能力---</option>
                                            <option value="1">被指导下完成</option>
                                            <option value="2">独立完成</option>
                                            <option value="3">熟练掌握</option>
                                            <option value="4">精通</option>
                                        </select>
                                    </div>
                                    <label for="testAbility_insert_select" class="control-label col-sm-2">检验能力:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="testAbility" id="testAbility_insert_select">
                                            <option value="">---选择检验能力---</option>
                                            <option value="有">有</option>
                                            <option value="无">无</option>
                                        </select>
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
                                    <label for="empId_update_input" class="control-label col-sm-2" >员工编号:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="empId" id="empId_update_input"/>
                                    </div>
                                    <label for="empName_update_input" class="control-label col-sm-2">员工姓名:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" name="empName" id="empName_update_input"/>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="version_update_input" class="control-label col-sm-2" >作业版本:</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" type="text" name="version" id="version_update_input"/>
                                    </div>
                                    <label for="section_update_select" class="control-label col-sm-2" >所属科室:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="section" id="section_update_select">
                                            <option value="">---选择所属科室---</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="proStage_update_select" class="control-label col-sm-2">业务阶段:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="proStage" id="proStage_update_select">
                                            <option value="">---选择业务阶段---</option>
                                            <option value="前期">前期</option>
                                            <option value="后期">后期</option>
                                            <option value="出品">出品</option>
                                        </select>
                                    </div>
                                    <label for="proType_update_select" class="control-label col-sm-2">业务类型:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="proType" id="proType_update_select">
                                            <option value="">---选择业务类型---</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group form-group-sm">
                                    <label for="logAbility_update_select" class="control-label col-sm-2">录入能力:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="logAbility" id="logAbility_update_select">
                                            <option value="">---选择录入能力---</option>
                                            <option value="1">被指导下完成</option>
                                            <option value="2">独立完成</option>
                                            <option value="3">熟练掌握</option>
                                            <option value="4">精通</option>
                                        </select>
                                    </div>
                                    <label for="testAbility_update_select" class="control-label col-sm-2">检验能力:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="testAbility" id="testAbility_update_select">
                                            <option value="">---选择检验能力---</option>
                                            <option value="有">有</option>
                                            <option value="无">无</option>
                                        </select>
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
</body>
<script type="text/javascript" src="${basePath}/js/weboption.js"></script>
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
            uniqueId: "uuid",                     //每一行的唯一标识，一般为主键列
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
                field:"section",
                title:"项目组"
            },{
                field:"proStage",
                title:"业务阶段"
            },{
                field:"proType",
                title:"业务类型"
            },{
                field:"logAbility",
                title:"录入能力"
            },{
                field:"testAbility",
                title:"检验能力"
            }]
        });
    }
    //显示模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.加载员工信息
        initUserInfo();
        //3.
        $("#add_modal").modal("toggle");
    });
    //初始化用户的基本数据
    function initUserInfo(){
        $.ajax({
            url:"${basePath}/currentEmp",
            type:"GET",
            dataType:"json",
            success:function (result) {
                if (1==result.code){
                    loadUserInfo(result.extend.entities[0]);
                }
            }
        });
    }
    function loadUserInfo(user) {
        $("#empId_insert_input").val(user.empId);
        $("#empName_insert_input").val(user.empName);
        $("#section_insert_select").val(user.section);
    }
    //保存按钮
    $("#save_btn").click(function(){
        //1.表单验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/proAbility",
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
            getSelectedInfo(selections[0].uuid);
            //3.显示模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("请选择单条数据");
            return;
        }
    });
    //根据id获取当前对象的信息
    function getSelectedInfo(uuid){
        $.ajax({
            url:"${basePath}/proAbility/"+uuid,
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
        $("#section_update_select").val(obj.section);
        $("#proStage_update_select").val(obj.proStage);
        $("#proStage_update_select").change();
        $("#proType_update_select").val(obj.proType);
        $("#logAbility_update_select").val(obj.logAbility);
        $("#testAbility_update_select").val(obj.testAbility);
        //在更新按钮上绑定主键
        $("#update_btn").attr("edit-id",obj.uuid);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单校验
        //2.发送请求
        $.ajax({
            url:"${basePath}/proAbility/"+$(this).attr("edit-id"),
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
                    ids+=this.uuid+",";
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
            url:"${basePath}/proAbility/"+id,
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
    var selectJson={
        "前期":["道路图标","中文名称","中文地址","英文名称","英文地址","深度信息","代理店"],
        "后期":["道路后期","设施后期"],
        "出品":["报表分析"]
    }
    /**
     * 关联下拉选
     */
    function linkSelect(parentSelect,subSelect,linkJson){
        //1.清空子下拉选中value不为空的option
        var defaultOption=subSelect.children("[value='']");
        //清空子下拉选
        subSelect.empty();
        subSelect.append(defaultOption);
        var optionArray=linkJson[parentSelect.value];
        for(var index in optionArray){
            subSelect.append("<option value='"+optionArray[index]+"'>"+optionArray[index]+"</option>");
        }
    }
    $("#proStage_search_select").change(function () {
        linkSelect(this,$("#proType_search_select"),selectJson);
    });
    $("#proStage_insert_select").change(function () {
        linkSelect(this,$("#proType_insert_select"),selectJson);
    });
    $("#proStage_update_select").change(function () {
        linkSelect(this,$("#proType_update_select"),selectJson);
    });
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
        window.location.href="${basePath}/proAbilityExcel?"+$("#search_form").serialize();
    });
    //加载项目组下拉选
    $("#section_search_select,#section_insert_select,#section_update_select").weboption({
        url:"${basePath}/sysGroup/loadAll",
        key:"groupName",
        value:"groupName",
        search:{groupType:"dept"},
        append:true,
        processResult:processData
    });
    //处理请求返回的数据
    function processData(resultData){
        var original_array = resultData.extend.entities;
        var tempArray = [];
        var result = original_array.filter(function(item){
            if (item.parentGroupCode.length >= 5 && !tempArray.includes(item.groupName)){
                tempArray.push(item.groupName);
                return true;
            }else {
                return false;
            }
        });
        return result;
    }
</script>
</html>