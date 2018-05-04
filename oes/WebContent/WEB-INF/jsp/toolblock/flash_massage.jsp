<%@page import="com.asher.oes.Constants"%>
<%@page import="com.asher.oes.util.StringUtil"%>
<%
  String isDisplayMassage;
  String flashMassage = "";
  String flashmassage = (String)session.getAttribute(Constants.OPERATION);
  if (StringUtil.isNull(flashmassage)) {
      isDisplayMassage = "style='display: none;'";
  } else if (flashmassage.equals("success")) {
      isDisplayMassage = "style='display: block; background-color: #FE9901;'";
      flashMassage = "Operation Success";
  } else {
      isDisplayMassage = "style='display: block; background-color: #FF0000;'";
      flashMassage = "Operation Fail";
  }
%>
<div class="flash_massage" id="flashMassage" <%=isDisplayMassage %>><%=flashMassage %></div>
<%
  if (!flashMassage.equals("")) {
%>
<script type="text/javascript">
  setTimeout(function() {
    document.getElementById("flashMassage").style.display = "none";
    }, 3000);
</script>
<%
  }
  session.removeAttribute(Constants.OPERATION);
%>