<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <title>浏览教师信息</title>
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
                            <h3 class="page-title">浏览教师信息</h3>
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
                                    <h4 class="mt-0 header-title" style="margin-left:10px;">教师信息</h4>
                                    <form id="findtea" target="_self" style="margin-left:10px;">
                                        	工号：<input id="epd" name="id" style="margin-left:20px;width:300px; height:30px;" type="text" value="">
                                        <button type="button" class="myButton" onclick="findTea()">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a title="导出Excel" style="outline:none;" href="../exportExcelServlet"><i style="font-size:20px;" class="fa fa-file-excel-o" aria-hidden="true"></i></a>
                                    </form><br>

                                    <form class="form-inline my-2" target="_self" id="upload" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <input type="file" name="filePath" accept=".xls,.xlsx" required>
                                        </div>

                                        <input class="myButton" type="submit" value="批量导入" onclick="uploadTea()">
                                    </form><br>
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr style="background-color:#3c6382;color:#ffffff;">
                                            <th>教师编号</th>
                                            <th>姓名</th>
                                            <th>性别</th>
                                            <th>联系方式</th>
                                            <th>职称</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${admTea}" var="tea">
                                        <tr>
                                            <td>${tea.id}</td>
                                            <td>${tea.name}</td>
                                            <td>${tea.sex}</td>
                                            <td>${tea.contact}</td>
                                            <td>${tea.title}</td>
                                            <td>
                                            	<a class="myButton1" style="width:60px;cursor: pointer" target="_self" onclick="deTea(${tea.id})"> <i class="fa fa-trash-o fa-lg"></i>&nbsp;删除</a>
                                                <a class="myButton" target="_self" onclick="toUpdateTea(${tea.id})" style="width:60px;cursor: pointer"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
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
        function deTea(id) {
            var gnl=confirm("是否删除？");
            if (gnl==true){
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/detea?id="+id,
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
        function toUpdateTea(id) {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/touptea?id="+id,
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        function findTea() {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/findtea",
                data: $('#findtea').serialize(),
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        function uploadTea() {
            var formData = new FormData($('#upload')[0]);
            $.ajax({
                type: 'post',
                url: "http://localhost:8080/kczl/readExcelFileServlet",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("上传失败");
                }
            });
        }
    </script>
</html>
