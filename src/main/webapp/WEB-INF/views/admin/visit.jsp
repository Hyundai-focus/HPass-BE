<%@ page pageEncoding="utf-8" %>
<%@ include file="includes/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css"/>
<link rel="stylesheet" href="/resources/css/adminTable.css"/>
<link rel="stylesheet" href="/resources/css/visit.css"/>
<script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
<script>
    $(document).ready(function () {
        $('#myTable').DataTable({
            order: [[2, "desc"]],
            "bInfo" : false
        });
    });
</script>
<div class="container-fluid" id="layoutSidenav_content">
    <main>

        <div class="container">
            <h1
                    style="font-family: 'Roboto', sans-serif; color: #333; /* 어두운 회색 */ font-size: 25px; /* 글꼴 크기 */ font-weight: bold;">
                오늘의 매장</h1>
            <div class="row">
                <div class="col-5">
                    <div class="row">
                        <div class="col">

                                <table class="table">
                                    <tbody>
                                    <c:forEach var="dto" items="${today}">
                                        <tr>
                                            <td>${dto.storeNo}F</td>
                                            <td>${dto.storeBrand}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                        </div>
                    </div>
                    <div class="row">
                        <div class="row custom-grid">
                            <div id="participation"></div>
                        </div>
                    </div>
                </div>
                <div class="col-7">
                    <div class="row custom-grid">
                        <b>방문 완료 : ${info.todayFinishNo}</b>
                        <table id="myTable"
                               class="table table-striped table-bordered table-hover"
                        style="text-align: center">
                            <thead class="table-dark">
                            <tr>
                                <th scope="col">고객 번호</th>
                                <th scope="col">방문 층</th>
                                <th scope="col">방문 시간</th>
                                <th scope="col">방문 상태</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="dto" items="${visit}">
                                <tr>
                                    <td>MEMBER${String.format("%06d", dto.memberNo)}</td>
                                    <td>${dto.storeNo}F</td>
                                    <td>${dto.visitTime}</td>
                                    <td>${dto.status}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<script>
    let subsNo = ${info.subsNo};
    let todayNo = ${info.todayNo};
</script>
<script src="/resources/js/todayStore.js"></script>

<%@ include file="includes/footer.jsp" %>