<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp"%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>人员综合信息管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!--Bootstrap、图标-->

    <link href="${requestScope.basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${requestScope.basePath}/css/font-awesome.min2.css" rel="stylesheet" />

    <!--ace、皮肤-->
    <link href="${requestScope.basePath}/css/aceNav/ace.css" rel="stylesheet" />
    <link href="${requestScope.basePath}/css/aceNav/ace-skins.min.css" rel="stylesheet" />
</head>
<script type="text/javascript">
    var baseUrl="${requestScope.basePath}";
</script>
<body class="skin-1">
<div id="_LDmask" style="position: absolute; top: 0; left: 0; bottom: 0; right: 0; background-color: white; z-index: 9999; text-align: center; background-image: url('data:image/gif;base64,R0lGODlhJQAlAJECAL3L2AYrTv///wAAACH/C05FVFNDQVBFMi4wAwEAAAAh+QQFCgACACwAAAAAJQAlAAACi5SPqcvtDyGYIFpF690i8xUw3qJBwUlSadmcLqYmGQu6KDIeM13beGzYWWy3DlB4IYaMk+Dso2RWkFCfLPcRvFbZxFLUDTt21BW56TyjRep1e20+i+eYMR145W2eefj+6VFmgTQi+ECVY8iGxcg35phGo/iDFwlTyXWphwlm1imGRdcnuqhHeop6UAAAIfkEBQoAAgAsEAACAAQACwAAAgWMj6nLXAAh+QQFCgACACwVAAUACgALAAACFZQvgRi92dyJcVJlLobUdi8x4bIhBQAh+QQFCgACACwXABEADAADAAACBYyPqcsFACH5BAUKAAIALBUAFQAKAAsAAAITlGKZwWoMHYxqtmplxlNT7ixGAQAh+QQFCgACACwQABgABAALAAACBYyPqctcACH5BAUKAAIALAUAFQAKAAsAAAIVlC+BGL3Z3IlxUmUuhtR2LzHhsiEFACH5BAUKAAIALAEAEQAMAAMAAAIFjI+pywUAIfkEBQoAAgAsBQAFAAoACwAAAhOUYJnAagwdjGq2amXGU1PuLEYBACH5BAUKAAIALBAAAgAEAAsAAAIFhI+py1wAIfkEBQoAAgAsFQAFAAoACwAAAhWUL4AIvdnciXFSZS6G1HYvMeGyIQUAIfkEBQoAAgAsFwARAAwAAwAAAgWEj6nLBQAh+QQFCgACACwVABUACgALAAACE5RgmcBqDB2MarZqZcZTU+4sRgEAIfkEBQoAAgAsEAAYAAQACwAAAgWEj6nLXAAh+QQFCgACACwFABUACgALAAACFZQvgAi92dyJcVJlLobUdi8x4bIhBQAh+QQFCgACACwBABEADAADAAACBYSPqcsFADs=');background-repeat:no-repeat;background-position:49% 40%">
</div>

<!--导航-->
<div id="navbar" class="navbar navbar-default navbar-fixed-top">
    <div class="navbar-container" id="navbar-container">

        <!--导航小按钮-->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <!--Logo-->
        <div class="navbar-header pull-left hidden-xs">
            <a href="#" class="navbar-brand">
                <i class="fa fa-reddit-alien"></i> 人员综合信息管理系统
            </a>
        </div>

        <!--右侧消息-->
        <div class="navbar-buttons navbar-header pull-right" role="navigation" style="white-space:nowrap">
            <ul class="nav ace-nav">
                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                        <span class="badge badge-important">2</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-bell"></i>共 2 条消息
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                        <span class="pull-left">
                                            系统通知：于2017...
                                        </span>
                                    <span class="pull-right badge badge-info">+12</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="clearfix">
                                        <span class="pull-left">
                                            你有有一条留言
                                        </span>
                                </div>
                            </a>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                查看全部<i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="green">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                        <span class="badge badge-success">5</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-envelope-o"></i>
                            共 5 个邮件
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">
                                <li>
                                    <a href="#">
                                        <img src="${requestScope.basePath}/images/photo.jpg" class="msg-photo" alt="图标" />
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Alex:</span>
                                                        功能未开放...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>a moment ago</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="${requestScope.basePath}/images/photo.jpg" class="msg-photo" alt="图标" />
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Alex:</span>
                                                        功能未开放...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>a moment ago</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="${requestScope.basePath}/images/photo.jpg" class="msg-photo" alt="图标" />
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Alex:</span>
                                                        功能未开放...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>a moment ago</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="${requestScope.basePath}/images/photo.jpg" class="msg-photo" alt="图标" />
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Alex:</span>
                                                        功能未开放...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>a moment ago</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img src="${requestScope.basePath}/images/photo.jpg" class="msg-photo" alt="图标" />
                                        <span class="msg-body">
                                                    <span class="msg-title">
                                                        <span class="blue">Alex:</span>
                                                        功能未开放...
                                                    </span>

                                                    <span class="msg-time">
                                                        <i class="ace-icon fa fa-clock-o"></i>
                                                        <span>a moment ago</span>
                                                    </span>
                                                </span>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="inbox.html">
                                查看全部<i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <!--个人信息-->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${requestScope.basePath}/images/photo.jpg" alt="头像" />
                        <span class="user-info">
                                <small>Welcome,</small>
                                系统管理员
                            </span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret" style="min-width:240px">
                        <li><a href="#"><i class="ace-icon fa fa-flag blue"></i>系统初始化向导</a></li>
                        <li><a href="#"><i class="ace-icon fa fa-cogs blue"></i>首页配置</a></li>
                        <li class="divider"></li>
                        <%--<li><a href="#"><i class="ace-icon fa fa-wechat green"></i>关注公众号</a></li>--%>
                        <%--<li><a href="#"><i class="ace-icon fa fa-apple"></i>App下载</a></li>--%>
                        <%--<li class="divider"></li>--%>
                        <li><a href="#"><i class="ace-icon fa fa-edit orange"></i>修改密码</a></li>
                        <li><a href="#"><i class="ace-icon fa fa-lock orange"></i>锁住屏幕</a></li>
                        <li class="divider"></li>
                        <li><a href="${requestScope.basePath}/logout" onclick="javascript:window._logout=true"><i class="ace-icon fa fa-power-off red"></i>安全退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<!--主-->
<div class="main-container" id="main-container">
    <div id="sidebar" class="sidebar responsive sidebar-fixed ">

        <div class="sidebar-shortcuts" id="sidebar-shortcuts" style="z-index:888;overflow:visible;text-align:left;">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large" style="white-space:nowrap;padding-left:5px">

                <!--展开收缩菜单-->
                <button class="btn btn-success sidebar-collapse" id="sidebar-collapse" title="伸缩菜单导航">
                    <i class="ace-icon fa fa-expand"></i>
                </button>

                <!--菜单模式设置-->
                <div class="btn btn-warning dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" style="color: white; display: block;">
                        <i class="ace-icon fa fa-cog"></i>
                    </a>
                    <ul id="ulset" class="dropdown-menu" style="color:#333;text-shadow:none;padding:0 5px">
                        <li>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
                            <label class="lbl" for="ace-settings-hover">菜单浮动</label>
                        </li>
                        <li>
                            <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
                            <label class="lbl" for="ace-settings-compact">菜单收缩</label>
                        </li>
                    </ul>
                </div>

                <!--切换主题-->
                <div id="divTheme" title="皮肤切换" class="btn btn-info dropdown dropdown-colorpicker">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" style="color: white; display: block;">
                        <i class="ace-icon fa fa-pencil"></i>
                    </a>
                    <ul class="dropdown-menu" id="switch_skin">
                        <li><a class="colorpick-btn selected" href="#" style="background-color:#222A2D;" data-skin="skin-1"></a></li>
                        <li><a class="colorpick-btn" href="#" style="background-color:#438EB9;" data-skin="no-skin"></a></li>
                        <li><a class="colorpick-btn" href="#" style="background-color:#C6487E;" data-skin="skin-2"></a></li>
                        <li><a class="colorpick-btn" href="#" style="background-color:#D0D0D0;" data-skin="skin-3 no-skin"></a></li>
                    </ul>
                </div>

                <!--样式设置-->
                <button class="btn btn-danger" id="btn_style_config" title="字体样式设置">
                    <i class="ace-icon fa fa-text-height"></i>
                </button>
            </div>

            <!--最小图标-->
            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-info"></span>
                <span class="btn btn-success"></span>
                <span class="btn btn-warning"></span>
                <span class="btn btn-danger"></span>
            </div>
        </div>

        <!--菜单-->
        <ul id="ulnav" class="nav nav-list"></ul>

    </div>

    <div class="main-content">

        <style>
            /* 选项卡·标签 */
            ._navtab {
                padding-top: 3px;
                position: relative;
                cursor: pointer !important;
                border-bottom: 2px solid #2C6AA0;
                _margin-top: 45px \9;
            }

            ._ntleft {
                top: 0;
                left: 0;
                width: 40px;
                height: 40px;
                line-height: 40px;
                text-indent: 7px;
                position: absolute;
                border-right: 1px solid #f2f2f2;
            }

            ._nttabs {
                height: 37px;
                overflow: hidden;
                position: relative;
                white-space: nowrap;
                margin: 0px 132px 0 43px;
            }

            ._nttabs ul._ntul {
                margin: 0;
                display: inline-block;
                white-space: nowrap !important;
            }

            ._nttabs ul._ntul li {
                font-size: 15px;
                overflow: hidden;
                margin: 0 3px 0 0;
                white-space: nowrap;
                display: inline-block;
            }

            ._nttabs ul._ntul li.active {
                color: #fff !important;
                background-color: #2C6AA0;
                border-radius: 3px 3px 0 0 !important;
            }

            ._nttabs ul._ntul li.active a {
                border: none;
                color: #fff !important;
            }

            ._nttabs ul._ntul li a {
                color: #000;
                display: block;
                margin: 0 !important;
                text-decoration: none;
                border: none !important;
                outline: none !important;
                padding: 10px 15px 5px !important;
                border-radius: 3px 3px 0 0 !important;
            }

            ._nttabs ul li a:hover {
                color: #FFF !important;
                background-color: #2C6AA0;
            }

            ._navtab .btn-group {
                top: 0;
                right: 0;
                width: 90px;
                height: 39px;
                border: none;
                line-height: 38px;
                position: absolute;
                background-color: transparent;
            }

            ._navtab .btn-group button {
                outline: none;
                white-space: nowrap;
                background-color: #fff;
                border: none !important;
            }

            ._ntright {
                top: 0;
                right: 90px;
                width: 40px;
                height: 40px;
                line-height: 40px;
                text-indent: 7px;
                position: absolute;
                background-color: #fff;
                border-left: 1px solid #f2f2f2;
                border-right: 1px solid #f2f2f2;
            }

            ._ntleft:hover, ._navtab .btn-group button:hover, ._ntright:hover {
                background-color: #f2f2f2;
            }

            /* 选项卡·内容 */
            ._navbox {
                padding: 0;
                border: 1px solid transparent;
            }

            ._navbox iframe {
                width: 100%;
                height: 100%;
                border: none;
            }
        </style>
        <!--选项卡·标签-->
        <div class="hidden-xs _navtab" id="navtab">
            <div class="_ntleft"><i class="fa fa-backward"></i></div>
            <div class="_nttabs">
                <ul class="_ntul">
                    <li class="active"><a href="#page_desk" data-toggle="tab"><i class="fa fa-home"></i> 桌面</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button data-toggle="dropdown">关闭操作 <i class="ace-icon fa fa-angle-down icon-on-right"></i></button>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a>定位当前选项卡</a></li>
                    <li class="divider"></li>
                    <li><a>关闭全部选项卡</a></li>
                    <li><a>关闭其它选项卡</a></li>
                </ul>
            </div>
            <div class="_ntright"><i class="fa fa-forward"></i></div>
        </div>
        <!--选项卡·内容-->
        <div class="tab-content _navbox" id="boxId">
            <div class="tab-pane active" id="page_desk">
                <!--配置首页-->
                <iframe src="${basePath}/setting/desk2" frameborder="0"></iframe>
            </div>
        </div>

        <!--页脚-->
        <div id="footer" style="overflow:hidden;text-align:center; line-height:1.2;">
            <a href="#" target="_blank"></a>
            Copyright &copy; 2017
        </div>

    </div>
</div>


<script src="${requestScope.basePath}/js/jquery/3.2.1/jquery.min.js"></script>
<script src="${requestScope.basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
<script src="${requestScope.basePath}/css/aceNav/ace.js"></script>


</body>
</html>

<!--框架首页初始化-->
<script src="${requestScope.basePath}/js/init.js"></script>
