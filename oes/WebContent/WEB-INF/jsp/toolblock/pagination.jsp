<%@page import="com.asher.oes.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.asher.oes.model.Pagination"%>
<%@page import="com.asher.oes.util.StringUtil"%>
<%@page import="com.asher.oes.util.PropertyUtil"%>
<%Pagination pagination = (Pagination)request.getAttribute(Constants.PAGINATION); 
  int currentPage = pagination.getCurrentPage();
  int pageSize = pagination.getPageSize();
  int pageCount = pagination.getPageCount();
%>
<div class="tab_sheet_foot">
  <input type="hidden" name="orderBy" id="orderBy" value="${PAGINATION.getOrderBy() }"/>
  <input type="hidden" id="deleteList" name="deleteList" value=""/>
  <input type="hidden" value="${PAGINATION.getCurrentPage() }" id="current_page" />
  <input type="hidden" value="<%=PropertyUtil.getStaticSource()%>" id="root"/>
  <input type="hidden" value="${PAGINATION.getPageCount()}" id="pageCount"/>
  <input type="button" value="Delete" class="delete" id="deleteOpera"/>
  <div class="pages">
   <a href=<%=(currentPage < 2?"javascript:return false;":"list?currentPage=" + (currentPage - 1) + "&pageSize=" + pageSize + "&orderBy=" + pagination.getOrderBy()) + "&search=" + pagination.getSearch()%>><img alt="before" src="${STATIC_SOURCE_ROOT }images/BTN_PageLeft_20x15.png" /></a>

     <!-- show page start -->
     <% if (pageCount <= 5) {
            for (int j = 1; j <= pageCount; j++) {
     %>
       <a class ="<%=currentPage == j?"current_page":"" %>" href="list?currentPage=<%=j %>&pageSize=<%=pageSize %>&orderBy=<%=pagination.getOrderBy() %>&search=<%=pagination.getSearch()%>"><%=j %></a>
     <% } } %>
     <% if (pageCount > 5) {
         
            if ((pageCount - currentPage) <= 4) {
                for (int k = 4; k >= 0; k--) {
     %>
       <a class ="<%=currentPage == (pageCount - k)?"current_page":"" %>" href="list?currentPage=<%=pageCount - k %>&pageSize=<%=pageSize %>&orderBy=<%=pagination.getOrderBy()%>&search=<%=pagination.getSearch()%>"><%=(pageCount - k) %></a>
     <% } } else {
                for (int j = 0; j < 3; j++) {
     %>
       <a class ="<%=currentPage == (currentPage + j)?"current_page":"" %>" href="list?currentPage=<%=currentPage + j %>&pageSize=<%=pageSize %>&orderBy=<%=pagination.getOrderBy()%>&search=<%=pagination.getSearch()%>"><%=(currentPage + j) %></a>
     <% } %>
       <a >...</a>
       <a class ="<%=currentPage == pagination.getPageCount()?"current_page":"" %>" href="list?currentPage=<%=pagination.getPageCount() %>&pageSize=<%=pageSize %>&orderBy=<%=pagination.getOrderBy()%>&search=<%=pagination.getSearch()%>"><%=pagination.getPageCount() %></a>
     <% } } %>
     <!-- show page end -->
   <a href=<%=currentPage > (pageCount - 1) ? "javascript:return false;" : ("list?currentPage=" + (currentPage + 1) + "&pageSize=" + pageSize + "&orderBy=" + pagination.getOrderBy())  + "&search=" + pagination.getSearch()%>>
     <img alt="next" src="${STATIC_SOURCE_ROOT }images/BTN_PageRight_20x15.png" />
   </a>
   <select class="page_select" onchange="refresh()" id="page_select">
     <%
       for (int i = 5; i < 11; i++) {
     %>
        <option value="<%=i %>" <%=(i == pageSize?"selected = 'selected'":"") %> ><%=i %></option>
     <%
       }
     %>
   </select>
   <span>Per page</span>
   <input type="text" class="page_size" id="tag_page"/>
   <input type="button" class="go" value="Go" id="goTarget" />
  </div>
</div>