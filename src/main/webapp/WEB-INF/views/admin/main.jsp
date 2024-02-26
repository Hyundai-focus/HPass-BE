<%@ page pageEncoding="utf-8"%>
<%@include file="includes/header.jsp"%>
<link
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
/>

<link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
/>
<link
        href="https://fonts.googleapis.com/css?family=Titillium+Web:400,600,700"
        rel="stylesheet"
/>

<link rel="stylesheet" href="/resources/main/styles.css" />
<!-- page content -->
<div id="layoutSidenav_content">
    <main>
        <div id="wrapper">
            <div class="content-area">
                <div class="container-fluid">
                    <div class="text-right mt-3 mb-3 d-fixed">
                    </div>
                    <div class="main">
                        <div class="row sparkboxes mt-4 mb-4">
                            <div class="col-md-4">
                                <div class="box box1">
                                    <div id="spark1"></div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="box box2">
                                    <div id="spark2"></div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="box box3">
                                    <div id="spark3"></div>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-5 mb-4">
                            <div class="col-md-6">
                                <div class="box">
                                    <div id="bar"></div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="box">
                                    <div id="donut"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.slim.min.js"></script>
        <script src="/resources/main/apexcharts.js"></script>
        <script src="/resources/main/data.js"></script>
        <script src="/resources/main/scripts.js"></script>

        <script></script>
    </main>
<!-- /page content -->
<%@include file="includes/footer.jsp"%>