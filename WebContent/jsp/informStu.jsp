<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
        <title>查看个人信息</title>
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
			table {
				position: relative;
				width: 100%;
				display: block;
			}  
			thead {
				float: left;
			} 
			tbody tr {
				display: inline-block;
			}
			th {
				width: 150px;
				height: 80px;
				display: block;
				text-align:center;
				vertical-align:center;
				font-size: 17px;
			}
			td {
				width: 400px;
				height: 80px;
				display: block;
				text-align:center;
				vertical-align:center;
				font-size: 17px;
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
            .myButton1 {
                box-shadow: 0px 10px 14px -6px #cf3a3a;
                background:linear-gradient(to bottom, #ed7676 5%, #ccafaf 100%);
                background-color:#ed7676;
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
                        <h3 class="page-title">浏览个人信息</h3>
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
                                <h4 class="mt-0 header-title" style="margin-left:10px;">个人信息</h4>
                                <div class="row">
                                    <div class="col-lg-6 col-lg-offset-3">
                                        <table id="table1" class="table table-striped table-hover">
                                            <thead>
	                                            <tr style="background-color:#3c6382;color:#ffffff;">
	                                                <th>姓名</th>
	                                                <th>性别</th>
	                                                <th>出生日期</th>
	                                                <th>所属班级</th>
	                                            </tr>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                    <td>${obj.name}</td>
                                                    <td>${obj.sex}</td>
                                                    <td>${obj.birthday}</td>
                                                    <td>${gs.get(obj.clazz)}</td>
                                                </tr>
                                            </tbody>
                                        </table><br>
                                        <div style="display:flex;justify-content:center;" class="form-group">
                                            <button id="s1" type="button" class="myButton" onclick="view6()">修改</button>
                                        </div>

                                        <input id="a1" style="display:none;background-color:#8ad4ed;" class="col-lg-6" type="button" value="+" />
                                        <input id="d1" style="display:none;background-color:#ed7676;" class="col-lg-6" type="button" value="-" />
                                        <input id="s3" style="display:none;color:#ff0000;width:100%;border:0px;" type="text" value="注：请同学们牢记班级编号，修改时请键入班级编号！"/>
                                        <br><br>
                                        <div style="display:flex;justify-content:center;" class="form-group">
                                            <button id="s2" style="display:none;" type="button" class="myButton1" onclick="insertInfoStu(${id})">确认</button>
                                        </div>
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
        function tdclick6(){
            var clickfunction = this;
            var td =$(this);
            var text =$(this).text();
            td.html("");
            var input =$("<input>");
            input.attr("value",text);
            input.keyup(function(event){
                var myEvent= event || window.event;
                var kcode =myEvent.keyCode;
                if(kcode == 13){
                    var inputnode = $(this);
                    var inputext= inputnode.val();
                    var tdNode =inputnode.parent();
                    tdNode.html(inputext);
                    tdNode.click(tdclick6);
                }
            });
            td.append(input);
            var inputdom= input.get(0);
            inputdom.select();
            td.unbind("click");
        }
        function view6(){
            var a1=document.getElementById("a1");
            var d1=document.getElementById("d1");
            var s1=document.getElementById("s1");
            var s2=document.getElementById("s2");
            var s3=document.getElementById("s3");
            if(a1.style.display=="none" && d1.style.display=="none"){
                s1.style.display="none";
                s2.style.display="";
                s3.style.display="";
                $(document).ready(function(){
                    var tds =$("td");
                    tds.click(tdclick6);
                });
            }
        }

        function insertInfoStu(id) {
            var gnl=confirm("确认修改？");
            if (gnl==true) {
                let tds = document.getElementsByTagName("td");
                let col1 = new Array();
                let col2 = new Array();
                let col3 = new Array();
                let col4 = new Array();
                for(let i=0;i<tds.length;i+=4) {
                    let col1_value=tds[i].innerHTML;
                    let col2_value=tds[i+1].innerHTML;
                    let col3_value=tds[i+2].innerHTML;
                    let col4_value=tds[i+3].innerHTML;
                    col1[Math.floor(i/4)]=col1_value;
                    col2[Math.floor(i/4)]=col2_value;
                    col3[Math.floor(i/4)]=col3_value;
                    col4[Math.floor(i/4)]=col4_value;
                }
                let arrData = {"col1":col1,"col2":col2,"col3":col3,"col4":col4};
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/insertinfostu?id="+id,
                    data: JSON.stringify(arrData),
                    contentType : "application/json;charset=utf-8",
                    //dataType:"json",
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
    </script>
</html>