<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
    $(document).ready(function css(){
    	$("#fs1").css("width","560px");
    	$("#fs2").css("width","560px");
    	$("#fs2").attr("disabled","disabled");
    	resetErrorMsg();
    });
    var oldemail;
    function showuser(){
    	$("#email").removeClass("error");
    	$("#status").html("");
        var email = document.getElementById("emailcheck").value;
        document.getElementById("myForm").reset();
	  $.ajax({
		  type: "GET",
		  url: "user/"+email,
		  success: function(returnedUserData){
			  if(returnedUserData===null){
				  $("#fs2").attr("disabled","disabled");
				  $("#error").html("No user exist with this Email");
				  $("#error").css("color","red");
				  $("#progressContainer").html("");
                  return false;
			  }
              else{
            	  $("#fs2").removeAttr("disabled");
                  oldemail = document.getElementById("emailcheck").value;
            	  $("#error").html("");
            	  showUserInForm(returnedUserData);
                  
              }
       },
	  dataType: "json"
	  });
	  return false;
    }

    function updateUser(){
    	 if(!validateForm())
			  return false;
        var formData = decodeURIComponent($("#myForm").serialize()); 
        var user = JSONString(formData);
        $.ajax({
			  type: "PUT",
			  url: "updateuser/"+oldemail,
			  data: user,
			  success: function(status){
				  if(status){
					  $("#fs2").attr("disabled","disabled");
					  fetched = false;
					  document.getElementById("myForm").reset();
					  document.getElementById("getEmail").reset();
					  $("#status").html("<h4>User details updated successfully</h4>");
					  $("#status").css("color","green");
					  $("#progressContainer").html("");
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
<!--    <b><span id="percent" class="percent"></span></b> -->
	<div id="progressContainer"></div>
   <br><br>
   <fieldset id="fs2">
<legend>Update</legend>
    <form action="success" id="myForm" method="post" onsubmit="return updateUser()">
			Name : <input type="text" name="name" id="name" required><span class="errormsg" id="nameerror"></span><br><br>
			EmailId : <input type="text" name="email" id="email" required><span class="errormsg" id="emailerror"></span><br><br>
			Phone : <input type="number" name="phone" id="phone"><span class="errormsg" id="phoneerror"></span><br><br>
			Company Name : <input type="text" name="companyName" id="companyName"><br><br>
			Address1 : <input type="text" name="address1" id="address1"><br><br>
			 Address2 : <input type="text" name="address2" id="address2"><br><br>
			 City : <input type="text" name="city" id="city"><br><br>
			 State : <input type="text" name="state" id="state"><br><br>
			 Country : <input type="text" name="country" id="country"><br><br>
			 Zipcode : <input type="number" name="zipcode" id="zipcode"><br><br>
  					<input type=submit id="submit" value="update">
  			
	</form>
	</fieldset>
	
	<span id="status"></span>
</body>
</html>