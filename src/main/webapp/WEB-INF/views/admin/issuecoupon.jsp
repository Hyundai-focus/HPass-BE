<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<link href="/resources/css/table.css" rel="stylesheet"/>
<div id="layoutSidenav_content">
    <main>
        <div>
            <h1 style="font-family: 'Roboto', sans-serif;
			           color: #333; /* 어두운 회색 */
			           font-size: 36px; /* 글꼴 크기 */
			           font-weight: bold; /* 굵은체 */">
                쿠폰 발급 현황
            </h1>
            <table class="table table-striped table-bordered table-hover">
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
                        <td>${total - subscriptionDTO.rowNo + 1}</td>
                        <td>${subscriptionDTO.email}</td>
                        <td>${subscriptionDTO.memberName}</td>
                        <td>${subscriptionDTO.subsStartDt}</td>
                        <td>${subscriptionDTO.payment}</td>
                        <td>${subscriptionDTO.subsStatus}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center" id="subs_page">
                    <c:if test="${pageDTO.prev}">
                        <li class="page-item">
                            <a class="page-link" href="javascript:;" onclick="subsList(${pageDTO.startPage-1},10)" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}"
                               var="idx">
                        <li class="page-item ${pageDTO.cri.page==idx?'active':''}">
                            <a class="page-link" href="javascript:;" onclick="subsList(${idx},10)">${idx}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageDTO.next}">
                        <li class="page-item">
                            <a class="page-link" href="javascript:;" onclick="subsList(${pageDTO.endPage+1},10)" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </main>
<%@include file="includes/footer.jsp"%>