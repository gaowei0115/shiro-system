<%--
  Created by IntelliJ IDEA.
  User: mmc
  Date: 2017/12/4
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>

<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<!--引用css  -->
<link type="text/css" rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />

<link rel="stylesheet" href="css/commod.css" type="text/css">
<link href="css/css/component.css" rel="stylesheet">
<!-- 引用js -->
<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrap/js/docs.min.js"></script>
<script type="text/javascript" src="common/util.js"></script>

<!--去掉悬浮窗 -->
<style type="text/css">#cl-dashboard{display: none}</style>

<link title="" href="formwork/css/style.css" rel="stylesheet" type="text/css"/>



</head>
<!--245580-->
<body  style="background:url(images/login_bg.jpg) no-repeat;background-size: 100% 100%;">
<div class="container"  style="width: 80%">
    <div class="col-md-offset-4 col-md-4 login_boder" style="border: 0px;">
        <div class="row" style="border: 0px !important;"></div>
        <div><img class="center-block margin-height" alt="" src="images/sys_logo.png" title="logo" width="100" height="100"></div>
        <h4 class="login_h1_font">玩玩登录APP</h4>
        <form id="loginForm" class="form-horizontal" action="login" method="post">

            <div class="form-group">
                <input type="text" class="form-control " id="loginCode" name="loginCode" placeholder="请输入用户名" />
            </div>
            <br>
            <div class="form-group">
                <input type="password" class="form-control " id="password" name="password" placeholder="请输入密码" />
            </div>
            <br>
            <div class="form-group">
                <input type="text" class="form-control " style="width: 40%;display: inline;" placeholder="请输入验证码" />
                <img src="http://login.350.net/captcha?r=0.732156167505309" />
            </div>
            <br>

            <input type="submit" id="login" class="btn btn-primary btn-block input_padding" style="background: #6da3de !important;" value="登录" />

        </form>
    </div>
</div>
</body>

<%--<script type="text/javascript">--%>
<%--//    $(function(){--%>
<%--//        $("#login").click(function () {--%>
<%--//            var loginCode = $("#loginCode").val();--%>
<%--//            var password = $("#password").val();--%>
<%--//            var data = {--%>
<%--//                "loginCode":loginCode,--%>
<%--//                "password":password--%>
<%--//            };--%>
<%--//            $.ajax({--%>
<%--//                url:getRootPath()+'/login',--%>
<%--//                type:"post",--%>
<%--//                dataType:"json",--%>
<%--//                data:JSON.stringify(data),--%>
<%--//                contentType:"application/json",--%>
<%--//                success : function(result) {--%>
<%--//                    if (result.status == '0') {--%>
<%--//                        window.location.href = getRootPath() + result.uri;--%>
<%--//                    } else {--%>
<%--//                        alert(result.message)--%>
<%--//                    }--%>
<%--//                },--%>
<%--//                error : function (result) {--%>
<%--//                    alert("fail!")--%>
<%--//                }--%>
<%--//            });--%>
<%--//        });--%>
<%--//    })--%>

<%--</script>--%>
</html>
