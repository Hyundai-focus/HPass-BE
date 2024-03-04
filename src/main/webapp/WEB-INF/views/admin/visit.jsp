<%@ page pageEncoding="utf-8"%>
<%@ include file="includes/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css" />
<link rel="stylesheet" href="/resources/css/adminTable.css" />
<script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
<script>
$(document).ready( function () {
    $('#myTable').DataTable({
    	rowGroup: {
            dataSrc: '신제품 번호'
        }
    });
} );
</script>
<div class="container" id="layoutSidenav_content">
	<main>
			<h1
				style="font-family: 'Roboto', sans-serif; color: #333; /* 어두운 회색 */ font-size: 25px; /* 글꼴 크기 */ font-weight: bold;">
				오늘의 매장</h1>
		<div class="col-md-6">
			<table class="table">
				<tbody>
					<c:forEach var="dto" items="${today}">
						<tr>
							<td>${dto.storeNo}</td>
							<td>${dto.storeBrand}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<table id="myTable"
			class="table table-striped table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">매장번호</th>
					<th scope="col">고객번호</th>
					<th scope="col">오늘날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${visit}">
					<tr>
						<td>${dto.storeNo}</td>
						<td>${dto.memberNo}</td>
						<td>${dto.todayStoreDt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</div>
<%@ include file="includes/footer.jsp"%>