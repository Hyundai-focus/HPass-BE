Apex.grid = {
  padding: {
    right: 0,
    left: 0,
  },
};

Apex.dataLabels = {
  enabled: false,
};

var randomizeArray = function (arg) {
  var array = arg.slice();
  var currentIndex = array.length,
    temporaryValue,
    randomIndex;

  while (0 !== currentIndex) {
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
};

// data for the sparklines that appear below header area
var sparklineData = [47, 45, 54, 38, 56, 24, 65, 31, 37, 39, 62, 51, 35, 41, 35, 27, 93, 53, 61, 27, 54, 43, 19, 46];

// the default colorPalette for this dashboard
//var colorPalette = ['#01BFD6', '#5564BE', '#F7A600', '#EDCD24', '#F74F58'];
var colorPalette =["#008FFB", "#FEB019", "#FF4560", "#775DD0", "#00D8B6"];

var spark1 = {
  chart: {
    id: "sparkline1",
    group: "sparklines",
    type: "area",
    height: 160,
    sparkline: {
      enabled: true,
    },
  },
  stroke: {
    curve: "straight",
  },
  fill: {
    opacity: 1,
  },
  series: [
    {
      name: "Visits",
      data: allVisitCnt,
    },
  ],
  labels: mainDate,
  yaxis: {
    min: 0,
  },
  xaxis: {
    type: "datetime",
  },
  colors: ["#00D8B6"],
  title: {
    text: allVisitSum,
    offsetX: 5,
    style: {
      fontSize: "24px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
  subtitle: {
    text: "이번 달 오늘의 매장 방문 완료",
    offsetX: 5,
    style: {
      fontSize: "14px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
};

var spark2 = {
  chart: {
    id: "sparkline2",
    group: "sparklines",
    type: "area",
    height: 160,
    sparkline: {
      enabled: true,
    },
  },
  stroke: {
    curve: "straight",
  },
  fill: {
    opacity: 1,
  },
  series: [
    {
      name: "Coupons",
      data: couponUseCnt,
    },
  ],
  labels: mainDate,
  yaxis: {
    min: 0,
  },
  xaxis: {
    type: "datetime",
  },
  colors: ["#00D8B6"],
  title: {
    text: `${couponUseSum}회 ㅣ ${Math.round(couponUseSum/(unusedCouponNum+couponUseSum)*100)}%`,
    offsetX: 5,
    style: {
      fontSize: "24px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
  subtitle: {
    text: "이번 달 쿠폰 사용 ㅣ 사용률",
    offsetX: 5,
    style: {
      fontSize: "14px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
};

var spark3 = {
  chart: {
    id: "sparkline3",
    group: "sparklines",
    type: "area",
    height: 160,
    sparkline: {
      enabled: true,
    },
  },
  stroke: {
    curve: "straight",
  },
  fill: {
    opacity: 1,
  },
  series: [
    {
      name: "Products",
      data: productCnt,
    },
  ],
  labels: mainDate,
  xaxis: {
    type: "datetime",
  },
  yaxis: {
    min: 0,
  },
  colors: ["#00D8B6"],
  title: {
    text: productSum,
    offsetX: 5,
    style: {
      fontSize: "24px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
  subtitle: {
    text: "이번 달 신제품 수령",
    offsetX: 5,
    style: {
      fontSize: "14px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
};

new ApexCharts(document.querySelector("#spark1"), spark1).render();
new ApexCharts(document.querySelector("#spark2"), spark2).render();
new ApexCharts(document.querySelector("#spark3"), spark3).render();

//달별 구독 가입/해지
let month_sub_Options = {
  title: {
    text: "월별 구독 가입/해지 현황",
    offsetX: 30,
    style: {
      fontSize: '18px',
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
    height: 340,
    type: 'area'
  },
  dataLabels: {
    enabled: false
  },
  colors: ['#008FFB', '#FEB019'],
  stroke: {
    curve: 'smooth'
  },
  xaxis: {
    categories: subsMonth
  }
};


let chart = new ApexCharts(document.querySelector("#month_sub_chart"), month_sub_Options);
chart.render();

var donutElement = document.querySelector("#donut");
var popupNames = donutElement.getAttribute("data-popupnames").split(",");
var bookingCounts = donutElement.getAttribute("data-bookingcounts").split(",").map(Number);

var optionDonut = {
  chart: {
    type: "donut",
    width: "100%",
    height: 300,
  },
  dataLabels: {
    enabled: false,
  },
  plotOptions: {
    pie: {
      customScale: 1.0,
      donut: {
        size: "70%",
      },
      offsetY: 20,
    },
    stroke: {
      colors: undefined,
    },
  },
  title: {
    text: "팝업스토어 예약 현황",
    style: {
      fontSize: "18px",
    },
  },
  series: bookingCounts,
  labels: popupNames,
  legend: {
    position: "right",
    offsetY: 30,
  },
};

function getTotalValue() {
  var total = bookingCounts.reduce((a, b) => a + b, 0);
  return '총 예약: ' + total.toString();
}


var donut = new ApexCharts(donutElement, optionDonut);
donut.render();

document.getElementById('coupon_value').textContent = `${couponUseSum} (${Math.round(couponUseSum/(unusedCouponNum+couponUseSum)*100)}%)`