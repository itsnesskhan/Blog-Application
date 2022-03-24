<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.blog.helper.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.blog.dao.PostDao"%>
<%@page import="com.blog.entities.User"%>
<%@page import ="com.blog.entities.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>profile</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link href ="css/mystyles.css" rel="stylesheet" type="">
</head>
<body>
<%@page errorPage='error_page.jsp' %>
<% 
User user = (User)session.getAttribute("currentUser");
if(user== null)
{
	response.sendRedirect("login.jsp");
}
%>

<!-- navbar -->
<nav class="navbar navbar-expand-lg navbar-dark my-back">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><span class="fas fa-blog fa-lg"></span>Blog</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">

        <li class="nav-item">
          <a class="nav-link" href="#"><span class="fas fa-info-circle fa-sm "></span>About</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="profile.jsp"><span class="fas fa-list-alt  fa-sm "></span>Categories</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" data-bs-toggle="modal" data-bs-target="#blog-post"  href="profile.jsp"><span class="fas fa-blog fa-sm fa-fw"></span>Add Blog</a>
        </li>

      </ul>
      
      
    </div>
    <ul class="navbar-nav mr-right">
      	<li class="nav-item ">
          <a class="nav-link " data-bs-toggle="modal" data-bs-target="#profile-details"><span class="fas fa-user-circle fa-sm fa-fw"></span><%=user.getName() %></a>
        </li>
      </ul>
      <ul class="navbar-nav ">
      	<li class="nav-item ">
          <a class="nav-link " href="LogoutServlet"><span class="fas fa-sign-out fa-sm  fa-fw"></span>Logout</a>
        </li>
      </ul>
  </div>
</nav>
<!-- end -->
<%
				Message message = (Message)session.getAttribute("msg");
				if(message!=null){%>
				<div class="alert <%=message.getCssClass() %> my-2" role="alert" >
 				 <%= message.getContent() %>
				</div>
				<%
				session.removeAttribute("msg");
				}
%>

<!-- call categories -->
<!-- this is body -->
<div class="container">
	<div class="row my-4">
	<div class ="col-md-4">
		<div class="list-group">
  <a href="#" onclick="getPost(0, this)" class="c-link list-group-item list-group-item-action active" aria-current="true">
    All Blogs</a>
    <%
    	PostDao pdao = new PostDao(ConnectionProvider.getConnect());
		ArrayList<Categories> lists = pdao.getAllCategories();
		for(Categories cat:lists){%>
			
 			 <a href="#" onclick="getPost(<%=cat.getId()%>, this)" class="c-link list-group-item list-group-item-action"><%=cat.getName() %></a>
	<% 	}
    %>
  
</div>
	</div>
	<div class="col-md-8">
	<div class="blog-container" id="blog-data">
	
	</div>
	<div class="text-center" id="rloder">  <i class="fas fa-sync fa-spin fa-5x"></i>
	<h4 class="text-center mt-2">Loading...</h4>
	</div>
	</div>
	</div>

</div>

<!-- modal -->


<!-- user-details -->
<!-- Modal -->
<div class="modal fade" id="profile-details" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title " id="exampleModalLabel">User Information</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<div class="text-center">
      	<img src="pics/<%=user.getProfile()%>" class="img-fluid" style="border-radius: 50%;max-width: 180px;">
      	</div>
      	<h4 class="text-center mt-2"><%=user.getName() %></h4>
      	<div id="user-details">
      		<table class="table">
      	<tr>
      		<th scope="row">ID</th>
      		<td><%=user.getId() %></td>
    	</tr>
    	<tr>
      		<th scope="row">Name</th>
      		<td><%=user.getName() %></td>
    	</tr>
    	<tr>
      		<th scope="row">Email</th>
      		<td><%=user.getEmail() %></td>
      
    	</tr>
    	<tr>
      		<th scope="row">Password</th>
      		<td><%=user.getPassword() %></td>
    	</tr>
    	<tr>
      		<th scope="row">Gender</th>
      		<td><%=user.getGender() %></td>
    	</tr>
    	<tr>
      		<th scope="row">Date of join</th>
      		<td><%=user.getDateTime().toString() %></td>
    	</tr>
		</table>
      	</div>
      	
      	<!-- user-edti -->
      	<div id="user-edit" style="display: none;">
      	<form action="EditServlet" method="post" enctype="multipart/form-data">
      		<table class="table">
      	<tr>
      		<th scope="row">ID</th>
      		<td><b><%=user.getId() %></b></td>
    	</tr>
    	<tr>
      		<th scope="row">Name</th>
      		<td><input type="text" class="form-control" value="<%=user.getName()%>" name="user-name"></td>
    	</tr>
    	<tr>
      		<th scope="row">Email</th>
      		<td><input type="text" class="form-control" value="<%=user.getEmail()%>" name="user-email"></td>
      
    	</tr>
    	<tr>
      		<th scope="row">Password</th>
      		<td><input type="password" class="form-control" value="<%=user.getPassword()%>" name="user-password"></td>
    	</tr>
    	<tr>
      		<th scope="row">Gender</th>
      		<td><b><%=user.getGender().toUpperCase() %></b></td>
    	</tr>
    	<tr>
      		<th scope="row">Profile</th>
      		<td><input type="file" class="form-control" value="<%=user.getProfile()%>" name="user-profile"></td>
    	</tr>
    	<tr>
      		<th scope="row">Date of join</th>
      		<td><%=user.getDateTime().toString() %></td>
    	</tr>
    	<tr>
    	<td colspan="2" class="text-center"><input type="submit" class="btn  btn-outline-primary " id="save-profile-btn" value="Save"></td>
    	<tr>
		</table>
      	</form>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id=edit-profile-btn>Edit</button>
      </div>
    </div>
  </div>
</div>
<!-- end of modal -->


<!-- addblog -->

<!-- Modal -->
<div class="modal fade" id="blog-post" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Add Blog</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <form action="AddBlogServlet" method="post" enctype="multipart/form-data" id="add-blog">
      		<table class="table">
    	<tr>
      		<th scope="row">Title</th>
      		<td><input type="text" class="form-control" value="" name="blog-title"></td>
    	</tr>
    	<tr>
      		<th scope="row">Content</th>
      		<td><textarea type="text" class="form-control" value="" name="blog-content" rows="7"></textarea></td>
      
    	</tr>
    	<tr>
    		<th scope="row">Category</th>
      		<td><select class="form-control" name="catId">
      		<option selected disabled>---Select Category</option>
    		<%
    		PostDao dao = new PostDao(ConnectionProvider.getConnect());
    		ArrayList<Categories> list = dao.getAllCategories();
    		for(Categories c:list){%>
    		   	<option value="<%=c.getId()%>"><%=c.getName() %></option>
    		<%   			
    		}  		
    		%>
    		
      		</select></td>
    	</tr>
    	<tr>
      		<th scope="row">Image</th>
      		<td><input type="file" class="form-control" value="" name="blog-image" ></td>
      
    	</tr>
    	<tr>
    	<td colspan="2" class="text-center"><input type="submit" class="btn  btn-outline-primary " value="POST"></td>
    	<tr>
		</table>
      	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<!-- endblog -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="  crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="JS/myscript.js"></script>
</body>
</html>