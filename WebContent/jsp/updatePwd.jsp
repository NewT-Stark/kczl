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
        <title>修改密码</title>
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
            .pass_progress {
                margin-top: 10px;
                background-color: #efefef;
                height: 10px;
                border-radius: 5px;
            }
            .pass_inner_progress {
                display: block;
                height: 100%;
                background-color: transparent;
                border-radius: 5px;
                width: 100%;
            }
            .error {
                background-color: #ff3300;
            }
            .middle {
                background-color: gold;
            }
            .strong {
                background-color: green;
            }
        </style>
    </head>
    <body id="main">
        <%
            String id = (String)session.getAttribute("id");
        	String pwd = (String)session.getAttribute("pwd");
            String position = (String)session.getAttribute("position");
            session.setAttribute("position",position);
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
                        <h3 class="page-title">修改密码</h3>
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
                                <h4 class="mt-0 header-title" style="margin-left:10px;">新旧密码</h4>
                                <div class="row">
                                    <div class="col-lg-6 col-lg-offset-3">
                                        <form target="_self">
                                            	旧密码：<input id="pwd" name="password" class="form-control" type="password" value="" onkeyup="checkpassword1();">
                                            <span id="tishi1"></span></input><br>
                                            	新密码：<input id="pwd1" name="password" class="form-control" type="password" value="">
                                            <div class="pass_progress">
                                                <span class="pass_inner_progress"></span>
                                            </div>
                                            	确认新密码：<input id="pwd2" name="password" class="form-control" type="password" onkeyup="checkpassword();" value="">
                                            <span id="tishi"></span></input>
                                            <br>
                                            	<div style="display:flex;justify-content:center;" class="form-group">
                                                <input  class="myButton" value="确认" type="button" onclick="updatePwd(${id});">
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
    	checkpassword1 = function() {
        	var password = ${obj.password};
            var repassword = document.getElementById("pwd").value;

            if(password == repassword) {
                document.getElementById("tishi1").innerHTML="<br><i style='color:green' class='fa fa-check-circle'></i><font color='green'>旧密码正确</font>";
            }else {
                document.getElementById("tishi1").innerHTML="<br><i style='color:red' class='fa fa-times-circle'></i><font color='red'>旧密码错误!</font>";
            }
        }
        $('#pwd1').keyup(function(e) {
            var ele_pass = document.getElementsByTagName("input")[1];
            var ele_progress = document.getElementsByClassName("pass_inner_progress")[0];
            var regxs = [];
            regxs.push(/[^a-zA-Z0-9_]/g);
            regxs.push(/[a-zA-Z]/g);
            regxs.push(/[0-9]/g);

            ele_pass.onkeyup = function () {
                var val = this.value;
                var len = val.length;
                var sec = 0;
                if (len >= 6) { // 至少六个字符
                    for (var i = 0; i < regxs.length; i++) {
                        if (val.match(regxs[i])) {
                            sec++;
                        }
                    }
                }
                var result = (sec / regxs.length) * 100;
                ele_progress.style.width = result + "%";
                if(result > 0 && result <= 50){
                    ele_progress.setAttribute("class","pass_inner_progress" + " error");
                }else if (result > 50 && result < 100) {
                    ele_progress.setAttribute("class","pass_inner_progress" + " middle");
                } else if (result == 100) {
                    ele_progress.setAttribute("class","pass_inner_progress" + " strong");
                }

            }
        });
        checkpassword = function() {
            var password = document.getElementById("pwd1").value;
            var repassword = document.getElementById("pwd2").value;

            if(password == repassword) {
                document.getElementById("tishi").innerHTML="<br><i style='color:green' class='fa fa-check-circle'></i><font color='green'>两次密码输入一致</font>";

            }else {
                document.getElementById("tishi").innerHTML="<br><i style='color:red' class='fa fa-times-circle'></i><font color='red'>两次输入密码不一致!</font>";
            }
        }

        updatePwd = function(id) {
            var gnl=confirm("确认修改密码？");
            if (gnl==true) {
                $.ajax({
                    type: "post",
                    url: "http://localhost:8080/kczl/updatepwd?id="+id,
                    data: $('#pwd1').serialize(),
                    success: function (msg) {
                        $("#main").html(msg);
                    },
                    error: function () {
                        alert("wrong");
                    }
                });
                back();
            }else{
                return false;
            }
        }
    </script>
</html>
