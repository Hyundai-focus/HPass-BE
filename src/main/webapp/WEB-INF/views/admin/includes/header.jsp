<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Dashboard - SB Admin</title>

    <link href="/resources/bootstrap/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark" style="background-color: #0f694a !important">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="/admin"><img class="hpass-logo" src="/resources/images/hpass.png"/><span
            class="hpass-admin" style="margin-left: 7px">관리자</span></a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!">
        <i class="fas fa-bars"></i>
    </button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0"></form>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a
                    class="nav-link dropdown-toggle"
                    id="navbarDropdown"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
            ><i class="fas fa-user fa-fw"></i
            ></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="/admin/logout">Logout</a></li>
            </ul>
        </li>
    </ul>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <a class="nav-link" href="/admin">
                        <div class="sb-nav-link-icon"></div>
                        메인
                    </a>
                    <a class="nav-link" href="/admin/subscription">
                        <div class="sb-nav-link-icon"></div>
                        구독 관리
                    </a>
                    <a class="nav-link" href="/admin/popup">
                        <div class="sb-nav-link-icon"></div>
                        팝업스토어 관리
                    </a>
                    <a
                            class="nav-link collapsed"
                            href="javascript:;"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapseLayouts"
                            aria-expanded="false"
                            aria-controls="collapseLayouts"
                    >
                        <div class="sb-nav-link-icon"></div>
                        신제품 관리
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div
                            class="collapse"
                            id="collapseLayouts"
                            aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion"
                    >
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/admin/product/apply">신청 현황</a>
                            <a class="nav-link" href="/admin/product/get">수령 현황</a>
                        </nav>
                    </div>
                    <a
                            class="nav-link collapsed"
                            href="javascript:;"
                            data-bs-toggle="collapse"
                            data-bs-target="#couponLayouts"
                            aria-expanded="false"
                            aria-controls="collapseLayouts"
                    >
                        <div class="sb-nav-link-icon"></div>
                        쿠폰 관리
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div
                            class="collapse"
                            id="couponLayouts"
                            aria-labelledby="headingOne"
                            data-bs-parent="#sidenavAccordion"
                    >
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/admin/coupon/issue">발급 현황</a>
                            <a class="nav-link" href="/admin/coupon/use">사용 현황</a>
                        </nav>
                    </div>
                    <a class="nav-link" href="/admin/visit">
                        <div class="sb-nav-link-icon"></div>
                        매장 방문 관리
                    </a>
                </div>
            </div>
        </nav>
    </div>