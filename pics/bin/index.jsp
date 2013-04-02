<%@ page import="java.util.*" %>
<%@ page import="dk.japps.pics.*" %>
<%@ page import="dk.japps.pics.file.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Jon Lind Pictures</title>
<link rel="stylesheet" href="etc/pics.css" type="text/css" />
</head>
<body>

<div class="menu">
	<% List<Folder> folders = new PicsLogic().getFolders(); %>
	<% for (Folder folder : folders) { %>
	<%   if (folder.getName().equals(request.getParameter("category"))) { %>
	<div class="selected_menu_item"><%= folder.getName() %></div>
	<%   } else { %>
	<div class="menu_item"><a href="index.jsp?category=<%= folder.getName() %>"><%= folder.getName() %></a></div>
	<%   } %>
	<% } %>
</div>

<div class="thumbnails">
	<% List<String> thumbs = new PicsLogic().getThumbnails(request.getParameter("category")); %>
	<% for (String thumb : thumbs) { %>
	<div class="thumbnail"><a href="preview.jsp?filename=<%= thumb %>"><img border="0" src="/pics/thumb/<%= thumb %>"></a></div>
	<% } %>
</div>

</body>
</html>