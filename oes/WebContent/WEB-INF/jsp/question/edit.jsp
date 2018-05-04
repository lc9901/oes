<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.asher.oes.Constants"%>
<%@ page import="com.asher.oes.util.PropertyUtil"%>
<%@ page import="com.asher.oes.util.FormatIdUtil"%>
<%@ page import="com.asher.oes.model.Question"%>
<%@ page import="com.asher.oes.model.User"%>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block"%> 
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>edit question</title>
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/reset.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/common.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/list.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/createquestion.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticSource() %>js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${STATIC_SOURCE_ROOT }js/questioncreate.js"></script>
    <script src="${STATIC_SOURCE_ROOT }js/common.js"></script>
  </head>

  <body onLoad="document.getElementById('Create').focus()">
  <%
  Question question = (Question)session.getAttribute(Constants.CURRENT_QUESTION);
  %>
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
        <div class="tab_body">
          <div class="bread">
            <ul>
             <li><a href="list">Question Management</a></li>&#160;&#160;&#62;&#160;
             <li><a>Create Question</a></li>&#160;&#160;&#62;&#160;
             <li><a>Edit Question</a></li>
            </ul>
          </div>
          <div class="question_form">
            <div class="form_left">
              <div class="items1">Question ID:</div>
              <div class="items2">Question:</div>
              <div class="items3">Answer:</div>
            </div>
            <div class="form_right">
              <form action="edit" id="submitQuestion" method="POST">
                <input type="text" readonly="readonly" class="form_id" id="questionID" name="questionCurrentID" value="<%=FormatIdUtil.formatId("E", question.getId(), 6) %>"/><br />
                <textarea rows="7" cols="71" class="form_description" id="questionDescription" name="questionDescription" ><%=question.getDescription()%></textarea>
                <div class="form_option">
                  <div class="opt_detail">
                    <div class="option" >
                      <img alt="A" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_<%=question.getAnswer().equals("a") ? "S":"Uns" %>elected_16x16.png" onclick="clickRadio('a', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_a"/>
                      &#160;A&#160;<input type="text" id="opt_detail_a" name="optDetailA" value="<%=question.getOptionA() %>" class="<%= question.getAnswer().equals("a") ? "answer_back":"" %>"/>
                    </div>
                    <div class="option" >
                      <img alt="B" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_<%=question.getAnswer().equals("b") ? "S":"Uns" %>elected_16x16.png" onclick="clickRadio('b', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_b"/>
                      &#160;B&#160;<input type="text" id="opt_detail_b" name="optDetailB" value="<%=question.getOptionB() %>" class="<%= question.getAnswer().equals("b") ? "answer_back":"" %>"/>
                    </div>
                    <div class="option" >
                      <img alt="C" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_<%=question.getAnswer().equals("c") ? "S":"Uns" %>elected_16x16.png" onclick="clickRadio('c', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_c"/>
                      &#160;C&#160;<input type="text" id="opt_detail_c" name="optDetailC" value="<%=question.getOptionC() %>" class="<%= question.getAnswer().equals("c") ? "answer_back":"" %>"/>
                    </div>
                    <div class="option" >
                      <img alt="D" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_<%=question.getAnswer().equals("d") ? "S":"Uns" %>elected_16x16.png" onclick="clickRadio('d', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_d"/>
                      &#160;D&#160;<input type="text" id="opt_detail_d" name="optDetailD" value="<%=question.getOptionD() %>" class="<%= question.getAnswer().equals("d") ? "answer_back":"" %>"/>
                    </div>
                    <input type="hidden" id="answer" name="answer" value="<%=question.getAnswer() %>" />
                  </div>
                </div>
                <div class="operation">
                  <input type="button" value="Save" onclick="saveQuestion()" id="Create" />
                  <input type="button" value="Cancel" onclick="goBackDetail(<%=question.getId()%>)"/>
                </div>
              </form>
            </div>
          </div>
          <div class="tip_massage">
            <span class="tip_massage_detail" id="tip_massage_detail_1">Description is required!</span><br />
            <span class="tip_massage_detail" id="tip_massage_detail_2">All of options are required!</span><br />
            <span class="tip_massage_detail" id="tip_massage_detail_3">There is no right answer!</span>
          </div>
        </div>
      </div>
      <!--  End Body  -->
        <div class="foot" >Copyright © 2017 Augmentum,Inc. All Rights Reserved</div>
    </div>
  </body>
</html>