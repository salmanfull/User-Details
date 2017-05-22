<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<style type="text/css">
	.error {
    color: #D8000C;
    background-color: #FFBABA;
    }
    span.errormsg {
    color: red;
    }
    
</style>
<script src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/utility.js"></script>
<script>

	$(document).ready(resetErrorMsg);
	  function addUser(){
		  if(!validateForm())
			  return false;
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
			Name : <input type="text" name="name" id="name" required><span class="errormsg" id="nameerror"></span><br><br>
			EmailId : <input type="text" name="email" id="email" required><span class="errormsg" id="emailerror"></span><br><br>
			Phone : <input type="number" name="phone" id="phone"><span class="errormsg" id="phoneerror"></span><br><br>
			Company Name : <input type="text" name="companyName"><br><br>
			 Address1 : <input type="text" name="address1"><br><br>
			 Address2 : <input type="text" name="address2"><br><br>
			 City : <input type="text" name="city"><br><br>
			 State : <input type="text" name="state"><br><br>
			 Country : <input type="text" name="country"><br><br>
			 Zipcode : <input type="text" name="zipcode"><br><br>
  					<input type=submit id="submit" value="submit">
  			
	</form>
	<br>
	<span id="status"></span><br><br>
	<a href="searchuser">Search User</a>
	
</body>
</html>