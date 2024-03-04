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
						<th scope="col" style="text-align: center;">예약번호</th>
						<th scope="col" style="text-align: center;">고객번호</th>
						<th scope="col" style="text-align: center;">팝업스토어 번호</th>
						<th scope="col" style="text-align: center;">팝업스토어 이름</th>
						<th scope="col" style="text-align: center;">예약날짜</th>
						<th scope="col" style="text-align: center;">예약시간</th>
						<th scope="col" style="text-align: center;">예약삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}">
							<tr>
								<td>${dto.bookingNo}</td>
								<td>${dto.memberNo}</td>
								<td>${dto.popupNo}</td>
								<th scope="row">${dto.popupName}</th>
								<td style="text-align: left;">${dto.bookingDt}</td>
								<td>${dto.bookingTime}</td>
								<td style="text-align: center; padding: 0; vertical-align: middle;">
								    <button type="button" class="btn btn-outline-danger" onclick="deleteBooking('${dto.bookingNo}')" style="font-size: 12px; ">삭제</button>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
	</main>
</div>
	<script>
	    function deleteBooking(bookingNo) {
	    	bookingNo = bookingNo.substring(5);
	    	
	        if (confirm("예약을 삭제하시겠습니까?")) {
	            $.ajax({
	                type: "DELETE",
	                url: "/admin/popup/booking/" + bookingNo,
	                success: function(response) {
	                    // 성공적으로 삭제된 경우, 해당 행을 테이블에서 제거하거나 새로고침
	                    window.location.reload();
	                },
	                error: function(xhr, status, error) {
	                    // 삭제에 실패한 경우, 오류 메시지를 표시
	                    alert("예약 삭제에 실패했습니다. 오류: " + error);
	                }
	            });
	        }
	    }
	</script>
<%@include file="includes/footer.jsp"%>