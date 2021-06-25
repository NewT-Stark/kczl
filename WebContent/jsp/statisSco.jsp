<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <title>资料下载</title>
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
            .myButton {
                box-shadow: 0px 10px 14px -6px #1e6873;
                background:linear-gradient(to bottom, #8ad4ed 5%, #17c5d1 100%);
                background-color:#8ad4ed;
                border-radius:5px;
                border:0px;
                width:80px;
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
            .myButton1 {
                box-shadow: 0px 10px 14px -6px #cf3a3a;
                background:linear-gradient(to bottom, #ed7676 5%, #ccafaf 100%);
                background-color:#ed7676;
                border-radius:5px;
                border:0px;
                width:80px;
                height:30px;
                display:inline-block;
                cursor:pointer;
                color:#fcfcfc;
                font-family:Arial;
                font-size:14px;
                font-weight:bold;
                padding:6px 6px;
                text-decoration:none;
                text-shadow:0px 1px 0px #cc425b;
            }
            .myButton1:hover {
                background:linear-gradient(to bottom, #ccafaf 5%, #ed7676 100%);
                background-color:#ccafaf;
            }
            .myButton1:active {
                position:relative;
                top:1px;
            }
            .myButton2 {
                box-shadow: 0px 10px 14px -6px #1e6873;
                background:linear-gradient(to bottom, #8ad4ed 5%, #17c5d1 100%);
                background-color:#8ad4ed;
                border-radius:5px;
                border:0px;
                width:150px;
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
            .myButton2:hover {
                background:linear-gradient(to bottom, #17c5d1 5%, #8ad4ed 100%);
                background-color:#17c5d1;
            }
            .myButton2:active {
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
                        <h3 class="page-title">资料下载</h3>
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
                                <h4 class="mt-0 header-title" style="margin-left:10px;">学生成绩查询</h4>
                                <form id="findSco1" target="_self" style="margin-left:10px;">
                                        	按学期：
                                        <select name="semester" style="margin-left:20px;width:300px; height:30px;">
                                            <c:forEach items="${semes}" var="seme">
		                                        <option value="${seme.semester }">${seme.semester }</option>
	                                        </c:forEach>
                                        </select>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        	按班级：
                                        <select name="clazz" style="margin-left:20px;width:300px; height:30px;">
                                            <c:forEach items="${cla}" var="clazz">
		                                        <option value="${clazz.id }">${gs.get(clazz.id) }</option>
	                                        </c:forEach>
                                        </select>
                                        <button type="button" class="myButton" onclick="findSco2()">查询</button>
                                </form><br>
                                <button type="button" class="myButton2" onclick="failSco()">查询不及格学生名单</button><br><br>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr style="background-color:#3c6382;color:#ffffff;">
                                        <td>学号</td>
                                        <td>姓名</td>
                                        <td>课程名</td>
                                        <td>成绩</td>
                                        <td>状态</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${teasco}" var="sco">
                                    	<c:if test="${sco.status eq '已审核' }">
                                    	<c:if test="${hb.get(sco.course_id) eq id }">
	                                        <tr>
	                                            <td>${sco.student_id}</td>
	                                            <td>${na.get(sco.student_id)}</td>
	                                            <td>${cs.get(sco.course_id)}</td>
	                                            <td>${sco.score}</td>
	                                            <td>${sco.status}</td>
	                                        </tr>
                                        </c:if></c:if>
                                    </c:forEach>
                                </tbody>
                                </table>
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
    	failSco = function() {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/failsco",
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
    	}
        findSco2 = function() {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/findsco",
                data: $('#findSco1').serialize(),
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
    </script>
</html>
