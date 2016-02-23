console.log('¯\\_(ツ)_/¯');

const request = function(url) {
	const ajax = function (type, data) {
		return $.ajax({
			url: url,
			type: type,
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
		    processData: false,
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			beforeSend: function (xhr) {
				xhr.setRequestHeader("X-CSRF-TOKEN", $('[name=_csrf]').val());
			}
		});
	};
	
	return {
		POST: (data) => ajax("POST", data),
		GET: () => ajax('GET'),
		DELETE: (data) => ajax('DELETE', data),
	};
}


const handle = function() {
	const user = function () {
		// implement the user management here
		const inputs = ["name", "surname", "street", "streetnumber", "plz", "email", "password"];
		
		
	};
	
	return {
		userManagement: () => user(),
	};
};

$(document).ready(function () {
	handle().userManagement();
});
