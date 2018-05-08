<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2018/3/27
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <title>导入管理</title>
    <%@include file="common/common.jsp"%>
    <link href="${basePath}/js/bootstrap-fileInput/css/fileinput.css" rel="stylesheet">
    <style>
        fieldset{padding:.35em .625em .75em;margin:0 2px;border:1px solid silver}

        legend{padding:.5em;border:0;width:auto}
        /*#file_list_table tr,td{*/
            /*border: red 1px solid;*/
        /*}*/
        #file_list_table tr{
            margin-bottom: 5px;
            padding-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div style="height: 15px;"></div>
        <div class="row">
            <div class="col-sm-10">
                <div class="form form-horizontal">
                    <div class="form-group form-group-sm">
                        <div class="col-sm-4">
                            <label for="fileName_select" class="control-label col-sm-4">选择文件</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="fileName_select">
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <label for="sheetName_select" class="control-label col-sm-4">选择sheet</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="sheetName_select" >
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <label for="orclTableName_select" class="control-label col-sm-4">选择表名</label>
                            <div class="col-sm-8">
                                <select class="form-control" id="orclTableName_select">
                                    <option value=""></option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-2">
                <div class="btn-group btn-group-sm">
                    <button class="btn btn-success" id="manage_file_btn">文件管理</button>
                    <button class="btn btn-primary" id="import_btn">开始导入</button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-11" style="margin-left: 30px;">
                <form id="data_form">
                    <input name="primaryKey" id="primaryKey_hidden" value="" hidden/>
                    <table class="table table-condensed" id="import_col_tb">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>ACCESS表字段</th>
                                <th>ORACLE表字段</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <!-- 文件管理 -->
    <div class="modal fade" id="list_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel1">文件列表</h4>
                    </div>
                <div class="tab1">
                    <div class="modal-body" style="margin-bottom: 0px;height: 300px;overflow: auto;">
                        <table id="file_list_table" style="width:100%;" cellspacing="10" cellpadding="10" class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 15%">序号</th>
                                <th style="width: 55%">文件名</th>
                                <th style="width: 30%;text-align: center">操作</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button id="add_file_btn" type="button" class="btn btn-primary">添加文件</button>
                    </div>
                </div>
                <div class="tab2" style="display: none;">
                    <div class="modal-body" style="margin-bottom: 0px;">
                        <div class="file-loading">
                            <input id="attach_file" name="attachFile" multiple type="file">
                        </div>
                        <div id="file-upload-errors"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="return_btn">返回</button>
                        <button id="upload_btn" type="button" class="btn btn-primary">开始上传</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 添加文件 -->
    <div class="modal fade" id="add_file_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" data-backdrop="static">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

            </div>
        </div>
    </div>
    <script type="text/javascript" src="${basePath}/js/bootstrap-fileInput/js/fileinput.js"></script>
    <script type="text/javascript" src="${basePath}/js/bootstrap-fileInput/js/locales/fileinput_zh.js"></script>
<script type="text/javascript">
    $(function () {
        initImportFile();
        initOracleDetail();
    });
    //获取可导入的文件
    function initImportFile() {
        $.ajax({
            url:"${basePath}/import/importFileDetail",
            method:"get",
            dataType:"json",
            success:function (result) {
                if (1==result.code){
                    initFileNameSelect(result.extend.info);
                }
            }
        })
    }
    var accessDB={};
    //解析请求数据并初始化文件下拉选
    function initFileNameSelect(result) {
        accessDB = result;
        var $tr = $("#fileName_select");
        $.each(result,function(index,val){
            $tr.append("<option value='"+val.dbName+"'>"+val.dbName+"</option>");
        });
    }
    var oracleDB={};
    //加载table中的Oracle字段
    function initOracleDetail() {
        $.ajax({
            url:"${basePath}/import/importOracleDetail",
            method:"get",
            dataType:"json",
            success:function (result) {
                if (1==result.code){
                    loadOrclTableNameSelect(result.extend.info);
                }
            }
        })
    }
    //加载数据库表名下拉选
    function loadOrclTableNameSelect(result){
        oracleDB = result.oracleDB;
        primaryKeyMap = result.primaryKey;
        var descriptJson = result.tableDescript;
        $.each(oracleDB,function (key,val) {
            $("#orclTableName_select").append("<option value='"+key+"'>"+descriptJson[key]+"</option>");
        });
    }
    //为表名下拉选添加事件
    $("#orclTableName_select").change(function () {
        var ele = this;
        //将表名对应的字段填充到table的select中去
        var columnArray = {};
        $.each(oracleDB,function (i,value) {
            if (ele.value == i){
                columnArray = oracleDB[i];
            }
        });
        var options="<option value=''></option>";
        $.each(columnArray,function (key,val) {
            options += "<option value='"+val+"'>"+key+"</option>";
        })
        //将生成的option添加至table中的select中
        $("table select").empty().append(options);
        //根据primaryKeyMap设置主键字段
        funSetPrimaryKey();
        //根据table中access字段自动匹配oracle字段
        console.log(primaryKeyMap);
    });
    var primaryKeyMap={};
    //根据primaryKeyMap设置主键字段
    function funSetPrimaryKey(){
        var tableName = $("#orclTableName_select").val();
        var primaryKey = primaryKeyMap[tableName] || "";
        $("#primaryKey_hidden").val(primaryKey);
    }
    //对tbody内的tr重新表序号
    function anewSort() {
        var $trs = $("table tbody tr");
        $trs.each(function (i) {
            $(this).children().eq(0).text(i+1);
        })
    }
    //删除按钮加上点击事件
    $("table").on('click',"button[name='del-btn']",function () {
        var $tr = $(this).closest("tr");
        $tr.remove();
        //重新排序
        anewSort();
    })
    //添加change事件
    $("#sheetName_select").change(function () {
        //清空table中的数据
        $("table tbody").empty();
        var columnArray = "";
        for (var i in accessDB){
            if ($("#fileName_select").val() == accessDB[i].dbName ){
                var accessSheetList=accessDB[i].accessTableList;
                for (var j in accessSheetList){
                    if (this.value == accessSheetList[j].tableName){
                        columnArray = accessSheetList[j].accessColumnList;
                        break;
                    }
                }
            }
        }
        $.each(columnArray ,function (index,val) {
            var $tr=$("<tr></tr>");
            $tr.append("<td>"+eval(index+1)+"</td>").append("<td><input class='form-control' name='accessField' value='"+val.columnName+"' readonly style='width: 180px;'/></td>").append("<td><select class='form-control' name='orclField' style='width: 180px;'></select></td>");
            $tr.append("<td>" +
                "<button class='btn btn-danger' name ='del-btn'>删除</button>" +
                "</td>");
            $("table tbody").append($tr);
        });

    });
    //添加change事件
    $("#fileName_select").change(function () {
        //清空sheet下拉选
        $("#sheetName_select").empty();
        //清空table中的数据
        $("table tbody").empty();
        //根据不同的文件名加载sheetName
        var sheetArray = "";
        for (var i in accessDB){
            if (this.value == accessDB[i].dbName){
                sheetArray = accessDB[i].accessTableList;
                break;
            }
        }
        var sheetOption="";
        if (sheetArray){
            $.each(sheetArray,function (index,val) {
                sheetOption +='<option value="'+val.tableName+'">'+val.tableName+'</option>';
            });
        }
        $("#sheetName_select").append("<option value=''></option>").append(sheetOption);
    });

    //表单数据校验
    function validateForm(){
        var flag = true;
        var value_container = '';
        var tb = document.getElementById("import_col_tb");
        var tb_tbody = tb.getElementsByTagName("tbody")[0];
        var tb_tbody_trs = tb_tbody.getElementsByTagName("tr");
        if (tb_tbody_trs.length == 0){
            alert("请至少指定一行数据！");
            return false;
        }
        for (var i=0; i<tb_tbody_trs.length; i++){
            var tds = tb_tbody_trs[i].childNodes;
            var td_select = tds[2].firstChild;
            var selectId = td_select.selectedIndex;
            var orl_val = td_select.options[selectId].value;
            if (!orl_val){
                alert("ORACLE字段不允许为空！");
                flag = false;
                break;
            }else{
                if (value_container.indexOf('['+orl_val+']') != -1){
                    alert("ORACLE字段不能出现重复！");
                    flag = false;
                    break;
                }
                value_container += '['+orl_val+']';
            }
        }
        return flag;
    }
    //导入数据开始
    $("#import_btn").click(function () {
        if (!validateForm()){
            return;
        };
        var $import_btn = $(this);
        //禁用导入按钮
        $import_btn.attr("disabled","true").text("导入中...");
        var fileName = $("#fileName_select").val();
        var sheetName = $("#sheetName_select").val();
        var orclTableName = $("#orclTableName_select").val();
        $.ajax({
            url:"${basePath}/import/importAccessData",
            method:"post",
            data:$("#data_form").serialize()+"&fileName="+fileName+"&sheetName="+sheetName+"&orclTableName="+orclTableName,
            dataType:"json",
            success:function(result){
                if(1==result.code){
                       alert("处理成功")
                }else{
                    alert("处理失败！")
                }
                //启用导入按钮
                $import_btn.removeAttr("disabled").text("开始导入");
            },
            error:function () {
                alert("请求异常！");
                //启用导入按钮
                $import_btn.removeAttr("disabled").text("开始导入");
            }
        })
    });
    //文件列表按钮
    $("#manage_file_btn").click(function () {
        //弹出模态框
        $("#list_modal").modal("toggle");
        //加载所有文件信息
        loadAllImportFileDetail();
    });
    //加载所有文件信息
    function loadAllImportFileDetail() {
        $.ajax({
            url:"${basePath}/import/importFileDetail",
            method:"get",
            dataType:"json",
            success:function (result) {
                if (1==result.code){
                    initFileListTable(result.extend);
                }
            }
        })
    }
    //初始化文件列表
    function initFileListTable(data) {
        var fileInfo = data.info;
        var downloadInfo = data.downloadInfo;
        var logJson = data.logInfo;
        var $tbody = $("#file_list_table tbody").empty();
        $.each(fileInfo,function (index,value) {
            var fileName = value.dbName.substring(0,value.dbName.indexOf("."));
            var $tr = $("<tr></tr>");
            $tr.append("<td>"+(index+1)+"</td>")
                .append("<td>"+value.dbName+"</td>")
                .append("<td><div style='text-align: center'><button class='btn btn-info btn-sm download-btn' data-download-url='"+(downloadInfo[value.dbName] || '')+"'><span class='fa fa-download'></button>&nbsp;" +
                        "<button class='btn btn-success btn-sm search-btn' data-search-id='"+(logJson[fileName] || '')+"'><span class='fa fa-search'></span></button>&nbsp;"+
                        "<button class='btn btn-danger btn-sm del-file-btn' data-delete-id='"+fileName+"'><span class='fa fa-trash'></span></button></div></td>");

            $tbody.append($tr);
        });
    }
    //查看导入文件的log日志
    $("#file_list_table").on('click',"button[class*='search-btn']",function () {
        if($(this).attr("data-search-id")){
            window.open($(this).attr("data-search-id"));
        }
    });
    //下载文件按钮
    $("#file_list_table").on('click',"button[class*='download-btn']",function () {
        if ($(this).attr("data-download-url")){
            window.open($(this).attr("data-download-url"));
        }
    });
    //删除文件按钮
    $("#file_list_table").on('click',"button[class*='del-file-btn']",function (){
        if (confirm("是否删除该文件！")){
            $.ajax({
                url:'${basePath}/import/deleteImportFile?fileName='+$(this).attr("data-delete-id"),
                method:'get',
                dataType:'json',
                success:function (result) {
                    if (1==result.code){
                        alert("删除成功");
                        //刷新列表
                        loadAllImportFileDetail();
                    }else {
                        alert("删除失败");
                    }
                }
            })
        }
    });
    //显示
    $("#add_file_btn").click(function () {
        $(".tab1").hide();
        $(".tab2").show();
    });
    //返回按钮
    $("#return_btn").click(function () {
        $(".tab2").hide();
        $(".tab1").show();
        //刷新列表
        loadAllImportFileDetail();
    });
    //初始化文件上传组件
    $("#attach_file").fileinput({
        showPreview: true,
        showUpload: false,
        language:'zh',
        elErrorContainer: '#file-upload-errors',
        allowedFileExtensions: ["accdb"],
        uploadUrl: '${basePath}/import/uploadImportFile',
        uploadExtraData:getExtraParameter
    });
    //上传文件是添加的额外参数
    function getExtraParameter(previewId,index){
        return {};
    }
    //上传按钮
    $("#upload_btn").on("click",function () {
        $("#attach_file").fileinput("upload");
    });
</script>
</body>
</html>
