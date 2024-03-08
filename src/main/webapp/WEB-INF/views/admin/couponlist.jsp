<%@ page pageEncoding="utf-8"%>
<%@ include file="includes/header.jsp"%>
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
			    쿠폰 목록
				<button type="button" class="btn btn-outline-success" style="float:right;" data-toggle="modal" data-target="#insertModal">쿠폰 등록</button>
			</h1>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">쿠폰 번호</th>
						<th scope="col">쿠폰 브랜드</th>
						<th scope="col">쿠폰 내용</th>
						<th scope="col">쿠폰 시작일</th>
						<th scope="col">쿠폰 만기일</th>
						<th scope="col" style="text-align: center;">쿠폰 삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}">
					    <tr>
					        <td>COUPON${String.format("%06d", dto.couponNo)}</td>
					        <td>${dto.couponBrand}</td>
					        <td>${dto.couponContent}</td>
					        <td>${dto.couponStartDt}</td>
							<td>${dto.couponEndDt}</td>
							<td style="text-align: center; padding: 0; vertical-align: middle;">
								<button type="button" class="btn btn-outline-danger" onclick="deleteCoupon('${dto.couponNo}')" style="font-size: 12px; ">삭제</button>
							</td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
	</main>
	<!-- 쿠폰 추가 모달 창 -->
	<div class="modal fade" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="insertModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="insertModalLabel">쿠폰 등록</h5>
					<button type="button" class="btn-close btn-close-white" aria-label="Close" data-dismiss="modal"/><!-- 닫기 버튼 -->
				</div>
				<div class="modal-body">
					<!-- 입력 폼 -->
					<form id="couponForm">
						<div class="form-group">
							<label for="couponBrand">브랜드</label>
							<input type="text" class="form-control" id="couponBrand" placeholder="브랜드">
						</div>
						<div class="form-group">
							<label for="couponContent">쿠폰 내용</label>
							<textarea class="form-control" id="couponContent" rows="3" placeholder="쿠폰 내용"></textarea>
						</div>
						<div class="form-group">
							<label for="couponStartDt">시작 날짜</label>
							<input type="date" class="form-control" id="couponStartDt">
						</div>
						<div class="form-group">
							<label for="couponEndDt">종료 날짜</label>
							<input type="date" class="form-control" id="couponEndDt">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" id="submitCoupon" style="background-color: black">등록</button>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		$('#submitCoupon').click(function() {
			// 제품 정보 수집
			let couponBrand = $('#couponBrand').val();
			let couponContent = $('#couponContent').val();
			let couponStartDt = $('#couponStartDt').val();
			let couponEndDt = $('#couponEndDt').val();
			// 확인창 표시
			let confirmMsg = confirm('쿠폰을 등록하시겠습니까?');
			if (confirmMsg) {
				let info = {
					couponBrand: couponBrand,
					couponContent: couponContent,
					couponStartDt: couponStartDt,
					couponEndDt: couponEndDt
				};
				// 제품 정보를 서버로 전송 (AJAX)
				$.ajax({
					url: '/admin/coupon/insert',
					method: 'POST',
					data: JSON.stringify(info),
					contentType: 'application/json;charset=UTF-8',
					success: function(result) {
						document.location.reload();
					},
					error: function(xhr, status, error) {
						console.error(error);
						// 오류가 발생한 경우에 대한 추가 처리를 여기에 작성할 수 있습니다.
					}
				});
			}
		});
		function deleteCoupon(couponNo) {
			if (confirm("쿠폰을 삭제하시겠습니까?")) {
				$.ajax({
					type: "DELETE",
					url: "/admin/coupon/" + couponNo,
					success: function(response) {
						// 성공적으로 삭제된 경우, 해당 행을 테이블에서 제거하거나 새로고침
						document.location.reload();
					},
					error: function(xhr, status, error) {
						// 삭제에 실패한 경우, 오류 메시지를 표시
						console.log("예약 삭제에 실패했습니다. 오류: " + error);
					}
				});
			}
		};
	</script>

<%@ include file="includes/footer.jsp"%>