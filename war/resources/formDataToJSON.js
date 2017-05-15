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