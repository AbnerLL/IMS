//基于准备好的dom，初始化echarts实例
var empAddressGroupCharts=echarts.init(document.getElementById('addressGroupDiv'));
//
var empGenderGroupCharts=echarts.init(document.getElementById('genderGroupDiv'));
//
var empSLingStatCharts=echarts.init(document.getElementById('sLingStatDiv'));
var roadQualityRateCharts=echarts.init(document.getElementById('roadQualityRateDiv'));
var poiQualityRateCharts=echarts.init(document.getElementById('poiQualityRateDiv'));
var empAptitudeCharts=echarts.init(document.getElementById('empAptitudeDiv'));
//员工分布
function loadEmpAddressGroup(){
    $.getJSON(basePath+"/charts/empAddressGroup",null,function(result){
        var legendData=new Array();
        var seriesData=new Array();
        var addressResult=result.extend.data;
        for(var i=0;i<addressResult.length ;i++){
            var item=new Object();
            item.name=addressResult[i].ADDRESS;
            item.value=addressResult[i].NUM;
            legendData.push(addressResult[i].ADDRESS);
            seriesData.push(item);
        }
        // 指定图表的配置项和数据
        empAddressGroupCharts.setOption({
            title : {
                text: '人员分布情况',
                subtext: '最新统计结果',
                x:'right'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: legendData
            },
            series : [
                {
                    name: '所占比例',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:seriesData,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
    })
}
//员工性别分布
function loadGenderGroup(){
    $.getJSON(basePath+"/charts/empGenderGroup",null,function(result){
        var legendData=new Array();
        var seriesData=new Array();
        var addressResult=result.extend.data;
        for(var i=0;i<addressResult.length ;i++){
            var item=new Object();
            item.name=addressResult[i].GENDER;
            item.value=addressResult[i].NUM;
            legendData.push(addressResult[i].GENDER);
            seriesData.push(item);
        }
        // 指定图表的配置项和数据
        empGenderGroupCharts.setOption({
            title : {
                text: '员工性别分布情况',
                subtext: '最新统计结果',
                x:'right'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: legendData
            },
            series : [
                {
                    name: '所占比例',
                    type: 'pie',
                    radius : ['35%',"55%"],
                    center: ['50%', '60%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:seriesData,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
    })
}
/**
 * 将请求的数组转化为页面需要显示的数据
 */
function translateData(result){
    var array=new Array();
    var sl_0_3=0;
    var sl_4_7=0;
    var sl_8_11=0;
    var sl_12_15=0;
    var sl_16_up=0;
    for(var i=0;i<result.length;i++){
        var age=result[i].AGE;
        var ageStat=result[i].AGENUM;
        if(age>=0&&age<=3){
            sl_0_3+=ageStat;
        }else if (age>=4&&age<=7){
            sl_4_7+=ageStat;
        }else if (age>=8&&age<=11){
            sl_8_11+=ageStat;
        }else if (age>=12&&age<=15){
            sl_12_15+=ageStat;
        }else{
            sl_16_up+=ageStat;
        }
    }
    array[0]=sl_0_3;
    array[1]=sl_4_7;
    array[2]=sl_8_11;
    array[3]=sl_12_15;
    array[4]=sl_16_up;
    return array;
}
//司龄统计
function loadSLingStat(){
    $.getJSON(basePath+"/charts/empSLingStat",null,function(result){
        var seriesData=new Array();
        var addressResult=translateData(result.extend.data);
        for(var i=0;i<addressResult.length ;i++){
            seriesData.push(addressResult[i]);
        }
        // 指定图表的配置项和数据
        empSLingStatCharts.setOption({
            title : {
                text: '员工工龄分布情况',
                subtext: '最新统计结果',
                x:'right'
            },
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['0-3年', '4-7年', '8-11年', '12-15年', '16年以上'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'统计数量',
                    type:'bar',
                    barWidth: '60%',
                    data:seriesData
                }
            ]

        });
    })
}
function translateData2(result){
    var rate=new Object();
    var audit_rate=new Array();
    var monitor_rate=new Array();
    for(var i=0;i<result.length;i++){
        var item=result[i];
        if("17春"==item.VERSION){
            audit_rate[0]=(((item.AUDIT_NUM-item.AUDIT_ERROR_NUM)/item.AUDIT_NUM).toFixed(4))*100;
            monitor_rate[0]=(((item.MONITOR_NUM-item.M_ERROR_NUM)/item.MONITOR_NUM).toFixed(4))*100;
        }else if ("17夏"==item.VERSION){
            audit_rate[1]=(((item.AUDIT_NUM-item.AUDIT_ERROR_NUM)/item.AUDIT_NUM).toFixed(4))*100;
            monitor_rate[1]=(((item.MONITOR_NUM-item.M_ERROR_NUM)/item.MONITOR_NUM).toFixed(4))*100;
        }else{
            audit_rate[2]=(((item.AUDIT_NUM-item.AUDIT_ERROR_NUM)/item.AUDIT_NUM).toFixed(4))*100;
            monitor_rate[2]=(((item.MONITOR_NUM-item.M_ERROR_NUM)/item.MONITOR_NUM).toFixed(4))*100;
        }
    }
    console.log(audit_rate);
    console.log(monitor_rate);
    rate.audit=audit_rate;
    rate.monitor=monitor_rate;
    return rate;
}
/**
 * 道路品质率统计
 */
function loadRoadQualityRate(){
    $.getJSON(basePath+"/charts/roadQualityRate",null,function(result){
        var rateResult=translateData2(result.extend.data);
        // 指定图表的配置项和数据
        roadQualityRateCharts.setOption({
            title : {
                text: '道路品质率情况',
                subtext: '最新统计结果',
                x:'right'
            },
            tooltip : {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c}'
            },
            legend: {
                left: 'left',
                data: ['质检品质率', '监察品质率','品质目标']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type: 'category',
                    name: 'x',
                    splitLine: {show: false},
                    data: ['17春', '17夏', '17秋', '17冬']
                }
            ],
            yAxis: {
                name: '品质率（%）',
                type:'value',
                min:'97'
            },
            series: [
                {
                    name: '质检品质率',
                    type: 'line',
                    data: rateResult.audit
                },
                {
                    name: '监察品质率',
                    type: 'line',
                    data: rateResult.monitor
                },
                {
                    name: '品质目标',
                    type: 'line',
                    data: [99.50, 99.50, 99.50, 99.50]
                }
            ]

        });
    })
}
/**
 * 设施品质率统计
 */
function loadPoiQualityRate(){
    $.getJSON(basePath+"/charts/poiQualityRate",null,function(result){
        var rateResult=translateData2(result.extend.data);
        // 指定图表的配置项和数据
        poiQualityRateCharts.setOption({
            title : {
                text: '设施品质率情况',
                subtext: '最新统计结果',
                x:'right'
            },
            tooltip : {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c}'
            },
            legend: {
                left: 'left',
                data: ['质检品质率', '监察品质率','品质目标']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type: 'category',
                    name: 'x',
                    splitLine: {show: false},
                    data: ['17春', '17夏', '17秋', '17冬']
                }
            ],
            yAxis: {
                name: '品质率（%）',
                type:'value',
                min:'97'
            },
            series: [
                {
                    name: '质检品质率',
                    type: 'line',
                    data: rateResult.audit
                },
                {
                    name: '监察品质率',
                    type: 'line',
                    data: rateResult.monitor
                },
                {
                    name: '品质目标',
                    type: 'line',
                    data: [99.50, 99.50, 99.50, 99.50]
                }
            ]

        });
    })
}
/**
 * 人员资质情况-道路
 */
function loadEmpAptitudeRoad(){
    // 指定图表的配置项和数据
    empAptitudeCharts.setOption({
        title: {
            text: '人员资质情况-道路',
            subtext: '最新统计结果',
            x:'right'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'line'
            }
        },
        legend: {
            data: ['免检', '抽检','全检']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: ['北京项目一组','北京项目二组','北京项目三组','北京项目四组']
        },
        series: [
            {
                name: '免检',
                type: 'bar',
                data: [12, 7, 6, 10]
            },
            {
                name: '抽检',
                type: 'bar',
                data: [11, 15, 21, 11]
            },
            {
                name: '全检',
                type: 'bar',
                data: [3, 7, 4, 16]
            }
        ]
    });
}