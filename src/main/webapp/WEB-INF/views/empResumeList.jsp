<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/11/26
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <%@include file="common/common.jsp"%>
    <title>履历信息</title>
    <link href="${basePath}/js/bootstrap-fileInput/css/fileinput.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <%--自定义表格工具栏--%>
    <div id="toolbar" class="btn-group">
        <shrio:hasPermission name="empResume:add">
            <button id="add_btn" class="btn btn-success"><span class="fa fa-plus"></span>新增</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="empResume:edit">
            <button id="edit_btn" class="btn btn-primary"><span class="fa fa-pencil-square-o"></span>修改</button>
        </shrio:hasPermission>
        <shrio:hasPermission name="empResume:delete">
            <button id="del_btn" class="btn btn-danger"><span class="fa fa-trash-o"></span>删除</button>
        </shrio:hasPermission>
        <button id="attachment_btn" class="btn btn-primary"><span class="fa fa-file"></span>&nbsp;添加附件</button>
        <shrioDiy:hasAnyPermission name="empResume:export,empResume:export:dept,empResume:export:section">
            <button id="export_btn" class="btn btn-success"><span class="fa fa-file-excel-o"></span></span>导出excel</button>
        </shrioDiy:hasAnyPermission>
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
                <h4 class="modal-title" id="myModalLabel"><span class="fa fa-plus fa-lg"></span>&nbsp;添加履历</h4>
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
                                <label for="empName_insert_input" class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_insert_input" placeholder="员工姓名">
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="speciality_insert_input" class="col-sm-2 control-label">特长</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="speciality" id="speciality_insert_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="certificate_insert_textarea" class="col-sm-2 control-label">所获证书</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="certificate" id="certificate_insert_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="award_insert_textarea" class="col-sm-2 control-label">奖励情况</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="award" id="award_insert_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="training_insert_textarea" class="col-sm-2 control-label">培训情况</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="training" id="training_insert_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="project_insert_textarea" class="col-sm-2 control-label">项目经验</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="project" id="project_insert_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workRecord_insert_textarea" class="col-sm-2 control-label">工作履历</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="workRecord" id="workRecord_insert_textarea"></textarea>
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
                <h4 class="modal-title" id="myEditModalLabel"><span class="fa fa-pencil-square-o fa-lg"></span>&nbsp;修改履历</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-11">
                        <form class="form-horizontal" id="edit_form">
                            <div class="form-group form-group-sm">
                                <label for="empId_update_input" class="col-sm-2 control-label">员工编号</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empId" id="empId_update_input" placeholder="员工编号">
                                </div>
                                <label for="empName_update_input" class="col-sm-2 control-label">员工姓名</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="empName" id="empName_update_input" placeholder="员工姓名">
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="speciality_update_input" class="col-sm-2 control-label">特长</label>
                                <div class="col-sm-10">
                                    <input class="form-control" type="text" name="speciality" id="speciality_update_input"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="certificate_update_textarea" class="col-sm-2 control-label">所获证书</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="certificate" id="certificate_update_textarea"></textarea>
                                    <%--<label for="certificate_attach_file" class="control-label sr-only" >所获证书附件</label>--%>
                                    <%--<input type="file" id="certificate_attach_file" multiple="multiple"/>--%>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="award_update_textarea" class="col-sm-2 control-label">奖励情况</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="award" id="award_update_textarea"></textarea>
                                    <%--<label for="award_attach_file" class="control-label sr-only" >奖励情况附件</label>--%>
                                    <%--<input type="file" id="award_attach_file" multiple="multiple"/>--%>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="training_update_textarea" class="col-sm-2 control-label">培训情况</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="training" id="training_update_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="project_update_textarea" class="col-sm-2 control-label">项目经验</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="project" id="project_update_textarea"></textarea>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="workRecord_update_textarea" class="col-sm-2 control-label">工作履历</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="3" name="workRecord" id="workRecord_update_textarea"></textarea>
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
<%--modal模态框(添加附件)--%>
<div class="modal fade" id="attachment_modal" tabindex="-1" role="dialog" aria-labelledby="myAttachmentModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myAttachmentModalLabel">添加附件</h4>
            </div>
            <div class="modal-body" >
                <div class="file-loading">
                    <input id="attach_file" name="attachFile" multiple type="file">
                </div>
                <div id="file-upload-errors"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="upload_btn" type="button" class="btn btn-primary">开始上传</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/bootstrap-fileInput/js/fileinput.js"></script>
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
        if(!value){
            return "";
        }
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
            url:"${basePath}/empResumes",
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
            uniqueId: "resumeId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: true,                   //是否显示父子表
            searchOnEnterKey:true,                 //设置为true，则按回车键进行搜索，否则自动搜索
            columns:[{                          //配置各列的属性
                checkbox:true
            },{
                field:"empId",
                title:"员工编号"
            },{
                field:"empName",
                title:"员工姓名"
            },{
                field:"speciality",
                title:"特长",
            },{
                field:"certificate",
                title:"证书"
            },{
                field:"award",
                title:"获奖情况",
            },{
                field:"training",
                title:"培训情况"
            },{
                field:"project",
                title:"项目经验"
            },{
                field:"workRecord",
                title:"工作履历"
            }],
            formatSearch:function(){
                return "支持编号和名称搜索";
            },
            onExpandRow:function(index,row,$detail){
                initSubTable(index,row,$detail);
            }
        });
    }

    //初始化子表
    function initSubTable(index,row,$detail){
        var resumeId=row.resumeId;//履历表主键，用来获取履历数据的附件
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            //获取数据的url
            url:"${basePath}/fileInfos?moduleId="+resumeId,
            method: 'get',//请求方式
//            toolbar: '#toolbar',                //工具按钮用哪个容器
//            classes:"table table-hover table-bordered",        //设置table的class属性
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
//            pagination: true,                   //是否显示分页（*）
//            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: myQueryParams,         //传递参数（*）
            responseHandler:myResponseHandler,  //设置解析服务器返回的数据
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
            uniqueId: "permissionId",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
//            detailView: true,                   //是否显示父子表
//            searchOnEnterKey:true,                 //设置为true，则按回车键进行搜索，否则自动搜索
            columns:[                         //配置各列的属性
                {
                    field:"oldFileName",
                    title:"文件名"
                },{
                    field:"fileExname",
                    title:"文件类型"
                },{
                    field:"uploadDate",
                    title:"上传日期",
                    formatter:dateFormatter
                },{
                    field:"resumeId",
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
        var editBtn="<button id='attachFile_download_btn' class='btn btn-primary'>下载</button>";
        var deleteBtn="<button id='attachFile_del_btn' class='btn btn-danger'>删除</button>";
        return editBtn+"&nbsp;"+deleteBtn;
    }
    //重写操作监听
    window.operateEvents={
        'click #attachFile_download_btn':function(e,value,row,index){
            showEditPermissionModal(row);
        },
        'click #attachFile_del_btn':function(e,value,row,index){
            deletePermission(row);
        }
    };
    //初始化文件上传组件
    $("#add_modal").on('shown.bs.modal',function(){
        //重置附件

        $("#uploader").powerWebUpload({auto:false ,fileNumLimit:5})
    });
    $("#add_modal").on('hidden.bs.modal',function(){
        //销毁webuploader对象
    });
    //显示新增模态框
    $("#add_btn").click(function(){
        //1.清空表单
        $("#add_form")[0].reset();
        //2.初始化用户信息
        initUserInfo();
        //3.弹出模态框并清空表单
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
        if (!user){
            return;
        }
        $("#empId_insert_input").val(user.empId);
        $("#empName_insert_input").val(user.empName);
    }
    //保存数据
    $("#save_btn").click(function(){
        //1.表单数据验证
        //2.发送ajax请求
        $.ajax({
            url:"${basePath}/empResume",
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
            initEditDataDetailById(selections[0].resumeId);
            //显示模态框
            $("#edit_modal").modal("toggle");
        }else{
            alert("选择单独一条记录");
        }
    });
    //根据ID查询对应数据并显示与编辑modal上
    function initEditDataDetailById(dataId){
        $.ajax({
            url:"${basePath}/empResume/"+dataId,
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
        $("#empId_update_input").val(obj.empId);
        $("#empName_update_input").val(obj.empName);
        $("#speciality_update_input").val(obj.speciality);
        $("#certificate_update_textarea").val(obj.certificate);
        $("#award_update_textarea").val(obj.award);
        $("#training_update_textarea").val(obj.training);
        $("#project_update_textarea").val(obj.project);
        $("#workRecord_update_textarea").val(obj.workRecord);
        $("#update_btn").attr("data-id",obj.resumeId);
    }
    //更新按钮
    $("#update_btn").click(function(){
        //1.表单数据验证
        //2.发送请求
        $.ajax({
            url:"${basePath}/empResume/"+$(this).attr("data-id"),
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
                    ids+=this.resumeId+",";
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
            url:"${basePath}/empResume/"+ids,
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
    //导出excel按钮
    $("#export_btn").click(function () {
        window.location.href="${basePath}/empResumeExcel?"+$("#search_form").serialize();
    });
    //添加附件按钮
    $("#attachment_btn").click(function () {
        $("#attachment_modal").modal("toggle");
    });
    $("#attach_file").fileinput({
        showPreview: false,
        showUpload: false,
        language:'zh',
        elErrorContainer: '#file-upload-errors',
        allowedFileExtensions: ["jpg", "png", "gif","txt"],
        uploadUrl: '${basePath}/file/fileUpload',
        uploadExtraData:getExtraParameter
    });
    //上传文件是添加的额外参数
    function getExtraParameter(previewId,index){
        console.log('previewId:'+previewId);
        console.log('index:'+index);
        return {};
    }
    //上传按钮
    $("#upload_btn").on("click",function () {
        $("#attach_file").fileinput("upload");
    });
</script>
</body>
</html>
