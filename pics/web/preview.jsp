<%@ page import="java.util.*" %>
<%@ page import="dk.japps.pics.*" %>
<%@ page import="dk.japps.pics.file.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>Jon Lind</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="etc/pics.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body style="">

	<%@include file="_navbar.jsp"%>

    <div class="container">
			
       	<div class="row">
        
			<div class="nav_buttons">
				<a class="btn btn-default" href="preview.jsp?filename=<%= new PictureFileUtil().getPrev(request.getParameter("filename")) %>&category=<%= request.getParameter("category") %>" role="button"><span class="glyphicon glyphicon-chevron-left"></span></a>
				<a class="btn btn-default" href="preview.jsp?filename=<%= new PictureFileUtil().getNext(request.getParameter("filename")) %>&category=<%= request.getParameter("category") %>" role="button"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
			
			<% if (Constants.SHOW_ORIGINALS) { %>
				<div class="preview_img"><a href="original.jsp?filename=<%= request.getParameter("filename") %>"><img class="img-responsive" title="click to view full size" border="0" src="./preview/<%= request.getParameter("filename") %>"></a></div>
			<% } else { %>
				<div class="preview_img"><a href="index.jsp?category=<%= request.getParameter("category") %>"><img class="img-responsive" title="click to close" border="0" src="./preview/<%= request.getParameter("filename") %>"></a></div>
			<% } %>
						
			<div class="exif_info">
			<div class="exif_item"><div class="exif_name">File name</div><div class="exif_value"><%= request.getParameter("filename") %></div></div>
			<% Map<String, String> result = new PictureFileUtil().getExifInfo(new PicsLogic().getOriginalPath(request.getParameter("filename"))); %>	
				<% for (String key : result.keySet()) { %>
				<div class="exif_item"><div class="exif_name"><%= key %></div><div class="exif_value"><%= result.get(key) %></div></div>			
				<% } %>		
			</div>

       	</div><!--/row-->

    </div><!--/.container-->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

	</body>
</html>
