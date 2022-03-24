<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page  isErrorPage= 'true' %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link href ="css/mystyles.css" rel="stylesheet" type="">
</head>
<body>
<div class="container " style="text-align:center;">
<img src ="img/error.png" class="img-fluid my-4" height="400" width="400" >
<h3 class= "display-3">Sorry ! Something went wrong...</h3>
<h3><%= exception %></h3>
    <a class="btn btn-outline-primary mb-2" href="login.jsp" role="button"><span class="fas fa-home  me-2"></span>Home</a>
</div>
</body>
</html>