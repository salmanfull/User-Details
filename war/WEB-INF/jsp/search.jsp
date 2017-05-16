<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script>
	function print(user){
		var result = "<fieldset>";
		result += "EmailID: " + user.email + "<br><br>";
		result += " Name: " + user.name + "<br><br>";
		result += " Phone: " + user.phone + "<br><br>";
		result += "Company Name: ";
		if(user.company){ 
			result += user.company.companyName + "<br><br>";
			result += "Company Address: " + user.company.companyAddress + "<br>";
		  }
		else{
			result += "<br><br>Company Address:<br>"; 
		  }
		result += "</fieldset>";
		$("#result").css("width","500px");
		$("#result").html(result);
	}
  function search(){
	  $("#result").html("<h4>Wait...Fetching user details</h4>");
	  $("#result").css("color","orange");
	  var email = document.getElementById("email").value;
	  $.ajax({
		  type: "GET",
		  url: "user/"+email,
		  success: function(user){
			  if(user===null){
				  $("#result").html("<h4>No user exist with this Email</h4>");
				  $("#result").css("color","red");
			  }
			  else
				  {
				  $("#result").css("color","black");
					print(user);
				  }
			  
		  },
		  dataType: "json"
	  });
	  return false;
  }
</script>
</head>
<body>
<h1>Search User</h1>
<form action="search" method="post" onsubmit="return search()">
			Email : <input type="email" name="email" id="email" required><br><br>
			
  					<input type=submit value="search">
  			
</form>
<br>
<form id="result"></form>
</body>
</html>