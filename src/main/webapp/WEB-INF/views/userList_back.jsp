<%--
  Created by IntelliJ IDEA.
  User: luozhihui
  Date: 2017/10/2
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <%@include file="head.jsp"%>
    <meta http-equiv="x-ua-compatible" content="IE=edeg,chrom=1">
    <%--禁止用户缩放user-scalable=no--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <%--基础css--%>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet"/>

    <%--基础js文件--%>
    <script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
    <script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="row" style="padding-left: 5px;padding-top: 10px;">
            <div class="col-md-4 col-xs-12">
                    <div class="input-group">
                        <input type="search" class="form-control" placeholder="关键字"/>
                        <span class="input-group-btn">
                            <button id="user_search_btn" class="btn btn-success">查询</button>
                        </span>
                    </div>
            </div>
            <div class="col-md-4 col-md-offset-4 col-xs-12">
                <button id="user_add_btn" class="btn btn-primary btn-sm">新增</button>
                <button id="user_update_btn" class="btn btn-warning btn-sm">修改</button>
                <button id="user_batch_del_btn" class="btn btn-danger btn-sm">删除</button>
            </div>
        </div>
        <%--表格数据--%>
        <div class="row" style="padding-left: 20px;padding-top: 10px;padding-right: 5px;">
            <div class="panel panel-info">
                <div class="panel-heading">用户列表</div>
                <%--数据列表--%>
                <table id="user_tables" class="table table-condensed table-hover table-responsive" style="height: 100px">
                    <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="check_all" title="全选"/>
                        </th>
                        <th>用户名</th>
                        <th>密码</th>
                        <th>有效标识</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
        <%--翻页功能--%>
        <div class="row" style="padding-left: 20px;padding-right: 5px;">
            <div class="col-md-6 col-xs-12" id="page_info_area">
            </div>
            <div class="col-md-6 col-xs-12" id="page_nav_area" style="text-align: right">
            </div>
        </div>

    </div>
    <%--modal模态框(用户添加)--%>
    <div class="modal fade" id="userAddmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加用户</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="user_add_form">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="email" class="form-control" name="username" id="username" placeholder="用户名">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否启用</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="status" id="isValid1" value="1" checked="checked"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="status" id="isValid12" value="0"> 否
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button id="user_save_btn" type="button" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script>
    //数据总记录数
    var totalRecodeNum;
    //用来存放当前页数
    var currentPage;
    $(function(){
        findByPage(1);
    });
    //定义一个根据通用返回结果解析数据的方法
    function build_table(result) {
        //1.解析表格数据
        build_users_table(result);
        //2.解析导航栏(1.解析导航栏信息，2.解析导航栏)
        build_page_info(result);

        build_page_nav(result);
    }
    //定义一个根据分页的请求ajax
    function findByPage(pn){
        $.ajax({
            url:"${basePath}/users",
            data:"pn="+pn,
            type:"get",
            dataType:"json",
            success:function(result){
                //1.解析表格数据
                build_users_table(result);
                //2.解析导航栏(1.解析导航栏信息，2.解析导航栏)
                build_page_info(result);

                build_page_nav(result);
            }
        })
    }
    //加载table表格数据
    function build_users_table(result){
        //先清空table中的数据
        $("#user_tables tbody").empty();
        var users=result.extend.pageInfo.list;
        $.each(users,function(index,item){
            var checkbox=$("<input type='checkbox' class='checkbox_item'/>").val(item.id);
            var checkboxTd=$("<td></td>").append(checkbox);
//            var num=$("<td></td>").append(index+1);
            var username=$("<td></td>").append(item.username);
            var password=$('<td></td>').append(item.password);
            var valid=$("<td></td>").append(item.status=="1"?"是":"否");
            var createTime=$("<td></td>").append(item.createTime);
            $("<tr></td>")
                .append(checkboxTd)
                .append(username)
                .append(password)
                .append(valid)
                .append(createTime)
                .appendTo("#user_tables tbody");
        })
    }
    //解析并显示分页信息page_info_area
    function build_page_info(result){
        //清空原有内容
        $("#page_info_area").empty();
        $("#page_info_area")
            .append("当前为"+result.extend.pageInfo.pageNum+"页，共"+result.extend.pageInfo.pages+"页，共"+result.extend.pageInfo.total+"条记录");
        //给总记录数赋值
        totalRecodeNum=result.extend.pageInfo.total;
        currentPage=result.extend.pageInfo.pageNum;
    }
    //解析并显示分页条page_nav_area
    function build_page_nav(result){
        //清空原有内容
        $("#page_nav_area").empty();
        var ui=$("<ul style='margin-bottom: 0;margin-top: 0;'></ul>").addClass("pagination pagination-sm");
        //构建第一页li
        var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        //构建前一页li
        var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
        //判断是否有前一页
        if(result.extend.pageInfo.hasPreviousPage==false){
            //添加禁止样式
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        }else{
            //添加单击事件
            firstPageLi.click(function(){
                findByPage(1);
            });
            prePageLi.click(function(){
                findByPage(result.extend.pageInfo.pageNum-1)
            });
        }
        //构建后一页li
        var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
        //构建最后一页li
        var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
        //判断是否有下一页
        if(result.extend.pageInfo.hasNextPage==false){
            nextPageLi.addClass("disabled")
            lastPageLi.addClass("disabled");
        }else{
            nextPageLi.click(function(){
                findByPage(result.extend.pageInfo.pageNum+1)
            });
            lastPageLi.click(function(){
                findByPage(result.extend.pageInfo.pages);
            });
        }
        //添加首页和前一页提示
        ui.append(firstPageLi).append(prePageLi);
        //循环构建提示条
        $.each(result.extend.pageInfo.navigatepageNums,function(index,item){
            var numLi=$("<li></li>").append($("<a></a>").append(item).attr("href","#"));
            //当前选中的li添加active样式(不绑定点击事件)
            if(result.extend.pageInfo.pageNum==item){
                numLi.addClass("active");
            }else{
                //绑定点击事件
                numLi.click(function(){
                    findByPage(item);
                });
            }
            ui.append(numLi);
        });
        //添加后一页和末页li
        ui.append(nextPageLi).append(lastPageLi);
        //将ui添加到nav中
        var nav=$("<nav></nav>").append(ui);
        $("#page_nav_area").append(nav);
    }
    //清空表单内容及样式
    function reset_form(ele){
        //清空表单内容
        $(ele)[0].reset();
        //清空表单验证样式及验证信息
        $(ele).find("*").removeClass("has-success has-error");
        $(ele).find(".help-block").text("");
    }
    //点击模态框背景，不隐藏模态框
    $("#user_add_btn").click(function(){
        //清楚模态框中的数据（表单重置）
       reset_form("#userAddmodal form");
        //显示模态框
        $("#userAddmodal").modal({
            backdrop:"static"
        });
    });
    //新增用户表单数据验证
    function validate_add_form(){
        var username=$("#username").val();
        var usernameRexp=/(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,10}$)/;
        //用户名校验
        if(!usernameRexp.test(username)){
            //添加提示信息及校验状态
            show_validate_msg("#username","error","用户名为6到16位数字符组合，或者3到10个字符");
            return false;
        }else{
            show_validate_msg("#username","success");
        }
        var password=$("#password").val();
        var passwordRexp=/^[a-z0-9_-]{6,18}$/;
        if(!passwordRexp.test(password)){
            show_validate_msg("#password","error","密码长度为6到18位");
            return false;
        }else{
            show_validate_msg("#password","success")
        }
        return true;
    }
    //显示表单信息
    function show_validate_msg(ele,status,msg){
        //清除原有校验状态和校验信息
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");
        if("success"==status){
            $(ele).parent().addClass("has-success");
        }else {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    }
    //新增用户(保存员工)
    $("#user_save_btn").click(function(){
        //校验表单数据
        if(!validate_add_form()){
            return;
        }
        //发送ajax请求
        $.ajax({
            url:"${basePath}/user",
            type:"POST",
            data:$("#user_add_form").serialize(),
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    alert("保存成功");
                    //关闭模态框
                    $("#userAddmodal").modal("hide");
                    //查询新插入的数据
                    findByPage(totalRecodeNum);
                }else{
                    alert("保存失败");
                }
            }
        });
    });
    //单选框的全选全不选
    $("#check_all").click(function(){
        $(".checkbox_item").prop("checked",$(this).prop("checked"));
    });
    //每个checkbox添加单击事件
    $(document).on("click",".checkbox_item",function(){
        var flag=$(".checkbox_item:checked").length==$(".checkbox_item").length;
        $("#check_all").prop("checked",flag);
    });
    //删除操作的ajax
    function del_user_batch(id) {
        $.ajax({
            url:"${basePath}/user/"+id,
            type:"DELETE",
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    //重新加载表格
                    findByPage(currentPage);
                }
            }
        });
    }
    //删除操作
    $("#user_batch_del_btn").click(function(){
        var items=$(".checkbox_item:checked");
        if(items.length==0){
            alert("请至少选择一条数据");
        }else{
            var id="";
            $.each(items,function(){
                id+=$(this).val()+",";
            });
            //去掉最后一个分隔符
            id=id.substring(0,id.length-1);
            //删除操作
            del_user_batch(id);

        }
    });
    //关键词搜索的ajax
    function user_search_ajax(keyWord) {
        $.ajax({
            url:"${basePath}/users/search",
            data:"keyword="+keyWord,
            type:"GET",
            dataType:"json",
            success:function(result){
                if(result.code==1){
                    build_table(result);
                }
            }
        });
    }
    //关键词搜索
    $("#user_search_btn").click(function(){
        var keyWord=$(document).find("input[type='search']").val();
        user_search_ajax(keyWord);
    });
</script>
