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
			    신청 현황
			</h1>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">신제품 신청번호</th>
						<th scope="col">고객번호</th>
						<th scope="col">신제품 번호</th>
						<th scope="col">신제품명</th>
						<th scope="col">마감일</th>
						<th scope="col">수령일</th>
						<th scope="col">수령상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${register}">
							<tr>
								<td>REGISTR${String.format("%06d", dto.productHistoryNo)}</td>
								<td>MEMBER${String.format("%06d", dto.memberNo)}</td>
								<td>NEWPRDT${String.format("%06d", dto.productNo)}</td>
								<td>${dto.productName}</td>
								<td>${dto.receiveDt}</td>
								<td>${dto.productHistoryDt}</td>
								<td>${dto.productStatus}</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
    </main>
<%@include file="includes/footer.jsp"%>