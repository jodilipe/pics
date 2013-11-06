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
