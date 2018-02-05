/// <reference path="jquery.min.js" />

//菜单设置
$('#sidebar-shortcuts-large').click(function (e) {
    e = e || window.event;
    var target = e.target || e.srcElement;
    if ($('#sidebar-collapse')[0].contains(target)) {
        $('#sidebar').hasClass('menu-min') ? $('#sidebar').removeClass('menu-min') : $('#sidebar').addClass('menu-min');
    }
    setTimeout(function () {
        if ($('#sidebar').hasClass("menu-min")) {
            $('#divTheme').show();
            $('#btn_style_config').show();
        } else {
            if ($('#sidebar').hasClass("compact")) {
                $('#divTheme').hide();
                $('#btn_style_config').hide();
            } else {
                $('#divTheme').show();
                $('#btn_style_config').show();
            }
        }
    }, 50)
});
$('#ulset').click(function (e) {
    var that = this;
    setTimeout(function () {
        var chks = $(that).find('input');
        localStorage["_RF_MenuType"] = (chks[0].checked ? 1 : 0) + "" + (chks[1].checked ? 1 : 0);
    }, 50)
});
$('#sidebar-collapse').click(function () {
    setTimeout(function () {
        localStorage["_RF_MenuMin"] = $('#sidebar').hasClass('menu-min') ? 1 : 0;
    }, 50);
});

//设置皮肤
$('#switch_skin').click(function (e) {
    e = e || window.event;//获取事件对象，兼容的写法
    var target = e.target || e.srcElement;//获取该元素对象 ；ie下支持e.srcElement，firefox支持e.target。
    if (target.nodeName == "A") {
        document.body.className = target.getAttribute('data-skin');
        $(this).find('a').each(function () { this.className = this.className.replace('selected', '') })
        target.className += ' selected';
        localStorage["_RF_SkinName"] = target.getAttribute('data-skin');
    }
});

//设置字体样式
$('#btn_style_config').click(function () {
    OpenPage('/setting/sysstyleconfig', ' 字体样式设置 ', 'fa-text-height');
});

//初始化本地配置
setTimeout(function () {
    try {
        if ("localStorage" in window) {
            //加载保存的皮肤
            var sn = localStorage["_RF_SkinName"];
            if (sn && sn.length > 4) {
                document.body.className = sn;
                $('#switch_skin').find('a').each(function () { this.className = this.className.replace('selected', '') })
                $('#switch_skin').find('a').each(function () { if (this.getAttribute("data-skin") == sn) { this.className += ' selected'; } });
            }

            //加载保存的字体样式
            var fs = localStorage["_Style_FontSize"],
                ff = localStorage["_Style_FontFamily"];
            fs && (document.body.style["font-size"] = fs),
            ff && (document.body.style["font-family"] = ff);
        }
    } catch (e) { }
}, 50);

//选项卡初始化
$('#navtab').click(function (e) {
    e = e || window.event;
    var target = e.target || e.srcElement;
    if (target.nodeName == 'I' && target.className.indexOf("fa fa-times-circle") >= 0) {
        //关闭
        var ta = target.parentElement, curli = $(ta.parentElement);
        if (curli.hasClass('active')) {
            curli.prev().addClass('active');
            $(ta.hash).prev().addClass('active');
        }
        curli.remove();
        $(ta.hash).remove();
    } else if ($(this).children().first()[0].contains(target)) {
        //左移
        var navul = $(this).find('ul').first(),
            nleft = parseInt(navul.css('margin-left').replace('px', '')) || 0;
        var curl = nleft + 350;
        curl < 200 - navul.width() && (curl = 200 - navul.width());
        curl > 0 && (curl = 0);

        flowRoll(nleft, curl);
        function flowRoll(nleft, curl) {
            nleft += 15;
            nleft > curl && (nleft = curl);
            navul.css('margin-left', nleft + "px");
            nleft != curl && setTimeout(function () { flowRoll(nleft, curl) }, 5);
        }
    } else if ($(this).children().last()[0].contains(target)) {
        //右移
        var navul = $(this).find('ul').first(),
            nleft = parseInt(navul.css('margin-left').replace('px', '')) || 0,
            nwidth = navul.width();
        var curl = nleft - 350;
        curl < navul.parent().width() - nwidth && (curl = navul.parent().width() - nwidth);
        curl > 0 && (curl = 0);

        flowRoll(nleft, curl);
        function flowRoll(nleft, curl) {
            nleft -= 15;
            nleft < curl && (nleft = curl);
            navul.css('margin-left', nleft + "px");
            nleft != curl && setTimeout(function () { flowRoll(nleft, curl) }, 5);
        }
    } else if ($(this).find('ul')[0].contains(target)) {
        setTimeout(PositionTab, 20);
    } else if (target.nodeName == "A" && target.innerHTML == "定位当前选项卡") {
        PositionTab();
    } else if (target.nodeName == "A" && target.innerHTML == "关闭全部选项卡") {
        var lis = $(this).find('ul').first().css('margin-left', 0).find('li');
        lis.each(function () {
            if (this != lis[0]) {
                $(this).remove();
                $($(this).children().first()[0].hash).remove();
            } else {
                lis.first().addClass('active');
                $($(this).children().first()[0].hash).addClass('active');
            }
        });
    } else if (target.nodeName == "A" && target.innerHTML == "关闭其它选项卡") {
        var lis = $(this).find('ul').first().css('margin-left', 0).find('li');
        lis.each(function () {
            if (this != lis[0] && this.className.indexOf('active') == -1) {
                $(this).remove();
                $($(this).children().first()[0].hash).remove();
            }
        });
    }
});

//选项卡定位
function PositionTab() {
    var navul = $('#navtab').find('ul').first(),
            nleft = parseInt(navul.css('margin-left').replace('px', '')) || 0,
            nwidth = navul.width();
    if (navul.parent().width() < nwidth) {
        var lwidth = 0, lthat, curl = 0;
        navul.find('li').each(function () {
            if (this.className.indexOf('active') >= 0) {
                lthat = $(this);
                return false;
            }
            lwidth += this.offsetWidth + 3.33;
        });
        lwidth < 0 && (lwidth = 0);

        var lnext = 0;
        //左定位
        if (-nleft > lwidth) {
            curl = lthat.prev().width() - lwidth;
        } else if ((lnext = lwidth + lthat.width() + lthat.next().width() - nleft) > navul.parent().width()) {
            curl = navul.parent().width() - lnext - nleft;
        } else { curl = nleft }
        curl > 0 && (curl = 0);
        curl < navul.parent().width() - nwidth && (curl = navul.parent().width() - nwidth);

        flowRoll(nleft, curl, nleft > curl ? 'right' : 'left');
        function flowRoll(nleft, curl, dir) {
            if (dir == "right") {
                nleft -= 15;
                nleft < curl && (nleft = curl);
            } else {
                nleft += 15;
                nleft > curl && (nleft = curl);
            }
            navul.css('margin-left', nleft + "px");
            nleft != curl && setTimeout(function () { flowRoll(nleft, curl, dir) }, 5);
        }
    }
}

//菜单点击
$('#ulnav').click(function (e) {
    if (e && e.preventDefault) { e.preventDefault() } else { window.event.returnValue = false }
    e = e || window.event;
    var target = e.target || e.srcElement;
    $(this).find('a').each(function () {
        if (this.contains(target)) {
            if (this.href.split('#')[1]) {
                OpenPage(this.href.split('#')[1], this.innerHTML);
            }
            return false;
        }
    });
});

//打开选项卡
function OpenPage(url, title, icon) {
    var navul = $('#navtab').find('ul').first();
    var isopen = false
    $('#boxId').find('iframe').each(function () {
        var shorturl = this.src.split(location.host)[1].toLowerCase();
        shorturl = shorturl.split('?')[0];
        if (url.toLowerCase().indexOf(shorturl) > -1) {
            //this.src = url;
            navul.find('li').removeClass('active');
            $('#boxId').children().removeClass('active');
            var boxdiv = $(this).parent();
            boxdiv.addClass('active');
            navul.find('a').each(function () {
                if (this.hash.substring(1) == boxdiv[0].id) {
                    $(this).parent().addClass('active');
                }
            });
            isopen = true;
            //定位、调整
            PositionTab();
            IframeAuto();
        }
    });
    if (!isopen) {
        navul.find('li').removeClass('active');
        $('#boxId').children().removeClass('active');
        var hid = "page_" + Math.random().toString().substring(2, 8);
        !icon && (icon = '');
        navul.append('<li class="active"><a href="#' + hid + '" data-toggle="tab"><i class="fa ' + icon + '"></i>' + title + '<i class="fa fa-times-circle"></i></a></li>');
        $('#boxId').append('<div class="tab-pane active" id="' + hid + '"><iframe frameborder="0" src="about:blank"></iframe></div>');
        //定位、调整
        PositionTab();
        IframeAuto();
        $('#' + hid).children()[0].src = url;
    }
    //Mobile 打开时隐藏菜单导航
    !isPC() && $('#menu-toggler')[0].click();
}

//JSON生成导航菜单
function TreeEach(json) {
    //拼接HTML
    this._html = this._html || '';

    for (var i = 0; i < json.length; i++) {
        //遍历对象
        // var id = json[i]["id"] || '',
        //     text = json[i]["name"] || '',
        //     url =json[i]["sourceUrl"] || '',
        //     icon = json[i]['clsName'] || '',
        //     subs = json[i]["children"] || [],
        //     pid = parseInt(json[i]["pid"]) || 0;
        var id = json[i]["moduleId"] || '',
            text = json[i]["moduleName"] || '',
            url =json[i]["moduleUrl"] || '',
            icon = json[i]['iconClass'] || '',
            subs = json[i]["children"] || [],
            pid = parseInt(json[i]["modulePid"]) || 0;
        url=url==''?'':baseUrl+url;
        icon != '' && (icon = "fa " + icon);
        //有下级
        if (subs.length) {
            this._html += '<li><a href="#" class="dropdown-toggle"><i class="menu-icon ' + icon + '"></i>'
                       + '<span class="menu-text"> ' + text + ' </span><b class="arrow fa fa-angle-down"></b></a>'
                       + '<ul class="submenu">';
        } else {
            this._html += '<li><a href="#' + url + '"><i class="menu-icon ' + icon + '"></i>'
                        + '<span class="menu-text"> ' + text + ' </span></a></li>';
        }

        //递归
        subs.length && TreeEach(subs, subs.length);

        //同一层的最后一个
        if (i + 1 == arguments[1]) {
            this._html += '</ul></li>';
        }
    }
    return this._html;
}

//Mobile
function isPC() { return !navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i) }

//Iframe自适应、延迟50毫秒响应
var _defer_iframe;
function IframeAuto() {
    clearTimeout(_defer_iframe);
    _defer_iframe = setTimeout(function () {
        var box = $('#boxId'), sh = $(window).height() - box[0].getBoundingClientRect().top - 3;
        sh -= $('#footer').height();
        box.find('iframe').css('height', sh);
        box.children('div').css("height", sh);
    }, 50);
}
//当调整浏览器窗口的大小时，发生 resize 事件
$(window).ready(function () {
    !isPC() && $('#footer').css('height', 0);
    IframeAuto();
}).resize(IframeAuto);
//获取当前用户的名称
setTimeout(function(){
    $.ajax({
       url:baseUrl+"/user/currentUser",
       type:"GET",
       dataType:"json",
       success:function(result){
           $(".user-info").append(result.extend.entities[0].nickname);
       } ,
       error:function(){
           $(".user-info").append("请求失败！");
    }
    });
},60);
//生成导航菜单
$.ajax({
    url: baseUrl+'/initModule',
    type: 'get',
    dataType: 'json',
    success: function (data) {
        $('#ulnav').data('nav', data.extend.menu);
        $('#ulnav').append(TreeEach(data.extend.menu));
    },
    error: function () {
        alert("加载菜单出错");
    },
    complete: function () {
        try {
            if ("localStorage" in window) {
                //加载保存的菜单设置
                var mt = localStorage["_RF_MenuType"],
                    mm = localStorage["_RF_MenuMin"];
                if (mt[1] == "1") {
                    $('#ace-settings-compact')[0].click();
                } else {
                    if (mt[0] == "1") {
                        $('#ace-settings-hover')[0].click();
                    }
                }
                mm == "1" && $('#sidebar-collapse')[0].click();
            }
        }
        catch (e) { }
        finally {
            //清除透明
            setTimeout(function () {
                $('#_LDmask').fadeOut(300).hide(300);
            }, 400);
        }
    }
});