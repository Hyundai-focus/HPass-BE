/**
 *
 */
const form = document.getElementById("operForm");
const page = document.querySelector(".pagination");

page.addEventListener("click", (e) => {
  e.preventDefault();

  // href 값 가져오기
  let href = e.target.getAttribute("href");

  // operForm 안의 page value 수정
  form.firstElementChild.value = href;

  form.submit();
});

// 상단의 amount 수정 시 operForm의 amount 요소의 value 값으로 세팅
// actionForm 전송
const amount = document.getElementById("amount");
amount.addEventListener("change", (e) => {
  const val = e.target.value;

  const amount = document.querySelector("#operForm input:nth-child(2)");
  amount.value = val;

  form.submit();
});
