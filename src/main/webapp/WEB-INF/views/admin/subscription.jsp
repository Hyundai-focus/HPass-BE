<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2024-02-27
  Time: PM 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css" />
<script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
<link rel="stylesheet" href="/resources/css/subscription.css" />
<script>
    $(document).ready( function () {
        $('#myTable').DataTable({
            order: [[3, "desc"]],
            "bInfo" : false
        });
    } );
</script>
<div id="layoutSidenav_content" style="justify-content: center;">
    <main>
        <div class="container">
            <div class="row">
                <div class="col-md-4"> <!-- 세로배치 -->
                    <div class="row custom-grid">
                        <div id="accumulate_profit"></div>
                    </div>
                    <div class="row custom-grid">
                        <div id="accumulate_subs"></div>
                    </div>
                    <div class="row custom-grid">
                        <div id="month_sub_chart"></div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="row custom-grid-top_out">
                        <div class="col custom-grid-top_in_left">
                            <h5>총 구독 매출</h5>
                            <h3 id="total_profit"></h3>
                        </div>
                        <div class="col custom-grid-top_in_right">
                            <h5 id="year_profit_title"></h5>
                            <h3 id="year_profit"></h3>
                        </div>
                    </div>
                    <div class="row custom-grid" id="myTable-grid">
                        <table id="myTable"  class="table table-striped table-bordered table-hover">
                            <thead class="table-dark">
                            <tr>
                                <th scope="col">구독 번호</th>
                                <th scope="col">구독자 이메일</th>
                                <th scope="col">구독자 이름</th>
                                <th scope="col">구독 시작일</th>
                                <th scope="col">결제 수단</th>
                                <th scope="col">구독 종료일</th>
                            </tr>
                            </thead>
                            <tbody id="subs_table">
                                <c:forEach var="subscriptionDTO" items="${subscriptionList}">
                                    <tr>
                                        <td>${subscriptionDTO.subsNo}</td>
                                        <td>${subscriptionDTO.email}</td>
                                        <td>${subscriptionDTO.memberName}</td>
                                        <td>${subscriptionDTO.subsStartDt}</td>
                                        <td>${subscriptionDTO.payment}</td>
                                        <td>${subscriptionDTO.subsStatus}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
<%--                        <nav aria-label="Page navigation example">--%>
<%--                            <ul class="pagination justify-content-center" id="subs_page">--%>
<%--                                <c:if test="${pageDTO.prev}">--%>
<%--                                <li class="page-item">--%>
<%--                                    <a class="page-link" href="javascript:;" onclick="subsList(${pageDTO.startPage-1},10)" aria-label="Previous">--%>
<%--                                        <span aria-hidden="true">&laquo;</span>--%>
<%--                                    </a>--%>
<%--                                </li>--%>
<%--                                </c:if>--%>
<%--                                <c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}"--%>
<%--                                           var="idx">--%>
<%--                                <li class="page-item ${pageDTO.cri.page==idx?'active':''}">--%>
<%--                                    <a class="page-link" href="javascript:;" onclick="subsList(${idx},10)">${idx}</a>--%>
<%--                                </li>--%>
<%--                                </c:forEach>--%>
<%--                                <c:if test="${pageDTO.next}">--%>
<%--                                <li class="page-item">--%>
<%--                                    <a class="page-link" href="javascript:;" onclick="subsList(${pageDTO.endPage+1},10)" aria-label="Next">--%>
<%--                                        <span aria-hidden="true">&raquo;</span>--%>
<%--                                    </a>--%>
<%--                                </li>--%>
<%--                                </c:if>--%>
<%--                            </ul>--%>
<%--                        </nav>--%>
                    </div>
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
        <c:forEach var="adminSubsDTO" items="${adminSubsList}" varStatus="status">
        subsMonth.push("${adminSubsDTO.subsMonth}");
        monthlySubsAddNum.push("${adminSubsDTO.subsAddCnt}");
        monthlySubsStopNum.push("${adminSubsDTO.subsStopCnt}")
        cumulativeAddNum.push("${adminSubsDTO.cumulativeCnt}");
        subsLeft.push(${adminSubsDTO.subsLeft});
        </c:forEach>
    </script>
    <script src="/resources/js/subscription.js"></script>
    <script src="/resources/js/subscriptionAjax.js"></script>
    <%@include file="includes/footer.jsp"%>
