<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <title>资料上传</title>
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
                    <li class="list-inline-item">
                        <button type="button" class="button-menu-mobile open-left waves-effect">
                            <i class="ion-navicon"></i>
                        </button>
                    </li>
                    <li class="hide-phone list-inline-item app-search">
                        <h3 class="page-title">资料上传与下载</h3>
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
                                <h4 class="mt-0 header-title" style="margin-left:10px;">学习资料上传与下载</h4>
                                <form class="form-inline my-2" id="findSco1" target="_self" enctype="multipart/form-data" style="margin-left:10px;">
                                	上传文件：
                                	<div class="form-group">
										<input type="file" name="file1">
									</div>
                                    <button type="button" class="myButton" onclick="upload()">上传</button>
                                </form><br>
                                <table class="table table-striped table-hover">
                                    <thead>
                                    <tr style="background-color:#3c6382;color:#ffffff;">
                                        <td>文件名</td>
                                        <td>操作</td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="me" items="${fileNameMap}">
                                    	<tr>
                                    		<td style="margin-left:0px;">
												<c:url value="/download" var="downurl">
													<c:param name="filename" value="${me.key}"></c:param>
												</c:url>${me.value}
											</td>
											<td>
												<a class="myButton" target="_self" style="width:60px;cursor: pointer" href="${downurl}"><i class="fa fa-pencil-square-o"></i>&nbsp;下载</a>
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
    <script src="../bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script>
        as1 = function() {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/assentsco",
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        toUpdateSco1 = function(id, cid) {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/tosentsco?id="+id+"&cid="+cid,
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        toUpdateSco4 = function(id, cid) {
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/toupdatesco?id="+id+"&cid="+cid,
                success:function(msg){
                    $("#main").html(msg);
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
        upload = function() {
        	var formData = new FormData($('#findSco1')[0]);
            $.ajax({
                type:"post",
                url:"http://localhost:8080/kczl/tea_upload",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success:function(msg){
                    alert("上传成功");
                    $("#main").html(msg);
                    
                },
                error:function(){
                    alert("wrong");
                }
            });
        }
    </script>
</html>
