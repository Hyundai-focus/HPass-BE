<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<!-- <link href="/resources/css/adminTable.css" rel="stylesheet"/> -->
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
			    신제품 목록
				<button type="button" class="btn btn-outline-success" style="float:right;" data-toggle="modal" data-target="#insertModal">신제품 등록</button>
			</h1>
			<table id="myTable" class="table table-striped table-bordered table-hover">
				<thead class="table-dark">
					<tr>
						<th scope="col">신제품 번호</th>
						<th scope="col">신제품 브랜드</th>
						<th scope="col">신제품 이름</th>
						<th scope="col">수령 날짜</th>
						<th scope="col">수령 장소</th>
						<th scope="col">재고</th>
						<th scope="col" style="text-align: center;">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}">
							<tr>
								<td>NEWPRDT${String.format("%06d", dto.productNo)}</td>
								<td>${dto.productBrand}</td>
								<td>${dto.productName}</td>
								<td>${dto.receiveDt}</td>
								<td>${dto.receiveLoc}</td>
								<td>${dto.stock}</td>
								<td style="text-align: center; padding: 0; vertical-align: middle;">
									<button type="button" class="btn btn-outline-danger" onclick="deleteBooking('${dto.productNo}')" style="font-size: 12px; ">삭제</button>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
		<!-- 신제품 추가 모달 창 -->
		<div class="modal fade" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="insertModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="insertModalLabel">신제품 등록</h5>
						<button type="button" class="btn-close btn-close-white" aria-label="Close" data-dismiss="modal"><!-- 닫기 버튼 -->
						</button>
					</div>
					<div class="modal-body">
						<!-- 입력 폼 -->
						<form id="productForm">
							<div class="form-group">
								<label for="productBrand">브랜드</label>
								<input type="text" class="form-control" id="productBrand" placeholder="브랜드">
							</div>
							<div class="form-group">
								<label for="productName">제품명</label>
								<input type="text" class="form-control" id="productName" placeholder="제품명">
							</div>
							<div class="form-group">
								<label for="stock">재고</label>
								<input type="number" class="form-control" id="stock" placeholder="재고">
							</div>
							<div class="form-group">
								<label for="receiveDt">입고 날짜</label>
								<input type="date" class="form-control" id="receiveDt">
							</div>
							<div class="form-group">
								<label for="receiveLoc">입고 위치</label>
								<input type="text" class="form-control" id="receiveLoc" placeholder="입고 위치">
							</div>
							<div class="form-group">
								<label for="productImg">신제품 사진 URL</label>
								<input type="text" class="form-control" id="productImg" placeholder="신제품 사진">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="submitProduct" style="background-color: black">등록</button>
					</div>
				</div>
			</div>
		</div>
	</main>


	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
		$('#submitProduct').click(function() {
			// 제품 정보 수집
			let productBrand = $('#productBrand').val();
			let productName = $('#productName').val();
			let stock = $('#stock').val();
			let receiveDt = $('#receiveDt').val();
			let receiveLoc = $('#receiveLoc').val();
			let productImg = $('#productImg').val();

			// 확인창 표시
			let confirmMsg = confirm('신제품을 등록하시겠습니까?');
			if (confirmMsg) {
				let info = {
					productBrand: productBrand,
					productName: productName,
					stock: stock,
					receiveDt: receiveDt,
					receiveLoc: receiveLoc,
					productImg: productImg
				};
				// 제품 정보를 서버로 전송 (AJAX)
				$.ajax({
					url: '/admin/product/insert',
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
		function deleteBooking(productNo) {
			if (confirm("예약을 삭제하시겠습니까?")) {
				$.ajax({
					type: "DELETE",
					url: "/admin/product/" + productNo,
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
<%@include file="includes/footer.jsp"%>