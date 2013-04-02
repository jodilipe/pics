<%@ page import="java.util.*" %>
<%@ page import="dk.japps.pics.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Original</title>
<link rel="stylesheet" href="etc/pics.css" type="text/css" />
</head>
<body>
<div class="picture_original">
<img border="0" src="<%= new PicsLogic().getOriginalRelativePath(request.getParameter("filename")) %>">
</div>
</body>
</html>