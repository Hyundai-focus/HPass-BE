<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css" />
<link rel="stylesheet" href="/resources/css/adminTable.css" />
<script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
<script>
$(document).ready( function () {
    $('#myTable').DataTable({
		order: [[0, "desc"]],
		"bInfo" : false
    });
} );
</script>
<div id="layoutSidenav_content">
	<main>
			<h1 style="font-family: 'Roboto', sans-serif;
			           color: #333; /* 어두운 회색 */
			           font-size: 25px; /* 글꼴 크기 */
			           font-weight: bold; /* 굵은체 */">
			    쿠폰 발급 현황
			</h1>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">발급번호</th>
						<th scope="col">고객번호</th>
						<th scope="col">쿠폰번호</th>
						<th scope="col">쿠폰 브랜드</th>
						<th scope="col">쿠폰 내용</th>
						<th scope="col">사용상태</th>
						<th scope="col">사용일시</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${issue}">
							<tr>
								<td>ISSUE${String.format("%06d", dto.couponHistoryNo)}</td>
								<td>MEMBER${String.format("%06d", dto.memberNo)}</td>
								<td>COUPON${String.format("%06d", dto.couponNo)}</td>
								<td>${dto.couponBrand}</td>
								<td>${dto.couponContent}</td>
								<td>${dto.couponIsUsed}</td>
								<td>${dto.couponUsedDt}</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
	</main>
	<%@include file="includes/footer.jsp"%>