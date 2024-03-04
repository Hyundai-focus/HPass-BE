<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css" />
<link rel="stylesheet" href="/resources/css/adminTable.css" />
<script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
<script>
$(document).ready( function () {
    $('#myTable').DataTable({
		order: [[0, "desc"]]
    });
} );
</script>
<div id="layoutSidenav_content">
	<main>
			<h1 style="font-family: 'Roboto', sans-serif;
			           color: #333; /* 어두운 회색 */
			           font-size: 25px; /* 글꼴 크기 */
			           font-weight: bold; /* 굵은체 */">
			    팝업스토어 예약 현황
			</h1>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">예약번호</th>
						<th scope="col">고객번호</th>
						<th scope="col">팝업스토어 번호</th>
						<th scope="col">팝업스토어 이름</th>
						<th scope="col">예약날짜</th>
						<th scope="col">예약시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}">
							<tr>
								<td>${dto.bookingNo}</td>
								<td>${dto.memberNo}</td>
								<td>${dto.popupNo}</td>
								<th scope="row">${dto.popupName}</th>
								<td>${dto.bookingDt}</td>
								<td>${dto.bookingTime}</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
	</main>
	<%@include file="includes/footer.jsp"%>