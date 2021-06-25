<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	//获取当前项目的路径，如：http://localhost:8080/项目名称。
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <title>课程资料自主学习系统</title>
        <meta content="Admin Dashboard" name="description" />
        <meta content="ThemeDesign" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <link rel="stylesheet" href="../bootstrap/css/morris.css">

        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../bootstrap/css/icons.css" rel="stylesheet" type="text/css">
        <link href="../bootstrap/css/style.css" rel="stylesheet" type="text/css">
        <link href="../bootstrap/css/styles.css" rel="stylesheet" type="text/css">

    </head>

    <body class="fixed-left">

        <%
        	String id = (String)session.getAttribute("id");
            String name = (String)session.getAttribute("name");
            String position = (String)session.getAttribute("position");
        %>

        <!-- Loader -->
        <div id="preloader">
            <div id="status">
                <div class="spinner"></div>
            </div>
        </div>

        <!-- Begin page -->
        <div id="wrapper">

            <div class="left side-menu">
                <button type="button" class="button-menu-mobile button-menu-mobile-topbar open-left waves-effect">
                    <i class="ion-close"></i>
                </button>

                <!-- LOGO -->
                <div class="topbar-left">
                    <div class="text-center">
                        <a href="" class="logo"><span style="color: #ffffff;">课程资料自主学习系统</span></a>
                    </div>
                </div>

                <div class="sidebar-inner slimscrollleft">

                    <div class="user-details">
                        <div class="text-center">
                            <%
                                if(("管理员").equals(position)){%>
                                    <img style="width:70px;height:63px;" src="../bootstrap/img/img_4.png" alt="" class="rounded-circle">
                            <% }else if(("教师").equals(position)){%>
                                    <img style="width:70px;height:63px;" src="../bootstrap/img/img_5.png" alt="" class="rounded-circle">
                            <% }else if(("学生").equals(position)){%>
                                    <img style="width:70px;height:63px;" src="../bootstrap/img/img_6.png" alt="" class="rounded-circle">
                            <% }%>
                        </div>
                        <div class="user-info">
                            <h4 class="font-16 text-white"><%out.print(position+"-"+name); %></h4><br>
                            <span class="text-white" style="font-size:14px;cursor:pointer;" onclick="back()"><i class="fa fa-fw fa-power-off"></i>退出系统</span>
                        </div>
                    </div>

                    <div id="sidebar-menu">
                        <ul>
                            <li class="font-16">
                                <a href="" class="waves-effect">
                                    <i class="ti-home"></i>
                                    <span> 主页 </span>
                                </a>
                            </li>
							<%
                            	if(("管理员").equals(position)){%>
                            <li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-address-book-o"></i> <span class="font-16"> 教师信息管理 </span> <span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                                <ul class="list-unstyled">
                                	<li><a target="_self" onclick="adm_tea()" style="cursor: pointer"><i class="fa fa-user-o"></i>教师列表</a></li>
                                	<li><a target="_self" onclick="toaddtea()" style="cursor: pointer"><i class="glyphicon glyphicon-plus"></i>新增教师</a></li>
                                </ul>
                            </li>
                            
							<li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-graduation-cap"></i> <span class="font-16"> 学生信息管理 </span> <span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                                <ul class="list-unstyled">
                                	<li><a target="_self" onclick="adm_stu()" style="cursor: pointer"><i class="ti-user"></i>学生列表</a></li>
                                	<li><a target="_self" onclick="toaddstu()" style="cursor: pointer"><i class="glyphicon glyphicon-plus"></i>新增学生</a></li>
                                </ul>
                            </li>
                            
                            <li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-book"></i> <span class="font-16"> 基本信息管理 </span> <span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                                <ul class="list-unstyled">
                                	<li><a target="_self" onclick="adm_coll()" style="cursor: pointer"><i class="fa fa-university"></i>学院列表</a></li>
                                	<li><a target="_self" onclick="adm_pro()" style="cursor: pointer"><i class="fa fa-code"></i>专业列表</a></li>
                                	<li><a target="_self" onclick="adm_cou()" style="cursor: pointer"><i class="fa fa-map"></i>课程列表</a></li>
                                	<li><a target="_self" onclick="adm_gra()" style="cursor: pointer"><i class="fa fa-users"></i>年级列表</a></li>
                                </ul>
                            </li>
                            <%} %>
                            
                            <li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="ti-pencil-alt"></i> <span class="font-16"> 学习资料管理 </span> <span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                                <ul class="list-unstyled">
                                    <%
                                        if(("管理员").equals(position)){%>
                                            <li><a target="_self" onclick="tea_sco()" style="cursor: pointer"><i class="fa fa-arrow-circle-o-up"></i>资料上传与下载</a></li>
                                        <%}
                                        else if(("教师").equals(position)){%>
                                            <li><a target="_self" onclick="tea_sco()" style="cursor: pointer"><i class="fa fa-arrow-circle-o-up"></i>资料上传与下载</a></li>
                                        <%}
                                        else{%>
                                        	<li><a target="_self" onclick="stu_find()" style="cursor: pointer"><i class="fa fa-bar-chart"></i>资料下载</a></li>
                                    	<%}
                                    %>
                                </ul>
                            </li>

                            <li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="fa fa-cog" aria-hidden="true"></i> <span class="font-16"> 账号设置 </span> <span class="pull-right"><i class="mdi mdi-chevron-right"></i></span></a>
                                <ul class="list-unstyled">
                                	<%if(("教师").equals(position)){%>
                                	<li><a target="_self" onclick="info(${id})" style="cursor: pointer"><i class="fa fa-address-card-o"></i>个人信息管理</a></li>
                                	<li><a target="_self" onclick="tea_cour()" style="cursor: pointer"><i class="fa fa-tasks" aria-hidden="true"></i>教授课程查询</a></li>
                                	<%}else if(("学生").equals(position)){ %>
                                	<li><a target="_self" onclick="info(${id})" style="cursor: pointer"><i class="fa fa-address-card-o"></i>个人信息管理</a></li>
                                	<%} %>
                                	<li><a target="_self" onclick="tup(${id})" style="cursor: pointer"><i class="fa fa-key" aria-hidden="true"></i>修改密码</a></li>
                                </ul>
                            </li>

                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <!-- end sidebarinner -->
            </div>
            <!-- Left Sidebar End -->

            <!-- Start right Content here -->

            <div class="content-page">
                <!-- Start content -->
                <div class="content" id="main">
                    <!-- Top Bar Start -->
                    <div class="topbar">

                        <nav class="navbar-custom">

                            <ul class="list-inline menu-left mb-0">
                                <li class="list-inline-item">
                                    <button type="button" class="button-menu-mobile open-left waves-effect">
                                        <i class="ion-navicon"></i>
                                    </button>
                                </li>
                                <li class="hide-phone list-inline-item app-search">
                                    <h3 class="page-title">网站新闻动态</h3>
                                </li>
                            </ul>

                            <div class="clearfix"></div>

                        </nav>

                    </div>
                    <!-- Top Bar End -->

                    <div class="newsCenterPanel">
        
					        <div class="newsCenterPanel_inner">
					
					            <div class="newContentBox odd">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">2</p>
					                        <p class="ym">2021-6</p>
					                    </div>
					                    <div class="newTitle">[Linux]在虚拟机中安装Python环境</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        linux系统中会自带python，可以先查看一下自带的python版本，如果符合要求，就不需要单独安装了，输入指令python，可以看到系统自带的......
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox even">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">24</p>
					                        <p class="ym">2021-5</p>
					                    </div>
					                    <div class="newTitle">[软件设计模式]原型模式的定义与特点</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        	原型（Prototype）模式的定义如下：用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。在这里......
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox odd">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">21</p>
					                        <p class="ym">2021-3</p>
					                    </div>
					                    <div class="newTitle">C++序列容器之 vector常见用法总结</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        vector是一个动态的序列容器，相当于一个size可变的数组。相比于数组，vector会消耗更多的内存以有效的动态增长。而相比于其他动态序......
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox even">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">16</p>
					                        <p class="ym">2021-4</p>
					                    </div>
					                    <div class="newTitle">审稿+写作经验总结：研究生易范的学术论文写作错误与不足</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        	摘要宜以第三人称方式客观描述论文研究什么问题，研究问题的思路方法和结果结论，以及论文主要包括哪些技术内容，突出创新性；且不......
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox odd">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">6</p>
					                        <p class="ym">2021-3</p>
					                    </div>
					                    <div class="newTitle">SqlServer基础之(触发器)</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        	触发器（trigger）是SQL server 提供给程序员和数据分析员来保证数据完整性的一种方法，它是与表事件相关的特殊的存储过程，它的执......
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox even">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">4</p>
					                        <p class="ym">2021-1</p>
					                    </div>
					                    <div class="newTitle">H5页面设计风格大盘点！</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        H5页面发展到现在，已经不像几年前那样稀有了，甚至可以说是遍地都是。只有好的H5页面才能够达到宣传推广的效果。但是设计有限，灵感缺乏该怎么办呢？下面小编来给大家盘点几个H5页面设计风格！
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox odd">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">4</p>
					                        <p class="ym">2020-10</p>
					                    </div>
					                    <div class="newTitle">线性回归：波士顿房价预测</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                       	 在数据量小的时候，往往正规方程的效果是比较好的，本次波士顿数据集有500+个样本，这数量很小，在运行的时候正规方程效果占上风的次数明......
					                    </div>
					                </a>
					
					            </div>
					
					            <div class="newContentBox even">
					                <a hidefocus="true" href="">
					                    <div class="time">
					                        <p class="day">15</p>
					                        <p class="ym">2020-12</p>
					                    </div>
					                    <div class="newTitle">机器学习(七)：PCA主成分分析和案例实现</div>
					
					                    <div class="newTitleIcon"></div>
					                    <div class="border"></div>
					                    <div class="newContent">
					                        	主成分分析(Principal Component Analysis，简称PCA)算法是降维中最常用的一种手段，降维的算法还有很多，比如奇异值分解(SVD)、因子......
					                    </div>
					                </a>
					
					            </div>
					
					        </div>
					    </div>
                    
                        <!-- container -->

                    </div>
                    <!-- Page content Wrapper -->


                </div>
                <!-- content -->

                <footer class="footer">
                    © 2021 课程资料自主学习系统 <i class="fa fa-gg-circle" aria-hidden="true"></i> By 破晓.
                </footer>

            </div>
            <!-- End Right content here -->

        </div>
        <!-- END wrapper -->

        <!-- jQuery  -->
        <script src="../bootstrap/js/jquery.min.js"></script>
        <script src="../bootstrap/js/popper.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script src="../bootstrap/js/modernizr.min.js"></script>
        <script src="../bootstrap/js/detect.js"></script>
        <script src="../bootstrap/js/fastclick.js"></script>
        <script src="../bootstrap/js/jquery.slimscroll.js"></script>
        <script src="../bootstrap/js/jquery.blockUI.js"></script>
        <script src="../bootstrap/js/waves.js"></script>
        <script src="../bootstrap/js/jquery.nicescroll.js"></script>
        <script src="../bootstrap/js/jquery.scrollTo.min.js"></script>

        <script src="../bootstrap/js/morris.min.js"></script>
        <script src="../bootstrap/js/raphael-min.js"></script>

        <script src="../bootstrap/js/dashborad.js"></script>
        <script src="../bootstrap/js/app.js"></script>
        <script>
        	var position = "${position}";
	        function adm_tea() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/admin_tea",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function toaddtea() {
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/toaddtea",
                    success:function(msg){
                        $("#main").html(msg);
                    },
                    error:function(){
                        alert("wrong");
                    }
                });
            }
	        function adm_stu() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/admin_stu",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function toaddstu() {
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/toaddstu",
                    success:function(msg){
                        $("#main").html(msg);
                    },
                    error:function(){
                        alert("wrong");
                    }
                });
            }
	        function adm_coll() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/admin_col",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function adm_pro() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/admin_pro",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function adm_cou() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/admin_cou",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function adm_gra() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/admin_gra",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function tea_sear() {
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
	        function tea_sco() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/teach_sco",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function tea_list() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/tea_list",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("系统功能尚在开发中，请耐心等待下一版本哦~");
	                }
	            });
	        }
	        function tea_cour() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/teach_cou",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function info(id) {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/information?id="+id,
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
	        function stu_find() {
	            $.ajax({
	                type:"post",
	                url:"http://localhost:8080/kczl/stu_sco",
	                success:function(msg){
	                    $("#main").html(msg);
	                },
	                error:function(){
	                    alert("wrong");
	                }
	            });
	        }
            function back() {
                location.href = "http://localhost:8080/kczl";
                return;
            }
            function tup(id) {
                $.ajax({
                    type:"post",
                    url:"http://localhost:8080/kczl/toupdatepwd?id="+id,
                    success:function(msg){
                        $("#main").html(msg);
                    },
                    error:function(){
                        alert("wrong");
                    }
                });
            }
        </script>

    </body>

</html>