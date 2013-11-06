<%@ page import="java.util.*" %>
<%@ page import="dk.japps.pics.*" %>
<%@ page import="dk.japps.pics.file.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Original</title>
<link rel="stylesheet" href="etc/pics.css" type="text/css" />
</head>
<body>

<%@include file="_menu.jsp"%>

<div class="picture_original">
<a href="javascript: history.go(-1)"><img border="0" src="<%= new PicsLogic().getOriginalRelativePath(request.getParameter("filename")) %>"></a>

<!-- <img border="0" src="<%= new PicsLogic().getOriginalRelativePath(request.getParameter("filename")) %>"> -->
</div>
</body>
</html>