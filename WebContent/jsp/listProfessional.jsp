<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <title>查看专业信息</title>
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
                        <h3 class="page-title">浏览专业信息</h3>
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
                                <h4 class="mt-0 header-title" style="margin-left:10px;">专业信息</h4>
                                <div class="row">
                                    <div class="col-lg-6 col-lg-offset-3">
                                        <table id="table1" class="table table-striped table-hover">
                                            <thead>
                                            <tr style="background-color:#3c6382;color:#ffffff;">
                                                <th>专业编号</th>
                                                <th>专业名称</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${admpro}" var="pro">
                                                <tr>
                                                    <td>${pro.id}</td>
                                                    <td>${pro.name}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <div style="display:flex;justify-content:center;" class="form-group">
                                            <button id="s1" type="button" class="myButton" onclick="view2()">修改</button>
                                        </div>

                                        <input id="a1" style="display:none;background-color:#8ad4ed;" class="col-lg-6" type="button" value="+" onclick="ar2()" />
                                        <input id="d1" style="display:none;background-color:#ed7676;" class="col-lg-6" type="button" value="-" onclick="del2()" />
                                        <br><br>
                                        <div style="display:flex;justify-content:center;" class="form-group">
                                            <button id="s2" style="display:none;" type="button" class="myButton1" onclick="insertPro()">确认</button>
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
        function tdclick2(){
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
                    tdNode.click(tdclick2);
                }
            });
            td.append(input);
            var inputdom= input.get(0);
            inputdom.select();
            td.unbind("click");
        }
        function view2(){
            var a1=document.getElementById("a1");
            var d1=document.getElementById("d1");
            var s1=document.getElementById("s1");
            var s2=document.getElementById("s2");
            if(a1.style.display=="none" && d1.style.display=="none"){
                a1.style.display="";
                d1.style.display="";
                s1.style.display="none";
                s2.style.display="";
                $(document).ready(function(){
                    var tds =$("td");
                    tds.click(tdclick2);
                });
            }
        }
        function ar2() {
            var tr="<tr><td name='id'><input type='text' /></td><td name='name'></td></tr>";
            $("#table1").append(tr);
            $("td").click(tdclick2);
        }

        function del2(){
            $("table tr:not(:first):not(:first):last").remove();
        }

        function insertPro() {
            var gnl=confirm("确认修改？");
            if (gnl==true) {
                let tds = document.getElementsByTagName("td");
                let col1 = new Array();
                let col2 = new Array();
                for(let i=0;i<tds.length;i+=2) {
                    let col1_value=tds[i].innerHTML;
                    let col2_value=tds[i+1].innerHTML;
                    col1[Math.floor(i/2)]=col1_value;
                    col2[Math.floor(i/2)]=col2_value;
                }
                let arrData = {"col1":col1,"col2":col2};
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/insertpro",
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
