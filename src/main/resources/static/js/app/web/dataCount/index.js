var theme_color = $MB.getThemeColor(theme);

$(document).ready(function() {

    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    $.ajax({
       type:"post",
       url:ctx +"dayCount",
        dataType: "json",
        success:function(data){
            $("#container").highcharts({
                chart: {
                    type: "spline",
                    animation: Highcharts.svg,
                    marginRight: 0,
                },
                title: {
                    text: "日常统计数据",
                    style: {
                        "font-size": "1.0rem"
                    }
                },
                xAxis: {
                    categories: data.xAxis
                },
                yAxis: {
                    title: {
                        text: null
                    },
                    plotLines: [ {
                        value: 0,
                        width: 1,
                        color: "#078048"
                    }]
                },

                credits: {
                    enabled: false
                },

                legend: {
                    align: 'left',
                    verticalAlign: 'top',
                    y: -8,
                    floating: true,
                    borderWidth: 0
                },
                exporting: {
                    enabled: false
                },
                series: [ {
                    name: "每日新增用户(人)",
                    data: data.useradd
                },{
                    color:"#1A5870",
                    name: "每日发帖数量（个）",
                    data:data.postadd
                },{     color:"#415788",
                    name: " 每日登陆人数（人）",
                    data: data.loginuser
                } ,]
            });
        }


    })


});