<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.w3-light-grey,.w3-hover-light-grey:hover,.w3-light-gray,.w3-hover-light-gray:hover{color:#000!important;background-color:#f1f1f1!important}
	.w3-container,.w3-panel{padding:0.01em 16px}
	.w3-container:after,.w3-container:before{content:"";display:table;clear:both}
	.w3-red,.w3-hover-red:hover{color:#fff!important;background-color:#f44336!important}
	.w3-orange,.w3-hover-orange:hover{color:#000!important;background-color:#ff9800!important}
	.w3-green,.w3-hover-green:hover{color:#fff!important;background-color:#4CAF50!important}
	.w3-center{display:inline-block;width:auto;text-align:center;text-align:center!important}
	.w3-round-xlarge{border-radius:16px}
	.w3-round,.w3-round-medium{border-radius:4px}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/utility.js"></script>
<script>
  function search(){
	  $("#result").html("<h4>Wait...Fetching user details</h4>");
	  $("#result").css("color","orange");
	  var email = document.getElementById("email").value;
	  $.ajax({
		  type: "GET",
		  url: "user/"+email,
		  success: function(userDetails){
			  if(userDetails===null){
				  $("#result").html("<h4>No user exist with this Email</h4>");
				  $("#result").css("color","red");
				  $("#progressContainer").html("");
			  }
			  else
				  {
				  $("#result").css("color","black");
				  displayUserDetails(userDetails);
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
<br><br>
<!-- <b><span id="percent" class="percent"></span></b> -->
<div id="progressContainer"></div><br><br>
<form id="result"></form><br><br>

</body>
</html>