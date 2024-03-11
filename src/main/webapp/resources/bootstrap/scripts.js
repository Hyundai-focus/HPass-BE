/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});
// 현재 페이지 URL을 가져오는 함수
function getCurrentPageUrl() {
    return window.location.pathname;
}

// 현재 페이지와 링크가 일치하는지 확인하고 스타일을 변경하는 함수
function applyActiveStyle() {
    var currentPageUrl = getCurrentPageUrl();
    var navLinks = document.querySelectorAll('.nav-link');

    navLinks.forEach(function(navLink) {
        if (navLink.getAttribute('href') === currentPageUrl) {
            navLink.classList.add('active'); // 현재 페이지에 해당하는 링크에 active 클래스 추가
        }
    });
}

// 하위 메뉴를 클릭했을 때 상위 메뉴 아이템의 스타일 변경
function highlightParentMenu() {
    var parentMenuItems = document.querySelectorAll('.nav-link.collapsed'); // 하위 메뉴를 포함하는 상위 메뉴 아이템 선택
    var currentUrl = getCurrentPageUrl(); // 현재 페이지의 URL 가져오기

    parentMenuItems.forEach(function(parentMenuItem) {
        var submenuLinks = parentMenuItem.nextElementSibling.querySelectorAll('.nav-link'); // 해당 상위 메뉴의 하위 메뉴 아이템 선택

        submenuLinks.forEach(function(submenuItem) {
            if (submenuItem.getAttribute('href') === currentUrl) { // 현재 페이지와 하위 메뉴 아이템의 URL이 일치하는지 확인
                parentMenuItem.classList.add('active'); // 상위 메뉴 아이템에 'active' 클래스 추가
            }
        });
    });
}

// 페이지 로드 시 실행
window.onload = function() {
    applyActiveStyle(); // 현재 페이지에 해당하는 링크에 스타일을 적용
    highlightParentMenu(); // 하위 메뉴를 클릭할 때 상위 메뉴 아이템의 스타일 변경
};