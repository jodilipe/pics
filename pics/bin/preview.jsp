<%@ page import="java.util.*" %>
<%@ page import="dk.japps.pics.*" %>
<%@ page import="dk.japps.pics.file.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Preview</title>
<link rel="stylesheet" href="etc/pics.css" type="text/css" />
</head>
<body>

<%@include file="_menu.jsp"%>

<div class="picture_preview">
<a href="original.jsp?filename=<%= request.getParameter("filename") %>"><img border="0" src="/pics/preview/<%= request.getParameter("filename") %>"></a>

<div class="exif_info">
<% Map<String, String> result = new PictureFileUtil().getExifInfo(new PicsLogic().getOriginalPath(request.getParameter("filename"))); %>	
<% List<String> keys = new ArrayList<String>(); %>	
<% keys.addAll(result.keySet()); %>	
<% Collections.sort(keys);%>
<% for (String key : keys) { %>		 
<div class="exif_item"><%= key + ": " + result.get(key) %></div>			
<% } %>		
</div>

<div class="preview_filename"><%=request.getParameter("filename")%></div>

</div>
</body>
</html>