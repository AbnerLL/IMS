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
                    <button class="btn btn-success" id="add_file_btn">添加文件</button>
                    <button class="btn btn-primary" id="import_btn">开始导入</button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-11" style="margin-left: 30px;">
                <form id="data_form">
                    <input name="primaryKey" id="primaryKey_hidden" value="" hidden/>
                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>ACCESS表字段</th>
                                <th>ORACLE表字段</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${basePath}/js/bootstrap-fileInput/js/fileinput.js"></script>
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

    //文件上传部分
    $("#attach_file").fileinput({
        showPreview: false,
        showUpload: false,
        language:'zh',
        elErrorContainer: '#file-upload-errors',
        allowedFileExtensions: ["accdb", "mdb"],
        uploadUrl: '${basePath}/file/fileUpload',
//        uploadExtraData:getExtraParameter
    });
    //表单数据校验
    function validateForm(){
        var $selects = $("table select");
        var flag =true;
        $selects.each(function (index) {
            if (!flag){
                return ;
            }
           if (!this.value){
               alert("ORACLE字段不允许为空！");
               flag = false;
               return flag;
           }
           if (index != 0 && $("table select:first").val() == this.value){
               alert("ORACLE字段不能出现重复！");
               flag = false;
               return flag; ;
           }
        });
        return flag;
    }
    //导入数据开始
    $("#import_btn").click(function () {
        if (!validateForm()){
            return;
        };
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
            },
            error:function () {
                alert("请求异常！");
            }
        })
    });
</script>
</body>
</html>
