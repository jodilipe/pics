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

	<!-- include _navbar.jsp -->  

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
		
		<!-- include _navbar_toggle_button.jsp -->
		  
		<%@include file="_header.jsp"%>

          <div class="row">
          
			<% List<String> thumbs = new PicsLogic().getThumbnails(request.getParameter("category")); %>
			<% for (String thumb : thumbs) { %>
				<div align="center" valign="bottom" class="thumbnail col-md-4 col-sm-6 col-lg-3">
					<p><a href="preview.jsp?filename=<%= thumb %>"><img border="0" src="./thumb/<%= thumb %>" title="<%= thumb %>"></a></p>
					<p><a class="btn btn-default" href="preview.jsp?filename=<%= thumb %>" role="button">View details »</a></p>
				</div>
			<% } %>

          </div><!--/row-->
        </div><!--/span-->

	<%@include file="_menu.jsp"%>
        
      </div><!--/row-->

	<%@include file="_footer.jsp"%>

    </div><!--/.container-->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>
