<%@ taglib uri="/WEB-INF/block.tld" prefix="block"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.asher.oes.util.PropertyUtil"%>
<%@ taglib uri="/WEB-INF/block.tld" prefix="block"%> 
<!DOCTYPE html >
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>welcome</title>
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/reset.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/common.css">
    <link rel="stylesheet" type="text/css" href="${STATIC_SOURCE_ROOT }css/list.css">
  </head>

  <body onkeydown="keyDown();">
    <block:display name="flashMassageBlock"/>
    <div class="submit_delete_background" id="deleteBackground">
    </div>
    <div class="submit_delete" id="deleteTip">
      <img class="close_button" id="close_button" alt="" src="${STATIC_SOURCE_ROOT }images/BTN_Close_16x16.png.png"/>
      <span class="delete_message">Are you sure delete the  selected question?</span>
      <div class="operat_button">
        <input type="button" value="Yes" class="submit_delete_button" id="deleteSubmit"/>
        <input type="button" value="No" class="submit_delete_button" id="deleteCancel"/>
      </div>
  </div>
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
          <div class="menu">
            <ul class="menu_list">
              <li class="menu_selected"><a href="#">Question List</a></li>
              <li><a href="create">Create Question</a></li>
            </ul>
          </div>
          <div class="tab_detail">
            <img class="tab_submit" src="${STATIC_SOURCE_ROOT }images/ICN_Search_15x20.png" id="searchBtu" />
            <input class="tab_search" type="text" placeholder="Please input keyword" value="${PAGINATION.getSearch()}" name="search" id="search" />
            <div class="tab_sheet">
              <block:display name="questionListBlock"/>
              <block:display name="paginationBlock"/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--  End Body  -->
    <div class="foot" >Copyright © 2017 Augmentum,Inc. All Rights Reserved</div>
  </body>
  <script src="${STATIC_SOURCE_ROOT }js/jquery-1.10.2.min.js"></script>
  <script src="${STATIC_SOURCE_ROOT }js/common.js"></script>
  <script src="${STATIC_SOURCE_ROOT }js/contenthome.js"></script>
  <script type="text/javascript">
  function keyDown()
  {
      if(window.event.keyCode == 13){
          var search = document.getElementById("search");
          if (search.value){
           document.all('searchBtu').click();
           }
      }
  }
  </script>
</html>