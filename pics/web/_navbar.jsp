    <div class="navbar navbar-static-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp"><img class="site_brand img-responsive" src="img/jon_lind_brand_light_xx_small.png"></a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">

  			<% List<Folder> folders = new PicsLogic().getFolders(); %>
			<% for (Folder folder : folders) { %>
			<%   if (folder.getName().equals(request.getParameter("category"))) { %>
            <li class="active"><a href="index.jsp?category=<%= folder.getName() %>"><%= folder.getName() %></a></li>
			<%   } else { %>
            <li><a href="index.jsp?category=<%= folder.getName() %>"><%= folder.getName() %></a></li>
			<%   } %>
			<% } %>

          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </div><!-- /.navbar -->

    