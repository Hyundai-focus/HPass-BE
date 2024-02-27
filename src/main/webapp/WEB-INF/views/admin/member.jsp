<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2024-02-26
  Time: AM 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
/>
<style>
    body {
        background-color: #eff4f7;
        font-family: 'Titillium Web', Arial, Helvetica, sans-serif;
    }
    /* Custom Styles */
    .custom-grid {
        margin: 10px; /* 마진 설정 */
        background-color: white; /* 배경색 설정 */

        padding: 20px; /* 내부 패딩 설정 */
        box-shadow: 0px 1px 22px -12px #607D8B;
    }
</style>
<div id="layoutSidenav_content">
    <main>
        <div class="container">
            <div class="row">
                <div class="col custom-grid">
                    <div id="accumulate_profit"></div>
                </div>
                <div class="col custom-grid">
                    <div id="accumulate_subs"></div>
                </div>
                <div class="col custom-grid">
                    <h2 id="total_profit"></h2>
                    <h2 id="year_profit"></h2>
                </div>
            </div>
            <div class="row">
                <div class="col custom-grid">
                    <div id="month_sub_chart"></div>
                </div>
                <div class="col-7 custom-grid">
                    표
                </div>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    <script>
        let monthlySubsAddNum = [];
        let monthlySubsStopNum = [];
        let cumulativeAddNum = [];
        let subsMonth = [];
        let subsLeft = [];
        <c:forEach var="subsAdminDTO" items="${subsAdminList}" varStatus="status">
        subsMonth.push("${subsAdminDTO.subsMonth}");
        monthlySubsAddNum.push("${subsAdminDTO.subsAddCnt}");
        monthlySubsStopNum.push("${subsAdminDTO.subsStopCnt}")
        cumulativeAddNum.push("${subsAdminDTO.cumulativeCnt}");
        subsLeft.push(${subsAdminDTO.subsLeft});
        </c:forEach>
    </script>
    <script src="/resources/js/member.js"/>
<%@include file="includes/footer.jsp"%>
