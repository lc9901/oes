<%@page import="com.asher.oes.util.PropertyUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.asher.oes.Constants;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticSource() %>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticSource() %>css/login.css">
  </head>

  <body class="no_select" onkeydown="keyDown();">
    <%
      Map<String,String> errorTip = (Map<String,String>)session.getAttribute(Constants.ERROR_TIP);
      if (errorTip == null) {
           errorTip = new HashMap<String, String>();
      }
    %>
    <div class="root">
      <p class="welcome">Welcome to login!</p>
      <div class="login_up">
          <img alt="logo" src="<%=PropertyUtil.getStaticSource() %>images/LOGO_Web_Login.png" class="login_logo" />
          <span class="logo_down">Online Exam Web System</span>
          <div class="login_form">
           <%
               String tip = (String)session.getAttribute(Constants.TIP);
               if (tip != null) {
                   out.print("<span class='tip_user' >" + tip + "</span>");
                   session.removeAttribute(Constants.TIP);
               }
           %>
            <span class="tip_user" id="tipUser"></span>
            <form action="login" method="POST" id="loginForm">
              <img alt="ico" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Usename_20x20.png" class="login_ioc_use position" />
              <input type="text" name="userName" id="userName" class="login_input_user login_input " placeholder="Username" />
                <img alt="password is required!" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Client_Login_Wrong.png" id="errorUserName" class="erroTip" /> 
              <br/>
              <img alt="ico" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Password_20x15.png" class="login_ioc_pass position" />
              <input type="password" name="password" id="password" class="login_input_pass login_input" placeholder="Password" />
                <img alt="password is required!" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Client_Login_Wrong.png" id="errorPassword" class="erroTip" />
              <br/>
              <input id="loginbtn" type="button" value="login" class="login_input submit" /><br/>
              <div class="login_input lab_check check fontmode ">
                <label ><input class="check" type="checkbox" /><span>&nbsp;&nbsp;&nbsp;Remember me</span></label>
              </div>
              <input type="hidden" name="go" value="<%=request.getParameter("go") %>" id="go"/>
              <input type="hidden" name="queryString" id="queryString"/>
              <input type="hidden" value="<%=request.getContextPath() %>" id="root"/>
              <a href="" class="forget fontmode ">Forgot password?</a>
            </form>
          </div>
      </div>
      <div class="login_foot">
        <hr />
        <span class="login_foot_con">Copyright © 2017 Augmentum,Inc. All Rights Reserved</span>
      </div>
    </div>
  </body>
  <script type="text/javascript" src="<%=PropertyUtil.getStaticSource() %>js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="<%=PropertyUtil.getStaticSource() %>js/login.js"></script>
  <script type="text/javascript">
  function keyDown()
  {
      if(window.event.keyCode == 13){
          if (document.all('loginbtn')!=null){
           document.all('loginbtn').click();
           }
         }
  }
  var queryString = location.hash;
  document.getElementById("queryString").value = queryString;
  </script>
</html>