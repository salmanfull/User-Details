<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<script src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/formDataToJSON.js"></script>
<script>

	  function addUser(){
		  $("#status").html("<h4>Wait...Storing user details</h4>");
		  $("#status").css("color","orange");
			var formData = decodeURIComponent($("#myForm").serialize()); 
			var user = JSONString(formData);
		  $.ajax({
			  type: "POST",
			  url: "user",
			  data: user,
			  success: function(status){
				  if(status){
					  $("#status").html("<h4>User details added successfully</h4>");
					  $("#status").css("color","green");
				  }
				  else
					  {
					  $("#status").html("<h4>User with the same EmailId already exists</h4>");
					  $("#status").css("color","red");
					  }
				  
			  },
			  dataType: "json",
			  headers: { 
				  'Accept': 'application/json',
				  'Content-Type': 'application/json' 
			  }
			});
		  return false;
		     }  

</script>
</head>
<body>
	<h1>Add User Details</h1>
	
	<form action="success" id="myForm" method="post" onsubmit="return addUser()">
			Name : <input type="text" name="name" required><br><br>
			EmailId : <input type="email" name="email" required><br><br>
			Phone : <input type="number" name="phone"><br><br>
			Company Name : <input type="text" name="companyName"><br><br>
			Company Address : <input type="text" name="companyAddress"><br><br>
  					<input type=submit value="submit">
  			
	</form>
	<br>
	<span id="status"></span><br><br>
	<a href="searchuser">Search User</a>
	
</body>
</html>