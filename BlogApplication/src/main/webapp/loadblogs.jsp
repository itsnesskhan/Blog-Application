<%@page import="com.blog.dao.LikedDao"%>
<%@page import="com.blog.entities.Blogs"%>
<%@page import="java.util.List"%>
<%@page import="com.blog.entities.Categories"%>
<%@page import="com.blog.helper.ConnectionProvider"%>
<%@page import="com.blog.dao.PostDao"%>

<div class="row">
<%
Thread.sleep(1000);
LikedDao doe = new LikedDao(ConnectionProvider.getConnect());
PostDao dao = new PostDao(ConnectionProvider.getConnect());
 List<Blogs> list =  null;
 int catId= Integer.parseInt(request.getParameter("cid"));
 if(catId==0){
	 list = dao.getAllBlogs();
 }
 else if(catId>0){
	 list = dao.getBlogsByCatId(catId);
	 System.out.println(list);
 }
 if(list.size()==0){
	 out.println("<h1 class='text-center dislay-3'>No blogs in this category</h1>");
 }
 
 for(Blogs c:list){%>
	<div class="col-md-6 mb-3">
	<div class="card">
  <img src="postImages/<%=c.getpImage() %>" class="card-img-top" alt="Image not found" height="500px">
  <div class="card-body">
    <h5 class="card-title"><%=c.getTitle() %></h5>
    <p class="card-text"><%=c.getContent() %></p>
    <p class="card-text"><%=c.getCatId() %></p>
    <div class="card-footer bg-light">
    	    <a href="blog-details.jsp?post=<%=c.getId() %>" class="btn btn-outline-primary">Read More</a>
    	    <a href="#!" onclick="likePost(<%=c.getId() %>,<%=c.getUserId() %>)"  class="btn btn-outline-primary"><i class="fas fa-thumbs-up"></i><span class="like-counter"><%=doe.countLikesOnPost(c.getId()) %></span></a>
    	    <a href="#!" class="btn btn-outline-primary"><i class="fas fa-comment"></i><span>14</span></a>
    		<!-- that stop defualt behaviour of #! ancor -->
    </div>
  </div>
</div>
	
	
	</div> <%
 }


%>
</div>