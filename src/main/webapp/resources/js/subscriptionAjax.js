function subsList(page, amount) {
    $.ajax({
            url: '/admin/subscription/list',
            type: 'POST',
            data: {
                'page': page,
                'amount': amount
            },
            dataType: "json",
            success: function (result) {
                let tableHtml = '';
                let pageHtml = '';
                let total = result.total;
                let subsList = result.subscriptionList;
                let pageDTO = result.pageDTO;
                subsList.forEach(function (subsDTO){
                    tableHtml += `<tr>
                                <td>${total - subsDTO.rowNo + 1}</td>
                                <td>${subsDTO.email}</td>
                                <td>${subsDTO.memberName}</td>
                                <td>${subsDTO.subsStartDt}</td>
                                <td>${subsDTO.payment}</td>
                                <td>${subsDTO.subsStatus}</td>
                              </tr>`
                })
                $("#subs_table").html(tableHtml)
                if(pageDTO.prev){
                    pageHtml += `<li class="page-item">
                        <a class="page-link" href="javascript:;" onclick="subsList(${pageDTO.startPage-1},10)" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>`
                }
                for(let idx= pageDTO.startPage ; idx <= pageDTO.endPage; idx++){
                    pageHtml += `
                    <li class="page-item ${pageDTO.cri.page==idx?'active':''}">
                                    <a class="page-link" href="javascript:;" onclick="subsList(${idx},10)">${idx}</a>
                                </li>
                    `
                }
                if(pageDTO.next){
                    pageHtml += `<li class="page-item">
                                    <a class="page-link" href="javascript:;" onclick="subsList(${pageDTO.endPage+1},10)" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>`
                }
                $("#subs_page").html(pageHtml);
            }
        },
    )
}