function JSONString(formData) {
    var data = formData.split("&");
		    var obj={};
            var company_obj = {};
            obj["company"] = company_obj;
		    for(var key in data)
		    {
                if(key>2){ 
                    compkey = data[key].split("=")[0];
                    value = data[key].split("=")[1];
                    company_obj[compkey] = value;
                    continue;
                }
		        obj[data[key].split("=")[0]] = data[key].split("=")[1];
		    }
		    return JSON.stringify(obj);
}
function checkPhone(){
	$("#phoneerror").html("");
	//$("#submit").removeAttr("disabled");
	$("#phone").removeClass("error");
	var num = $("#phone").val();
	if(num.toString().length!=10 && num.toString().length!=0){
		$("#phone").addClass("error");
		$("#phoneerror").html("Phone number must be of length 10 or null");
		//$("#submit").attr("disabled","disabled");
		return false;
	}
	return true;
}