<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title>更新成绩信息</title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <meta content="Admin Dashboard" name="description" />
        <meta content="ThemeDesign" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../bootstrap/css/icons.css" rel="stylesheet" type="text/css">
        <link href="../bootstrap/css/style.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            .RadioStyle input {
                display: none
            }
            .RadioStyle label {
                border: 1px solid #00a4ff;
                padding: 2px 10px 2px 5px;
                line-height: 28px;
                min-width: 80px;
                text-align: center;
                float: left;
                margin: 2px;
                border-radius: 4px
            }
            .RadioStyle input:checked + label {
                background: url(/bootstrap/img/img_2.svg) no-repeat right bottom;
                background-size: 21px 21px;
                color: #00a4ff
            }
            .myButton {
                box-shadow: 0px 10px 14px -6px #1e6873;
                background:linear-gradient(to bottom, #8ad4ed 5%, #17c5d1 100%);
                background-color:#8ad4ed;
                border-radius:5px;
                border:0px;
                width:200px;
                height:30px;
                display:inline-block;
                cursor:pointer;
                color:#fcfcfc;
                font-family:Arial;
                font-size:14px;
                font-weight:bold;
                padding:6px 6px;
                text-decoration:none;
                text-shadow:0px 1px 0px #1a6178;
            }
            .myButton:hover {
                background:linear-gradient(to bottom, #17c5d1 5%, #8ad4ed 100%);
                background-color:#17c5d1;
            }
            .myButton:active {
                position:relative;
                top:1px;
            }
        </style>
    </head>
    <body id="main">
        <%
            String id = (String)session.getAttribute("id");
            String position = (String)session.getAttribute("position");
        %>
        <div class="topbar">
            <nav class="navbar-custom">
                <ul class="list-inline menu-left mb-0">
                    <li class="list-inline-item">
                        <button type="button" class="button-menu-mobile open-left waves-effect">
                            <i class="ion-navicon"></i>
                        </button>
                    </li>
                    <li class="hide-phone list-inline-item app-search">
                        <h3 class="page-title">修改</h3>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </nav>
        </div>
        <div class="page-content-wrapper" style="background-color: #ffffff;margin-left:10px;margin-right:10px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card m-b-30">
                            <div class="card-body">
                                <h4 class="mt-0 header-title" style="margin-left:10px;">修改成绩信息</h4>
                                <div class="row">
                                    <div class="col-lg-6 col-lg-offset-3">
                                        <form id="updatesco" target="_self">
                                            	<input style="display: none;" name="id" class="form-control" type="text" value="${sco.id }">
                                            	<input style="display: none;" name="student_id" class="form-control" type="text" value="${sco.student_id }">
                                            	<input style="display: none;" name="course_id" class="form-control" type="text" value="${sco.course_id }">
                                            	姓名：<input readonly="readonly" class="form-control" type="text" value="${na.get(sco.student_id)}"><br>
                                            	课程名：<input readonly="readonly" class="form-control" type="text" value="${cs.get(sco.course_id)}"><br>
                                            	分数：<input name="score" class="form-control" type="text" value="${sco.score }"><br>
                                            	状态：<input readonly="readonly" name="status" class="form-control" type="text" value="${sco.status }"><br>
                                            <div style="display:flex;justify-content:center;" class="form-group">
                                                <input  class="myButton" value="修改" type="button" onclick="updateSco()">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="../bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script>
        function updateSco() {
            var gnl=confirm("点击更新后，之前的数据信息将被覆盖，是否继续？");
            if (gnl==true) {
                $.ajax({
                    type: "post",
                    url: "http://localhost:8080/kczl/updatesco",
                    data: $('#updatesco').serialize(),
                    success: function (msg) {
                        $("#main").html(msg);
                    },
                    error: function () {
                        alert("wrong");
                    }
                });
            }else{
                return false;
            }
        }
    </script>
</html>
