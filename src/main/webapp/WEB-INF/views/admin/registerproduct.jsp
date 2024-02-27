<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<div id="layoutSidenav_content">
    <main>	
    		<h1 style="font-family: 'Roboto', sans-serif;
			           color: #333; /* 어두운 회색 */
			           font-size: 36px; /* 글꼴 크기 */
			           font-weight: bold; /* 굵은체 */">
			    신청 현황
			</h1>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th scope="col">멤버번호</th>
						<th scope="col">신제품 번호</th>
						<th scope="col">신제품 수령번호</th>
						<th scope="col">수령일</th>
						<th scope="col">수령상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${register}">
							<tr>
								<td>${dto.memberNo}</td>
								<td>${dto.productNo}</td>
								<td>${dto.productHistoryNo}</td>
								<td>${dto.productHistoryDt}</td>
								<td>${dto.productStatus}</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>

		<!-- 페이지 나누기 시작 -->
		<nav aria-label="...">
			<ul class="pagination justify-content-center">
				<c:if test="${pageDTO.prev}">
					<li class="page-item"><a class="page-link"
						href="${pageDTO.startPage-1}">Previous</a></li>
				</c:if>

				<c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}"
					var="idx">
					<li class="page-item ${pageDTO.cri.page==idx?'active':''}"><a
						class="page-link" href="${idx}">${idx}</a></li>
				</c:forEach>

				<c:if test="${pageDTO.next}">
					<li class="page-item"><a class="page-link"
						href="${pageDTO.endPage+1}">Next</a></li>
				</c:if>
			</ul>
		</nav>
		<!-- 페이지 나누기 종료 -->

		<!-- 페이지 나누기 링크 처리를 위한 폼 -->
		<form action="/admin/registerproduct" id="operForm">
			<input type="hidden" name="page" value="${cri.page}" /> <input
				type="hidden" name="amount" value="${cri.amount}" />
		</form>
		<script src="/resources/popup.js"></script>
    </main>
<%@include file="includes/footer.jsp"%>