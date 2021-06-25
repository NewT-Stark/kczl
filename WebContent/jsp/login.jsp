<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>课程资料自主学习系统</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="robots" content="all,follow">
        <link rel="stylesheet" href="https://ajax.aspnetcdn.com/ajax/bootstrap/4.2.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="../bootstrap/css/style.default.css" id="theme-stylesheet">
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
                background: url(../../bootstrap/img/img_2.svg) no-repeat right bottom;
                background-size: 21px 21px;
                color: #00a4ff
            }
        </style>
    </head>
    <%
        String error = (String) session.getAttribute("error");
        if(error != null){
    %>
    <script type = "text/javascript">
        alert("用户信息输入错误！请重新输入");
    </script>
    <%
            session.invalidate();
        }
    %>
    <body>
        <div class="page login-page" style="font-size:16px;">
            <div class="container d-flex align-items-center">
                <div class="form-holder">
                    <div class="row">
                        <!-- Logo & Information Panel-->
                        <div style="height:200px;margin-left:300px;" class="col-lg-6">
                            <div class="info d-flex align-items-start">
                                <div class="content">
                                    <div class="logo">
                                        <h1>欢迎登录</h1>
                                    </div>
                                    <p>课程资料自主学习系统</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div style="height:400px;margin-left:300px;" class="col-lg-6 bg-white">
                            <div class="form d-flex align-items-start">
                                <div class="content">
                                    <form method="post" action="../admin_login" class="form-validate" id="loginFrom" onsubmit="check_login()">
                                        <div class="form-group">
                                            <input id="id" type="text" name="id" required data-msg="请输入账号" placeholder="账号" value="" class="input-material">
                                        </div>
                                        <div class="form-group">
                                            <input id="pwd" type="password" name="pwd" required data-msg="请输入密码" placeholder="密码" class="input-material">
                                        </div>
                                        <div class="RadioStyle">
                                            <div class="Block PaddingL">
                                                <input id="position1" name="position" type="radio" value="管理员" checked="checked" />&nbsp;&nbsp;
                                                <label for="position1">管理员登录</label>&nbsp;&nbsp;
                                                <input id="position2" name="position" type="radio" value="教师" />&nbsp;&nbsp;
                                                <label for="position2">教师登录</label>&nbsp;&nbsp;
                                                <input id="position3" name="position" type="radio" value="学生" />
                                                <label for="position3">学生登录</label>
                                            </div>
                                        </div><br><br>

                                        <button id="login" type="submit" class="btn btn-primary">登录</button>
                                        <div style="margin-top: -40px;">
                                            <div class="custom-control custom-checkbox " style="float: right;">
                                                <input type="checkbox" class="custom-control-input" id="check2" >
                                                <label class="custom-control-label" for="check2">自动登录</label>
                                            </div>
                                            <div class="custom-control custom-checkbox " style="float: right;">
                                                <input type="checkbox" class="custom-control-input" id="check1" >
                                                <label class="custom-control-label" for="check1">记住密码&nbsp;&nbsp;</label>
                                            </div>
                                        </div>
                                    </form>
                                    <br />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- JavaScript files-->
        <script src="https://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script src="../bootstrap/js/jquery.validate.min.js"></script><!--表单验证-->
        <script src="../bootstrap/js/front.js"></script>
        <script>
            function check_login() {
                var id = document.getElementById("id");
                var pwd = document.getElementById("pwd");
                var position = document.getElementById("position");
                if(position.value == "-1") {
                    window.alert("请选择登录身份，不得为空！");
                    return false;
                }else {
                    if(id.value == "" || pwd.value == "") {
                        window.alert("登录ID、登录密码都不能为空！");
                        return false;
                    }
                    return true;
                }
            }

        </script>
    </body>
</html>