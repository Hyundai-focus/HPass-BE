<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css" />
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
<div id="layoutSidenav_content">
    <main>
    		<h1 style="font-family: 'Roboto', sans-serif;
			           color: #333; /* 어두운 회색 */
			           font-size: 36px; /* 글꼴 크기 */
			           font-weight: bold; /* 굵은체 */">
			    신청 현황
			</h1>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">고객번호</th>
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
    </main>
<%@include file="includes/footer.jsp"%>