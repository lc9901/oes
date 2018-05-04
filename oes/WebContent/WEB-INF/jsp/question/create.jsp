<%@page import="com.asher.oes.util.PropertyUtil"%>
<%@page import="com.asher.oes.Constants"%>
<%@ page import="com.asher.oes.model.User"%>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>create question</title>
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/reset.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/common.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/list.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/createquestion.css">
    <script type="text/javascript" src="${STATIC_SOURCE_ROOT }js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${STATIC_SOURCE_ROOT }js/common.js"></script>
    <script type="text/javascript" src="${STATIC_SOURCE_ROOT }js/questioncreate.js"></script>
  </head>

  <body onkeydown="keyDown();">
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
             <li><a>Create Question</a></li>
            </ul>
          </div>
          <div class="question_form">
            <div class="form_left">
              <div class="items1">Question ID:</div>
              <div class="items2">Question:</div>
              <div class="items3">Answer:</div>
            </div>
            <div class="form_right">
              <form action="createsubmit" id="submitQuestion" method="POST">
                <input type="text" readonly="readonly" class="form_id" id="questionID" name="questionID" value="${DraftQuestionID}"/><br />
                <textarea rows="7" cols="71" class="form_description" id="questionDescription" name="questionDescription"></textarea>
                <div class="form_option">
                  <div class="opt_detail">
                    <div class="option" >
                      <img alt="A" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_Unselected_16x16.png" onclick="clickRadio('a', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_a"/>
                      &#160;A&#160;<input type="text" id="opt_detail_a" name="optDetailA" placeholder="Please input the answer"/></div>
                    <div class="option" >
                      <img alt="B" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_Unselected_16x16.png" onclick="clickRadio('b', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_b"/>
                      &#160;B&#160;<input type="text" id="opt_detail_b" name="optDetailB" placeholder="Please input the answer"/></div>
                    <div class="option" >
                      <img alt="C" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_Unselected_16x16.png" onclick="clickRadio('c', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_c"/>
                      &#160;C&#160;<input type="text" id="opt_detail_c" name="optDetailC" placeholder="Please input the answer"/></div>
                    <div class="option" >
                      <img alt="D" src="${STATIC_SOURCE_ROOT }images/BTN_Radio_Unselected_16x16.png" onclick="clickRadio('d', '<%=PropertyUtil.getStaticSource()%>')" id="opt_radio_d"/>
                      &#160;D&#160;<input type="text" id="opt_detail_d" name="optDetailD" placeholder="Please input the answer"/></div>
                  </div>
                  <input type="hidden" id="answer" name="answer" value="4" />
                </div>
                <div class="operation">
                  <input type="button" value="Create" onclick="creatQuestion()" id="Create" />
                  <input type="button" value="Cancel" onclick="goBack()"/>
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
  <script type="text/javascript">
    function keyDown()
    {
        if(window.event.keyCode == 13){
            if (document.all('Create')!=null){
               document.all('Create').click();
            }
        }
    }
  </script>
</html>