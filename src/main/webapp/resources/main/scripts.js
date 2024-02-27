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
var colorPalette = ["#00D8B6", "#008FFB", "#FEB019", "#FF4560", "#775DD0"];

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
      data: randomizeArray(sparklineData),
    },
  ],
  labels: [...Array(24).keys()].map((n) => `2024-02-0${n + 1}`),
  yaxis: {
    min: 0,
  },
  xaxis: {
    type: "datetime",
  },
  colors: ["#008FFB"],
  title: {
    text: "424,652",
    offsetX: 30,
    style: {
      fontSize: "24px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
  subtitle: {
    text: "이번 달 일일 매장 방문",
    offsetX: 30,
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
      data: randomizeArray(sparklineData),
    },
  ],
  labels: [...Array(24).keys()].map((n) => `2024-02-0${n + 1}`),
  yaxis: {
    min: 0,
  },
  xaxis: {
    type: "datetime",
  },
  colors: ["#008FFB"],
  title: {
    text: "235,312",
    offsetX: 30,
    style: {
      fontSize: "24px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
  subtitle: {
    text: "이번 달 쿠폰 사용",
    offsetX: 30,
    style: {
      fontSize: "14px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
};

var productElement = document.querySelector("#spark3");
var dailyProduct = productElement.getAttribute("data-products").split(",");
var cumulativeProduct = parseInt(productElement.getAttribute("data-total"));
var date = productElement.getAttribute("data-dt").split(",");

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
      data: dailyProduct,
    },
  ],
  labels: date,
  xaxis: {
    type: "datetime",
  },
  yaxis: {
    min: 0,
  },
  colors: ["#008FFB"],
  title: {
    text: cumulativeProduct,
    offsetX: 30,
    style: {
      fontSize: "24px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
  subtitle: {
    text: "이번 달 신제품 수령",
    offsetX: 30,
    style: {
      fontSize: "14px",
      cssClass: "apexcharts-yaxis-title",
    },
  },
};

new ApexCharts(document.querySelector("#spark1"), spark1).render();
new ApexCharts(document.querySelector("#spark2"), spark2).render();
new ApexCharts(productElement, spark3).render();

var optionsBar = {
  chart: {
    type: "bar",
    height: 380,
    width: "100%",
    stacked: true,
  },
  plotOptions: {
    bar: {
      columnWidth: "45%",
    },
  },
  colors: colorPalette,
  series: [
    {
      name: "구독 가입 수",
      data: [42, 52, 16, 55, 59, 51, 45, 32, 26, 33, 44, 51, 42, 56],
    },
    {
      name: "구독 해지 수",
      data: [6, 12, 4, 7, 5, 3, 6, 4, 3, 3, 5, 6, 7, 4],
    },
  ],
  labels: [10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23],
  xaxis: {
    labels: {
      show: false,
    },
    axisBorder: {
      show: false,
    },
    axisTicks: {
      show: false,
    },
  },
  yaxis: {
    axisBorder: {
      show: false,
    },
    axisTicks: {
      show: false,
    },
    labels: {
      style: {
        colors: "#78909c",
      },
    },
  },
  title: {
    text: "구독자 가입 현황",
    align: "left",
    style: {
      fontSize: "18px",
    },
  },
};

var chartBar = new ApexCharts(document.querySelector("#bar"), optionsBar);
chartBar.render();

var donutElement = document.querySelector("#donut");
var popupNames = donutElement.getAttribute("data-popupnames").split(",");
var bookingCounts = donutElement.getAttribute("data-bookingcounts").split(",").map(Number);

var optionDonut = {
  chart: {
    type: "donut",
    width: "100%",
    height: 400,
  },
  dataLabels: {
    enabled: false,
  },
  plotOptions: {
    pie: {
      customScale: 0.8,
      donut: {
        size: "75%",
      },
      offsetY: 20,
    },
    stroke: {
      colors: undefined,
    },
  },
  colors: colorPalette,
  title: {
    text: "팝업스토어 예약 현황",
    style: {
      fontSize: "18px",
    },
  },
  series: bookingCounts,
  labels: popupNames,
  legend: {
    position: "left",
    offsetY: 80,
  },
};

var donut = new ApexCharts(donutElement, optionDonut);
donut.render();

// on smaller screen, change the legends position for donut
var mobileDonut = function () {
  if ($(window).width() < 768) {
    donut.updateOptions(
      {
        plotOptions: {
          pie: {
            offsetY: -15,
          },
        },
        legend: {
          position: "bottom",
        },
      },
      false,
      false
    );
  } else {
    donut.updateOptions(
      {
        plotOptions: {
          pie: {
            offsetY: 20,
          },
        },
        legend: {
          position: "left",
        },
      },
      false,
      false
    );
  }
};

$(window).resize(function () {
  mobileDonut();
});
