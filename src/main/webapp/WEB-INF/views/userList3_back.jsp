<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="head.jsp"%>
    <meta charset="utf-8" />
    <title>角色管理</title>
    <meta http-equiv="x-ua-compatible" content="IE=edeg,chrom=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- 基础css -->

    <link href="${basePath}/css/aceNav/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min2.css" />


    <!-- 表格控件jqGrid css文件 -->

    <link rel="stylesheet" href="${basePath}/css/jquery-ui-1.10.3.full.min.css" />
    <%--时间样式控件--%>
    <link rel="stylesheet" href="${basePath}/css/datepicker.css" />
    <link rel="stylesheet" href="${basePath}/css/ui.jqgrid.css" />

    <!-- fonts -->

    <%--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />--%>

    <!-- ace模板的css文件 -->

    <link rel="stylesheet" href="${basePath}/css/ace.min.css" />
    <link rel="stylesheet" href="${basePath}/css/aceNav/ace-rtl.min.css" />
    <link rel="stylesheet" href="${basePath}/css/aceNav/ace-skins.min.css" />
    <!-- ace settings handler -->

    <script src="${basePath}/js/ace/ace-extra.min.js"></script>

</head>

<body>

<div class="main-container" id="main-container">
    <%--<script type="text/javascript">--%>
    <%--try{ace.settings.check('main-container' , 'fixed')}catch(e){}--%>
    <%--</script>--%>

    <div class="main-container-inner">

        <%--<div class="main-content">--%>

        <div class="page-content">
            <div class="row">
                <div class="col-md-12 bgwhite" id="myBtnMenu" style="margin-top:5px">
                    <button id="m_Add" class="btn btn-sm  btn-primary">
                        <i class="fa fa-plus"></i>增加
                    </button>
                    <button id="m_Edit" class="btn btn-sm  btn-warning">
                        <i class="fa fa-edit"></i>修改
                    </button>
                    <button id="m_Del" class="btn btn-sm  btn-danger">
                        <i class="fa fa-trash-o"></i>删除
                    </button>
                    <button id="m_Reload" class="btn btn-sm  btn-info">
                        <i class="fa fa-refresh"></i>刷新
                    </button>
                    <div class="btn-group">
                        <button id="list_Config" class="btn btn-sm  btn-primary" data-toggle="dropdown" aria-haspopup="true">
                            <i class="fa fa-gear"></i>配置 <i class="caret"></i>
                        </button>
                        <ul class="dropdown-menu dropdown-yellow">
                            <li>
                                <a id="list_Config_Table" href="javascript:void(0)">
                                    <i class="fa fa-table orange" ></i>表格配置
                                </a>
                            </li>
                            <li>
                                <a id="list_Config_Form" href="javascript:void(0)">
                                    <i class="fa fa-table orange"></i>表单配置
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button id="m_Auth" class="btn btn-sm  btn-success">
                        <i class="fa fa-gear"></i>权限设置
                    </button>
                </div>
            </div>
            <div class="row" style="padding-top: 5px">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <table id="grid-table"></table>

                    <div id="grid-pager"></div>

                    <script type="text/javascript">
                        var $path_base = "/";//this will be used in gritter alerts containing images
                    </script>

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
        <%--</div><!-- /.main-content -->--%>



    </div><!-- /.main-container -->

    <%--<!-- basic scripts -->--%>

    <%--<!--[if !IE]> -->--%>

    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>--%>

    <%--<!-- <![endif]-->--%>

    <%--<!--[if !IE]> -->--%>

    <%--<script type="text/javascript">--%>
        <%--window.jQuery || document.write("<script src='../../static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");--%>
    <%--</script>--%>

    <%--<!-- <![endif]-->--%>

    <%--<script type="text/javascript">--%>
        <%--if("ontouchend" in document) document.write("<script src='../../static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");--%>
    <%--</script>--%>
    <script src="${basePath}/js/jquery/3.2.1/jquery.min.js"></script>
    <script src="${basePath}/js/browser.js"></script>
    <script src="${basePath}/js/bootstrap/3.3.7/bootstrap.min.js"></script>
    <script src="${basePath}/js/ace/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->

    <script src="${basePath}/js/bootrap-datepicker/1.6.4/bootstrap-datepicker.min.js"></script>
    <script src="${basePath}/js/jqGrid/4.4.3/jquery.jqGrid.min.js"></script>
    <script src="${basePath}/js/jqGrid/4.4.3/grid.locale-cn.js"></script>

    <!-- ace scripts -->

    <script src="${basePath}/js/ace/ace-elements.min.js"></script>
    <%--<script src="${basePath}/css/aceNav/ace.js"></script>--%>

    <!-- inline scripts related to this page -->

    <script type="text/javascript">
        var grid_data =
            [
                {id:"1",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
                {id:"2",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
                {id:"3",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                {id:"4",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
                {id:"5",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
                {id:"6",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                {id:"7",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
                {id:"8",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                {id:"9",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                {id:"10",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
                {id:"11",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
                {id:"12",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                {id:"13",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
                {id:"14",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
                {id:"15",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                {id:"16",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
                {id:"17",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                {id:"18",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                {id:"19",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                {id:"20",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
                {id:"21",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
                {id:"22",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                {id:"23",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"}
            ];

        jQuery(function($) {
            var grid_selector = "#grid-table";
            var pager_selector = "#grid-pager";

            jQuery(grid_selector).jqGrid({
                //direction: "rtl",

                data: grid_data,
                datatype: "local",
                height: "260",
                colNames:['操作', 'ID','Last Sales','Name', 'Stock', 'Ship via','Notes'],
                colModel:[
                    {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                        formatter:'actions',
                        formatoptions:{
                            keys:true,

                            delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
                            //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                        }
                    },
                    {name:'id',index:'id', width:60, sorttype:"int", editable: true},
                    {name:'username',index:'username',width:90, editable:true},
                    {name:'password', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
                    {name:'status',index:'status', width:70, editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
                    {name:'roles', width:90, editable: true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}},
                    {name:'createTime',index:'createTime', width:150, sortable:true,editable: true}
                ],

                viewrecords : true,
                rowNum:10,
                rowList:[10,20,30],
                pager : pager_selector,
                altRows: true,
                //toppager: true,

                multiselect: true,
                //multikey: "ctrlKey",
                multiboxonly: true,

                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        styleCheckbox(table);

                        updateActionIcons(table);
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },

                editurl: $path_base+"/dummy.html",//nothing is saved
                caption: "角色列表",


                autowidth: true,

                //设置接收后台的参数
                jsonReader:{

                }
            });

            //enable search/filter toolbar
            //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

            //switch element when editing inline
            function aceSwitch( cellvalue, options, cell ) {
                setTimeout(function(){
                    $(cell) .find('input[type=checkbox]')
                        .wrap('<label class="inline" />')
                        .addClass('ace ace-switch ace-switch-5')
                        .after('<span class="lbl"></span>');
                }, 0);
            }
            //enable datepicker
            function pickDate( cellvalue, options, cell ) {
                setTimeout(function(){
                    $(cell) .find('input[type=text]')
                        .datepicker({format:'yyyy-mm-dd' , autoclose:true});
                }, 0);
            }


            //navButtons
            jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                { 	//navbar options
                    edit: true,
                    editicon : 'fa fa-pencil blue',
                    add: true,
                    addicon : 'fa fa-plus-circle purple',
                    del: true,
                    delicon : 'fa fa-trash red',
                    search: true,
                    searchicon : 'fa fa-search orange',
                    refresh: true,
                    refreshicon : 'fa fa-refresh green',
                    view: true,
                    viewicon : 'fa fa-search-plus grey',
                },
                {
                    //edit record form
                    //closeAfterEdit: true,
                    //beforeShowForm:每次编辑之前都会执行
                    recreateForm: true,
                    beforeShowForm : function(e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    //new record form
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm : function(e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    }
                },
                {
                    //delete record form
                    recreateForm: true,
                    beforeShowForm : function(e) {
                        var form = $(e[0]);
                        if(form.data('styled')) return false;

                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_delete_form(form);

                        form.data('styled', true);
                    },
                    onClick : function(e) {
                        alert(1);
                    }
                },
                {
                    //search form
                    recreateForm: true,
                    afterShowSearch: function(e){
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                        style_search_form(form);
                    },
                    afterRedraw: function(){
                        style_search_filters($(this));
                    }
                    ,
                    multipleSearch: true,
                    /**
                     multipleGroup:true,
                     showQuery: true
                     */
                },
                {
                    //view record form
                    recreateForm: true,
                    beforeShowForm: function(e){
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    }
                }
            )



            function style_edit_form(form) {
                //enable datepicker on "sdate" field and switches for "stock" field
                form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
                    .end().find('input[name=stock]')
                    .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

                //update buttons classes
                var buttons = form.next().find('.EditButton .fm-button');
                buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
                buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
                buttons.eq(1).prepend('<i class="icon-remove"></i>')

                buttons = form.next().find('.navButton a');
                buttons.find('.ui-icon').remove();
                buttons.eq(0).append('<i class="icon-chevron-left"></i>');
                buttons.eq(1).append('<i class="icon-chevron-right"></i>');
            }

            function style_delete_form(form) {
                var buttons = form.next().find('.EditButton .fm-button');
                buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
                buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
                buttons.eq(1).prepend('<i class="icon-remove"></i>')
            }

            function style_search_filters(form) {
                form.find('.delete-rule').val('X');
                form.find('.add-rule').addClass('btn btn-xs btn-primary');
                form.find('.add-group').addClass('btn btn-xs btn-success');
                form.find('.delete-group').addClass('btn btn-xs btn-danger');
            }
            function style_search_form(form) {
                var dialog = form.closest('.ui-jqdialog');
                var buttons = dialog.find('.EditTable')
                buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
                buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
                buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
            }

            function beforeDeleteCallback(e) {
                var form = $(e[0]);
                console.log(form);
                if(form.data('styled')) return false;

                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_delete_form(form);

                form.data('styled', true);
            }

            function beforeEditCallback(e) {
                var form = $(e[0]);
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                style_edit_form(form);
            }



            //it causes some flicker when reloading or navigating grid
            //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
            //or go back to default browser checkbox styles for the grid
            function styleCheckbox(table) {
                /**
                 $(table).find('input:checkbox').addClass('ace')
                 .wrap('<label />')
                 .after('<span class="lbl align-top" />')


                 $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
                 .find('input.cbox[type=checkbox]').addClass('ace')
                 .wrap('<label />').after('<span class="lbl align-top" />');
                 */
            }


            //unlike navButtons icons, action icons in rows seem to be hard-coded
            //you can change them like this in here if you want
            function updateActionIcons(table) {
                /**
                 var replacement =
                 {
                     'ui-icon-pencil' : 'icon-pencil blue',
                     'ui-icon-trash' : 'icon-trash red',
                     'ui-icon-disk' : 'icon-ok green',
                     'ui-icon-cancel' : 'icon-remove red'
                 };
                 $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
                 */
            }

            //replace icons with FontAwesome icons like above
            function updatePagerIcons(table) {
                var replacement =
                    {
                        'ui-icon-seek-first' : 'fa fa-angle-double-left bigger-140',
                        'ui-icon-seek-prev' : 'fa fa-angle-left bigger-140',
                        'ui-icon-seek-next' : 'fa fa-angle-right bigger-140',
                        'ui-icon-seek-end' : 'fa fa-angle-double-right bigger-140'
                    };
                $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                    var icon = $(this);
                    var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                    if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
                })
            }

            function enableTooltips(table) {
                $('.navtable .ui-pg-button').tooltip({container:'body'});
                $(table).find('.ui-pg-div').tooltip({container:'body'});
            }

            //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');


        });
    </script>
</div>
<div style="display:none"></div>
</body>
</html>
