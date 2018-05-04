<%@page import="com.asher.oes.Constants"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="com.asher.oes.util.PropertyUtil"%>
<%@page import="com.asher.oes.util.StringUtil"%>
<%@page import="com.asher.oes.util.FormatIdUtil"%>
<%@page import="com.asher.oes.model.Question"%>
<%@page import="java.util.List"%>
<%@ page import="com.asher.oes.model.User"%>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>question detail</title>
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/reset.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/common.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/list.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/createquestion.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/questiondetail.css">
  </head>

  <body onLoad="document.getElementById('Create').focus()">
    <div class="body">
      <!-- Start title  -->
      <block:display name="pageTitleBlock"/>
      <!-- End title  -->
      <!--  Start Body  -->
      <div class="texts" >
        <div class="tab_titel">
          <div class="tab_titels">
            <ul>
            <li class="tab_selected"><a href="">Question Management</a></li>
            <li><a>Exam Management</a></li>
          </ul>
          </div>
        </div>
        <%
          Question question = (Question)session.getAttribute(Constants.CURRENT_QUESTION);
        %>
        <div class="tab_body">
          <div class="bread">
            <ul>
             <li><a href="list">Question Management</a></li>&#160;&#160;&#62;&#160;
             <li><a><%=FormatIdUtil.formatId("E", question.getId(), 6) %></a></li>
            </ul>
          </div>
          <div class="question_form">
            <div class="form_left">
              <div class="items1">Question ID:</div>
              <div class="items2">Question:</div>
              <div class="items3">Answer:</div>
            </div>
            <div class="form_right">
                <div class="detail_id" id="questionID" ><%=FormatIdUtil.formatId("E", question.getId(), 6) %></div>
                <div class="detail_description" id="questionDescription" ><%=HtmlUtils.htmlEscape(question.getDescription()) %></div>
                <div class="detail_option">
                  <span class="datail_option_item <%=question.getAnswer().equals("a") ? "datail_option_right_item" : "" %>">A&nbsp;&nbsp;<%=HtmlUtils.htmlEscape(question.getOptionA()) %></span><br/>
                  <span class="datail_option_item <%=question.getAnswer().equals("b") ? "datail_option_right_item" : "" %>">B&nbsp;&nbsp;<%=HtmlUtils.htmlEscape(question.getOptionB()) %></span><br/>
                  <span class="datail_option_item <%=question.getAnswer().equals("c") ? "datail_option_right_item" : "" %>">C&nbsp;&nbsp;<%=HtmlUtils.htmlEscape(question.getOptionC()) %></span><br/>
                  <span class="datail_option_item <%=question.getAnswer().equals("d") ? "datail_option_right_item" : "" %>">D&nbsp;&nbsp;<%=HtmlUtils.htmlEscape(question.getOptionD()) %></span><br/>
                </div>
                <div class="operation">
                  <input type="button" value="Edit" onclick="editPage(<%=question.getId() %>)" id="Create" />
                  <input type="button" value="Delete" id="deleteSingle"/>
                </div>
            </div>
          </div>
        </div>
      </div>
      <!--  End Body  -->
        <div class="foot" >Copyright © 2017 Augmentum,Inc. All Rights Reserved</div>
    </div>
    <div class="submit_delete_background" id="deleteBackground">
  </div>
  <div class="submit_delete" id="deleteTip">
    <span class="delete_message">Are you sure delete the  selected question?</span>
    <div class="operat_button">
      <input type="button" value="Yes" class="submit_delete_button" id="deleteSubmit" onclick="detailDelete(<%=question.getId()%>)"/>
      <input type="button" value="No" class="submit_delete_button" id="deleteCancel"/>
    </div>
  </div>
  </body>
  <script src="${STATIC_SOURCE_ROOT }js/jquery-1.10.2.min.js"></script>
  <script src="${STATIC_SOURCE_ROOT }js/common.js"></script>
  <script src="${STATIC_SOURCE_ROOT }js/contenthome.js"></script>
</html>