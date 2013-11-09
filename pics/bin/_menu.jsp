        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="list-group">
          
  			<% List<Folder> folders = new PicsLogic().getFolders(); %>
			<% for (Folder folder : folders) { %>
			<%   if (folder.getName().equals(request.getParameter("category"))) { %>
            <a href="index.jsp?category=<%= folder.getName() %>" class="list-group-item active"><%= folder.getName() %></a>
			<%   } else { %>
            <a href="index.jsp?category=<%= folder.getName() %>" class="list-group-item"><%= folder.getName() %></a>
			<%   } %>
			<% } %>
			
          </div>
        </div><!--/span-->
