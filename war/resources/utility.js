function JSONString(formData) {
    var data = formData.split("&");
		    var user={};
            var company_obj = {};
            var address={};
            company_obj["companyAddress"]=address;
            user["company"] = company_obj;
		    for(var index in data)
		    {
		    	var key=data[index].split("=")[0];
		    	var value = data[index].split("=")[1];
		    	if(index==3){ 
		    		if(value!=="")
		    			company_obj[key]=value;
		    		continue;
		    	}
		    	else if(index>3){ 
		    		if(value!=="")
		    			address[key]=value;
		    		continue;
                }
		        user[key] = value;
		    }
		    return JSON.stringify(user);
}
function isValidPhone(){
	$("#phoneerror").html("");
	$("#phone").removeClass("error");
	var num = $("#phone").val();
	if(num.toString().length!=10 && num.toString().length!=0){
		$("#phone").addClass("error");
		$("#phoneerror").html("Phone number must be of length 10 or null");
		return false;
	}
	return true;
}
function isValidEmail() {
	var email = $("#email").val();
	$("#emailerror").html("");
	$("#email").removeClass("error");
	if(email.charAt(0)===" "){
		$("#email").addClass("error");
		$("#emailerror").html("Email cannot start with a space");
		return false;
	}
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var isValid = re.test(email);
    if(isValid)
    	return true;
    $("#email").addClass("error");
	$("#emailerror").html("Enter a proper EmailID"); 
	return false;
}

function isValidName(){
	var name = $("#name").val().toLowerCase();
	$("#nameerror").html("");
	$("#name").removeClass("error");
	if(name.charAt(0)===" "){
		$("#name").addClass("error");
		$("#nameerror").html("Name cannot start with a space");
		return false;
	}
	for(var index in name){
			var ch=name.charAt(index);
		if((ch >= 'a' && ch<='z') || ch==="-" || ch===" ")
			continue;
		$("#name").addClass("error");
		$("#nameerror").html("Name cannot contain special chararcters or numbers");
		return false;
	}	
	
	return true;
}

function validateForm(){
	  var isPhoneValid = isValidPhone();
	  var isEmailValid = isValidEmail();
	  var isNameValid = isValidName();
	  if(!isPhoneValid || !isEmailValid || !isNameValid){
		  $("#status").html("");
		  return false;
	  }
	  return true;
}

function showUserInForm(userDetails){
	var user = userDetails.user;
	var percentage = userDetails.percent;
	document.getElementById("name").value = user.name;
    document.getElementById("phone").value = user.phone;
    document.getElementById("email").value = user.email;
    if(user.company){
    document.getElementById("companyName").value = user.company.companyName;
    if(user.company.companyAddress){
    	$("#address1").val(user.company.companyAddress.address1);
    	$("#address2").val(user.company.companyAddress.address2);
    	$("#city").val(user.company.companyAddress.city);
    	$("#state").val(user.company.companyAddress.state);
    	$("#country").val(user.company.companyAddress.country);
    	$("#zipcode").val(user.company.companyAddress.zipcode);
    }
    	
    }
    showProgress(percentage);
}

function displayUserDetails(userDetails){
	var user = userDetails.user;
	var company = user.company;
	var address = company?company.companyAddress : null;
	var percentage=userDetails.percent;
	
	var result = "<fieldset>";
	result += "EmailID: " + user.email + "<br><br>";
	result += " Name: " + user.name + "<br><br>";
	result += " Phone: " + user.phone + "<br><br>";
	result += "Company Name: " + (company && company.companyName ? ""+company.companyName : "") + "<br><br>";
	result += "Address 1:" + (address && address.address1? ""+address.address1 : "" )+ "<br><br>";
	result += "Address 2:" + (address && address.address2? ""+address.address2 : "" )+ "<br><br>";
	result += "City:" + (address && address.city? ""+address.city : "" )+ "<br><br>";
	result += "State:" + (address && address.state? ""+address.state : "") + "<br><br>";
	result += "Country:" + (address && address.country? ""+address.country : "") + "<br><br>";
	result += "Zipcode:" + (address && address.zipcode? ""+address.zipcode : "") + "<br><br>";
	result += "</fieldset>";
	$("#result").css("width","500px");
	$("#result").html(result);
	showProgress(percentage);
	
}

function resetErrorMsg(){
	$("#email").focus(function () {
		$("#email").removeClass("error");
		$("#emailerror").html("");
	});
	$("#phone").focus(function () {
		$("#phone").removeClass("error");
		$("#phoneerror").html("");
	});
	$("#name").focus(function () {
		$("#name").removeClass("error");
		$("#nameerror").html("");
	});
	
}

function showProgress(percentage){
	var addProgress = "<div class=\"w3-light-grey w3-round\" style=\"width:560px\"><div id=\"progressBar\"></div></div>";
	$("#progressContainer").html(addProgress);
	if (percentage < 50) {
		$("#progressBar").addClass("w3-container w3-red w3-center w3-round");
		} else if (percentage > 49 && percentage < 100) {
		$("#progressBar").addClass("w3-container w3-orange w3-center w3-round");
		} else {
		$("#progressBar").addClass("w3-container w3-green w3-center w3-round");
		}
	var width = percentage;
	var id = setInterval(move, 10);
	var count = 0;

	function move() {

	if (count >= width) {
	clearInterval(id);
	} else {
	count++;
	$("#progressBar").css("width", count + "%");
	$("#progressBar").html(count + "%");
	}
	}
}