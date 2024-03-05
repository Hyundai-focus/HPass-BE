<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/login.css" />
    <link rel="stylesheet" href="/resources/bootstrap/styles.css" />
    <title>CSS Login Screen Tutorial</title>
</head>
<body>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark" style="background-color: #0f694a !important">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="/admin/login"><img class="hpass-logo" src="/resources/images/hpass.png"/><span
            class="hpass-admin" style="margin-left: 7px">관리자</span></a>
</nav>
<div class="login-page">
    <div class="form" style="margin-top: 20px;">
        <div class="login">
            <div class="login-header"><img src="/resources/images/login_logo.png" style="width: 45%"/></div>
        </div>
        <form class="login-form" style="border-radius: 5px">
            <input id= "id-input" class="login-input" type="text" placeholder="ID" style="border-radius: 5px" />
            <input id= "pw-input" class="login-input" type="password" placeholder="Password" style="border-radius: 5px" />
            <button id="login-btn" style="border-radius: 5px">login</button>
        </form>
    </div>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    // 쿠키를 가져오는 함수
    function getCookie(name) {
        // 쿠키 문자열을 가져옴
        var cookieString = document.cookie;
        // 쿠키 문자열을 ; 로 분할하여 배열로 만듦
        var cookies = cookieString.split(';');
        // 배열을 순회하면서 주어진 이름의 쿠키를 찾음
        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            // 쿠키 문자열의 앞부분의 공백을 제거
            cookie = cookie.trim();
            // 현재 쿠키가 주어진 이름으로 시작하는지 확인
            if (cookie.indexOf(name) === 0) {
                // 주어진 이름과 일치하는 쿠키를 찾으면 해당 값을 반환
                return cookie.substring(name.length + 1);
            }
        }
        // 일치하는 쿠키를 찾지 못하면 null을 반환
        return null;
    }
    $(document).ready(function() {
        $('#login-btn').click(function(e) {
            e.preventDefault();

            let userId = $('#id-input').val();
            let password = $('#pw-input').val();
            console.log(userId,password)
            $.ajax({
                type: 'POST',
                url: '/login',
                contentType: 'application/json',
                data: JSON.stringify({userId: userId, password: password}),
                success: function(response) {
                    // 응답에서 accessToken 값을 가져와서 쿠키에 저장
                    console.log(response)
                    console.log(response.accessToken)
                    if (response.role == 'ROLE_ADMIN') {
                        document.cookie = "accessToken=" + response.accessToken;
                        const accessToken = getCookie('accessToken');
                        console.log(accessToken)
                        document.location.href = '/admin'
                    }
                },
                error: function(xhr, status, error) {
                    console.error(error);
                    // 에러 처리 로직 추가
                }
            });
        });
    });

</script>
</html>

