<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.error {
    color: #D8000C;
    background-color: #FFBABA;
    }
</style>
<script src="${pageContext.request.contextPath}/resources/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/formDataToJSON.js"></script>
    <script>
    $(document).ready(function css(){
    	$("#fs1").css("width","500px");
    	$("#fs2").css("width","500px");
    	$("#fs2").attr("disabled","disabled");
    	$("#email").focus(function () {
    		$("#email").removeClass("error");
    	});
    });
    var oldemail;
    function showuser(){
    	$("#email").removeClass("error");
    	$("#status").html("");
        var email = document.getElementById("emailcheck").value;
        document.getElementById("myForm").reset();
	  $.ajax({
		  type: "POST",
		  url: "search?email="+email,
		  success: function(user){
			  if(user===null){
				  $("#fs2").attr("disabled","disabled");
				  $("#error").html("No user exist with this Email");
				  $("#error").css("color","red");
                  return false;
			  }
              else{
            	  $("#fs2").removeAttr("disabled");
                  oldemail = document.getElementById("emailcheck").value;
            	  $("#error").html("");
                  document.getElementById("name").value = user.name;
                  document.getElementById("phone").value = user.phone;
                  document.getElementById("email").value = user.email;
                  if(user.company){
                  document.getElementById("companyName").value = user.company.companyName;
                  document.getElementById("companyAddress").value = user.company.companyAddress;
                  }
              }
       },
	  dataType: "json"
	  });
	  return false;
    }

    function updateUser(){
        var formData = decodeURIComponent($("#myForm").serialize()); 
        var user = JSONString(formData);
        $.ajax({
			  type: "POST",
			  url: "updateuser?email="+oldemail,
			  data: user,
			  success: function(status){
				  if(status){
					  $("#fs2").attr("disabled","disabled");
					  fetched = false;
					  document.getElementById("myForm").reset();
					  document.getElementById("getEmail").reset();
					  $("#status").html("<h4>User details updated successfully</h4>");
					  $("#status").css("color","green");
				  }
				  else
					  {
					 $("#email").addClass("error");
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
<fieldset id="fs1">
<legend>Email</legend>
     <form id="getEmail" action="success" method="post" onsubmit="return showuser()">
        <input type="email" id="emailcheck" required>
         <input type="submit" value="submit">
         <span id="error"></span>
    </form>
   </fieldset>
   <br><br>
   <fieldset id="fs2">
<legend>Update</legend>
    <form action="success" id="myForm" method="post" onsubmit="return updateUser()">
			Name : <input type="text" name="name" id="name" required><br><br>
			EmailId : <input type="email" name="email" id="email" required><br><br>
			Phone : <input type="number" name="phone" id="phone"><br><br>
			Company Name : <input type="text" name="companyName" id="companyName"><br><br>
			Company Address : <input type="text" name="companyAddress" id="companyAddress"><br><br>
  					<input type=submit value="update">
  			
	</form>
	</fieldset>
	<span id="status"></span>
</body>
</html>