/**
 * Created by luozhihui on 2018/3/25.
 */
(function($,window){
    var appPath = window.appPath ? window.appPath : "";

    /**
     * 设置查询参数
     */
    function queryParam() {

    }

    /**
     * 结果数据解析(default)
     */
    function responseHandler(resultData){
        return resultData.extend.entities;
    }

    /**
     * 解析请求反回的结果
     */
    function parseContent(item,result,option) {
        var htmlContent="";
        var resultData = option.processResult(result);
        $.each(resultData,function(index,obj){
            htmlContent +='<option value="'+obj[option.key]+'">'+obj[option.value]+'</option>';
        });
        //向容器中添加解析的html内容
        item.each(function () {
            if (option.append){
                $(this).append(htmlContent);
            }else{
                $(this).html(htmlContent);
            }
        });
    }
    /**
     * 根据配置获取数据
     * @param options
     */
    function loadContent(item,options) {
        if (!options.search ){
            window.console.log("请指定查询条件");
            return ;
        }
        //获得请求数据
        //发送ajax请求获取字典数据
        $.ajax({
            url:options.url,
            type:"get",
            data:options.search,
            dataType:"json",
            success:function (result) {
                if (1 == result.code){
                    parseContent(item,result,options);
                }else{
                    window.console.log("请求失败，请检查配置！")
                }
            }
        });
    }
    /**
     * 初始化指定的容器
     * @param options
     */
    function initContainer(item,options){
        //默认参数
        var defaults={
            url:appPath+'/dictionary/loadAll',//设置请求路径
            key:"dictCode",
            value:"dictName",
            append:false,
            search:{},
            processResult:responseHandler
        }
        //使用用户自定义参数覆盖默认参数
        var opt=$.extend(defaults,options);
        //加载内容
        loadContent(item,opt);
        //结束
    }
    $.fn.weboption = function (options) {
        var ele = this;
        initContainer(ele,options);
    }
})(jQuery,window)
