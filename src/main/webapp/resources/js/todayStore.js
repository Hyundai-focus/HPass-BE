var optionsCircle1 = {
    chart: {
        type: 'radialBar',
        height: 300,
        zoom: {
            enabled: false
        }
    },
    colors: ['#E91E63'],
    plotOptions: {
        radialBar: {
            dataLabels: {
                name: {
                    show: false
                },
                value: {
                    offsetY: 0
                }
            }
        }
    },
    series: [((todayNo/subsNo)*100).toFixed(1)],
    theme: {
        monochrome: {
            enabled: false
        }
    },
    legend: {
        show: false
    },
    title: {
        text: '구독자 중 이벤트 참여율',
        align: 'left'
    },
    subtitle: {
        text: `참여인원: ${todayNo}명`
    }
}

var chartCircle1 = new ApexCharts(document.querySelector('#participation'), optionsCircle1);
chartCircle1.render();