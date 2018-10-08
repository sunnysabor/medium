$(function () {
    $.ajax({
        url: "/sys/user/list",
        type: "get",
        data:{
            "limit":100,
            "offset":0
        },
        success: function (r) {
            var disableNumber=0;
            var enableNumber=0;
            for(var i=0;i<r.rows.length;i++){
                if(r.rows[i].status==0){
                    disableNumber++;
                }else{
                    enableNumber++;
                }
            }
            //加载图表
            var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
            var pieoption = {
                title: {
                    text: '该网站启用用户和禁用用户的比例',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['启用', '禁用']
                },
                calculable: true,
                series: [
                    {
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: enableNumber, name: '启用'},
                            {value: disableNumber, name: '禁用'}
                        ]
                    }
                ]
            };
            pieChart.setOption(pieoption);
            $(window).resize(pieChart.resize);
        }
    });
});
