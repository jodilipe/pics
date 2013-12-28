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
			
		<%@include file="_category_img.jsp"%>

       	<div class="row">
        
		<% List<String> thumbs = new PicsLogic().getThumbnails(request.getParameter("category")); %>
		<% if (!thumbs.isEmpty()) { %>
			<% for (String thumb : thumbs) { %>
				<div class="thumbnail col-xs-6 col-sm-6 col-md-4 col-lg-3">
					<p><a href="preview.jsp?filename=<%= thumb %>&category=<%= request.getParameter("category") %>"><img border="0" src="./thumb/<%= thumb %>" title="<%= thumb %>"></a></p>
					<p><span class="img_description"><%= thumb %></span></p>
				</div>
			<% } %>
		<% } else { %>
			<div class="content col-sm-12 col-lg-12 col-xl-12">
				<h1>Pictures by Jon Lind</h1>
			</div><!--/span-->  
		<% } %>

       	</div><!--/row-->

    </div><!--/.container-->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

	</body>
</html>
