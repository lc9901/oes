<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.asher.oes.Constants"%>
<%@page import="com.asher.oes.util.PropertyUtil"%>
<%@page import="com.asher.oes.util.FormatIdUtil"%>
<%@page import="com.asher.oes.model.Question"%>
<%@page import="com.asher.oes.model.Pagination"%>
<%@page import="com.asher.oes.util.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%Pagination pagination = (Pagination)request.getAttribute(Constants.PAGINATION); %>
<div class="tab_sheet_title">
  <ul>
    <li class="li_number">&nbsp;</li>
    <li class="li_id" id="order" >
      ID
      <img alt="order" src="<%=PropertyUtil.getStaticSource() %>images/ICN_<%=pagination.getOrderBy().equals("ASC") ? "Increase" : "Decrese" %>_10x15.png" >
    </li>
    <li class="li_desc">Description</li>
    <li class="li_edit">Edit</li>
    <li class="li_opera" >
      <img name="check_box" id="check_box" class="check_box" alt="checked" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Unselected_15x15.png">
    </li>
  </ul>
</div>
<%
  int currentPage = pagination.getCurrentPage();
  int pageSize = pagination.getPageSize();
  int pageCount = pagination.getPageCount();
  List<Question> questionlist = new ArrayList<Question>();
  questionlist = (List<Question>)request.getAttribute(Constants.QUESTION_LIST);
  if (questionlist.size() != 0) {
      %>
          <div id="questionList">
      <%
      for (int i = 0 ; i < questionlist.size() ; i++) {
%>
          <div class="tab_sheet_body">
            <ul>
              <li class="li_number"><%=pageSize*(currentPage-1)+i+1 %></li>
              <li class="li_id"><%=FormatIdUtil.formatId("Q", questionlist.get(i).getId(), 6) %></li>
              <li class="li_desc">
                <a title="<%=questionlist.get(i).getDescription()%>" href="<%=questionlist.get(i).getId() %>">
                  <%=questionlist.get(i).getDescription() %>
                </a>
              </li>
              <li class="li_edit">
                <a href="<%=questionlist.get(i).getId() %>">
                  <img alt="edit" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Edit_15x15.png" />
                </a>
              </li>
              <li class="li_opera">
                <img name="opera_status" alt="checked" src="<%=PropertyUtil.getStaticSource() %>images/ICN_Unselected_15x15.png"  id="<%=questionlist.get(i).getId()%>" />
              </li>
            </ul>
          </div>
          <%
            }
          %>
          </div>
          <%
  } else {
          %>
          <div class="tab_sheet_body">
            <div class="no_date">There is no data ,please input the new word!!!</div>
          </div>
<%
  }
%>