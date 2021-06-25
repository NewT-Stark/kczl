<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <title>新增学生</title>
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
                        <h3 class="page-title">新增学生</h3>
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
                                <h4 class="mt-0 header-title" style="margin-left:10px;">新增学生信息</h4>
                                <div class="row">
                                    <div class="col-lg-6 col-lg-offset-3">
                                        <form id="addstu" target="_self">
                                            	姓名：<input name="name" class="form-control" type="text" value=""><br>
                                            <input name="password" style="display: none;" class="form-control" type="password" value="123">
                                           	 性别：<br>
                                            <div class="RadioStyle">
                                                <div class="Block PaddingL">
                                                    <input id="sex1" name="sex" type="radio" value="男" />&nbsp;&nbsp;
                                                    <label for="sex1">男</label>&nbsp;&nbsp;
                                                    <input id="sex2" name="sex" type="radio" value="女" />
                                                    <label for="sex2">女</label>
                                                </div>
                                            </div><br><br>
                                            	所属班级：<br>
                                            	<select name="clazz" class="form-control">
		                                            <c:forEach items="${cla}" var="clazz">
				                                        <option value="${clazz.id }">${gs.get(clazz.id)}</option>
			                                        </c:forEach>
		                                        </select><br>
                                            	出生日期：<br>
                                        			<select name="year" id="year0" style="width:165px; height:30px;">
                                        				<option value="">选择 年</option>
                                        				<option value="1995">1995</option>
                                        				<option value="1996">1996</option>
                                        				<option value="1997">1997</option>
                                        				<option value="1998">1998</option>
                                        				<option value="1999">1999</option>
                                        				<option value="2000">2000</option>
                                        				<option value="2001">2001</option>
                                        				<option value="2002">2002</option>
                                        				<option value="2003">2003</option>
                                        				<option value="2004">2004</option>
                                        				<option value="2005">2005</option>
                                        				<option value="2006">2006</option>
                                        				<option value="2007">2007</option>
                                        				<option value="2008">2008</option>
                                        				<option value="2009">2009</option>
                                        				<option value="2010">2010</option>
                                        				<option value="2011">2011</option>
                                        				<option value="2012">2012</option>
                                        				<option value="2013">2013</option>
                                        				<option value="2014">2014</option>
                                        				<option value="2015">2015</option>
                                        				<option value="2016">2016</option>
														<option value="2017">2017</option>
														<option value="2018">2018</option>
														<option value="2019">2019</option>
														<option value="2020">2020</option>
														<option value="2021">2021</option>
													</select>年
												  	<select name="month" id="month0" style="margin-left:10px;width:165px; height:30px;">
												  		<option value="">选择 月</option>
												  		<option value="01">1</option>
														<option value="02">2</option>
														<option value="03">3</option>
														<option value="04">4</option>
														<option value="05">5</option>
														<option value="06">6</option>
														<option value="07">7</option>
														<option value="08">8</option>
														<option value="09">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>月
												  	<select name="day" id="days0" style="margin-left:10px;width:165px; height:30px;">
												  		<option value="" selected>选择 日</option>
												  	</select>日
                                        </form><br>
											<div style="display:flex;justify-content:center;" class="form-group">
                                                <input  class="myButton" value="添加" type="button" onclick="addStu()">
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
        function addStu() {
            var gnl=confirm("确定要添加?");
            if (gnl==true){
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/addstu",
                    data: $('#addstu').serialize(),
                    success:function(msg){
                        $("#main").html(msg);
                    },
                    error:function(){
                        alert("wrong");
                    }
                });
                return true;
            }else{
                return false;
            }

        }
        
        $(document).ready(function(){
    		var str=""; 
    		for(var i=1;i<32;i++){
    				str+="<option value=" + i + "> " + i + "</option>";
    			}
    			$(str).appendTo("#days0");
    	$("#month0").change(function(){
    		var yearstr=$("#year0 option:selected").val();
    		var monthstr=$("#month0 option:selected").text();
    		var str="";
    	
    		if(monthstr=='1' || monthstr=='3' || monthstr=='5' || monthstr=='7' || monthstr=='8' || monthstr=='10' || monthstr=='12'){
    			$("#days0").empty();
    			for(var i=1;i<32;i++){
    				str+="<option value=" + i + "> " + i + "</option>";
    			}
    			$(str).appendTo("#days0");
    		}else if(monthstr==2){
    			$("#days0").empty();
    			if(yearstr%100!=0 && yearstr%4==0 || yearstr%100==0 && yearstr%400==0){
    				for(var i=1;i<30;i++){
    					str+="<option value=" + i + "> " + i + "</option>";
    				}
    			}else{
    				for(var i=1;i<29;i++){
    					str+="<option value=" + i + "> " + i + "</option>";
    				}
    			}
    			$(str).appendTo("#days0");
    		}else{
    			$("#days0").empty();
    			for(var i=1;i<31;i++){
    				str+="<option value=" + i + "> " + i + "</option>";
    			}
    			$(str).appendTo("#days0");
    			}
    });
    $("#year0").change(function(){
    		var yearstr=$("#year0 option:selected").val();
    		var monthstr=$("#month0 option:selected").text();
    		var str="";
    		 if(monthstr==2){
    			$("#days0").empty();
    			if(yearstr%100!=0 && yearstr%4==0 || yearstr%100==0 && yearstr%400==0){
    				for(var i=1;i<30;i++){
    					str+="<option value=" + i + "> " + i + "</option>";
    				}
    			}else{
    				for(var i=1;i<29;i++){
    					str+="<option value=" + i + "> " + i + "</option>";
    				}
    			}
    			$(str).appendTo("#days0");
    		}
    	});
    });
    </script>
</html>
