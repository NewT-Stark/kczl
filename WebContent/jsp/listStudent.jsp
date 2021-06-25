<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <title>浏览学生信息</title>
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
                        <li class="hide-phone list-inline-item app-search">
                            <h3 class="page-title">浏览学生信息</h3>
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
                                    <h4 class="mt-0 header-title" style="margin-left:10px;">学生信息</h4>
                                    <form id="findStu" target="_self" style="margin-left:10px;">
                                        	按学号：<input name="id" style="margin-left:20px;width:300px; height:30px;" type="text" value="">
                                        <button type="button" class="myButton" onclick="findStu()">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a title="导出Excel" style="outline:none;" href="../kczl/downloadexcelStu.do"><i style="font-size:20px;" class="fa fa-file-excel-o" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                        	按班级：
                                        <select name="clazz" style="margin-left:20px;width:300px; height:30px;">
                                            <c:forEach items="${cla}" var="clazz">
		                                        <option value="${clazz.id }">${gs.get(clazz.id) }</option>
	                                        </c:forEach>
                                        </select>
                                        <button type="button" class="myButton" onclick="findCla()">查询</button>
                                    </form><br>
											
                                    <form class="form-inline my-2" target="_self" id="upload" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <input type="file" name="salFile" accept=".xls,.xlsx" required>
                                        </div>

                                        <input class="myButton" type="submit" value="批量导入" onclick="uploadStu()">
                                    </form><br>
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr style="background-color:#3c6382;color:#ffffff;">
                                            <td>学号</td>
                                            <td>姓名</td>
                                            <td>性别</td>
                                            <td>出生日期</td>
                                            <td>班级</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${admStu}" var="student">
                                        <tr>
                                            <td>${student.id}</td>
                                            <td>${student.name}</td>
                                            <td>${student.sex}</td>
                                            <td>${student.birthday}</td>
                                            <td>${gs.get(student.clazz)}</td>
                                            <td>
                                            	<a class="myButton1" style="width:60px;cursor: pointer" target="_self" onclick="deStu(${student.id})"> <i class="fa fa-trash-o fa-lg"></i>&nbsp;删除</a>
                                                <a class="myButton" target="_self" onclick="toUpdateStu(${student.id})" style="width:60px;cursor: pointer"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
                                            </td>
                                        </tr>
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
    <script type="text/javascript" src="../bootstrap/js/jquery-3.3.1.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>

    <script>
        function findStu() {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/findstu",
                data: $('#findStu').serialize(),
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        function findCla() {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/findcla",
                data: $('#findStu').serialize(),
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        function deStu(id) {
            var gnl=confirm("是否删除？");
            if (gnl==true){
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/destu?id="+id,
                    success:function(msg){
                        $("#main").html(msg);
                    },
                    error:function(){
                        alert("wrong");
                    }
                });
            }else{
                return false;
            }
        }
        function toUpdateStu(id) {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/toupstu?id="+id,
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        function uploadStu() {
            var formData = new FormData($('#upload')[0]);
            $.ajax({
                type: 'post',
                url: "http://localhost:8080/kczl/importexcelstu",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success:function(msg){
                    alert("导入成功");
                    $("#main").html(msg);
                },
                error:function(){
                    alert("上传失败");
                }
            });
        }
    </script>
</html>
