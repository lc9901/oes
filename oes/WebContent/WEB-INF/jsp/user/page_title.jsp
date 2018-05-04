<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="title" id="main">
        <div class="title_logo"></div>
        <span class="title_oes">Online Exam Web System</span>
        <div class="title_right">
          <div class="title_right_ri">
            <a class="language">中文</a>
            <a class="logout" id="logout" >Logout</a>
          </div>
          <img alt="" src="${STATIC_SOURCE_ROOT }images/ICN_Web_PersonalInformation_20x20.png" />
          <span>${USER.getUserName()}</span>
        </div>
      </div>