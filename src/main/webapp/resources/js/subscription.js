let accumulate_subs = {
    chart: {
        id: 'sparkline1',
        group: 'sparklines',
        type: 'area',
        height: 160,
        sparkline: {
            enabled: true
        },
    },
    stroke: {
        curve: 'straight'
    },
    fill: {
        opacity: 1,
    },
    series: [{
        name: '누적 구독 가입 수',
        data: cumulativeAddNum
    }],
    labels: subsMonth,
    yaxis: {
        min: 0
    },
    colors: ['#00D8B6'],
    title: {
        text: cumulativeAddNum.at(-1),
        offsetX: 10,
        style: {
            fontSize: '24px',
            cssClass: 'apexcharts-yaxis-title'
        }
    },
    subtitle: {
        text: '누적 구독 가입 수',
        offsetX: 10,
        style: {
            fontSize: '14px',
            cssClass: 'apexcharts-yaxis-title'
        }
    }
}
let accumulate_profit = {
    chart: {
        id: 'sparkline1',
        group: 'sparklines',
        type: 'area',
        height: 160,
        sparkline: {
            enabled: true
        },
    },
    stroke: {
        curve: 'straight'
    },
    fill: {
        opacity: 1,
    },
    series: [{
        name: 'HPass 구독자 수',
        data: subsLeft
    }],
    labels: subsMonth,
    yaxis: {
        min: 0
    },
    colors: ['#00D8B6'],
    title: {
        text: (subsLeft.at(-1)*4500).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원 ㅣ " + subsLeft.at(-1)+ "명",
        offsetX: 10,
        style: {
            fontSize: '24px',
            cssClass: 'apexcharts-yaxis-title'
        }
    },
    subtitle: {
        text: '이번 달 HPass 수익 ㅣ 구독자 수',
        offsetX: 10,
        style: {
            fontSize: '14px',
            cssClass: 'apexcharts-yaxis-title'
        }
    }
}
new ApexCharts(document.querySelector("#accumulate_subs"), accumulate_subs).render();
new ApexCharts(document.querySelector("#accumulate_profit"), accumulate_profit).render();

//달별 구독 가입/해지
let month_sub_Options = {
    title: {
        text: monthlySubsAddNum.at(-1),
        offsetX: 10,
        style: {
            fontSize: '24px',
            cssClass: 'apexcharts-yaxis-title'
        }
    },
    subtitle: {
        text: '이번 달 신규 구독 수',
        offsetX: 10,
        style: {
            fontSize: '14px',
            cssClass: 'apexcharts-yaxis-title'
        }
    }
    ,
    series: [{
        name: '구독 가입 수',
        data: monthlySubsAddNum
    }, {
        name: '구독 해지 수',
        data: monthlySubsStopNum
    }],
    chart: {
        id: 'sparkline1',
        group: 'sparklines',
        type: 'area',
        height: 200,
        sparkline: {
            enabled: true
        },
    },
    dataLabels: {
        enabled: false
    },
    colors: ['#008FFB', '#FEB019'],
    stroke: {
        curve: 'smooth'
    },

    xaxis: {
        categories: subsMonth,
        enabled: false
    }
};


let chart = new ApexCharts(document.querySelector("#month_sub_chart"), month_sub_Options);
chart.render();

let total_profit = 0;
let year_profit = 0;
for (let i = 0; i < subsLeft.length; i++) {
    total_profit += subsLeft[i]*4500;
    if (i>=subsLeft.length-12) year_profit += subsLeft[i]*4500;
}

document.querySelector("#total_profit").innerText =  total_profit.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원"
document.querySelector("#year_profit").innerText = year_profit.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원"


